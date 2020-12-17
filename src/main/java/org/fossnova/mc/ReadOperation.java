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

import java.util.Collection;

/**
 * Only read operations can directly access values from service container.
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public interface ReadOperation extends Operation {
    /**
     * Gets required value from service container.
     * @param name value name
     * @param <V> value type
     * @return associated value with given name, or throws ValueNotBoundException if value is not bound
     * @throws IllegalStateException if value retrieval attempt is detected for non active operation
     */
    <V> V getRequiredValue(String name) throws ValueNotBoundException;

    /**
     * Gets optional value from service container.
     * @param name value name
     * @param <V> value type
     * @return associated value with given name, or <code>null</code> if not present
     * @throws IllegalStateException if value retrieval attempt is detected for non active operation
     */
    <V> V getOptionalValue(String name);

    Collection<String> getValueNames();

    /**
     * Gets service controller providing given value name.
     * @param name value name
     * @return service controller providing given value name, or <code>null</code> if not present
     * @throws IllegalStateException if value retrieval attempt is detected for non active operation
     */
    ServiceController getController(String name);

    Collection<ServiceController> getControllers();
}
