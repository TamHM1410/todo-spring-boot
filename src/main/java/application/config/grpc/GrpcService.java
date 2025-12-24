package application.config.grpc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import test.proto.HelloReply;
import test.proto.HelloRequest;
import test.proto.TestSimpleGrpc;


@AllArgsConstructor
@Service
public class GrpcService {

    private final TestSimpleGrpc.TestSimpleBlockingStub stub;

    public String sayHello(String name) {
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();
        HelloReply reply = stub.sayHello(request);
        return reply.getMessage();
    }

}
