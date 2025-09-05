package com.collicode.buildingblocks.mediator.abstractions.queries;


import com.collicode.buildingblocks.mediator.abstractions.requests.IRequestHandler;

public interface IQueryHandler<TQuery extends IQuery<TResponse>, TResponse> extends IRequestHandler<TQuery, TResponse> {
    TResponse handle(TQuery query);
}
