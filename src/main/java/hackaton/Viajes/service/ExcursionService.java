package hackaton.Viajes.service;

import hackaton.Viajes.model.Excursion;
import hackaton.Viajes.repository.ExcursionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcursionService implements IExcursionService{

    @Autowired
    ExcursionRepository excursionRepository;

    @Override
    public List<Excursion> excursions() {
        return this.excursionRepository.findAll();
    }

    @Override
    public Excursion findById(Integer idExcursion) {
        Excursion excursion = this.excursionRepository.findById(idExcursion).orElse(null);
        return excursion;
    }

    @Override
    public Excursion save(Excursion excursion) {
        return this.excursionRepository.save(excursion);
    }

    @Override
    public void deleteById(Integer idExcursion) {
        this.excursionRepository.deleteById(idExcursion);
    }


}
