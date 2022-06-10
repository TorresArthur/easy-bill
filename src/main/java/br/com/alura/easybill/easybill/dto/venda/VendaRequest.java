package br.com.alura.easybill.easybill.dto.venda;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

public class VendaRequest {
    @NotNull @Positive
    private Long clienteId;
    @NotEmpty
    private List<ItemVendaRequest> itensRequest = new ArrayList<>();


    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<ItemVendaRequest> getItens() {
        return itensRequest;
    }

    public void setItens(List<ItemVendaRequest> itensRequest) {
        this.itensRequest = itensRequest;
    }

    public void adicionaItem(ItemVendaRequest itemVendaRequest) {
        itensRequest.add(itemVendaRequest);
    }
}
