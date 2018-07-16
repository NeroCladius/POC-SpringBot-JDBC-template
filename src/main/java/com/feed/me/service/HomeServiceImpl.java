package com.feed.me.service;

import com.feed.me.domain.dto.DataFilter;
import com.feed.me.domain.dto.Person;
import com.feed.me.domain.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeServiceImpl implements HomeService {

    private final EntityManager em;

    @Autowired
    public HomeServiceImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Person> getFilteredData(DataFilter dataFilter) {


        StringBuilder queryString = new StringBuilder()
                .append("SELECT * FROM DATA d WHERE 1=1 ");

        if (dataFilter.getId() != null) {
            queryString.append(" AND d.ID = :id ");
        }

        if (!StringUtils.isEmpty(dataFilter.getName())) {
            queryString.append(" AND d.NAME = :name ");
        }

        if (!StringUtils.isEmpty(dataFilter.getPerson())) {
            queryString.append(" AND d.PERSON = :person");
        }

        Query query = em.createNativeQuery(queryString.toString(), PersonEntity.class);

        if (dataFilter.getId() != null) {
            query.setParameter("id", dataFilter.getId());
        }

        if (!StringUtils.isEmpty(dataFilter.getName())) {
            query.setParameter("name", dataFilter.getName());
        }

        if (!StringUtils.isEmpty(dataFilter.getPerson())) {
            query.setParameter("person", dataFilter.getPerson());
        }

        @SuppressWarnings("unchecked")
        List<PersonEntity> resultList = query.getResultList();

        em.flush();

        return resultList
                .stream()
                .map(this::entityToData)
                .collect(Collectors.toList());


    }

    private Person entityToData(PersonEntity entity) {
        Person data = new Person();

        if (entity.getId() != null) {
            data.setId(entity.getId());
        }

        if (entity.getName() != null) {
            data.setName(Arrays.asList((entity.getName().split(","))));
        }

        if (entity.getPerson() != null) {
            data.setPerson(Arrays.asList((entity.getPerson().split(","))));
        }

        return data;
    }

}
