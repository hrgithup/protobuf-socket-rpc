

# Release Notes #

## Trunk HEAD ##

  * ...

## Version 2.0 (SVN [Revision 94](https://code.google.com/p/protobuf-socket-rpc/source/detail?r=94)) ##

  * Add true async support to Java implementation.
  * Add persistent connection support to Java. Multiple rpcs over single connection.

## Version 1.3.2 (SVN [Revision 89](https://code.google.com/p/protobuf-socket-rpc/source/detail?r=89)) ##

  * Add metadata to python/setup.py
  * Rename Python module from protobuf to protobuf.socketrpc to avoid name clashes ([Issue 17](https://code.google.com/p/protobuf-socket-rpc/issues/detail?id=17))
  * Join thread instead of busy waiting for synchronous calls (Python)
  * Protobuf 2.3.0 compatible
  * Reorganized tree to match Maven conventions
  * Support more options to start the Server (Java)
  * SocketRpcChannel deprecated in favour of RpcChannelImpl (Java)
  * Fixes for [Issue 11](https://code.google.com/p/protobuf-socket-rpc/issues/detail?id=11) (Cannot create different server side handlers for the same service) and [Issue 12](https://code.google.com/p/protobuf-socket-rpc/issues/detail?id=12) (Exceptions on server not propogated to client)
  * Fixed unit tests

## Version 1.3.1 (SVN [Revision 46](https://code.google.com/p/protobuf-socket-rpc/source/detail?r=46)) ##

  * Allow customization of server socket with backlog and bind inet addr (Java)
  * Added Service class to make rpc calls cleaner and hide some of the setup details (Python)

## Version 1.3 (SVN [Revision 33](https://code.google.com/p/protobuf-socket-rpc/source/detail?r=33)) ##

  * Protobuf 2.2.0 compatible and dependent.
  * Moving error reasons out of response message into its own top level type in the proto. Added client side error reasons to the error codes to be consistent.

## Version 1.2 (SVN [Revision 12](https://code.google.com/p/protobuf-socket-rpc/source/detail?r=12)) ##

  * Errors that occur on server side are not relayed back to client. Client has new error reasons for them in the controller.
  * Client callback behavior is faithful replication of server callback scenarios:
    * Callback is called with good response.
    * Callback is not called.
    * Callback is called with null.
    * Callback is called with empty protocol buffer.
  * Added comprehensive unit tests for client and server.

## Version 1.1 (SVN [Revision 9](https://code.google.com/p/protobuf-socket-rpc/source/detail?r=9)) ##

  * Added error handling. When errors occur, the client can find out what error occurred from the controller.
  * Started adding unit tests.