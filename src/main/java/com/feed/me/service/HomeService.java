package com.feed.me.service;

import com.feed.me.domain.dto.DataFilter;
import com.feed.me.domain.dto.Person;

import java.util.List;

public interface HomeService {

    List<Person> getFilteredData(DataFilter dataFilter);
}
