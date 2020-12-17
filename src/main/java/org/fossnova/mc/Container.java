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

    boolean upgrade(ReadOperation operation, Listener<UpdateOperation> listener);
    boolean downgrade(UpdateOperation operation, Listener<ReadOperation> listener);

    /**
     * Creates a new modifying operation asynchronously.
     * The completion listener is called when operation is created.
     * @param listener completion listener
     * @return true if request was accepted, false if container has been shutdown
     */
    boolean newUpdateOperation(Listener<UpdateOperation> listener);

    /**
     * Creates a new read-only operation asynchronously.
     * The completion listener is called when operation is created.
     * @param listener completion listener
     * @return true if request was accepted, false if container has been shutdown
     */
    boolean newReadOperation(Listener<ReadOperation> listener);

    /**
     * Determines whether the specified operation have been created by this service container.
     * @param operation to check ownership
     * @return true if created by this service container, false otherwise
     */
    boolean owns(Operation operation);

    /**
     * Indicates whether a PREPARED modifying operation can be committed.
     * If return value is <code>false</code> then such operation should be reverted with
     * compensating operation created via {@link #restart(UpdateOperation, Listener)} method.
     * @param operation to check if it can be committed
     * @return true if modifying operation can be committed, false if it shouldn't be committed
     */
    boolean canCommit(UpdateOperation operation);

    /**
     * Commits the work done by PREPARED updating operation or ACTIVE read-only operation.
     * @param operation to be committed
     * @param listener completion listener
     * @param <O> either updating or read-only operation
     * @return <code>true</code> if commit request was accepted, <code>false</code> otherwise
     */
    <O extends Operation> boolean commit(O operation, Listener<O> listener);

    /**
     * Forces ACTIVE updating operation to transition to PREPARED state.
     * When operation will enter PREPARED state it will call associated completion listener for notification.
     * Once completion listener is called, either {@link #commit(Operation, Listener)} or
     * {@link #restart(UpdateOperation, Listener)} have to be called
     * in order to allow other pending operations to proceed.
     * @param operation ACTIVE operation
     * @param listener completion listener
     * @return <code>true</code> if prepare request was accepted, <code>false</code> otherwise
     */
    boolean prepare(UpdateOperation operation, Listener<UpdateOperation> listener);

    /**
     * Restarts PREPARED updating operation to emulate compensating operation behavior.
     * The updating operation instance associated with completion listener will always be different
     * from updating operation that was restarted. In other words operation passed as first parameter
     * will be TERMINATED and new updating transaction in ACTIVE state will be created and passed to listener.
     * @param operation operation to TERMINATE and create compensating operation for
     * @param listener completion listener
     * @return <code>true</code> if restart request was accepted, <code>false</code> otherwise
     */
    boolean restart(UpdateOperation operation, Listener<UpdateOperation> listener);

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
     * @param listener completion listener for container shut down.
     * @return <code>true</code> if shutdown request was accepted, <code>false</code> otherwise
     */
    boolean shutdown(Listener<Container> listener);

    /**
     * Initiates an orderly shutdown in which previously submitted operations are executed,
     * but no new operations will be accepted. Invocation has no additional effect if already shut down.
     *
     * @param timeout the maximum time to wait
     * @param unit the time unit of the timeout argument
     * @param listener completion listener for container shut down.
     * @return <code>true</code> if shutdown request was accepted, <code>false</code> otherwise
     */
    boolean shutdown(long timeout, TimeUnit unit, Listener<Container> listener);
}
