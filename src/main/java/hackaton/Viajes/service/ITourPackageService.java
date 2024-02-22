package hackaton.Viajes.service;

import hackaton.Viajes.model.TourPackage;

import java.util.List;

public interface ITourPackageService {

    public List<TourPackage> tourPackages();

    public TourPackage findById(Integer idTourPackage);

    public void save(TourPackage tourPackage);

    public void delete(TourPackage tourPackage);
}
