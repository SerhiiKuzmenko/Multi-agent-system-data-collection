package com.serhiikuzmenko.spring.mas.service;

import com.serhiikuzmenko.spring.mas.entity.Country;
import com.serhiikuzmenko.spring.mas.entity.University;

import java.util.List;

public interface UniversityService {
    public List<University> getAllUniversity();
    public Country getCountry(String name);
}
