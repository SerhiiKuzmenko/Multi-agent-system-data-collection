package com.serhiikuzmenko.spring.mas.dao;

import com.serhiikuzmenko.spring.mas.entity.Country;
import com.serhiikuzmenko.spring.mas.entity.University;

import java.util.List;

public interface UniversityDAO {
    public List<University> getAllUniversity();

    public Country getCountry(String name);
}
