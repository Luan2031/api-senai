package com.victor.segundaapi.controller;

import com.victor.segundaapi.model.TurmaModel;
import com.victor.segundaapi.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(value = "/turma")
@Controller(value = "*")
public class TurmaController {

    @Autowired
    TurmaService turmaService;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<TurmaModel>> listarTurma() {
        List<TurmaModel> lista = turmaService.listarTurmas();
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<TurmaModel> cadastrarTurma(@RequestBody TurmaModel turma) {
        TurmaModel turmaSalva = turmaService.cadastrarTurma(turma);

        return ResponseEntity.status(HttpStatus.CREATED).body(turmaSalva);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> listarPorId( @PathVariable Long id){
        Optional<TurmaModel> turma = turmaService.listarPorId(id);

        if(turma.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(turma);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", "Turma não encontrado"));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Long id) {
        boolean turmaExiste = turmaService.deletarTurma(id);

        if (turmaExiste) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Turma excluido com sucesso"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", "Turma não existe"));
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> atualizarTurma( @PathVariable Long id,   @RequestBody TurmaModel turmaModel) {
        TurmaModel turma = turmaService.atualizarTurma(id,turmaModel);

        if(turma != null){
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Turma atualizada com sucesso!"));
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", "Falha ao atualizar turma. Verifique os dados da requisição"));
        }
    }
}