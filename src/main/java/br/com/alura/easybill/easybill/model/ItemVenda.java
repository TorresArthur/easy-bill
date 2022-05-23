package br.com.alura.easybill.easybill.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidade;
    private String observacao;
    private BigDecimal precoUnitario;
    private BigDecimal getPrecoUnitarioPromocional;
    private Venda venda;
    private Product product;
}
