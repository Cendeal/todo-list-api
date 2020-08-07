package com.thoughtworks.springboottodolist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TodoDto {
    private Integer id;
    private boolean status;
    private String text;
    private String title;
    private String type;
    private Date updated;
    private Date created;
}
