package com.example.back_prueba_tecnica_civa.service;

import com.example.back_prueba_tecnica_civa.models.request.BusRequest;
import com.example.back_prueba_tecnica_civa.models.response.BusResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BusService {
    List<BusResponse> getAllBuses(Pageable pageable);
    BusResponse createBus(BusRequest busRequest);
    BusResponse getBusById(Integer busId);
    void deleteBysById(Integer busId);
}
