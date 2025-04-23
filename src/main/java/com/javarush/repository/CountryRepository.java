package com.javarush.repository;

import com.javarush.config.SessionManager;
import com.javarush.entity.Country;
import java.util.List;
import org.hibernate.Session;

public class CountryRepository {

    public List<Country> getAll() {
        Session session = SessionManager.getCurrentSession();
        return session.createQuery("select c from Country c join fetch c.languages", Country.class).list();
    }
}
