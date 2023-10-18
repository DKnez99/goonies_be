package com.goonsquad.goonies.exception;

import java.text.MessageFormat;

public class EntityNotFoundException extends RuntimeException {

    private static final String ENTITY_NOT_FOUND_MSG = "Entity not found";
    private static final String ENTITY_NOT_FOUND_WITH_ID_FORMAT = "Entity with ID {0,number,#} not found";
    private static final String ENTITY_NOT_FOUND_WITH_TYPE_FORMAT = "{0} not found";
    private static final String ENTITY_NOT_FOUND_WITH_TYPE_AND_ID_FORMAT = "{0} with ID {1,number,#} not found";
    private static final String ENTITY_NOT_FOUND_WITH_TYPE_AND_PARAM_FORMAT = "{0} {1} not found";

    public EntityNotFoundException() { super(ENTITY_NOT_FOUND_MSG); }

    public <T extends Number> EntityNotFoundException(T id) {
        super(MessageFormat.format(ENTITY_NOT_FOUND_WITH_ID_FORMAT, id));
    }

    public <C extends Class<?>> EntityNotFoundException(C clazz) {
        super(MessageFormat.format(ENTITY_NOT_FOUND_WITH_TYPE_FORMAT, clazz.getSimpleName()));
    }

    public <C extends Class<?>, T extends Number> EntityNotFoundException(C clazz, T id) {
        super(MessageFormat.format(ENTITY_NOT_FOUND_WITH_TYPE_AND_ID_FORMAT, clazz.getSimpleName(), id));
    }

    public <C extends Class<?>> EntityNotFoundException(C clazz, String param) {
        super(MessageFormat.format(ENTITY_NOT_FOUND_WITH_TYPE_AND_PARAM_FORMAT, clazz.getSimpleName(), param));
    }


}
