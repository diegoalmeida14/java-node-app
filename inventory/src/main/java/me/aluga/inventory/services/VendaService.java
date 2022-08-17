package me.aluga.inventory.services;

import java.util.List;
import java.util.Optional;
import me.aluga.inventory.entity.Venda;

public interface VendaService {
    List<Venda> getAllVendas();
    Optional<Venda> findById(int id);
    Venda save(Venda std);
    void deleteById(int id);
}