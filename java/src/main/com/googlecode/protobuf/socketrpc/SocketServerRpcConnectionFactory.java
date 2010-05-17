// Copyright (c) 2010 Shardul Deo
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

package com.googlecode.protobuf.socketrpc;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.logging.Logger;

/**
 * Implementation of {@link SocketServerRpcConnectionFactory} that uses
 * {@link ServerSocket} to receive/respond RPCs.
 *
 * @author Shardul Deo
 */
public class SocketServerRpcConnectionFactory implements
    ServerRpcConnectionFactory {

  private static final Logger LOG =
      Logger.getLogger(SocketServerRpcConnectionFactory.class.getName());

  private final int port;
  private final int backlog;
  private final InetAddress bindAddr;

  private volatile ServerSocket serverSocket = null;

  /**
   * @param port Port that this server socket will be started on.
   */
  public SocketServerRpcConnectionFactory(int port) {
    this(port, 0, null);
  }

  /**
   * @param port Port that this server socket will be started on.
   * @param backlog the maximum length of the queue. A value <=0 uses default
   *        backlog.
   * @param bindAddr the local InetAddress the server socket will bind to. A
   *        null value binds to any/all local IP addresses.
   */
  public SocketServerRpcConnectionFactory(int port, int backlog,
      InetAddress bindAddr) {
    this.port = port;
    this.backlog = backlog;
    this.bindAddr = bindAddr;
  }

  @Override
  public Connection createConnection() throws IOException {
    // Use Java 1.5+ double checked locking to lazy init
    ServerSocket local = serverSocket;
    if (local == null) {
      local = initServerSocket();
    }
    // Thread blocks here waiting for requests
    return new SocketConnection(serverSocket.accept());
  }

  private synchronized ServerSocket initServerSocket() throws IOException {
    ServerSocket local = serverSocket;
    if (local == null) {
      LOG.info("Listening for requests on port: " + port);
      serverSocket = local = new ServerSocket(port, backlog, bindAddr);
    }
    return local;
  }

  @Override
  public void close() throws IOException {
    ServerSocket local = serverSocket;
    if (local != null && !local.isClosed()) {
      local.close();
    }
  }
}
