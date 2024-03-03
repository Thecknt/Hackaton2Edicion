package hackaton.Viajes.service;

import hackaton.Viajes.model.Transportation;
import hackaton.Viajes.repository.TransportationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransportationService implements ITransportationService{

    @Autowired
    TransportationRepository transportationRepository;
    @Override
    public List<Transportation> transportations() {
        return this.transportationRepository.findAll();
    }

    @Override
    public Transportation findById(Integer idTransportation) {
        Transportation transportation = this.transportationRepository.findById(idTransportation).orElse(null);
        return transportation;
    }

    @Override
    public Transportation save(Transportation transportation) {
        return this.transportationRepository.save(transportation);
    }

    @Override
    public void deleteById(Integer idTransportation) {
        this.transportationRepository.deleteById(idTransportation);
    }


}
