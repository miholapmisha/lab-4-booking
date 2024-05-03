package org.swagger.lab2_swagger.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Schema(description = "Represents a tour package offered by the tourist company")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tour_packages")
public class TourPackage {

    @Schema(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Schema(description = "The name of the tour package")
    private String name;

    @NotBlank
    @Schema(description = "The description of the tour package")
    private String description;

    @NotNull
    @Schema(description = "The price of the tour package")
    private Double price;

    @NotNull
    @Schema(description = "The duration of the tour package in days")
    private Integer duration;

    @Schema(description = "List of activities included in the tour package")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Activity> activities = new ArrayList<>();
}
