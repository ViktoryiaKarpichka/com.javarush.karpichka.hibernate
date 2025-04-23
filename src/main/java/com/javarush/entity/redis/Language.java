package com.javarush.entity.redis;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Language {
    private String language;
    private Boolean isOfficial;
    private BigDecimal percentage;
}
