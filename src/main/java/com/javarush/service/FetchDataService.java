package com.javarush.service;

import com.javarush.config.HibernateConfig;
import com.javarush.entity.City;
import com.javarush.repository.CityRepository;
import com.javarush.repository.CountryRepository;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;


public class FetchDataService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public FetchDataService(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    public List<City> getFetchedData() {
        try (Session currentSession = HibernateConfig.getSessionFactory().getCurrentSession()) {
            List<City> allCities = new ArrayList<>();
            currentSession.beginTransaction();
            int totalCount = cityRepository.getTotalCount();
            int step = 500;
            for (int i = 0; i < totalCount; i += step) {
                allCities.addAll(cityRepository.getItems(i, step));
            }
            currentSession.getTransaction().commit();
            return allCities;
        }
    }

    // List<Country> countries = countryRepository.getAll();
}
