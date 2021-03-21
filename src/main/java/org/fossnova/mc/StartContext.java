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
 * Start context provides completion marking methods for either successfull or unsuccessfull service start action.
 * It also provides methods for intalling provided values into the service container
 * and retrieving dependency values from service container.
 * <p>
 * <B>Thread Safety:</B>
 * Instances of this interface are thread safe.
 * </p>
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public interface StartContext extends Context {
    /**
     * Triggers an asynchronous start action. Must be called from within the service start method.
     * This asynchronous start action will not be considered complete until indicated so by calling either
     * {@link #complete()} or {@link #fail(Throwable)} method on this context.
     * @throws IllegalStateException if called after either {@link #complete()} or {@link #fail(Throwable)} method or
     * if this method was called multiple times or this context is not valid anymore
     */
    @Override
    void asynchronous();
    /**
     * Must be called when either synchronous or asynchronous start action was successfully completed.
     * @throws IllegalStateException if called after {@link #fail(Throwable)} method or if this method was called
     * multiple times or this context is not valid anymore
     */
    @Override
    void complete();
    /**
     * Retrieves dependency value from the service container. This dependency value name
     * must have been configured via {@link ServiceBuilder#requires(String...)} method.
     * @param name dependency value name
     * @param <V> dependency value type
     * @return dependency value
     * @throws IllegalArgumentException if method parameter is null or on an attempt to retrieve value not configured
     * via {@link ServiceBuilder#requires(String...)} method
     * @throws IllegalStateException if called after either {@link #complete()} or {@link #fail(Throwable)} method or
     * if this context is not valid anymore
     */
    @Override
    <V> V getValue(String name);
    /**
     * Provides value to the service container. This provided value name
     * must have been configured via {@link ServiceBuilder#provides(String...)} method.
     * @param name provided value name
     * @param value provided value
     * @param <V> provided value type
     * @throws IllegalArgumentException if any method paramter is null or on an attempt to provide value not configured
     * via {@link ServiceBuilder#provides(String...)} method
     * @throws IllegalStateException if called after either {@link #complete()} or {@link #fail(Throwable)} method or
     * if this context is not valid anymore
     */
    <V> void setValue(String name, V value);
    /**
     * Must be called when either synchronous or asynchronous start action failed.
     * @param reason failure reason
     * @throws IllegalArgumentException if reason is null
     * @throws IllegalStateException if called after {@link #complete()} method or if this method was called
     * multiple times or this context is not valid anymore
     */
    void fail(Throwable reason);
}
