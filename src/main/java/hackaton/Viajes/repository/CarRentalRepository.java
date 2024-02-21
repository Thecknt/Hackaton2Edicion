package hackaton.Viajes.repository;

import hackaton.Viajes.model.CarRental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRentalRepository extends JpaRepository<CarRental, Integer> {
}
