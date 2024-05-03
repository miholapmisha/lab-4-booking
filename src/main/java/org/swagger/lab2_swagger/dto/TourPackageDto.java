package org.swagger.lab2_swagger.dto;

import lombok.Data;

import java.util.List;

@Data
public class TourPackageDto {

    private String name;

    private String description;

    private Double price;

    private Integer duration;

    private List<Integer> activitiesIds;

}
