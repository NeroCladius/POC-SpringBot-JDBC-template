package com.feed.me.rest;

import com.feed.me.domain.dto.DataFilter;
import com.feed.me.domain.dto.Person;
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

    @RequestMapping(value = "/field",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Person>> getFields(DataFilter dataFilter) {
        List<Person> field = service.getFilteredData(dataFilter);
        return new ResponseEntity<>(field, HttpStatus.OK);
    }

}
