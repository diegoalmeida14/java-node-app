package me.aluga.inventory.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.aluga.inventory.dto.Search;
import me.aluga.inventory.entity.Cliente;
import me.aluga.inventory.repository.ClienteRepository;
import me.aluga.inventory.services.ClienteService;
import me.aluga.inventory.services.impl.ClienteServiceImpl;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    ClienteRepository.class
})
public class ClienteServiceImplTest {
    @MockBean
    private ClienteRepository clienteRepository;

    private ClienteService clienteService;

    @BeforeEach
    void beforeEach() {
       this.clienteService = new ClienteServiceImpl(clienteRepository);
    }
    @Test
    void test(){
        System.out.println("teste");
    }
   
    @Test
    void whenSearchReturns(){
        // ARRANGE
        Cliente cliente = new Cliente();
        cliente.setId(1);
        Mockito.when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.of(cliente));
        Search search = new Search();
        search.setNome("");
        // ACT
        Optional<Cliente> clientesReturn = clienteService.findById(cliente.getId());
        // ASSERT
        assertEquals(  clientesReturn, clientesReturn);
    }

    @Test
    void whenAddReturn(){
        // ARRANGE
        Cliente clienteMock = new Cliente();
        clienteMock.setNome("teste");
        clienteMock.setCpf("50");
        clienteMock.setEmail("tipo@gmail.com");
      
        Mockito.when(clienteRepository.save(clienteMock)).thenReturn(clienteMock);
        // ACT
        Cliente clientesReturn = clienteService.save(clienteMock);
        // ASSERT
        assertEquals(clienteMock, clientesReturn);
    }
    
}
