package hackaton.Viajes.service;

import hackaton.Viajes.model.CarRental;

import java.util.List;

public interface ICarRentalService {

    public List<CarRental> listCar();

    public CarRental findById(Integer idCarRental);

    public void save(CarRental carRental);


    public void delete(CarRental carRental);
}
