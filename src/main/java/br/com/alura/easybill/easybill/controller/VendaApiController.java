package br.com.alura.easybill.easybill.controller;


import br.com.alura.easybill.easybill.dto.venda.VendaResponse;
import br.com.alura.easybill.easybill.dto.venda.VendaRequest;
import br.com.alura.easybill.easybill.model.ItemVenda;
import br.com.alura.easybill.easybill.model.Venda;
import br.com.alura.easybill.easybill.repository.ClienteRepository;
import br.com.alura.easybill.easybill.repository.ItemVendaRepository;
import br.com.alura.easybill.easybill.repository.ProductRepository;
import br.com.alura.easybill.easybill.repository.VendaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static br.com.alura.easybill.easybill.model.Status.REALIZADA;

@RestController
public class VendaApiController {

    private ItemVendaRepository itemVendaRepository;
    private VendaRepository vendaRepository;
    private ClienteRepository clienteRepository;
    private ProductRepository productRepository;

    public VendaApiController(ItemVendaRepository itemVendaRepository,
                              VendaRepository vendaRepository, ClienteRepository clienteRepository, ProductRepository productRepository){
        this.itemVendaRepository = itemVendaRepository;
        this.vendaRepository =  vendaRepository;
        this.clienteRepository = clienteRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("api/vendas")
    public void cadastraVenda(@RequestBody VendaRequest vendaRequest){

        Venda venda = new Venda();
        venda.setCliente(vendaRequest.retornaCliente(clienteRepository));
        venda.setDataVenda(LocalDateTime.now());
        venda.setStatus(REALIZADA);

        List<ItemVenda> itens = vendaRequest.retornaListaItemVenda(productRepository);
        itemVendaRepository.saveAll(itens);

        itens.forEach(item ->{
            item.setVenda(venda);
        });
        vendaRepository.save(venda);
    }

    @GetMapping("/api/vendas/{id}")
    public ResponseEntity<VendaResponse> detalhaVenda(@PathVariable Long id){
        Optional<Venda> optionalVenda = vendaRepository.findById(id);
        if(!optionalVenda.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Venda venda = vendaRepository.findById(id).get();
        List<ItemVenda> itens = itemVendaRepository.findItensVendaPorVenda(id);
        VendaResponse vendaResponse = new VendaResponse();
        vendaResponse.fromVenda(venda, itens);
        return ResponseEntity.ok(vendaResponse);
    }
}
