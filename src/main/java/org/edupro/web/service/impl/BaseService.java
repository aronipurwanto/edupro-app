package org.edupro.web.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.edupro.web.model.response.ResponseError;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class BaseService {

    protected List<FieldError> readError(RestClientException e){
        String message = e.getMessage();
        if(!message.contains("400")){
            return Collections.emptyList();
        }

        message = message.replace("400 :","");
        message = message.substring(2, message.length()-1);

        ResponseError errorApi;
        try {
            JsonObject jsonObject = JsonParser.parseString(message).getAsJsonObject();
            errorApi = new Gson().fromJson(jsonObject, ResponseError.class);
        }catch (JsonSyntaxException ex){
            return Collections.emptyList();
        }

        if(errorApi.getErrors() == null || errorApi.getErrors().isEmpty()) {
            return Collections.emptyList();
        }

        List<FieldError> result  = new ArrayList<>();
        for(Map<String,String> error: errorApi.getErrors()){
            for(Map.Entry<String,String> entry: error.entrySet()){
                FieldError fieldError = new FieldError("", entry.getKey(),entry.getValue());
                result.add(fieldError);
            }
        }

        return result;
    }
}
