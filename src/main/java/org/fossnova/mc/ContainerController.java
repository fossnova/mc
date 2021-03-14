package org.fossnova.mc;

import java.util.concurrent.TimeUnit;

public interface ContainerController {

    /**
     * Gets container associated with this controller.
     * @return container instance
     */
    Container getContainer();

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
