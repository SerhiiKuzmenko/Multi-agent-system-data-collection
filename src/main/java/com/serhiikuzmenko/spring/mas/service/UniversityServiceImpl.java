package com.serhiikuzmenko.spring.mas.service;

import com.serhiikuzmenko.spring.mas.dao.UniversityDAO;
import com.serhiikuzmenko.spring.mas.entity.Country;
import com.serhiikuzmenko.spring.mas.entity.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService{
    @Autowired
    private UniversityDAO universityDAO;
    @Override
    @Transactional
    public List<University> getAllUniversity() {
        return universityDAO.getAllUniversity();
    }

    @Override
    @Transactional
    public Country getCountry(String name) {
        return universityDAO.getCountry(name);
    }
}
