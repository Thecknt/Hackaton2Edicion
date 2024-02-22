package hackaton.Viajes.service;

import hackaton.Viajes.model.Transportation;

import java.util.List;

public interface ITransportationService {

    public List<Transportation> transportations();

    public Transportation findById(Integer idTransportation);

    public void save(Transportation transportation);

    public void delete(Transportation transportation);
}
