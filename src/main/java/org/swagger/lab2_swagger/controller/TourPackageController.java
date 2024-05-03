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
import org.swagger.lab2_swagger.model.TourPackage;
import org.swagger.lab2_swagger.service.TourPackageService;

import java.util.List;

@RestController
@RequestMapping("/tour-packages")
@RequiredArgsConstructor
@Tag(name = "Tour package", description = "Tour package endpoints")
public class TourPackageController {

    private final TourPackageService tourPackageService;

    @Operation(summary = "Get list of tour packages")
    @GetMapping
    public ResponseEntity<List<TourPackage>> getTourPackages() {
        return new ResponseEntity<>(tourPackageService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get tour package by own id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the tour package",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TourPackage.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Tour package not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TourPackage> getTourPackage(@Parameter(description = "Tour package id") @PathVariable Long id) {
        return new ResponseEntity<>(tourPackageService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Create tour package")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tour package successfully created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TourPackage.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid body object",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Unable to create tour package due conflict",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<TourPackage> createTourPackage(@Parameter(description = "Tour package body")
                                                         @RequestBody TourPackage tourPackage) {
        return new ResponseEntity<>(tourPackageService.save(tourPackage), HttpStatus.CREATED);
    }

    @Operation(summary = "Update tour package")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tour package successfully updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TourPackage.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid body object",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Unable to update tour package due conflict",
                    content = @Content)

    })
    @PutMapping("/{id}")
    public ResponseEntity<TourPackage> updateTourPackage(@Parameter(description = "Tour package id") @PathVariable Long id,
                                                         @Parameter(description = "Tour package body") @RequestBody TourPackage tourPackage) {
        return new ResponseEntity<>(tourPackageService.update(id, tourPackage), HttpStatus.OK);
    }

    @Operation(summary = "Update tour package")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tour package successfully updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TourPackage.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid body object",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Unable to update tour package due conflict",
                    content = @Content)

    })
    @PatchMapping("/{id}")
    public ResponseEntity<TourPackage> partialUpdateTourPackage(@Parameter(description = "Tour package id") @PathVariable Long id,
                                                                @Parameter(description = "Tour package body") @RequestBody TourPackage tourPackage) {
        return new ResponseEntity<>(tourPackageService.update(id, tourPackage), HttpStatus.OK);
    }

    @Operation(summary = "Delete tour package")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted tour package",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TourPackage.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Tour package not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTourPackage(@Parameter(description = "Tour package id") @PathVariable Long id) {
        tourPackageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
