package me.aluga.inventory.services.impl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.aluga.exception.ClienteNotFoundException;
import me.aluga.exception.ProdutoNotFoundException;
import me.aluga.inventory.entity.Cliente;
import me.aluga.inventory.entity.Produto;
import me.aluga.inventory.entity.Venda;
import me.aluga.inventory.repository.VendaRepository;
import me.aluga.inventory.services.ClienteService;
import me.aluga.inventory.services.ProdutoService;
import me.aluga.inventory.services.VendaService;
@Service
public class VendaServiceImpl implements VendaService {
    VendaRepository vendaRepo;
    ClienteService clienteService;
    ProdutoService produtoService;
    @Autowired
    public VendaServiceImpl(VendaRepository vendaRepo, ClienteService clienteService,  ProdutoService produtoService) {
        this.vendaRepo = vendaRepo;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }
    @Override
    public List<Venda> getAllVendas() {
        return vendaRepo.findAll();
    }
    @Override
    public Optional<Venda> findById(int id) {
        return vendaRepo.findById(id);
    }
    @Override
    public Venda save(Venda venda) {
        Cliente cliente = clienteService.findById(venda.getCliente().getId()).orElseThrow(()->new ClienteNotFoundException("Cliente with "+venda.getCliente().getId()+" is Not Found!"));
        venda.setCliente(cliente);
        List<Integer> ids = venda.getProdutos().stream().map(x -> x.getId()).collect(Collectors.toList());
        List<Produto> produtos = produtoService.findByIds(ids);
        
        produtos.forEach(produto ->{
            if(produto.getQuantity() == 0){
                throw new ProdutoNotFoundException("quantidade é insuficiente");
            }
            produto.setQuantity(produto.getQuantity()-1);
            produtoService.save(produto);
        });
        venda.setProdutos(produtos);

        return vendaRepo.save(venda);

    }
    @Override
    public void deleteById(int id) {
        vendaRepo.deleteById(id);
        
    }
    
}