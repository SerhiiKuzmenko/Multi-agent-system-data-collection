package com.serhiikuzmenko.spring.mas.service;

import com.serhiikuzmenko.spring.mas.dao.CountryDAO;
import com.serhiikuzmenko.spring.mas.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CountryServiceImpl implements CountryService{
    @Autowired
    CountryDAO countryDAO;
    @Override
    @Transactional
    public List<Country> getAllCountries() {
        return countryDAO.getAllCountries();
    }

    @Override
    @Transactional
    public Country getCountry(int id) {
        return countryDAO.getCountry(id);
    }
}
