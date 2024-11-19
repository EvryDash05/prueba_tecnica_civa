package com.example.back_prueba_tecnica_civa.business;

import com.example.back_prueba_tecnica_civa.commons.constants.ErrorConstants;
import com.example.back_prueba_tecnica_civa.commons.enums.BusStatus;
import com.example.back_prueba_tecnica_civa.entity.BrandEntity;
import com.example.back_prueba_tecnica_civa.entity.BusEntity;
import com.example.back_prueba_tecnica_civa.exception.custom.BusinessException;
import com.example.back_prueba_tecnica_civa.models.request.BusRequest;
import com.example.back_prueba_tecnica_civa.models.response.BusResponse;
import com.example.back_prueba_tecnica_civa.repository.BrandRepository;
import com.example.back_prueba_tecnica_civa.repository.BusRepository;
import com.example.back_prueba_tecnica_civa.service.BusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusBusiness implements BusService {

    private final BusRepository busRepository;
    private final BrandRepository brandRepository;

    @Override
    public List<BusResponse> getAllBuses(Pageable pageable) {
        int page = this.createPageable(pageable);
        return this.busRepository.findAll(PageRequest.of(page, pageable.getPageSize()))
                .stream().map(this::toResponse)
                .toList();
    }

    @Override
    public BusResponse createBus(BusRequest busRequest) {
        Optional<BrandEntity> findBrand = this.brandRepository.findByBrandName(busRequest.getBrand());
        if (findBrand.isEmpty()) {
            throw new BusinessException(ErrorConstants.RECORD_NOT_FOUND_CODE, ErrorConstants.RECORD_NOT_FOUND_MESSAGE);
        } else {
            BusEntity newBus = BusEntity.builder()
                    .busNumber(busRequest.getBusNumber())
                    .licensePlate(busRequest.getLicensePlate())
                    .createdAt(LocalDateTime.now())
                    .features(busRequest.getFeatures())
                    .brand(findBrand.get())
                    .status(BusStatus.Activo)
                    .build();
            return toResponse(this.busRepository.save(newBus));
        }
    }

    @Override
    public BusResponse getBusById(Integer busId) {
        Optional<BusEntity> findBus = this.busRepository.findById(busId);
        if(findBus.isEmpty()) {
            throw new BusinessException(ErrorConstants.RECORD_NOT_FOUND_CODE, ErrorConstants.RECORD_NOT_FOUND_MESSAGE);
        }
        return toResponse(findBus.get());
    }

    @Override
    public void deleteBysById(Integer busId) {
        Optional<BusEntity> findBus = this.busRepository.findById(busId);
        if(findBus.isEmpty()) {
            throw new BusinessException(ErrorConstants.RECORD_NOT_FOUND_CODE, ErrorConstants.RECORD_NOT_FOUND_MESSAGE);
        }
        this.busRepository.delete(findBus.get());
    }

    private BusResponse toResponse(BusEntity entity){
        return new ModelMapper().map(entity, BusResponse.class);
    }

    private int createPageable(Pageable pageable){
        int page = pageable.getPageNumber();
        if(page != 0){
            page -= 1;
        }
        return page;
    }

}
