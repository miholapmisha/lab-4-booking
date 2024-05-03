package org.swagger.lab2_swagger.dto;

import lombok.Data;
import org.swagger.lab2_swagger.model.Status;

@Data
public class BookingDto {

    private Long id;

    private Long touristsId;

    private Long tourPackageId;

    private Status status;
}
