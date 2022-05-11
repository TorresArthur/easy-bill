package br.com.alura.easybill.easybill.controller;

import br.com.alura.easybill.easybill.dto.ShowProduct;
import br.com.alura.easybill.easybill.model.Product;
import br.com.alura.easybill.easybill.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@Controller
@RequestMapping("/api")
public class ProductApiController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("produtos")
    @ResponseBody
    public List<ShowProduct> retornaLista(){
        List<Product> lista = productRepository.findAll();
        return ShowProduct.toProductList(lista);
    }

    @GetMapping("/produtos/{id}")
    @ResponseBody
    public ShowProduct retornaProdutoPorId(@PathVariable Long id){
        return ShowProduct.oneProductList(productRepository.findById(id).get());
    }

    @PostMapping("admin/produtos")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> cadastraNovoJson(@RequestBody Product product, UriComponentsBuilder uriBuilder){
        productRepository.save(product);

        URI uri = uriBuilder.path("/api/admin/produtos/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @PutMapping("admin/produtos/{id}")
    public ResponseEntity<Product> atualizar(@PathVariable Long id, @Valid@RequestBody Product product){
        product.atualizar(id, productRepository);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("admin/produtos/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id){
        productRepository.delete(productRepository.findById(id).get());
     return ResponseEntity.ok().build();
    }
}
