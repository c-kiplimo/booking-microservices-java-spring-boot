package com.collicode.booking.grpcclient;


import flight.FlightServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.client.config.GrpcChannelsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import passenger.PassengerServiceGrpc;


@Configuration
public class GrpcClientsConfiguration {

    private final GrpcChannelsProperties grpcChannelsProperties;

    public GrpcClientsConfiguration(GrpcChannelsProperties grpcChannelsProperties) {
        this.grpcChannelsProperties = grpcChannelsProperties;
    }

    @Bean
    public FlightServiceGrpc.FlightServiceBlockingStub flightServiceBlockingStub() {
        var channelProperties = this.grpcChannelsProperties.getChannel("flight-service");

        ManagedChannel channel = ManagedChannelBuilder.forAddress(channelProperties.getAddress().getHost(), channelProperties.getAddress().getPort())
                .usePlaintext()
                .build();

        return FlightServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    public PassengerServiceGrpc.PassengerServiceBlockingStub passengerServiceBlockingStub() {
        var channelProperties = this.grpcChannelsProperties.getChannel("passenger-service");

        ManagedChannel channel = ManagedChannelBuilder.forAddress(channelProperties.getAddress().getHost(), channelProperties.getAddress().getPort())
                .usePlaintext()
                .build();

        return PassengerServiceGrpc.newBlockingStub(channel);
    }
}
