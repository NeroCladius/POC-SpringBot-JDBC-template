package com.feed.me.rest;

import com.feed.me.domain.dto.DataFilter;
import com.feed.me.domain.entity.PersonEntity;
import com.feed.me.service.HomeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private final HomeServiceImpl service;

    public HomeController(HomeServiceImpl service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<PersonEntity>> getFields(DataFilter dataFilter) {

        List<PersonEntity> field = service.getFilteredData(dataFilter);

        return new ResponseEntity<>(field, HttpStatus.OK);
    }

}
