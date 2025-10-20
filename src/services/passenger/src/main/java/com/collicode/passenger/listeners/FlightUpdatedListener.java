package com.collicode.passenger.listeners;


import com.collicode.buildingblocks.contracts.flight.FlightUpdated;
import com.collicode.buildingblocks.rabbitmq.MessageHandler;
import com.collicode.buildingblocks.utils.jsonconverter.JsonConverterUtils;
import org.slf4j.Logger;
import org.slf4j.event.KeyValuePair;
import org.springframework.stereotype.Component;

@Component
public class FlightUpdatedListener implements MessageHandler<FlightUpdated> {

    private final Logger logger;

    public FlightUpdatedListener(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void onMessage(FlightUpdated flightUpdated) {
        logger.info("Do other processing after update flight in passenger service for this flight: {}", new KeyValuePair("flight_updated_event", JsonConverterUtils.serializeObject(flightUpdated)));
    }
}
