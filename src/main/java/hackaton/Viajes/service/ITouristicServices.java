package hackaton.Viajes.service;

import hackaton.Viajes.model.TouristicService;
import java.util.List;

public interface ITouristicServices {

    public List<TouristicService> allServices();

    public TouristicService findById(Integer idTouristicService);

    public TouristicService save(TouristicService touristicService);

    public void deleteById(Integer idTouristicService);
}
