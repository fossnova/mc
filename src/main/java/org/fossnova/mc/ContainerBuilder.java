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

/***
 * Container builder is used for configuring and creating new service containers.
 * Default auto shutdown mode is <code>true</code> if not configured otherwise.
 * <p>
 * <B>Thread Safety:</B>
 * Instances of this interface cannot be used by multiple threads.
 * Only thread that requested the container builder is allowed to use it.
 * Any attempt to violate this will result in concurrency exception.
 * </p>
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public interface ContainerBuilder {
    /**
     * Sets container name.
     * @param containerName container name
     * @return this builder instance
     * @throws IllegalArgumentException if container name is <code>null</code>
     * @throws IllegalStateException if called after {@link #install()} method
     * @throws java.util.ConcurrentModificationException if used by multiple threads
     */
    ContainerBuilder name(String containerName);

    /**
     * Specifies if service container should automatically shut down on VM exit.
     * @param autoShutdown if true container will shutdown on VM exit
     * @return this builder instance
     * @throws IllegalStateException if called after {@link #install()} method
     * @throws java.util.ConcurrentModificationException if used by multiple threads
     */
    ContainerBuilder autoShutdown(boolean autoShutdown);

    /**
     * Sets the core number of container threads.
     * @param corePoolSize core threads pool size
     * @return this builder instance
     * @throws IllegalArgumentException if <code>corePoolSize</code> is less than zero
     * @throws IllegalStateException if called after {@link #install()} method
     * @throws java.util.ConcurrentModificationException if used by multiple threads
     */
    ContainerBuilder corePoolSize(int corePoolSize);

    /**
     * Sets the time limit for which container threads may remain idle before being terminated.
     * @param time the time to wait
     * @param unit the time unit of the time argument
     * @return this builder instance
     * @throws IllegalArgumentException if time is less than zero or time unit is <code>null</code>
     * @throws IllegalStateException if called after {@link #install()} method
     * @throws java.util.ConcurrentModificationException if used by multiple threads
     */
    ContainerBuilder keepAliveTime(long time, TimeUnit unit);

    /**
     * Sets the maximum allowed number of container threads.
     * @param maximumPoolSize maximum threads pool size
     * @return this builder instance
     * @throws IllegalArgumentException if <code>maximumPoolSize</code> is less than or equal to zero
     * @throws IllegalStateException if called after {@link #install()} method
     * @throws java.util.ConcurrentModificationException if used by multiple threads
     */
    ContainerBuilder maximumPoolSize(int maximumPoolSize);

    /**
     * Creates new service container.
     * @return container controller
     * @throws IllegalArgumentException if <code>maximumPoolSize</code> is less than <code>corePoolSize</code>
     * @throws java.util.ConcurrentModificationException if used by multiple threads
     */
    ContainerController install();
}
