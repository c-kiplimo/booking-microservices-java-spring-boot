package com.collicode.buildingblocks.mediator.abstractions.commands;


import com.collicode.buildingblocks.mediator.abstractions.requests.IRequest;

public interface ICommand<TResponse> extends IRequest<TResponse>, IBaseCommand {
}
