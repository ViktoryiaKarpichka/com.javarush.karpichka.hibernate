package com.javarush.util;

import com.javarush.entity.City;
import com.javarush.entity.Country;
import com.javarush.entity.CountryLanguage;
import com.javarush.entity.redis.CityCountry;
import com.javarush.entity.redis.Language;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.experimental.UtilityClass;
import org.hibernate.Hibernate;

@UtilityClass
public class Converter {
    private Set<Language> transformLanguages(Set<CountryLanguage> languages) {
        Set<Language> result = new HashSet<>();
        for (CountryLanguage cl : languages) {
            Language language = Language.builder()
                    .language(cl.getLanguage())
                    .isOfficial(cl.getIsOfficial())
                    .percentage(cl.getPercentage())
                    .build();
            result.add(language);
        }
        return result;

    }

    private List<CityCountry> transformData(List<City> allCities) {
        List<CityCountry> result = new ArrayList<>();

        for (City city : allCities) {
            Country country = city.getCountry();
            Hibernate.initialize(country.getLanguages());

            Set<CountryLanguage> languages = country.getLanguages();
            result.add(CityCountry.builder()
                    .id(city.getId())
                    .cityName(city.getName())
                    .population(city.getPopulation())
                    .cityDistrict(city.getDistrict())
                    .countryCode2(country.getCode2())
                    .countryCode(country.getCode())
                    .continent(country.getContinent())
                    .countryName(country.getName())
                    .countryPopulation(country.getPopulation())
                    .countryRegion(country.getRegion())
                    .countrySurfaceArea(country.getSurfaceArea())
                    .languages(transformLanguages(languages))
                    .build());

        }
        return result;
    }

    public static List<CityCountry> getPreparedData(List<City> cities) {
        return transformData(cities);
    }
}

