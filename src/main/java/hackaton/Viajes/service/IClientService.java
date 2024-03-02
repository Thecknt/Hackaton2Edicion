package hackaton.Viajes.service;

import hackaton.Viajes.model.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientService {

    public List<Client> clients();

    public Client findById(Integer idClient);

    public void save(Client client);

    public void deleteById(Integer idClient);
}
