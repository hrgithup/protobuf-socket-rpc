# Python Usage #

The following example shows a server and a client using the `protobuf.socketrpc` Python API.

## Server side ##

```
// Start server
server = protobuf.socketrpc.server.SocketRpcServer(port)
server.registerService(MyServiceImpl())
server.run()
```

## Client Side ##

```
// Define callback
class Callback:
  def run(self,response):
    print "Received Response: %s" % response

// Create channel
channel = protobuf.socketrpc.channel.SocketRpcChannel(hostname,port)
controller = channel.newController()

// Call service
service  = MyService_Stub(channel)
service.myMethod(controller,request,Callback())

// Check success
if controller.failed():
  print "Rpc failed %s : %s" % (controller.error,controller.reason)
```


## Further examples ##

There are two more examples included in the source tarball. You might also want to have a look at the unit tests.