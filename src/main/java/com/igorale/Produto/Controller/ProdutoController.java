package com.igorale.Produto.Controller;

import com.igorale.Produto.Model.Produto;
import com.igorale.Produto.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
        private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizar) {
        return produtoRepository.findById(id)
                .map(produto ->{
                    produto.setNome(produtoAtualizar.getNome());
                    produto.setPreco(produtoAtualizar.getPreco());
                    return produtoRepository.save(produto);
                })
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }
}