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
 * Service modes.
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public enum Mode {
    /**
     * Service starts as soon as all dependencies are satisfied.
     * Will start even if there are no demandants
     * and will stay running if all demandants are gone.
     * Actively demands dependencies at install time.
     */
    ACTIVE,
    /**
     * Service starts as soon as all dependencies
     * are satisfied and service is demanded to start.
     * Will stay running when all demandants are gone.
     * Does not demand dependencies at install time.
     */
    LAZY,
    /**
     * Service starts as soon as all dependencies
     * are satisfied and service is demanded to start.
     * Will stop when all demandants are gone.
     * Does not demand dependencies at install time.
     */
    ON_DEMAND
}
