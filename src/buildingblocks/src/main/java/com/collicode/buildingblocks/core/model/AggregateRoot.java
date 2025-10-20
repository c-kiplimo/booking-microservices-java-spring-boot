package com.collicode.buildingblocks.core.model;


import com.collicode.buildingblocks.core.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
public abstract class AggregateRoot<T> extends BaseEntity<T> {
    // Use CopyOnWriteArrayList for thread-safe concurrent access
    private static final CopyOnWriteArrayList<DomainEvent> domainEvents = new CopyOnWriteArrayList<>();
    protected T id;

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public void addDomainEvent(DomainEvent domainEvent) {
        domainEvents.add(domainEvent);
    }

    public void clearDomainEvents() {
        domainEvents.clear();
    }
}
