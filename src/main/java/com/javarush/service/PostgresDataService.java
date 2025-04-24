package com.javarush.service;

import com.javarush.config.SessionManager;
import com.javarush.entity.City;
import com.javarush.entity.CountryLanguage;
import com.javarush.repository.CityRepository;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.hibernate.Session;

@AllArgsConstructor
public class PostgresDataService {
    private final CityRepository cityRepository;

    public void testPostgresData(List<Integer> ids) {
        try (SessionManager sessionManager = new SessionManager()) {
            Session session = SessionManager.getCurrentSession();
            session.beginTransaction();
            for (Integer id : ids) {
                City city = cityRepository.getById(id);
                Set<CountryLanguage> languages = city.getCountry().getLanguages();
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            Session session = SessionManager.getCurrentSession();
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException("Transaction failed", e);
        }
    }
}
