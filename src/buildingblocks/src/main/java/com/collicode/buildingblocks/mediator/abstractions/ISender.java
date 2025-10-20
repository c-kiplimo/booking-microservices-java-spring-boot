package com.collicode.buildingblocks.mediator.abstractions;


import com.collicode.buildingblocks.mediator.abstractions.commands.ICommand;
import com.collicode.buildingblocks.mediator.abstractions.queries.IQuery;
import com.collicode.buildingblocks.mediator.abstractions.requests.IRequest;

public interface ISender {

    <TResponse> TResponse send(IRequest<TResponse> request);

    <TResponse> TResponse send(ICommand<TResponse> command);

    <TResponse> TResponse send(IQuery<TResponse> query);
}
