package hackaton.Viajes.service;

import hackaton.Viajes.model.Excursion;
import hackaton.Viajes.repository.ExcursionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExcursionService implements IExcursionService{

    @Autowired
    ExcursionRepository excursionRepository;

    @Override
    public List<Excursion> excursions() {
        return excursionRepository.findAll();
    }

    @Override
    public Excursion findById(Integer idExcursion) {
        Excursion excursion = excursionRepository.findById(idExcursion).orElse(null);
        return excursion;
    }

    @Override
    public void save(Excursion excursion) {
        excursionRepository.save(excursion);
    }

    @Override
    public void delete(Excursion excursion) {
       excursionRepository.delete(excursion);
    }
}
