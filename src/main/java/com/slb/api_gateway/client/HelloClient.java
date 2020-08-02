package com.slb.api_gateway.client;

import com.slb.gateway.HelloRequest;
import com.slb.gateway.HelloResponse;
import com.slb.gateway.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloClient {

    private final ManagedChannel managedChannel;
    private final HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub;

    public HelloClient(String host, int port) {
        this.managedChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        this.helloServiceBlockingStub = HelloServiceGrpc.newBlockingStub(this.managedChannel);
    }

    public void hello() {
        log.info("Calling Server..");
        HelloRequest helloRequest = HelloRequest.newBuilder().setFirstName("Pankaj").setLastName("Kumar").build();
        HelloResponse hello = helloServiceBlockingStub.hello(helloRequest);
        log.info(""+hello);
    }

    public static void main(String[] args) {
        HelloClient client = new HelloClient("0.0.0.0",8080);
        client.hello();
    }
}
