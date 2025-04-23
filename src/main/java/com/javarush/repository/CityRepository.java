package com.javarush.repository;

import com.javarush.config.SessionManager;
import com.javarush.entity.City;
import java.util.List;
import lombok.AllArgsConstructor;
import org.hibernate.Session;

@AllArgsConstructor
public class CityRepository {
    public List<City> getItems(int offset, int limit) {
        Session session = SessionManager.getCurrentSession();
        return session.createQuery("select c from City c", City.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    public int getTotalCount() {
        Session session = SessionManager.getCurrentSession();
        return Math.toIntExact(session.createQuery("select count(c) from City c", Long.class).uniqueResult());
    }

}
