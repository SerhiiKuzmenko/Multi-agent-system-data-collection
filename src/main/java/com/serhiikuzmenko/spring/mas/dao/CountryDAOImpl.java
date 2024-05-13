package com.serhiikuzmenko.spring.mas.dao;

import com.serhiikuzmenko.spring.mas.entity.Country;
import com.serhiikuzmenko.spring.mas.entity.University;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CountryDAOImpl implements CountryDAO{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Country> getAllCountries() {
        Session session = sessionFactory.getCurrentSession();
        List<Country> countries = session.createQuery("FROM Country", Country.class).getResultList();
        return countries;
    }

    @Override
    public Country getCountry(int id) {
        Session session = sessionFactory.getCurrentSession();
        Country country = session.get(Country.class, id);
        return country;
    }


}
