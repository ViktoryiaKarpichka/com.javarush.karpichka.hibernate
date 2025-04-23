package com.javarush.config;

import com.javarush.entity.City;
import com.javarush.entity.Country;
import com.javarush.entity.CountryLanguage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {
    private static HibernateConfig instance;
    private final SessionFactory sessionFactory;

    private HibernateConfig() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(CountryLanguage.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (instance == null) {
            instance = new HibernateConfig();
        }
        return instance.sessionFactory;
    }
}
