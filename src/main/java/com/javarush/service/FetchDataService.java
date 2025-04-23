package com.javarush.service;

import com.javarush.config.SessionManager;
import com.javarush.entity.City;
import com.javarush.entity.Country;
import com.javarush.repository.CityRepository;
import com.javarush.repository.CountryRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.hibernate.Session;

@AllArgsConstructor
public class FetchDataService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public List<City> getFetchedData() {
        try (SessionManager sessionManager = new SessionManager()) {
            Session session = SessionManager.getCurrentSession();
            session.beginTransaction();
            List<Country> countries = countryRepository.getAll();
            List<City> allCities = new ArrayList<>();
            int totalCount = cityRepository.getTotalCount();
            int step = 500;
            for (int i = 0; i < totalCount; i += step) {
                allCities.addAll(cityRepository.getItems(i, step));
            }

            session.getTransaction().commit();
            return allCities;
        } catch (Exception e) {
            Session session = SessionManager.getCurrentSession();
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException("Transaction failed", e);
        }
    }
}
