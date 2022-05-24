package br.com.alura.easybill.easybill.controller;


import br.com.alura.easybill.easybill.dto.cliente.ClienteRequest;
import br.com.alura.easybill.easybill.dto.cliente.ClienteResponse;
import br.com.alura.easybill.easybill.model.Cliente;
import br.com.alura.easybill.easybill.repository.ClienteRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class ClienteApiController {

    ClienteRepository repository;

    public ClienteApiController(ClienteRepository repository){
        this.repository = repository;
    }


    @PostMapping("api/clientes")
    public ResponseEntity<ClienteResponse> cadastraCliente(@RequestBody @Valid ClienteRequest request, UriComponentsBuilder uriBuilder){
        Cliente cliente = request.toCliente();
        repository.save(cliente);
        ClienteResponse response = new ClienteResponse();
        response.fromCliente(cliente);

        URI uri = uriBuilder.path("/api/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }


    @GetMapping("admin/clientes")
    public ResponseEntity<List<ClienteResponse>> detalhaClientesPorEstado(@RequestParam(value = "estado", defaultValue = "") String estado){
        List<ClienteResponse> listaResponse = new ArrayList<>();
        ClienteResponse response = new ClienteResponse();
        List<Cliente> listaCliente;

        if(estado.isEmpty()){
            listaCliente = repository.findAll();
        }
        else{
            listaCliente = repository.findByEstado(estado);
        }

        listaResponse = response.fromListaCliente(listaCliente);
        return ResponseEntity.ok(listaResponse);
    }


    @GetMapping("api/clientes/{id}")
    public ResponseEntity<ClienteResponse> detalhaClientePorId(@PathVariable Long id){
        Optional<Cliente> optionalCliente = repository.findById(id);
        if(!optionalCliente.isPresent()){
            return ResponseEntity.badRequest().build();
        }

        Cliente cliente = repository.findById(id).get();
        ClienteResponse response = new ClienteResponse();
        response.fromCliente(cliente);

        return ResponseEntity.ok(response);
    }

}
