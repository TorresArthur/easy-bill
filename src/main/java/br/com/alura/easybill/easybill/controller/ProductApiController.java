package br.com.alura.easybill.easybill.controller;

import br.com.alura.easybill.easybill.dto.produto.CadastroRequest;
import br.com.alura.easybill.easybill.dto.produto.CadastroResponse;
import br.com.alura.easybill.easybill.dto.ShowProduct;
import br.com.alura.easybill.easybill.model.Product;
import br.com.alura.easybill.easybill.validator.VerficaPrecoPromocional;
import br.com.alura.easybill.easybill.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class ProductApiController {

    private final ProductRepository productRepository;
    private VerficaPrecoPromocional verificador;


    public ProductApiController(ProductRepository productRepository, VerficaPrecoPromocional verificador){
        this.productRepository = productRepository;
        this.verificador = verificador;
    }


    @GetMapping("produtos") @Cacheable(value = "listaDeProdutos")
    public Page<ShowProduct> retornaLista(@RequestParam(value = "pagina", defaultValue = "") Integer pagina){

        if(pagina == null){
            pagina = 0;
        }
        Pageable pageable = PageRequest.of(pagina, 5, Sort.by(Sort.Direction.DESC, "nome"));

        return productRepository.findAll(pageable).map(ShowProduct::toShowProduct);
    }


    @PostMapping("aW52YWxpZGEgY2FjaGUgbGlzdGFnZW0gcHJvZHV0b3M")
    @CacheEvict(value = "listaDeProdutos", allEntries = true)
    public void invalidaCache(){
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<ShowProduct> retornaProdutoPorId(@PathVariable Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ShowProduct.toShowProduct(optionalProduct.get()));
    }

    @PostMapping("admin/produtos")
    public ResponseEntity<CadastroResponse> cadastraNovoJson(@RequestBody @Valid CadastroRequest cadastroRequest, UriComponentsBuilder uriBuilder)
            throws MethodArgumentNotValidException {

       BeanPropertyBindingResult result = new BeanPropertyBindingResult(cadastroRequest, "request");
        verificador.verifica(cadastroRequest, result);

        if(result.hasErrors()){
            throw new MethodArgumentNotValidException(null, result);
        }

        Product product = cadastroRequest.toProduto();
        productRepository.save(product);

        CadastroResponse cadastroResponse = product.toCadastroResponse();


        URI uri = uriBuilder.path("/api/admin/produtos/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(cadastroResponse);
    }

    @PutMapping("admin/produtos/{id}")
    public ResponseEntity<CadastroResponse> atualizar(@PathVariable Long id, @Valid @RequestBody Product product){
        product.atualizar(id, productRepository);
        CadastroResponse response = product.toCadastroResponse();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("admin/produtos/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        productRepository.deleteById(id);
     return ResponseEntity.ok().build();
    }
}
