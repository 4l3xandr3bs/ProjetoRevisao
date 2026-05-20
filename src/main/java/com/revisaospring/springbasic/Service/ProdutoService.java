package com.revisaospring.springbasic.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revisaospring.springbasic.Entity.Produto;
import com.revisaospring.springbasic.Repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository; // ingecao de dependencia

    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) { // para usar quando for usar no bd -- Optional => treta exesecao facil
        return produtoRepository.findById(id);
    }

    public Produto cadastraProduto(Produto oProduto) { // salva e altera
        return produtoRepository.save(oProduto);
    }

    public Produto alterarProduto(Produto dadosAtualizar, Long id) { //frufru
        Produto produtoBuscado = buscarPorId(id).orElseThrow( // busca por objeto
                () -> new IllegalArgumentException("Produto Não Encontrado"));

        produtoBuscado.setNome(dadosAtualizar.getNome());
        produtoBuscado.setCategoria(dadosAtualizar.getCategoria());
        produtoBuscado.setValor(dadosAtualizar.getValor());

        return produtoRepository.save(produtoBuscado);
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

}
