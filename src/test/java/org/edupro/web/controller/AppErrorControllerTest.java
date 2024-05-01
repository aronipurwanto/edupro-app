package org.edupro.web.controller;

import com.google.gson.Gson;
import org.edupro.web.model.response.ResponseError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppErrorControllerTest {
    @Test
    void stringJsonToObject() {
        String json = "{\"statusCode\":400,\"message\":\"Invalid Input\",\"errors\":[{\"nisn\":\"Kode minimal 8 dan maximal 20\"}]}";
        ResponseError object = new Gson().fromJson(json, ResponseError.class);
        assertEquals(400, object.getStatusCode());
        assertEquals("Invalid Input", object.getMessage());
        assertNotNull(object.getErrors());
    }
}