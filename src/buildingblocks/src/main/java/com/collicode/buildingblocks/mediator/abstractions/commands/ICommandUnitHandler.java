package com.collicode.buildingblocks.mediator.abstractions.commands;


import com.collicode.buildingblocks.mediator.abstractions.requests.Unit;

public interface ICommandUnitHandler<TCommand extends ICommandUnit> extends ICommandHandler<TCommand, Unit> {
}
