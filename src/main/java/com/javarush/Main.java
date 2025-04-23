package com.javarush;

import com.javarush.config.HibernateConfig;
import com.javarush.entity.City;
import com.javarush.repository.CityRepository;
import com.javarush.repository.CountryRepository;
import com.javarush.service.FetchDataService;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        CityRepository cityRepository = new CityRepository();
        CountryRepository countryRepository = new CountryRepository();

        FetchDataService fetchDataService = new FetchDataService(cityRepository, countryRepository);

        try {
            List<City> cities = fetchDataService.getFetchedData();
            System.out.println("Fetched " + cities.size() + " cities:");
            for (int i = 0; i < Math.min(10, cities.size()); i++) {
                System.out.println(cities.get(i)); // или cities.get(i).getName() и т.д.
            }
        } catch (Exception e) {
            System.err.println("Ошибка при получении данных: " + e.getMessage());
            e.printStackTrace();
        } finally {
            HibernateConfig.getSessionFactory().close();
        }
    }
}
