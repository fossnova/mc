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
 * Stop context provides completion marking methods for service stop action.
 * It also provides method for retrieving dependency values from service container.
 * <p>
 * <B>Thread Safety:</B>
 * Instances of this interface are thread safe.
 * </p>
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public interface StopContext extends Context {
    /**
     * Triggers an asynchronous stop action. Must be called from within the service stop method.
     * This asynchronous stop action will not be considered complete until indicated so by calling
     * {@link #complete()} method on this context.
     * @throws IllegalStateException if called after {@link #complete()} method or
     * if this method was called multiple times or this context is not valid anymore
     */
    @Override
    void asynchronous();
    /**
     * Must be called when either synchronous or asynchronous stop action was completed.
     * @throws IllegalStateException if this method was called multiple times or this context is not valid anymore
     */
    @Override
    void complete();
    /**
     * Retrieves dependency value from the service container. This dependency value name
     * must have been configured via {@link ServiceBuilder#requires(String...)} method.
     * @param name dependency value name
     * @param <V> dependency value type
     * @return dependency value
     * @throws IllegalArgumentException on an attempt to retrieve value not configured via
     * {@link ServiceBuilder#requires(String...)} method
     * @throws IllegalStateException if called after {@link #complete()} method or if this context is not valid anymore
     */
    @Override
    <V> V getValue(String name);
}
