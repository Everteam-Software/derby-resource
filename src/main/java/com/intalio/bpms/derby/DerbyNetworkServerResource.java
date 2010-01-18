package com.intalio.bpms.derby;

import java.lang.reflect.*;
import java.net.InetAddress;
import java.io.*;
import org.apache.derby.drda.NetworkServerControl;

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
  
  
  public void start() {
      PrintWriter writer;
      if (_host != null && _port != 0) {
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

