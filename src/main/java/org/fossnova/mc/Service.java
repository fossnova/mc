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
 * A service provides values to its dependents. It can be started and stopped.
 * It is guaranteed by the service container that service
 * will always have access to its dependencies in both lifecycle methods.
 * Service must always ensure thread safety and protect any mutable state appropriately.
 * <p>
 * <B>Thread Safety:</B>
 * Implementations of this interface must be thread safe.
 * </p>
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public interface Service {
    /**
     * Starts the service.
     * This lifecycle method is called by the service container if and only if all
     * configured dependencies of this service are available.
     * Start methods that involve only activities that cannot block should always
     * be executed synchronously using either {@link StartContext#complete()} for successfull
     * start or {@link StartContext#fail(Throwable)} in case of start failure.
     * If the service start involves any activity that may block
     * then asynchronous mechanism provided by {@link StartContext#asynchronous()}
     * should be used to free service container worker threads.
     * @param ctx service start context
     */
    void start(StartContext ctx);
    /**
     * Stops the service.
     * This lifecycle method is called by the service container if one of the
     * configured dependencies of this service is going to be unavailable but
     * it is guaranteed that all dependencies are still available until this
     * lifecycle method will not complete.
     * Stop methods that involve only activities that cannot block should always
     * be executed synchronously using {@link StopContext#complete()}.
     * If the service stop involves any activity that may block
     * then asynchronous mechanism provided by {@link StopContext#asynchronous()}
     * should be used to free service container worker threads.
     * @param ctx service stop context
     */
    void stop(StopContext ctx);
}