package br.com.alura.easybill.easybill.repository;


import br.com.alura.easybill.easybill.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
