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

/**
 * Provides an access to the operation's <code>post phase</code> lifecycle events.
 * There are three <code>Post Phase</code> kind of events defined for particular operation:
 * <UL>
 *     <LI><code>post-prepare</code> - action completion listeners are called before operation will enter <B>PREPARED</B> state</LI>
 *     <LI><code>post-restart</code> - action completion listeners are called called before operation will enter <B>RESTARTED</B> state</LI>
 *     <LI><code>post-commit</code> - action completion listeners are called called before operation will enter <B>COMMITTED</B> state</LI>
 * </UL>
 * <P>
 * Action processing code communicates with associated operation
 * via {@link org.fossnova.mc.Context#complete()} method.
 * Operation will block until all actions will not be completed.
 * This allows 'Atomicity' like behaviour for compound operations
 * leveraging service container operations.
 * </P>
 * <P>Action processing code cannot fail. Users have to be careful
 * to always call {@link org.fossnova.mc.Context#complete()} especially
 * in cases {@link org.fossnova.mc.Context#asynchronous()} was called
 * before and action code is executed in some other thread.
 * Failing to do so operation will block forever. That would result in
 * deadlock-like behaviour in the running system.
 * </P>
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public interface Context {
    void complete();
    // TODO: implementation will delegate to UpdateOperation.addProblem() method
    void addProblem(Problem problem);
    void asynchronous();
}
