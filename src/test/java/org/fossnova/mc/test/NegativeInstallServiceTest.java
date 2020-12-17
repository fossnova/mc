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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.fossnova.mc.ReadOperation;
import org.fossnova.mc.ServiceConfigurationException;
import org.fossnova.mc.UpdateOperation;
import org.junit.Test;

/**
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public final class NegativeInstallServiceTest extends AbstractUpdateOperationTest {

    @Test
    public void attemptToInstallInvalidService() throws Exception {
        // attempt to create invalid service
        final UpdateOperation updateOp = newUpdateOperation();
        try {
            updateOp.addService().install();
            fail();
        } catch (ServiceConfigurationException e) {
            assertNotNull(e);
        } finally {
            prepare(updateOp);
            commit(updateOp);
        }
        // ensure nothing changed inside container
        final ReadOperation readOp = newReadOperation();
        try {
            assertEquals(0, readOp.getControllers().size());
            assertEquals(0, readOp.getValueNames().size());
        } finally {
            commit(readOp);
        }
    }

}
