package com.example.back_prueba_tecnica_civa.business;

import com.example.back_prueba_tecnica_civa.entity.BusEntity;
import com.example.back_prueba_tecnica_civa.models.request.BusRequest;
import com.example.back_prueba_tecnica_civa.models.response.BusResponse;
import com.example.back_prueba_tecnica_civa.repository.BusRepository;
import com.example.back_prueba_tecnica_civa.service.BusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusBusiness implements BusService {

    private final BusRepository busRepository;

    @Override
    public List<BusResponse> getAllBuses() {
        return this.busRepository.findAll().stream().map(this::toResponse)
                .toList();
    }

    @Override
    public BusResponse createBus(BusRequest busRequest) {
        return null;
    }

    @Override
    public BusRequest getBusById(Integer busId) {
        return null;
    }

    @Override
    public void deleteBysById(Integer busId) {

    }

    private BusResponse toResponse(BusEntity entity){
        return new ModelMapper().map(entity, BusResponse.class);
    }

}
