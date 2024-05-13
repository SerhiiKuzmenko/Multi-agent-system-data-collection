package com.serhiikuzmenko.spring.mas.service;

import com.serhiikuzmenko.spring.mas.entity.Country;

import java.util.List;

public interface CountryService {
    public List<Country> getAllCountries();
    public Country getCountry(int id);
}
