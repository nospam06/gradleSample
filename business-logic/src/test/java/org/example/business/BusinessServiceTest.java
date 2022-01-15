package org.example.business;

import org.example.json.BaseTest;
import org.example.list.BusinessService;
import org.example.model.JsonModel;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BusinessServiceTest extends BaseTest {

    @Test
    void convert() {
        BusinessService businessService = getBean(BusinessService.class);
        JsonModel model = new JsonModel();
        String uuid = UUID.randomUUID().toString();
        model.setUuid(uuid);
        model.setNumber(BigDecimal.TEN);
        model.setAttributes(List.of("hello world"));
        String actual = businessService.convert(model);
        String expected = "{\"uuid\":\"" + uuid + "\",\"number\":10,\"attributes\":[\"hello world\"]}";
        assertEquals(expected, actual);

        String alternative = "{\"uuid\":\"" + uuid + "\",\"number\":10.0,\"attributes\":\"hello world\"}";
        JsonModel actualModel = businessService.convert(alternative);
        model.setNumber(model.getNumber().setScale(1, RoundingMode.HALF_UP));
        assertEquals(model, actualModel);
    }
}