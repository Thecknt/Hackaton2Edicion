package hackaton.Viajes.service;

import hackaton.Viajes.model.Transportation;
import java.util.List;

public interface ITransportationService {

    public List<Transportation> transportations();

    public Transportation findById(Integer idTransportation);

    public Transportation save(Transportation transportation);

    public void deleteById(Integer idTransportation);
}
