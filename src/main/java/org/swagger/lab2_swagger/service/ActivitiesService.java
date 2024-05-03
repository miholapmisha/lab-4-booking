package org.swagger.lab2_swagger.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.swagger.lab2_swagger.exception.NotFoundException;
import org.swagger.lab2_swagger.model.Activity;
import org.swagger.lab2_swagger.repository.ActivityRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ActivitiesService {

    private final ActivityRepository activitiesRepository;

    public List<Activity> getAll() {
        return activitiesRepository.findAll();
    }

    @Transactional
    public Activity save(Activity activity) {
        return activitiesRepository.save(activity);
    }

    public Activity findById(Long id) {
        return activitiesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tour package not found"));
    }

    @Transactional
    public void deleteById(Long id) {
        activitiesRepository.deleteById(id);
    }

    @Transactional
    public Activity update(Long id, Activity activity) {
        Activity activityToUpdate = findById(id);
        activityToUpdate.setName(activity.getName());
        activityToUpdate.setDescription(activity.getDescription());
        activityToUpdate.setBookings(activity.getBookings());
        activityToUpdate.setPrice(activity.getPrice());
        activityToUpdate.setLocation(activity.getLocation());
        activityToUpdate.setTourPackage(activity.getTourPackage());

        return activityToUpdate;
    }
}
