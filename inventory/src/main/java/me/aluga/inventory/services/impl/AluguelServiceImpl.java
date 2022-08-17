package me.aluga.inventory.services.impl;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.aluga.exception.AluguelNotFoundException;
import me.aluga.exception.ClienteNotFoundException;
import me.aluga.exception.ProdutoNotFoundException;
import me.aluga.inventory.entity.Aluguel;
import me.aluga.inventory.entity.Cliente;
import me.aluga.inventory.entity.Produto;
import me.aluga.inventory.repository.AluguelRepository;
import me.aluga.inventory.services.AluguelService;
import me.aluga.inventory.services.ClienteService;
import me.aluga.inventory.services.ProdutoService;
@Service
public class AluguelServiceImpl implements AluguelService {
    AluguelRepository aluguelRepo;
    ClienteService clienteService;
    ProdutoService produtoService;
    @Autowired
    public AluguelServiceImpl(AluguelRepository aluguelRepo,  ClienteService clienteService, ProdutoService produtoService ) {
        this.aluguelRepo = aluguelRepo;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    
    @Override
    public List<Aluguel> getAllAluguels() {
        return aluguelRepo.findAll();
    }
    @Override
    public Optional<Aluguel> findById(int id) {
        return aluguelRepo.findById(id);
    }
    @Override
    public Aluguel save(Aluguel aluguel) {
        Cliente cliente = clienteService.findById(aluguel.getCliente().getId()).orElseThrow(()->new ClienteNotFoundException("Cliente with "+aluguel.getCliente().getId()+" is Not Found!"));
        aluguel.setCliente(cliente);
        List<Integer> ids = aluguel.getProdutos().stream().map(x -> x.getId()).collect(Collectors.toList());
        List<Produto> produtos = produtoService.findByIds(ids);
        
        produtos.forEach(produto ->{
            if(produto.getQuantity() == 0){
                throw new ProdutoNotFoundException("quantidade é insuficiente");
            }
            produto.setQuantity(produto.getQuantity()-1);
            produtoService.save(produto);
        });
        aluguel.setProdutos(produtos);

        
        aluguel.setDtIncio(new Date().getTime());
        aluguel.setDtVencimento(new Date().getTime() + 691200000);
        
        return aluguelRepo.save(aluguel);
    }

    @Override
    public Aluguel devolucao(int aluguelId) {
        
        Aluguel alugelDB = aluguelRepo.findById(aluguelId).orElseThrow(()->new AluguelNotFoundException("Cliente with "+aluguelId+" is Not Found!"));
       
        alugelDB.getProdutos().forEach(produto ->{
            produto.setQuantity(produto.getQuantity()+1);
            produtoService.save(produto);
        });
        alugelDB.setDtDevolucao(new Date().getTime());        
        return aluguelRepo.save(alugelDB);
    }
    @Override
    public void deleteById(int id) {
        aluguelRepo.deleteById(id);
        
    }
    
}