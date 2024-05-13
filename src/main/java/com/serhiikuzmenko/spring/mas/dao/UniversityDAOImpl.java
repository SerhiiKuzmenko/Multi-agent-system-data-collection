package com.serhiikuzmenko.spring.mas.dao;

import com.serhiikuzmenko.spring.mas.entity.Country;
import com.serhiikuzmenko.spring.mas.entity.University;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UniversityDAOImpl implements UniversityDAO{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<University> getAllUniversity() {
        Session session = sessionFactory.getCurrentSession();
        List<University> universities = session.createQuery("FROM University", University.class).getResultList();
        return universities;
    }

    @Override
    public Country getCountry(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from University where name = :name");
        query.setParameter("name", name);
        University university = (University) query.uniqueResult();

        return university.getCountry();
    }
}
