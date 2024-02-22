package hackaton.Viajes.service;

import hackaton.Viajes.model.TourPackage;
import hackaton.Viajes.repository.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TourPackageService implements ITourPackageService{

    @Autowired
    TourPackageRepository tourPackageRepository;

    @Override
    public List<TourPackage> tourPackages() {
        return tourPackageRepository.findAll();
    }

    @Override
    public TourPackage findById(Integer idTourPackage) {
        TourPackage tourPackage = tourPackageRepository.findById(idTourPackage).orElse(null);
        return tourPackage;
    }

    @Override
    public void save(TourPackage tourPackage) {
        tourPackageRepository.save(tourPackage);
    }

    @Override
    public void delete(TourPackage tourPackage) {
        tourPackageRepository.delete(tourPackage);
    }
}
