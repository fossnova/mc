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

import java.util.Collection;

/**
 * Service controller is associated with every service that was successfully installed into the service container.
 * It can be used to remove service from the service container
 * or to query information about associated service.
 * Its {@link #missing()}, {@link #state()} and {@link #reason()} methods should be
 * called inside block of code guarded by {@link ContainerHoldHandle} handle.
 * <p>
 * <B>Thread Safety:</B>
 * Instances of this interface are thread safe.
 * </p>
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public interface ServiceController {
    /**
     * Gets service mode.
     * @return service mode
     */
    ServiceMode mode();

    /**
     * Gets service state.
     * @return service state
     */
    ServiceState state();

    /**
     * Gets all value names our service provide.
     * @return all value names our service provide
     */
    Collection<String> provides();

    /**
     * Gets all dependency value names our service require.
     * @return all dependency value names our service require
     */
    Collection<String> requires();

    /**
     * Gets all dependency value names that are not available.
     * @return dependency value names that are not available.
     */
    Collection<String> missing();

    /**
     * Returns failure reason if any.
     * @return failure reason if any.
     */
    Throwable reason();
}
