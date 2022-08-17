package me.aluga.inventory.controller;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.aluga.exception.VendaNotFoundException;
import me.aluga.inventory.entity.Venda;
import me.aluga.inventory.services.VendaService;

@RestController
@RequestMapping("/api")
public class VendaController {
    VendaService vendaService;
    @Autowired
    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }
    @GetMapping(value="/api/")
    public List<Venda> getAllVendas(){
        return vendaService.getAllVendas();
    }           
    @GetMapping(value="/venda/{id}")
    public Venda getVendaById(@PathVariable("id") @Min(1) int id) {
        Venda std = vendaService.findById(id).orElseThrow(()->new VendaNotFoundException("Venda with "+id+" is Not Found!"));
        return std;
    }           
    @PostMapping(value="/venda")
    public Venda addVenda(@Valid @RequestBody Venda std) {
        return vendaService.save(std);
    }           
}
