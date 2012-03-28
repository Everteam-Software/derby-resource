package com.intalio.bpms.derby;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;

public class DerbyNetworkLifeCycleListener7  extends DerbyNetworkServerResource7 
implements LifecycleListener {

/**
 * Manages lifecycle depending on events sent from the server.
 */
public void lifecycleEvent(LifecycleEvent event) {
	if (event.getType().equals(Lifecycle.BEFORE_START_EVENT)) {
        start();
    }else if (event.getType().equals(Lifecycle.AFTER_STOP_EVENT)) {
        stop();
    }
}

}
