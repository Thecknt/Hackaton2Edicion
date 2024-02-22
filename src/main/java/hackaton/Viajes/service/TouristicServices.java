package hackaton.Viajes.service;

import hackaton.Viajes.model.TouristicService;
import hackaton.Viajes.repository.TouristicServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TouristicServices implements ITouristicServices{

    @Autowired
    TouristicServiceRepository touristicServiceRepository;

    @Override
    public List<TouristicService> allServices() {
        return touristicServiceRepository.findAll();
    }

    @Override
    public TouristicService findById(Integer idTouristicService) {
        TouristicService touristicService= touristicServiceRepository.findById(idTouristicService).orElse(null);
        return touristicService;
    }

    @Override
    public void save(TouristicService touristicService) {
       touristicServiceRepository.save(touristicService);
    }

    @Override
    public void delete(TouristicService touristicService) {
       touristicServiceRepository.delete(touristicService);
    }
}
