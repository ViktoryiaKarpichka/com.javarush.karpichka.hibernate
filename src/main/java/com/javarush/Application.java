package com.javarush;

import com.javarush.config.HibernateConfig;
import com.javarush.entity.City;
import com.javarush.entity.redis.CityCountry;
import com.javarush.repository.CityRepository;
import com.javarush.repository.CountryRepository;
import com.javarush.service.FetchDataService;
import com.javarush.service.PostgresDataService;
import com.javarush.service.RedisDataService;
import com.javarush.util.Converter;
import java.util.List;
import org.hibernate.SessionFactory;

public class Application {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final FetchDataService fetchDataService;
    private final PostgresDataService postgresDataService;
    private final RedisDataService redisDataService;

    public Application() {
        this.cityRepository = new CityRepository();
        this.countryRepository = new CountryRepository();
        this.fetchDataService = new FetchDataService(cityRepository, countryRepository);
        this.postgresDataService = new PostgresDataService(cityRepository);
        this.redisDataService = new RedisDataService();
    }

    public void getAllData() {
        List<City> cities = fetchDataService.getFetchedData();
        List<CityCountry> preparedData = Converter.getPreparedData(cities);
        redisDataService.pushToRedis(preparedData);
    }

    public void compareSpeedReceivedData() {
        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

        long startRedis = System.currentTimeMillis();
        redisDataService.testRedisData(ids);
        long stopRedis = System.currentTimeMillis();

        long startPostgres = System.currentTimeMillis();
        postgresDataService.testPostgresData(ids);
        long stopPostgres = System.currentTimeMillis();

        System.out.printf("Redis:\t%d ms\n", (stopRedis - startRedis));
        System.out.printf("PostgreSQL:\t%d ms\n", (stopPostgres - startPostgres));
    }

    public void shutdown() {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
        redisDataService.closeRedisConnection();
    }
}
