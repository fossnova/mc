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

/**
 * Service builder is used for configuring and installing services into the service container.
 * Every service must <code>provide</code> at least one value.
 * Service may <code>require</code> values provided by other services.
 * Default service mode is {@link ServiceMode#ACTIVE} if not configured otherwise.
 * <p>
 * <B>Thread Safety:</B>
 * Instances of this interface cannot be used by multiple threads.
 * Only thread that requested the service builder is allowed to use it.
 * Any attempt to violate this will result in concurrency exception.
 * </p>
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public interface ServiceBuilder {
    /**
     * Specifies value names provided by the service.
     * @param values names of provided values
     * @return this builder instance
     * @throws IllegalArgumentException if either <code>null</code> value name was provided or identical value name
     * was specified via {@link #requires(String...)} method
     * @throws IllegalStateException if called after {@link #install()} method
     * @throws java.util.ConcurrentModificationException if used by multiple threads
     */
    ServiceBuilder provides(String... values);
    /**
     * Specifies value names required by the service.
     * @param values names of required values
     * @return this builder instance
     * @throws IllegalArgumentException if either <code>null</code> value name was provided or identical value name
     * was specified via {@link #provides(String...)} method
     * @throws IllegalStateException if called after {@link #install()} method
     * @throws java.util.ConcurrentModificationException if used by multiple threads
     */
    ServiceBuilder requires(String... values);

    /**
     * Sets service mode.
     * @param mode service mode
     * @return this builder instance
     * @throws IllegalStateException if called after {@link #install()} method
     * @throws java.util.ConcurrentModificationException if used by multiple threads
     */
    ServiceBuilder mode(ServiceMode mode);
    /**
     * Sets service instance.
     * @param service service instance
     * @return this builder instance
     * @throws IllegalStateException if called after {@link #install()} method
     * @throws java.util.ConcurrentModificationException if used by multiple threads
     */
    ServiceBuilder instance(Service service);
    /**
     * Installs service into the service container.
     * @return service controller if installation was successfull
     * @throws IllegalStateException if called multiple times or if container have been shut down
     * @throws IllegalArgumentException if either service instance or provided value was not configured
     * @throws CycleDetectedException if service installation would introduce cycle in values dependency chain
     * @throws DuplicateDetectedException if service installation would introduce duplicit value
     * @throws java.util.ConcurrentModificationException if used by multiple threads
     */
    ServiceController install();
}
