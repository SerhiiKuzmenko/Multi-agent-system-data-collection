package com.serhiikuzmenko.spring.mas.dao;

import com.serhiikuzmenko.spring.mas.entity.Country;
import com.serhiikuzmenko.spring.mas.entity.University;

import java.util.List;

public interface CountryDAO {
    public List<Country> getAllCountries();
    public Country getCountry(int id);
}
