package com.example.back_prueba_tecnica_civa.service;

import com.example.back_prueba_tecnica_civa.models.request.BusRequest;
import com.example.back_prueba_tecnica_civa.models.response.BusResponse;

import java.util.List;

public interface BusService {
    List<BusResponse> getAllBuses();
    BusResponse createBus(BusRequest busRequest);
    BusRequest getBusById(Integer busId);
    void deleteBysById(Integer busId);
}
