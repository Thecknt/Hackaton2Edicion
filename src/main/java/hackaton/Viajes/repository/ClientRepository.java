package hackaton.Viajes.repository;

import hackaton.Viajes.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
