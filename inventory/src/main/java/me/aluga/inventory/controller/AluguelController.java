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

import me.aluga.exception.AluguelNotFoundException;
import me.aluga.inventory.entity.Aluguel;
import me.aluga.inventory.services.AluguelService;

@RestController
@RequestMapping("/api")
public class AluguelController {
    AluguelService aluguelservice;
    @Autowired
    public AluguelController(AluguelService aluguelservice) {
        this.aluguelservice = aluguelservice;
    }
    @GetMapping(value="/aluguel")
    public List<Aluguel> getAllAluguels(){
        return aluguelservice.getAllAluguels();
    }           
    @GetMapping(value="/aluguel/{id}")
    public Aluguel getAluguelById(@PathVariable("id") @Min(1) int id) {
        Aluguel std = aluguelservice.findById(id).orElseThrow(()->new AluguelNotFoundException("Aluguel with "+id+" is Not Found!"));
        return std;
    }           
    @PostMapping(value="/aluguel")
    public Aluguel addAluguel(@Valid @RequestBody Aluguel std) {
        return aluguelservice.save(std);
    }           
    @PutMapping(value="/aluguel/devolucao/{id}")
    public Aluguel devolucao(@PathVariable("id") @Min(1) int id) {
       
        return aluguelservice.devolucao(id);   
    }  
    @DeleteMapping(value="/aluguel/{id}")
    public String deleteAluguel(@PathVariable("id") @Min(1) int id) {
        Aluguel std = aluguelservice.findById(id)
                                     .orElseThrow(()->new AluguelNotFoundException("Aluguel with "+id+" is Not Found!"));
        aluguelservice.deleteById(std.getId());
        return "Aluguel with ID :"+id+" is deleted";            
    }
}
