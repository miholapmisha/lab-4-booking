package org.swagger.lab2_swagger.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.swagger.lab2_swagger.exception.NotFoundException;
import org.swagger.lab2_swagger.model.TourPackage;
import org.swagger.lab2_swagger.repository.TourPackageRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TourPackageService {

    private final TourPackageRepository tourPackageRepository;

    public List<TourPackage> getAll() {
        return tourPackageRepository.findAll();
    }

    @Transactional
    public TourPackage save(TourPackage tourPackage) {
        return tourPackageRepository.save(tourPackage);
    }

    public TourPackage findById(Long id) {
        return tourPackageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tour package not found"));
    }

    @Transactional
    public void deleteById(Long id) {
        tourPackageRepository.deleteById(id);
    }

    @Transactional
    public TourPackage update(Long id, TourPackage tourPackage) {
        TourPackage tourPackageToUpdate = findById(id);
        tourPackageToUpdate.setName(tourPackage.getName());
        tourPackageToUpdate.setActivities(tourPackage.getActivities());
        tourPackageToUpdate.setDuration(tourPackage.getDuration());
        tourPackageToUpdate.setPrice(tourPackage.getPrice());
        tourPackageToUpdate.setDescription(tourPackage.getDescription());

        return tourPackageToUpdate;
    }

}
