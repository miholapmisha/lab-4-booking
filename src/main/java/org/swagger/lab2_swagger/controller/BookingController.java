package org.swagger.lab2_swagger.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swagger.lab2_swagger.dto.BookingDto;
import org.swagger.lab2_swagger.model.Booking;
import org.swagger.lab2_swagger.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@Tag(name = "Bookings", description = "Booking endpoints")
public class BookingController {

    private final BookingService bookingService;

    @Operation(summary = "Get list of all bookings")
    @GetMapping
    public ResponseEntity<List<BookingDto>> getBookings() {
        return new ResponseEntity<>(bookingService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get booking by own id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the booking",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBooking(@Parameter(description = "Booking id") @PathVariable Long id) {
        return new ResponseEntity<>(bookingService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Create booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Booking successfully created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid body object",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Unable to create booking due conflict",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@Parameter(description = "Booking body") @RequestBody BookingDto booking) {
        return new ResponseEntity<>(bookingService.save(booking), HttpStatus.CREATED);
    }

    @Operation(summary = "Update booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking successfully updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid body object",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Unable to update booking due conflict",
                    content = @Content)

    })
    @PutMapping("/{id}")
    public ResponseEntity<BookingDto> updateBooking(@Parameter(description = "Booking id") @PathVariable Long id,
                                                 @Parameter(description = "Booking body") @RequestBody BookingDto booking) {
        return new ResponseEntity<>(bookingService.update(id, booking), HttpStatus.OK);
    }

    @Operation(summary = "Delete booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted booking",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@Parameter(description = "Booking id") @PathVariable Long id) {
        bookingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
