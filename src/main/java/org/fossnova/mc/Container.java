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
import java.util.concurrent.TimeUnit;

/**
 * Service container.
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public interface Container {
    /**
     * Gets container name.
     * @return container name
     */
    String getName();

    /**
     * Whether this service container will automatically shut down on VM exit.
     * @return <code>true</code> if this service container will automatically shut down on VM exit, <code>false</code> otherwise
     */
    boolean isAutoShutdown();

    /**
     * Returns number of service container internal worker threads
     * @return number of service container internal worker threads
     */
    int getThreadsCount();

    ServiceBuilder addService();
    void removeService(ServiceController controller);
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

    /**
     * Returns <code>true</code> if this service container has been shut down.
     * @return true if this service container has been shut down
     */
    boolean isShutdown();

    /**
     * Returns <code>true</code> if all operations have completed following shut down.
     * Note that <code>isTerminated</code> is never <code>true</code>
     * unless <code>shutdown</code> was called first.
     * @return true if all operations have completed following shut down
     */
    boolean isTerminated();

    /**
     * Initiates an orderly shutdown in which previously submitted operations are executed,
     * but no new operations will be accepted. Invocation has no additional effect if already shut down.
     *
     * @param containerListener completion listener for container shut down.
     * @return <code>true</code> if shutdown request was accepted, <code>false</code> otherwise
     */
    boolean shutdown(ContainerListener containerListener);

    /**
     * Initiates an orderly shutdown in which previously submitted operations are executed,
     * but no new operations will be accepted. Invocation has no additional effect if already shut down.
     *
     * @param timeout the maximum time to wait
     * @param unit the time unit of the timeout argument
     * @param containerListener completion listener for container shut down.
     * @return <code>true</code> if shutdown request was accepted, <code>false</code> otherwise
     */
    boolean shutdown(long timeout, TimeUnit unit, ContainerListener containerListener);
}
