package application.config.grpc;

import authentication.proto.AuthenticationSimpleGrpc;
import io. grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation. Bean;
import org.springframework. context.annotation.Configuration;

import test.proto.TestSimpleGrpc;

@Configuration
public class GrpcClientConfig {

    @Value("${channel.authentication.host}")
    private String authenticationHost;

    @Value("${channel.authentication.port}")
    private int authenticationPort;

    @Bean
    public ManagedChannel managedChannel() {
        return ManagedChannelBuilder
                .forAddress(authenticationHost, authenticationPort)
                .usePlaintext()
                .build();
    }

    @Bean
    public TestSimpleGrpc.TestSimpleBlockingStub simpleBlockingStub(ManagedChannel channel) {
        return TestSimpleGrpc.newBlockingStub(channel);
    }
    @Bean
    public AuthenticationSimpleGrpc.AuthenticationSimpleBlockingStub authenticationBlockingStub(ManagedChannel channel) {
        return AuthenticationSimpleGrpc.newBlockingStub(channel);
    }

    // Thêm stub cho streaming
//    @Bean
//    public TestSimpleGrpc.TestSimpleStub simpleStub(ManagedChannel channel) {
//        return TestSimpleGrpc.newStub(channel);
//    }
}