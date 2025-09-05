package com.collicode.buildingblocks.mediator.abstractions;


import com.collicode.buildingblocks.mediator.abstractions.notifications.INotification;

public interface IPublisher {
    <TNotification extends INotification> Void publish(TNotification notification) throws Exception;
}
