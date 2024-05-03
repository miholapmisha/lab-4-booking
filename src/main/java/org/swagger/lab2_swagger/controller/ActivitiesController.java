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
import org.swagger.lab2_swagger.model.Activity;
import org.swagger.lab2_swagger.service.ActivitiesService;

import java.util.List;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
@Tag(name = "Activities", description = "Activities endpoints")
public class ActivitiesController {

    private final ActivitiesService activitiesService;

    @Operation(summary = "Get list of all activities")
    @GetMapping
    public ResponseEntity<List<Activity>> getActivities() {
        return ResponseEntity.ok(activitiesService.getAll());
    }

    @Operation(summary = "Get activity by own id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the activity",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Activity.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "activity not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivity(@Parameter(description = "Activity id") @PathVariable Long id) {
        return ResponseEntity.ok(activitiesService.findById(id));
    }

    @Operation(summary = "Create activity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Activity successfully created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Activity.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid body object",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Unable to create activity due conflict",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<Activity> createActivity(@Parameter(description = "Activity body")
                                                   @RequestBody Activity activity) {
        return new ResponseEntity<>(activitiesService.save(activity), HttpStatus.CREATED);
    }

    @Operation(summary = "Update activity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Activity successfully updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Activity.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid body object",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Unable to update activity due conflict",
                    content = @Content)

    })
    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long id,
                                                   @Parameter(description = "Activity body") @RequestBody Activity activity) {
        return new ResponseEntity<>(activitiesService.update(id, activity), HttpStatus.OK);
    }

    @Operation(summary = "Update activity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Activity successfully updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Activity.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid body object",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Unable to update activity due conflict",
                    content = @Content)

    })
    @PatchMapping("/{id}")
    public ResponseEntity<Activity> partialUpdateActivity( @PathVariable Long id,
                                                           @Parameter(description = "Activity body") @RequestBody Activity activity) {
        return new ResponseEntity<>(activitiesService.update(id, activity), HttpStatus.OK);
    }

    @Operation(summary = "Delete activity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted activity",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Activity.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "activity not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActivity(@Parameter(description = "activity id") @PathVariable Long id) {
        activitiesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
