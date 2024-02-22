package hackaton.Viajes.service;

import hackaton.Viajes.model.Client;

import java.util.List;

public interface IClientService {

    public List<Client> clients();

    public Client findById(Integer idClient);

    public void saven(Client client);

    public void delete(Client client);
}
