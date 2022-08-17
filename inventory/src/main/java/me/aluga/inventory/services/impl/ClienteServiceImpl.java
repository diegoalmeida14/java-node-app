package me.aluga.inventory.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import me.aluga.inventory.entity.Cliente;
import me.aluga.inventory.repository.ClienteRepository;
import me.aluga.inventory.services.ClienteService;
@Service
public class ClienteServiceImpl implements ClienteService {
    ClienteRepository clienteRepo;
    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }
    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepo.findAll();
    }
    @Override
    public Optional<Cliente> findById(int id) {
        return clienteRepo.findById(id);
    }
    @Override
    public Cliente save(Cliente c) {
        return clienteRepo.save(c);

    }
    @Override
    public void deleteById(int id) {
        clienteRepo.deleteById(id);
        
    }
    
}