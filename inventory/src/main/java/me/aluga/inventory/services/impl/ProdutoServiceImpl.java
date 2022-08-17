package me.aluga.inventory.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import me.aluga.exception.ProdutoNotFoundException;
import me.aluga.inventory.dto.Search;
import me.aluga.inventory.entity.Produto;
import me.aluga.inventory.repository.ProdutoRepository;
import me.aluga.inventory.services.ProdutoService;
@Service
public class ProdutoServiceImpl implements ProdutoService {
    ProdutoRepository produtoRepo;
    @Autowired
    public ProdutoServiceImpl(ProdutoRepository produtoRepo) {
        this.produtoRepo = produtoRepo;
    }
    @Override
    public List<Produto> getAllProdutos() {
        return produtoRepo.findAll();
    }
    @Override
    public Optional<Produto> findById(int id) {
        return produtoRepo.findById(id);
    }

    @Override
    public List<Produto> search(Search c) {
        //TODO  talvez um re-work com a busca apenas pelo objeto fosse a melhor estrategia

        
        if(c.getNome() == null  && c.getTipo() != null ){//TIPO
            return produtoRepo.findByTipo(c.getTipo());
        }else if (c.getNome() != null  && c.getTipo() == null ){//NOME
            return produtoRepo.findByName(c.getNome());
        }else if (c.getNome() != null  && c.getTipo() != null ){//TIPO E NOME
            return produtoRepo.findByProduto(c.getNome() , c.getTipo());
        }else{
            throw new ProdutoNotFoundException("Tipo e nome são obrigatórios!");
        }


    }

    

    @Override
    public Produto save(Produto c) {
        
        return produtoRepo.save(c);

    }
    @Override
    public void deleteById(int id) {
        produtoRepo.deleteById(id);
        
    }
    @Override
    public List<Produto> findByIds(List<Integer> ids) {
        return  produtoRepo.findByIds(ids);
    }
    
}