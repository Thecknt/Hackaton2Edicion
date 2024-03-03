package hackaton.Viajes.service;

import hackaton.Viajes.model.TouristicService;
import hackaton.Viajes.repository.TouristicServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TouristicServices implements ITouristicServices{

    @Autowired
    TouristicServiceRepository touristicServiceRepository;

    @Override
    public List<TouristicService> allServices() {
        return this.touristicServiceRepository.findAll();
    }

    @Override
    public TouristicService findById(Integer idTouristicService) {
        TouristicService touristicService= this.touristicServiceRepository.findById(idTouristicService).orElse(null);
        return touristicService;
    }

    @Override
    public TouristicService save(TouristicService touristicService) {
      return this.touristicServiceRepository.save(touristicService);
    }

    @Override
    public void deleteById(Integer idTouristicService) {
        this.touristicServiceRepository.deleteById(idTouristicService);
    }


}
