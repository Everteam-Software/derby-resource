/**
 *  Copyright (C) 2010, Intalio Inc.
 *
 * This file is part of Derby Resource.
 *
 * Derby Resource is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Derby Resource is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Derby Resource.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Date         Author             Changes
 * Jan 18, 2010      Antoine Toulme     Created
 */
package com.intalio.bpms.derby;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;

/**
 * Tomcat lifecycle listener to make sure the Derby database
 * is started and shut down appropriately.
 * Note that the listener starts the derby server when the host and port
 * are set, very early in the lifecycle of the app server.
 * 
 * @author <a href="http://www.intalio.com">Intalio Inc.</a>
 */
public class DerbyNetworkLifeCycleListener extends DerbyNetworkServerResource 
    implements LifecycleListener {

    /**
     * Manages lifecycle depending on events sent from the server.
     */
    public void lifecycleEvent(LifecycleEvent event) {
        if (event.getType().equals(Lifecycle.AFTER_STOP_EVENT)) {
            stop();
        }
    }

}
