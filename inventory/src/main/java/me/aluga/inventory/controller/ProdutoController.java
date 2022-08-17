package me.aluga.inventory.controller;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.aluga.exception.ProdutoNotFoundException;
import me.aluga.inventory.dto.Search;
import me.aluga.inventory.entity.Produto;
import me.aluga.inventory.services.ProdutoService;

@RestController
@RequestMapping("/api")
public class ProdutoController {
    ProdutoService produtoService;
    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
    @GetMapping(value="/produtos")
    public List<Produto> getAllProdutos(){
        return produtoService.getAllProdutos();
    }           
    @GetMapping(value="/produto/{id}")
    public Produto getProdutoById(@PathVariable("id") @Min(1) int id) {
        Produto std = produtoService.findById(id).orElseThrow(()->new ProdutoNotFoundException("Produto with "+id+" is Not Found!"));
        return std;
    }           

    @PostMapping(value="/produto/search")
    public  List<Produto> search(@Valid @RequestBody Search std) {
        List<Produto> retunList = produtoService.search(std);
        return retunList;
    }           

    @PostMapping(value="/produto")
    public Produto addProduto(@Valid @RequestBody Produto std) {
        return produtoService.save(std);
    }           
    @PutMapping(value="/produto/{id}")
    public Produto updateProduto(@PathVariable("id") @Min(1) int id, @Valid @RequestBody Produto newstd) {
        Produto stdu = produtoService.findById(id)
                                     .orElseThrow(()->new ProdutoNotFoundException("Produto with "+id+" is Not Found!"));
        stdu.setTipo(newstd.getTipo());
        stdu.setDescription(newstd.getDescription());
        return produtoService.save(stdu);   
    }           
    @DeleteMapping(value="/produto/{id}")
    public String deleteProduto(@PathVariable("id") @Min(1) int id) {
        Produto std = produtoService.findById(id)
                                     .orElseThrow(()->new ProdutoNotFoundException("Produto with "+id+" is Not Found!"));
        produtoService.deleteById(std.getId());
        return "Produto with ID :"+id+" is deleted";            
    }
}
