package com.example.back_prueba_tecnica_civa.controllers;

import com.example.back_prueba_tecnica_civa.models.response.BusResponse;
import com.example.back_prueba_tecnica_civa.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bus")
public class BusController {

    private final BusService busService;

    @GetMapping("/findAll")
    public ResponseEntity<List<BusResponse>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(busService.getAllBuses(pageable));
    }

    @GetMapping("/findById/{busId}")
    public ResponseEntity<BusResponse> findById(@PathVariable Integer busId) {
        return ResponseEntity.ok(busService.getBusById(busId));
    }

}
