package com.feed.me.service;

import com.feed.me.domain.dto.DataFilter;
import com.feed.me.domain.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeServiceImpl implements HomeService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HomeServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<PersonEntity> getFilteredData(DataFilter dataFilter) {


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

        jdbcTemplate.queryForList(queryString.toString(),)

        @SuppressWarnings("unchecked")
        List<PersonEntity> resultList = query.getResultList();


        return resultList
                .stream()
                .map(this::entityToData)
                .collect(Collectors.toList());

    }

    private PersonEntity entityToData(PersonEntity personEntity) {
        PersonEntity data = new PersonEntity();

        data.setId(personEntity.getId());
        data.setName(personEntity.getName());
        data.setPerson(personEntity.getPerson());

        return data;
    }
}
