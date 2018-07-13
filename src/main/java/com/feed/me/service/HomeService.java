package com.feed.me.service;

import com.feed.me.domain.dto.DataFilter;
import com.feed.me.domain.entity.PersonEntity;

import java.util.List;

public interface HomeService {

    List<PersonEntity> getFilteredData(DataFilter dataFilter);
}
