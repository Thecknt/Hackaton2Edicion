package hackaton.Viajes.repository;

import hackaton.Viajes.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    Optional<UserEntity>findByUsername(String username);

    public Optional<UserEntity> findById(Integer idUserentity);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
