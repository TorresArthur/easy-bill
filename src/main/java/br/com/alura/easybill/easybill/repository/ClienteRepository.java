package br.com.alura.easybill.easybill.repository;

import br.com.alura.easybill.easybill.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

   List<Cliente> findByEstado(String estado);
}
