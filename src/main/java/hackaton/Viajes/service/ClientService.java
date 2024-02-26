package hackaton.Viajes.service;

import hackaton.Viajes.model.Client;
import hackaton.Viajes.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientService implements IClientService{

    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Client> clients() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Integer idClient) {
         Client client = clientRepository.findById(idClient).orElse(null);
         return client;
    }

    @Override
    public void save(Client client) {
       clientRepository.save(client);
    }

    @Override
    public void delete(Client client) {
         clientRepository.delete(client);
    }
}
