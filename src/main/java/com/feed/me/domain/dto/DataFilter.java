package com.feed.me.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataFilter {

    private Long id;

    private List<String> name;

    private List<String> person;
}
