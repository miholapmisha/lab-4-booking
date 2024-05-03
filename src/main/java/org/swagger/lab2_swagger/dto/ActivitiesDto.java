package org.swagger.lab2_swagger.dto;

import lombok.Data;

@Data
public class ActivitiesDto {

    private String name;

    private String description;

    private Double price;

    private String location;

    private Long tourPackageId;
}
