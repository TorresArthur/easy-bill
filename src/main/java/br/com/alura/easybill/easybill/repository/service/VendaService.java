package br.com.alura.easybill.easybill.repository.service;

import br.com.alura.easybill.easybill.dto.venda.VendaRequest;
import br.com.alura.easybill.easybill.model.Cliente;
import br.com.alura.easybill.easybill.model.ItemVenda;
import br.com.alura.easybill.easybill.model.Status;
import br.com.alura.easybill.easybill.model.Venda;
import br.com.alura.easybill.easybill.repository.ClienteRepository;
import br.com.alura.easybill.easybill.repository.ItemVendaRepository;
import br.com.alura.easybill.easybill.repository.ProductRepository;
import br.com.alura.easybill.easybill.repository.VendaRepository;
import br.com.alura.easybill.easybill.validator.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    private final ClienteRepository clienteRepository;
    private final ProductRepository productRepository;
    private final VendaRepository vendaRepository;
    private final ItemVendaRepository itemVendaRepository;

    public VendaService(ClienteRepository clienteRepository, ProductRepository productRepository, VendaRepository vendaRepository, ItemVendaRepository itemVendaRepository) {
        this.clienteRepository = clienteRepository;
        this.productRepository = productRepository;
        this.vendaRepository = vendaRepository;
        this.itemVendaRepository = itemVendaRepository;
    }


    public Venda registraVenda(VendaRequest vendaRequest) {
        Cliente cliente = clienteRepository.findById(vendaRequest.getClienteId())
                .orElseThrow(() -> new NotFoundException("Não encontrado cliente: " + vendaRequest.getClienteId()));
        Venda venda = getVenda(cliente);
        vendaRepository.save(venda);

        List<ItemVenda> itens = getItemVendas(vendaRequest, venda);
        itemVendaRepository.saveAll(itens);
        return venda;
    }

    private List<ItemVenda> getItemVendas(VendaRequest vendaRequest, Venda venda) {
        List<ItemVenda> itens = new ArrayList<>();
        vendaRequest.getItens().forEach(itemRequest ->
                itens.add(itemRequest.toItemVenda(productRepository, venda))

        );
        return itens;
    }

    private Venda getVenda(Cliente cliente) {
        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setDataVenda(LocalDateTime.now());
        venda.setStatus(Status.REALIZADA);
        return venda;
    }

}

