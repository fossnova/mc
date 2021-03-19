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
 * Service container is providing methods for installing and removing services.
 * It also provides method to block itself allowing user code to inspect container internals.
 * <p>
 * <B>Thread Safety:</B>
 * Instances of this interface are thread safe.
 * </p>
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public interface Container {
    /**
     * Adds service to the service container.
     * @return new service builder instance
     */
    ServiceBuilder addService();
    /**
     * Removes service from the service container.
     * @param controller controller of service to be removed
     * @throws IllegalStateException if container have been shutdown
     */
    void removeService(ServiceController controller);
    /**
     * Wait for the service container to complete all its operations and then block it.
     * @param timeout to wait for this request completion
     * @param unit the time unit of the time argument
     * @return hold handle of the container
     * @throws InterruptedException if interrupted while waiting
     */
    ContainerHoldHandle acquireHoldHandle(long timeout, TimeUnit unit) throws InterruptedException;
}
