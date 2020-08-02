package com.slb.api_gateway.service;

import com.slb.gateway.HelloRequest;
import com.slb.gateway.HelloResponse;
import com.slb.gateway.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloService extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        log.info("Calling Hello Service..");
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        HelloResponse helloResponse = HelloResponse.newBuilder().
                setGreeting("Hello " + firstName + " , " + lastName).build();
        responseObserver.onNext(helloResponse);
        responseObserver.onCompleted();
        log.info("Finished calling Hello Service..");
    }
}
