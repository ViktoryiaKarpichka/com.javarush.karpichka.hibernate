package com.javarush.repository;

import com.javarush.config.SessionManager;
import com.javarush.entity.Country;
import java.util.List;
import lombok.AllArgsConstructor;
import org.hibernate.Session;

@AllArgsConstructor
public class CountryRepository {

    public List<Country> getAll() {
        Session session = SessionManager.getCurrentSession();
        return session.createQuery("select c from Country c", Country.class).list();
    }
}
