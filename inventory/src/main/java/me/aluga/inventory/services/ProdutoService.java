package me.aluga.inventory.services;

import java.util.List;
import java.util.Optional;

import me.aluga.inventory.dto.Search;
import me.aluga.inventory.entity.Produto;

public interface ProdutoService {
    List<Produto> getAllProdutos();
    Optional<Produto> findById(int id);
    List<Produto> findByIds(List<Integer> ids);
    List<Produto> search(Search c);
    


    Produto save(Produto std);
    void deleteById(int id);
}
