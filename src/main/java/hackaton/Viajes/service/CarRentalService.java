package hackaton.Viajes.service;

import hackaton.Viajes.model.CarRental;
import hackaton.Viajes.repository.CarRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CarRentalService implements ICarRentalService{

    @Autowired
    private CarRentalRepository carRentalRepository;

    @Override
    public List<CarRental> listCar() {
        return carRentalRepository.findAll();
    }

    @Override
    public CarRental findById(Integer idCarRental) {
        CarRental carRental = carRentalRepository.findById(idCarRental).orElse(null);
        return carRental;
    }

    @Override
    public void save(CarRental carRental) {
       carRentalRepository.save(carRental);
    }

    @Override
    public void delete(CarRental carRental) {
        carRentalRepository.delete(carRental);
    }
}
