package com.intalio.bpms.derby;

import java.io.Writer;

import org.apache.derby.drda.NetworkServerControl;

public class DerbyNetworkServerResource7 {
	
	private boolean _debug = false;

	  public DerbyNetworkServerResource7() {
	      _debug = System.getProperty("com.intalio.bpms.derby.debug", "false").equalsIgnoreCase("true");
	  }
	  
	  /**
	   * Starts the Derby server.
	   */
	  public void start() {
          try {
              System.out.println("Starting Derby Network server");
              NetworkServerControl server = new NetworkServerControl();
              server.start(null);
          } catch (Exception e) {
              throw new RuntimeException(e);
          }
	  }
	  
	  /**
	   * Stops the Derby server.
	   */
	  public void stop() {
	     try {
              System.out.println("Stopping Derby Network server");
              NetworkServerControl server = new NetworkServerControl();
              server.shutdown();
          } catch (Exception e) {
              throw new RuntimeException(e);
          }
	  }
	  
	  /**
	   * An empty logger that ignores whatever is logged to it.
	   *
	   * @author <a href="http://www.intalio.com">Intalio Inc.</a>
	   */
	  static class DevNullPrintWriter extends Writer {
	      public void write(char[] chars, int off, int len) {
	          // nothing
	      }
	      public void flush() {
	          // nothing
	      }
	      public void close() {
	          // nothing
	      }
	  }

}
