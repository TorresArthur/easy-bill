package br.com.alura.easybill.easybill.controller;


import br.com.alura.easybill.easybill.dto.venda.VendaResponse;
import br.com.alura.easybill.easybill.dto.venda.VendaRequest;
import br.com.alura.easybill.easybill.model.ItemVenda;
import br.com.alura.easybill.easybill.model.Venda;
import br.com.alura.easybill.easybill.repository.ClienteRepository;
import br.com.alura.easybill.easybill.repository.ItemVendaRepository;
import br.com.alura.easybill.easybill.repository.ProductRepository;
import br.com.alura.easybill.easybill.repository.VendaRepository;
import br.com.alura.easybill.easybill.service.VendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
public class VendaApiController {

    private final ItemVendaRepository itemVendaRepository;
    private final VendaRepository vendaRepository;
    private final VendaService vendaService;

    public VendaApiController(ItemVendaRepository itemVendaRepository,
                              VendaRepository vendaRepository, VendaService vendaService){
        this.itemVendaRepository = itemVendaRepository;
        this.vendaRepository =  vendaRepository;
        this.vendaService = vendaService;
    }

    @Transactional
    @PostMapping("api/vendas")
    public ResponseEntity<?> cadastraVenda(@RequestBody VendaRequest vendaRequest){
        vendaService.registraVenda(vendaRequest);
        return ResponseEntity.ok().build();
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
