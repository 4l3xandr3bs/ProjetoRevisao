package com.revisaospring.springbasic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revisaospring.springbasic.Entity.Produto;
import com.revisaospring.springbasic.Service.ProdutoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/produtoCTR")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;// produtoService é um objeto

    @GetMapping("/main")
    public String telaMain() {
        return "main";
    }

    @GetMapping("/listarProdutos")
    public String telaListarProdutos(Model model) {// model é um objeto
        model.addAttribute("produtos", produtoService.buscarTodos());
        return "listarProdutos";
    }

    @GetMapping("/formCadastrar") /// para chamar a tela
    public String telaCadastraProduto(Model model) {
        model.addAttribute("produto", new Produto());
        return "cadastrarProduto";
    }

    @PostMapping("/salvarProduto")
    public String cadastrarProduto(@ModelAttribute Produto oProduto) { // oProduto é um obleto
        produtoService.cadastraProduto(oProduto);
        return "redirect:/produtoCTR/listarProdutos";
    }

    @GetMapping("/formAlterar/{id}")
    public String telaAlterarProduto(@PathVariable Long id, Model oModel) {
        Produto oProduto = produtoService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        oModel.addAttribute("produtoEditar", oProduto);
        return "cadastrarProduto";
    }

    @PostMapping("/alterarProduto/{id}")
    public String alterarProduto(@PathVariable Long id, @ModelAttribute Produto oProduto) {

        produtoService.alterarProduto(oProduto, id);
        return "redirect:/produtoCTR/listarProdutos";
    }

    @GetMapping("/deletarProduto/{id}")
    public String deletarProduto(@PathVariable Long id) {
        
        produtoService.deletarProduto(id);
        return "redirect:/produtoCTR/listarProdutos";
    }

}
