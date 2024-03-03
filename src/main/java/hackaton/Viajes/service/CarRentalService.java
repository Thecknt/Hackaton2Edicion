package hackaton.Viajes.service;

import hackaton.Viajes.model.CarRental;
import hackaton.Viajes.repository.CarRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarRentalService implements ICarRentalService{

    @Autowired
    private CarRentalRepository carRentalRepository;

    @Override
    public List<CarRental> listCar() {
        return this.carRentalRepository.findAll();
    }

    @Override
    public CarRental findById(Integer idCarRental) {
        CarRental carRental = this.carRentalRepository.findById(idCarRental).orElse(null);
        return carRental;
    }

    @Override
    public CarRental save(CarRental carRental) {
       return this.carRentalRepository.save(carRental);
    }

    @Override
    public void deleteById(Integer idCarRental) {
        this.carRentalRepository.deleteById(idCarRental);
    }


}
