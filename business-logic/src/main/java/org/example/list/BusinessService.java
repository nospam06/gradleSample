package org.example.list;

import lombok.RequiredArgsConstructor;
import org.example.model.JsonModel;
import org.example.utilities.JsonService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BusinessService {
    private final JsonService jsonService;

    public String convert(JsonModel model) {
        return jsonService.serialize(model).orElse("");
    }

    public JsonModel convert(String model) {
        return jsonService.deserialize(model, JsonModel.class).orElse(null);
    }
}
