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
 * TODO: javadoc
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public interface Context {
    // TODO: implementation will delegate to UpdateOperation.addProblem() method
    void addProblem(Problem problem);
    // TODO: if this method is not explicitly called and neither is complete() in lifecycle methods then fallback method is complete()
    void asynchronous();
    void complete();
    ServiceController getController();
    void execute(Runnable command);
    ServiceBuilder addService();
}
