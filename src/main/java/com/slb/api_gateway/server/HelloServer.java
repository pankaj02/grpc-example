package com.slb.api_gateway.server;

import com.slb.api_gateway.service.HelloService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HelloServer {
    private int port;
    private final Server server;

    public HelloServer(int port) {
        this.port = port;
        HelloService helloService = new HelloService();
        this.server = ServerBuilder.forPort(port).addService(helloService).build();
    }

    public void start() throws IOException {
        log.info("Starting Server..");
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            try {
                this.stop();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }

    private void stop() throws IOException, InterruptedException {
        log.info("Stopping Server..");
        if(server !=null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }
    private void blockUntilShutDown() throws InterruptedException {
        if(this.server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HelloServer helloServer = new HelloServer(8080);
        helloServer.start();
        helloServer.blockUntilShutDown();
    }



}
