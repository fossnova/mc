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

/***
 * Service container builder.
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public interface ContainerBuilder {
    /**
     * Sets container name.
     * @param name container name
     * @return this builder
     */
    ContainerBuilder setName(final String name);

    /**
     * Specifies if service container will automatically shut down on VM exit.
     * @param autoShutdown if true container will shutdown on VM exit
     * @return this builder
     */
    ContainerBuilder setAutoShutdown(final boolean autoShutdown);

    /**
     * The number of service container internal worker threads (must be greater than zero)
     * @param threadsCount work threads count
     * @return this builder
     */
    ContainerBuilder setThreadsCount(final int threadsCount);

    /**
     * Creates new service container using given configuration.
     * @return new service container controller instance
     */
    ContainerController finish();
}
