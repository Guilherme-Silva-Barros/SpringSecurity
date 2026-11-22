package com.gestaoProdutos.gestaoProdutos.controller;

import com.gestaoProdutos.gestaoProdutos.entity.Produto;
import com.gestaoProdutos.gestaoProdutos.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public List<Produto> listar(){
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id){
        return produtoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }
    @PostMapping
    public ResponseEntity<Produto> salvar(@Valid @RequestBody Produto produtos){
         Produto produto = produtoRepository.save(produtos);
         return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@Valid @PathVariable Long id,
                                             @Valid @RequestBody Produto produto){
        return produtoRepository.findById(id)
                .map(produtos->{
                    produto.setId(id);
                    produto.setNome(produto.getNome());
                    produto.setQuantidade(produto.getQuantidade());
                    produto.setValor(produto.getValor());
                    return ResponseEntity.ok(produtoRepository.save(produto));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        if(!produtoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        produtoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
