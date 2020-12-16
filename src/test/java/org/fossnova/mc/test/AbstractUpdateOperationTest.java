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
package org.fossnova.mc.test;

import org.fossnova.mc.*;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
class AbstractUpdateOperationTest {
    protected UpdateOperation updateOp;
    private Container container;
    private final Object lock = new Object();
    private volatile boolean down;

    @Before
    public final void init() {
        down = false;
        container = ContainerFactory.newContainer().install();
        updateOp = getUpdateOperation();
    }

    private UpdateOperation getUpdateOperation() {
        if (container.newUpdateOperation(new Listener<UpdateOperation>() {
            @Override
            public void onComplete(UpdateOperation op) {
                synchronized (lock) {
                    updateOp = op;
                    notify();
                }
            }
        })) {
            synchronized (lock) {
                while (updateOp == null) {
                    try {
                        lock.wait();
                    } catch (InterruptedException ignored) {
                    }
                }
            }
            return updateOp;
        }
        return null;
    }

    @After
    public final void destroy() {
        // prepare
        CompletionListener<UpdateOperation> prepareListener = new CompletionListener<>();
        container.prepare(updateOp, prepareListener);
        prepareListener.awaitCompletionUninterruptibly();
        // commit
        CompletionListener<UpdateOperation> commitListener = new CompletionListener<>();
        container.commit(updateOp, commitListener);
        commitListener.awaitCompletionUninterruptibly();
        // shutdown
        container.shutdown(new Listener<Container>() {
            @Override
            public void onComplete(Container result) {
                down = true;
                synchronized (lock) {
                    lock.notify();
                }
            }
        });
        synchronized (lock) {
            while (!down) {
                try { lock.wait(); } catch (InterruptedException ignored) {}
            }
        }
    }
}
