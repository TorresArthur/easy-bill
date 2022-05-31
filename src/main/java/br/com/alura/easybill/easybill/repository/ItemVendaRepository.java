package br.com.alura.easybill.easybill.repository;
import br.com.alura.easybill.easybill.dto.venda.VendasPorProdutoProjection;
import br.com.alura.easybill.easybill.model.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {

    @Query(value = "SELECT p.nome AS nome, SUM(i.quantidade) AS quantidade FROM product p JOIN itemvenda i WHERE i.product_id = p.id GROUP BY (p.nome)", nativeQuery=true)
    List<VendasPorProdutoProjection> findProdutoPorQuantidade();

    @Query(value = "SELECT * FROM itemvenda i WHERE i.venda_id = :id", nativeQuery = true)
    List<ItemVenda> findItensVendaPorVenda(Long id);
}
