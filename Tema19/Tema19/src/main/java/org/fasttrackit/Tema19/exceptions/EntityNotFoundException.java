package org.fasttrackit.Tema19.exceptions;

public class EntityNotFoundException extends RuntimeException{

    private int entityId;

    public EntityNotFoundException(String message, int entityId) {
        super(message);
        this.entityId = entityId;
    }

    public int getEntityId() {
        return entityId;
    }
}
