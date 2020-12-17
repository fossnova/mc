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
package org.fossnova.mc.test;

import org.fossnova.mc.*;
import org.junit.After;
import org.junit.Before;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
class AbstractUpdateOperationTest {
    protected Container container;
    private final Object lock = new Object();

    @Before
    public final void init() {
        container = ContainerFactory.newContainer().install();
    }

    protected UpdateOperation newUpdateOperation() throws Exception {
        final CompletableFuture<UpdateOperation> f = new CompletableFuture<>();
        return container.newUpdateOperation((op) -> f.complete(op)) ? f.get() : null;
    }

    protected void prepare(final UpdateOperation op) throws Exception {
        final CompletionListener l = new CompletionListener();
        container.prepare(op, l);
        l.awaitCompletion();
    }

    protected void commit(final UpdateOperation op) throws Exception {
        final CompletionListener l = new CompletionListener();
        container.commit(op, l);
        l.awaitCompletion();
    }

    protected void restart(final UpdateOperation op) throws Exception {
        final CompletionListener l = new CompletionListener();
        container.restart(op, l);
        l.awaitCompletion();
    }

    @After
    public final void destroy() throws Exception {
        final CompletableFuture<Container> f = new CompletableFuture<>();
        if (container.shutdown(c -> f.complete(c))) f.get();
    }
}
