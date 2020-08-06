package com.thoughtworks.springboottodolist.dto;

import java.util.Date;

public class TodoDto {
    private Integer id;
    private boolean status;
    private String text;
    private String title;
    private String type;
    private Date updated;
    private Date created;

    public TodoDto() {

    }
}
