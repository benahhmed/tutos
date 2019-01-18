package com.example.demoActuator.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BooleanResponse.class, name = "BOOLEAN_RESPONSE"),
        @JsonSubTypes.Type(value = ListResponse.class, name = "LIST_RESPONSE"),
        @JsonSubTypes.Type(value = ObjectResponse.class, name = "OBJECT_RESPONSE"),
        @JsonSubTypes.Type(value = PaginatedListResponse.class, name = "PAGINATED_LIST_RESPONSE")}
)
public abstract class GenericResponseBody {
    protected ResponseEnum type;
}
