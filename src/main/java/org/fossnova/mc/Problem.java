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

import java.io.Serializable;

/**
 * Problem reported to the operation.
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public final class Problem implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Severity severity;
    private final String message;
    private final Throwable reason;

    /**
     * Create new problem with given severity and root cause.
     * @param severity problem severity
     * @param reason root cause
     * @throws NullPointerException if severity or root cause is null
     */
    public Problem(final Severity severity, final Throwable reason) {
        this(severity, null, reason);
    }

    /**
     * Create new problem with given severity and description.
     * @param severity problem severity
     * @param message problem description
     * @throws NullPointerException if severity or root cause is null
     */
    public Problem(final Severity severity, final String message) {
        this(severity, message, null);
    }

    /**
     * Create new problem with given severity and description.
     * @param severity problem severity
     * @param message problem description
     * @param reason root cause
     * @throws NullPointerException if severity or description or root cause is null
     */
    public Problem(final Severity severity, final String message, final Throwable reason) {
        if (severity == null) throw new NullPointerException();
        if (message == null && reason == null) throw new NullPointerException();
        this.severity = severity;
        this.message = message;
        this.reason = reason;
    }

    /**
     * Returns problem severity
     * @return problem severity
     */
    public Severity getSeverity() {
        return severity;
    }

    /**
     * Returns problem description or <code>null</code> if not available
     * @return problem description or <code>null</code> if not available
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns root cause of a problem or <code>null</code> if not available
     * @return root cause of a problem or <code>null</code> if not available
     */
    public Throwable getReason() {
        return reason;
    }

}
