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

import java.util.concurrent.TimeUnit;

/**
 * Operations are the only access point to the service container.
 * There are two types of operations:
 *
 * <UL>
 *     <LI>{@link UpdateOperation} - modifying operation can modify internal {@link ServiceContainer} states</LI>
 *     <LI>{@link ReadOperation} - read-only operations cannot modify internal {@link ServiceContainer} states</LI>
 * </UL>
 *
 * Every <B>Operation</B> is associated with <B>ServiceContainer</B> that created it.
 * At every point of time either single <B>UpdateOperation</B> is running
 * or multiple <B>ReadOperation</B>s are executing concurrently against its container.
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public interface Operation {
    /**
     * Register <I>post-commit</I> phase completion listener for notification.
     * @param listener completion listener
     * @throws IllegalStateException if attempting to register completion listener for non active operation
     */
    void addPostCommit(Listener<Context> listener);

    /**
     * Indicates whether this operation have been committed.
     * @return {@code true} if operation have been committed, {@code false} otherwise
     */
    boolean isCommitted();

    /**
     * Returns how long is / was operation running.
     * @param unit time unit
     * @return how long is / was operation running
     */
    long getDuration(TimeUnit unit);

    /**
     * Adds problem description to this operation.
     * @param problem problem description
     * @throws IllegalStateException if problem is reported for non active operation
     */
    void addProblem(Problem problem);

    /**
     * Returns operation problems report.
     * @return operation problems report
     */
    OperationReport getProblems();

    /**
     * Returns operation hold handle.
     * @return operation hold handle
     * @throws IllegalStateException if hold handle was requested for non active operation
     */
    OperationHoldHandle holdOn();
}
