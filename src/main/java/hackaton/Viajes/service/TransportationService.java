package hackaton.Viajes.service;

import hackaton.Viajes.model.Transportation;
import hackaton.Viajes.repository.TransportationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TransportationService implements ITransportationService{

    @Autowired
    TransportationRepository transportationRepository;
    @Override
    public List<Transportation> transportations() {
        return transportationRepository.findAll();
    }

    @Override
    public Transportation findById(Integer idTransportation) {
        Transportation transportation = transportationRepository.findById(idTransportation).orElse(null);
        return transportation;
    }

    @Override
    public void save(Transportation transportation) {
        transportationRepository.save(transportation);
    }

    @Override
    public void delete(Transportation transportation) {
        transportationRepository.delete(transportation);
    }
}
