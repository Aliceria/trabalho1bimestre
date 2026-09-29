package com.mycompany.mavenproject1.controller;

import com.mycompany.mavenproject1.model.Jogo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController //essa classe disponibiliza as rotas da API
@RequestMapping("/jogo")
public class JogoController {

    List<Jogo> jogos = new ArrayList<>(); //simulando nosso banco de dados
    Integer proximoId = 1;

    @GetMapping
    public ResponseEntity<List<Jogo>> listarJogos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) String plataforma) {

        List<Jogo> jogosFiltrados = new ArrayList<>();
        for (Jogo jogo : jogos) {
            //o jogo precisa atender a todos os filtros informados
            boolean nomeIgual = nome == null || jogo.getNome().equalsIgnoreCase(nome);
            boolean generoIgual = genero == null || jogo.getGenero().equalsIgnoreCase(genero);
            boolean plataformaIgual = plataforma == null || jogo.getPlataforma().equalsIgnoreCase(plataforma);

            if (nomeIgual && generoIgual && plataformaIgual) {
                jogosFiltrados.add(jogo);
            }
        }
        return ResponseEntity.ok(jogosFiltrados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogo> buscarJogo(@PathVariable Integer id) {
        for (Jogo jogo : jogos) {
            if (jogo.getId().equals(id)) {
                return ResponseEntity.ok(jogo);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Jogo> cadastrarJogo(@RequestBody(required = false) Jogo jogo) {
        if (jogo == null || jogo.getNome() == null || jogo.getNome().isBlank()
                || jogo.getGenero() == null || jogo.getGenero().isBlank()
                || jogo.getPlataforma() == null || jogo.getPlataforma().isBlank()
                || jogo.getPreco() == null || jogo.getPreco() < 0) {
            return ResponseEntity.badRequest().build();
        } else {
            jogo.setId(proximoId); //gerando o ID manualmente
            proximoId++;
            jogos.add(jogo);
            return ResponseEntity.ok(jogo);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogo> atualizarJogo(@PathVariable Integer id,
            @RequestBody(required = false) Jogo jogoAtualizado) {
        if (jogoAtualizado == null || jogoAtualizado.getNome() == null || jogoAtualizado.getNome().isBlank()
                || jogoAtualizado.getGenero() == null || jogoAtualizado.getGenero().isBlank()
                || jogoAtualizado.getPlataforma() == null || jogoAtualizado.getPlataforma().isBlank()
                || jogoAtualizado.getPreco() == null || jogoAtualizado.getPreco() < 0) {
            return ResponseEntity.badRequest().build();
        }
        for (Jogo jogo : jogos) {
            if (jogo.getId().equals(id)) {
                jogo.setNome(jogoAtualizado.getNome());
                jogo.setGenero(jogoAtualizado.getGenero());
                jogo.setPlataforma(jogoAtualizado.getPlataforma());
                jogo.setPreco(jogoAtualizado.getPreco());
                return ResponseEntity.ok(jogo);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirJogo(@PathVariable Integer id) {
        for (int i = 0; i < jogos.size(); i++) {
            if (jogos.get(i).getId().equals(id)) {
                jogos.remove(i);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}


