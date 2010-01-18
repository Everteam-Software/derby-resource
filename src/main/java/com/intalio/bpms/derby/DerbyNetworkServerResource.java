/**
 * Copyright Intalio, Inc. 2009-2010.
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
 * Author: Alex Boisvert (created Jan 2009) 
 */
package com.intalio.bpms.derby;

import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetAddress;

import org.apache.derby.drda.NetworkServerControl;

/**
 * A bean starting the Derby database on the host and port indicated.
 * This bean can be called as a resource through this server.xml invocation:
 * 	<Resource name="derby/DerbyNetworkServerResource" auth="Container"
 *      type="com.intalio.bpms.derby.DerbyNetworkServerResource"
 *      factory="org.apache.naming.factory.BeanFactory"
 *      host="localhost"
 *      port="1527"/>
 *      
 * @author <a href="http://www.intalio.com">Intalio Inc.</a>
 */
public class DerbyNetworkServerResource {

  private boolean _debug = false;
  
  private int _port = 0;
  
  private String _host = null;

  public DerbyNetworkServerResource() {
      _debug = System.getProperty("com.intalio.bpms.derby.debug", "false").equalsIgnoreCase("true");
  }
  
  public String getHost() {
    return _host;
  }

  public void setHost(String host) {
    _host = host;
    start();
  }

  public String getPort() {
    return Integer.toString(_port);
  }

  public void setPort(String port) {
    _port = Integer.parseInt(port);
    start();
  }
  
  /**
   * Whether the network control should be started
   * @return true if host and port are provided.
   */
  protected boolean shouldStart() {
      return _host != null && _port != 0;
  }
  
  /**
   * Starts the Derby server.
   */
  public void start() {
      PrintWriter writer;
      if (shouldStart()) {
          try {
              System.out.println("Starting Derby Network server on "+_host+":"+_port);
              NetworkServerControl server = new NetworkServerControl(InetAddress.getByName(_host), _port);
              if (_debug) {
                  writer = new PrintWriter(System.out, true);
              } else {
                  writer = new PrintWriter(new DevNullPrintWriter(), true);
              }
              server.start(writer);
          } catch (Exception e) {
              throw new RuntimeException(e);
          }
      }
  }
  
  /**
   * Stops the Derby server.
   */
  public void stop() {
      if (_host != null && _port != 0) {
          try {
              System.out.println("Stopping Derby Network server on "+_host+":"+_port);
              NetworkServerControl server = new NetworkServerControl(InetAddress.getByName(_host), _port);
              server.shutdown();
          } catch (Exception e) {
              throw new RuntimeException(e);
          }
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

