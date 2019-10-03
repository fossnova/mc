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
 * Severity of reported problem.
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public enum Severity {
    /**
     * This problem will not cause undesirable effects but it is something the user should be made aware of
     * - the operation <B>DON'T NEED TO</B> be compensated in this case.
     */
    INFO,
    /**
     * This problem could possibly cause undesirable effects now or in the future
     * - the operation <B>SHOULD</B> be compensated in this case.
     */
    WARNING,
    /**
     * This problem will likely cause undesirable effects now or in the future
     * - the operation <B>HAVE TO</B> be compensated in this case.
     */
    ERROR,
    /**
     * This problem will cause irreparable damage to the system integrity
     * - the operation <B>HAVE TO</B> be compensated in this case.
     */
    CRITICAL
}
