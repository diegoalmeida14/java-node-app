package me.aluga.inventory.services;

import java.util.List;
import java.util.Optional;
import me.aluga.inventory.entity.Aluguel;

public interface AluguelService {
    List<Aluguel> getAllAluguels();
    Optional<Aluguel> findById(int id);
    Aluguel save(Aluguel std);
    Aluguel devolucao(int aluguelId);

    void deleteById(int id);
}