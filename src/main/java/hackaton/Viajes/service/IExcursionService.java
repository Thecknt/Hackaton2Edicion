package hackaton.Viajes.service;

import hackaton.Viajes.model.Excursion;
import java.util.List;

public interface IExcursionService {

    public List<Excursion> excursions();

    public Excursion findById(Integer idExcursion);

    public Excursion save(Excursion excursion);

    public void deleteById(Integer idExcursion);
}
