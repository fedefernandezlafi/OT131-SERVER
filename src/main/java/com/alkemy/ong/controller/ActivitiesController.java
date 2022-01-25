package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivitiesDTO;
import com.alkemy.ong.service.ActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.alkemy.ong.util.Constants.REQ_GET_MAPP_ACTIVITIES;

@RestController
@RequestMapping(REQ_GET_MAPP_ACTIVITIES)
public class ActivitiesController {

    @Autowired
    private ActivitiesService activitiesService;

    /**
     * GET Request
     * @return A list of ActivitiesDTO with all Entities saved in DB
     */
    @GetMapping
    public ResponseEntity<List<ActivitiesDTO>> getAll() {

        return ResponseEntity.ok().body(activitiesService.getAll());
    }

    /**
     * POST Request /activities -
     * Validates sent attributes name and content and saves it in activities table
     * @param dto
     * @return DTO already saved
     */
    @PostMapping
    public ResponseEntity<ActivitiesDTO> save(@Valid @RequestBody ActivitiesDTO dto) {

        ActivitiesDTO updated = activitiesService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(updated);

    }

    /**
     * PUT Request /activities/:id
     * Validates that there is an ActivityEntity related to the received id in activities table.
     * If there is not, returns an error, else it updates the Entity and returns the updated attributes
     * @param id Of the Entity to be updated
     * @param dto With all the new attributes
     * @return The dto already saved
     */
    @PutMapping("/{id}")
    public ResponseEntity<ActivitiesDTO> update(@PathVariable Long id, @Valid @RequestBody ActivitiesDTO dto) {

        ActivitiesDTO result = activitiesService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }
}
