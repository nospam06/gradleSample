package org.example.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class JsonModel {
    private String uuid;
    private BigDecimal number;
    private List<String> attributes;
}
