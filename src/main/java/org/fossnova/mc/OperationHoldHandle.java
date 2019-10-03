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
 */package org.fossnova.mc;

/**
 * Handle for blocking ACTIVE operations.
 * Operation will stay ACTIVE until all acquired hold handles are not released.
 * It is useful for letting know when external code is working on behalf of an operation.
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public interface OperationHoldHandle {
    /**
     * Adds problem description to associated operation.
     * Have to be called before {@link #release()} method is called.
     * @param problem problem description
     * @throws IllegalStateException if problem is reported for non active operation
     */
    void addProblem(Problem problem);

    /**
     * Releases <code>this</code> handle to allow associated operation to proceed.
     */
    void release();
}
