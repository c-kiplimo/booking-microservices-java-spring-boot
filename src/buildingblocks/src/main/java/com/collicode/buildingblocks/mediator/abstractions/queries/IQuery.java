package com.collicode.buildingblocks.mediator.abstractions.queries;


import com.collicode.buildingblocks.mediator.abstractions.requests.IRequest;

public interface IQuery<TResponse> extends IBaseQuery, IRequest<TResponse> {
}
