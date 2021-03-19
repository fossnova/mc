/*
 * Copyright (c) 2012-2021, FOSS Nova Software Foundation (FNSF),
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

import java.util.concurrent.TimeUnit;

/**
 * Container controller is providing methods for getting service container, shutting it down or inspecting its configuration.
 * <p>
 * <B>Thread Safety:</B>
 * Instances of this interface are thread safe.
 * </p>
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public interface ContainerController {
    /**
     * Gets container associated with this controller.
     * @return container instance
     */
    Container getContainer();
    /**
     * Gets container name.
     * @return container name
     */
    String getName();
    /**
     * Whether associated service container will automatically shut down on VM exit.
     * @return <code>true</code> if associated service container will automatically shut down on VM exit, <code>false</code> otherwise
     */
    boolean isAutoShutdown();
    /**
     * Returns the core number of container threads.
     * @return the core number of container threads
     */
    int getCorePoolSize();
    /**
     * Returns the time limit for which container threads may remain idle before being terminated.
     * @param unit the desired time unit of the result
     * @return the time limit
     */
    long getKeepAliveTime(TimeUnit unit);
    /**
     * Returns the maximum allowed number of container threads.
     * @return the maximum allowed number of container threads
     */
    int getMaximumPoolSize();
    /**
     * Returns <code>true</code> if associated service container has been shut down.
     * @return true if associated service container has been shut down
     */
    boolean isShutdown();
    /**
     * Returns <code>true</code> if all container operations have completed following shut down.
     * @return if all container operations have completed following shut down
     */
    boolean isTerminated();
    /**
     * Blocks until all container operations have completed execution after a shutdown request,
     * or the timeout occurs, or the current thread is interrupted, whichever happens first.
     * @param timeout the maximum time to wait
     * @param unit the time unit of the timeout argument
     * @throws InterruptedException if interrupted while waiting
     */
    void awaitTermination(long timeout, TimeUnit unit) throws InterruptedException;
    /**
     * Initiates the graceful shutdown of the service container.
     * All previously submitted container operations will be executed, but no new operations will be accepted.
     * Invocation has no additional effect if already shut down.
     * This method does not wait for previously submitted container operations to complete execution.
     * Use {@link #awaitTermination(long, TimeUnit)} to do that.
     */
    void shutdown();
}
