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

public final class Problem {
    private final Severity severity;
    private final String message;
    private final Throwable reason;

    public Problem(final Severity severity, final Throwable reason) {
        this(severity, null, reason);
    }

    public Problem(final Severity severity, final String message) {
        this(severity, message, null);
    }

    public Problem(final Severity severity, final String message, final Throwable reason) {
        if (severity == null) throw new NullPointerException();
        if (message == null && reason == null) throw new NullPointerException();
        this.severity = severity;
        this.message = message;
        this.reason = reason;
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getReason() {
        return reason;
    }
}
