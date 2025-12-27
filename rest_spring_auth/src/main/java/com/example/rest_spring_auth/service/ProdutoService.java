package com.example.rest_spring_auth.service;

import org.springframework.stereotype.Service;

import com.example.rest_spring_auth.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

import com.example.rest_spring_auth.exceptions.RecursoNaoEncontradoException;
import com.example.rest_spring_auth.model.Produto;


@Service
public class ProdutoService {
    

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id){
        return produtoRepository.findById(id)
        .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com ID " + id + " não encontrado"));
    }

    public Produto salvaProduto (Produto produto){
        return produtoRepository.save(produto);
    }

    public void deletarProduto (Long id){
        if(!produtoRepository.existsById(id)){
            throw new RecursoNaoEncontradoException("Produto com ID " + id + " não encontrado");
        }
        produtoRepository.deleteById(id);
    }

}
