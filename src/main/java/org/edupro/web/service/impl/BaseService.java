package org.edupro.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.edupro.web.model.response.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class BaseService<T> {
    private final RestTemplate restTemplate;
    private final Class<T> typeParameterClass;
}
