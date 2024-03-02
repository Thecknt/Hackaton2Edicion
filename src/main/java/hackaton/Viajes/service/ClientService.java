package hackaton.Viajes.service;

import hackaton.Viajes.model.Client;
import hackaton.Viajes.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements IClientService{

    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Client> clients() {
        return this.clientRepository.findAll();
    }

    @Override
    public Client findById(Integer idClient) {
         Client client = this.clientRepository.findById(idClient).orElse(null);
         return client;
    }

    @Override
    public void save(Client client) {

        this.clientRepository.save(client);
    }

    @Override
    public void deleteById(Integer idClient) {
        this.clientRepository.findById(idClient);
    }


}
