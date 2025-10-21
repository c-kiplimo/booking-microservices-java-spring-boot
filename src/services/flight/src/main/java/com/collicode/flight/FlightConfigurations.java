package com.collicode.flight;


import com.collicode.buildingblocks.collector.OtelCollectorConfiguration;
import com.collicode.buildingblocks.core.event.EventDispatcherConfiguration;
import com.collicode.buildingblocks.jpa.JpaConfiguration;
import com.collicode.buildingblocks.keycloak.KeycloakConfiguration;
import com.collicode.buildingblocks.logger.LoggerConfiguration;
import com.collicode.buildingblocks.mediator.MediatorConfiguration;
import com.collicode.buildingblocks.mongo.MongoConfiguration;
import com.collicode.buildingblocks.outboxprocessor.PersistMessageProcessorConfiguration;
import com.collicode.buildingblocks.problemdetails.CustomProblemDetailsHandler;
import com.collicode.buildingblocks.rabbitmq.RabbitmqConfiguration;
import com.collicode.buildingblocks.swagger.SwaggerConfiguration;
import com.collicode.buildingblocks.threadpool.ThreadPoolConfiguration;
import com.collicode.buildingblocks.web.WebClientConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
  CustomProblemDetailsHandler.class,
  JpaConfiguration.class,
  MongoConfiguration.class,
  LoggerConfiguration.class,
  FlywayAutoConfiguration.FlywayConfiguration.class,
  RabbitmqConfiguration.class,
  OtelCollectorConfiguration.class,
  SwaggerConfiguration.class,
  KeycloakConfiguration.class,
  WebClientConfiguration.class,
  ThreadPoolConfiguration.class,
  PersistMessageProcessorConfiguration.class,
  EventDispatcherConfiguration.class,
  MediatorConfiguration.class
})
public class FlightConfigurations {
}


