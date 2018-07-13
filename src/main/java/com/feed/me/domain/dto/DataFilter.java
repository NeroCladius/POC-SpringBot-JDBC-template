package com.feed.me.domain.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class DataFilter {

    private Long id;

    private List<String> person;

    private List<String> name;
}
