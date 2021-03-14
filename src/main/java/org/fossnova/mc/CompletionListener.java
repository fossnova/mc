/*
 * Copyright (c) 2012-2019, FOSS Nova Software Foundation (FNSF),
 * and individual contributors as indicated by the @author tags.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
* 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.fossnova.mc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Operation completion utility listener that allows synchronized wait for operation phase to be completed.
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 *
 * @param <R> operation result type.
 */
public final class CompletionListener<R> {

    private final CountDownLatch latch = new CountDownLatch(1);

    private volatile R result;

    /**
     * {@inheritDoc}
     */
    public void onComplete(final R result) {
        this.result = result;
        latch.countDown();
    }

    /**
     * Awaits operation phase result interruptibly.
     * @return operation phase result
     * @throws InterruptedException if waiting thread have been interrupted
     */
    public R awaitCompletion() throws InterruptedException {
        if (result != null) return result;
        latch.await();
        return result;
    }

    /**
     * Awaits operation phase result interruptibly in the specified time limit.
     * @param timeout the maximum time to wait
     * @param unit the time unit of the {@code timeout} argument
     * @return operation phase result
     * @throws InterruptedException if waiting thread have been interrupted
     * @throws TimeoutException if timed out
     */
    public R awaitCompletion(final long timeout, final TimeUnit unit) throws InterruptedException, TimeoutException {
        if (result != null) return result;
        if (!latch.await(timeout, unit)) {
            throw new TimeoutException();
        }
        return result;
    }

    /**
     * Awaits operation phase result uninterruptibly.
     * Thread interruption doesn't cause this method to return immediately.
     * Instead the interruption attempts are tracked and later propagated to the current thread prior method return.
     * @return operation phase result
     */
    public R awaitCompletionUninterruptibly() {
        if (result != null) return result;
        boolean intr = false;
        try {
            while (true) {
                try {
                    latch.await();
                    break;
                } catch (InterruptedException e) {
                    intr = true;
                }
            }
            return result;
        } finally {
            if (intr) Thread.currentThread().interrupt();
        }
    }

    /**
     * Awaits operation phase result uninterruptibly in the specified time limit.
     * Thread interruption doesn't cause this method to return immediately.
     * Instead the interruption attempts are tracked and later propagated to the current thread prior method return.
     * @param timeout the maximum time to wait
     * @param unit the time unit of the {@code timeout} argument
     * @return operation phase result
     * @throws TimeoutException if timed out
     */
    public R awaitCompletionUninterruptibly(final long timeout, final TimeUnit unit) throws TimeoutException {
        if (result != null) return result;
        boolean intr = false;
        try {
            long now = System.nanoTime();
            long remaining = unit.toNanos(timeout);
            while (true) {
                if (remaining <= 0L) {
                    throw new TimeoutException();
                }
                try {
                    if (latch.await(remaining, TimeUnit.NANOSECONDS)) {
                        break;
                    }
                } catch (InterruptedException e) {
                    intr = true;
                } finally {
                    remaining -= (-now + (now = System.nanoTime()));
                }
            }
            return result;
        } finally {
            if (intr) Thread.currentThread().interrupt();
        }
    }
}
