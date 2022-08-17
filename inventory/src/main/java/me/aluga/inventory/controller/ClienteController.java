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

import me.aluga.exception.ClienteNotFoundException;
import me.aluga.inventory.entity.Cliente;
import me.aluga.inventory.services.ClienteService;

@RestController
@RequestMapping("/api")
public class ClienteController {
    ClienteService clienteservice;
    @Autowired
    public ClienteController(ClienteService clienteservice) {
        this.clienteservice = clienteservice;
    }
    @GetMapping(value="/clientes")
    public List<Cliente> getAllClientes(){
        return clienteservice.getAllClientes();
    }           
    @GetMapping(value="/clientes/{id}")
    public Cliente getClienteById(@PathVariable("id") @Min(1) int id) {
        Cliente std = clienteservice.findById(id).orElseThrow(()->new ClienteNotFoundException("Cliente with "+id+" is Not Found!"));
        return std;
    }           
    @PostMapping(value="/clientes")
    public Cliente addCliente(@Valid @RequestBody Cliente std) {
        return clienteservice.save(std);
    }           
    @PutMapping(value="/clientes/{id}")
    public Cliente updateCliente(@PathVariable("id") @Min(1) int id, @Valid @RequestBody Cliente newstd) {
        Cliente stdu = clienteservice.findById(id)
                                     .orElseThrow(()->new ClienteNotFoundException("Cliente with "+id+" is Not Found!"));
        stdu.setNome(newstd.getNome());
        stdu.setEmail(newstd.getEmail());
        return clienteservice.save(stdu);   
    }           
    @DeleteMapping(value="/clientes/{id}")
    public String deleteCliente(@PathVariable("id") @Min(1) int id) {
        Cliente std = clienteservice.findById(id)
                                     .orElseThrow(()->new ClienteNotFoundException("Cliente with "+id+" is Not Found!"));
        clienteservice.deleteById(std.getId());
        return "Cliente with ID :"+id+" is deleted";            
    }
}
