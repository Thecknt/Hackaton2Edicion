package hackaton.Viajes.service;

import hackaton.Viajes.model.TourPackage;
import hackaton.Viajes.repository.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TourPackageService implements ITourPackageService{

    @Autowired
    TourPackageRepository tourPackageRepository;

    @Override
    public List<TourPackage> tourPackages() {
        return this.tourPackageRepository.findAll();
    }

    @Override
    public TourPackage findById(Integer idTourPackage) {
        TourPackage tourPackage = this.tourPackageRepository.findById(idTourPackage).orElse(null);
        return tourPackage;
    }

    @Override
    public TourPackage save(TourPackage tourPackage) {
        return this.tourPackageRepository.save(tourPackage);
    }

    @Override
    public void deleteById(Integer idTourPackage) {
        this.tourPackageRepository.deleteById(idTourPackage);
    }


}
