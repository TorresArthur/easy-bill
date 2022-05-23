package br.com.alura.easybill.easybill.repository;

import br.com.alura.easybill.easybill.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
