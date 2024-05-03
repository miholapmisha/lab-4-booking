package org.swagger.lab2_swagger.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "Represents a booking made by a tourist")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

    @Schema(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Schema(description = "The ID of the tourist making the booking")
    @ManyToOne
    private User tourist;

    @NotNull
    @Schema(description = "The ID of the tour package booked")
    @ManyToOne
    private TourPackage tourPackage;

    @NotNull
    @Schema(description = "The date of the booking")
    private LocalDateTime date;

    @NotNull
    @Schema(description = "The status of the booking")
    @Enumerated(EnumType.STRING)
    private Status status;
}
