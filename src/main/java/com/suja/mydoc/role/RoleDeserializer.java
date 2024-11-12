package com.suja.mydoc.role;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class RoleDeserializer extends JsonDeserializer<Role> {

    @Override
    public Role deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String roleValue = jsonParser.getText();
        try {
            return Role.valueOf(roleValue.toUpperCase()); // Convert input to uppercase to match enum values
        } catch (IllegalArgumentException e) {
            return null; // or handle invalid input as you see fit
        }
    }
}
