package com.feed.me.service;

import com.feed.me.domain.dto.DataFilter;
import com.feed.me.domain.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeServiceImpl implements HomeService {

    private final EntityManagerFactory em;

    @Autowired
    public HomeServiceImpl(EntityManagerFactory em) {
        this.em = em;
    }

    public List<PersonEntity> getFilteredData(DataFilter dataFilter) {

        EntityManager session = em.createEntityManager();

        try {
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

            Query query = session.createNativeQuery(queryString.toString(), PersonEntity.class);

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


            return resultList
                    .stream()
                    .map(this::entityToData)
                    .collect(Collectors.toList());

        } catch (NoResultException e) {
            return null;
        } finally {
            if (session.isOpen()) session.close();
        }

    }

    private PersonEntity entityToData(PersonEntity personEntity) {
        PersonEntity data = new PersonEntity();

        data.setId(personEntity.getId());
        data.setName(personEntity.getName());
        data.setPerson(personEntity.getPerson());

        return data;
    }

}
