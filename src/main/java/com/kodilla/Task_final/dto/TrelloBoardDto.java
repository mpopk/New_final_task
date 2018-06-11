package com.kodilla.Task_final.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class TrelloBoardDto {

    private String id;
    private String name;


    public TrelloBoardDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public TrelloBoardDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
