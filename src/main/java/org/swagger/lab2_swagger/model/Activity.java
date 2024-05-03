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
@Schema(description = "Represents an activity offered by the tourist company")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "activities")
public class Activity {

    @Schema(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Schema(description = "The name of the activity")
    private String name;

    @NotBlank
    @Schema(description = "The description of the activity")
    private String description;

    @NotNull
    @Schema(description = "The price of the activity")
    private Double price;

    @NotBlank
    @Schema(description = "The location of the activity")
    private String location;

    @ManyToOne
    private TourPackage tourPackage;

    @OneToMany
    private List<Booking> bookings = new ArrayList<>();
}
