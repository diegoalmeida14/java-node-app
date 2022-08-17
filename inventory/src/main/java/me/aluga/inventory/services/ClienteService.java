package me.aluga.inventory.services;

import java.util.List;
import java.util.Optional;
import me.aluga.inventory.entity.Cliente;

public interface ClienteService {
    List<Cliente> getAllClientes();
    Optional<Cliente> findById(int id);
    Cliente save(Cliente std);
    void deleteById(int id);
}