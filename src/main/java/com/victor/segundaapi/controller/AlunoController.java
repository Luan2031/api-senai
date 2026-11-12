package com.victor.segundaapi.controller;

import com.victor.segundaapi.model.AlunoModel;
import com.victor.segundaapi.model.ProfessorModel;
import com.victor.segundaapi.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/aluno")
@CrossOrigin(value = "*")

public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<AlunoModel>> listarAlunos(){
        List<AlunoModel> lista = alunoService.listarAlunos();
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<AlunoModel> cadastrarAluno(@RequestBody AlunoModel aluno) {
        AlunoModel alunoSalvo = alunoService.cadastrarAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvo);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletarPorId( @PathVariable Long id) {
        boolean alunoExiste = alunoService.deletarAluno(id);

        if (alunoExiste) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Professor excluido com sucesso"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", "Professor nao existe"));
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> listarPorId( @PathVariable Long id){
        Optional<AlunoModel> aluno = alunoService.listarPorId(id);

        if(aluno.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(aluno);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", "Professor nao encontrado"));
        }
    }

//    @PutMapping(value = "/{id}")
//    public ResponseEntity<?> atualizarAluno( @PathVariable Long id,   @RequestBody AlunoModel alunoModel){
//        ProfessorModel aluno = alunoService.atualizarAluno(id,alunoModel);
//
//        if(aluno != null){
//            return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Professor atualizado com sucesso!"));
//        } else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", "Falha ao atualizar professor. Verifique os dados da requisicao"));
//        }
    }
    