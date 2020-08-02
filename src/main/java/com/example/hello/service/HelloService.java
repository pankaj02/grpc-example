package com.example.hello.service;

import com.example.hello.HelloRequest;
import com.example.hello.HelloResponse;
import com.example.hello.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
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
