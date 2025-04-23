package com.javarush.entity.redis;

import com.javarush.entity.Continent;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityCountry {
    private Integer id;
    private String cityName;
    private String cityDistrict;
    private Integer population;
    private String countryCode;
    private String alternativeCountryCode;
    private String countryName;
    private Continent continent;
    private String countryRegion;
    private BigDecimal countrySurfaceArea;
    private Integer countryPopulation;
    private Set<Language> languages;
}
