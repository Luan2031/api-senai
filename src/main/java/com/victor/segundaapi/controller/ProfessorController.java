package com.victor.segundaapi.controller;

import com.victor.segundaapi.model.ProfessorModel;
import com.victor.segundaapi.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.MarshalledObject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/professor")
@CrossOrigin(value = "*")

public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody ProfessorModel professor){
        boolean sucesso = professorService.autenticar(professor.getEmail(), professor.getSenha());

        if(sucesso){
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "professor autenticado com sucesso"));
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("mensagem", "email ou senha invalido"));
        }
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<List<ProfessorModel>> listarProfessores() {
        List<ProfessorModel> lista = professorService.listarProfessores();
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<?> cadastrarProfessor(@RequestBody ProfessorModel professor) {
        ProfessorModel professorSalvo = professorService.cadastrarProfessor(professor);
        if (professorSalvo == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("mesagem", "email ja cadastrado ou senha vazia"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(professorSalvo);
    }



    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletarPorId( @PathVariable Long id) {
        boolean professorExiste = professorService.deletarProfessor(id);

        if (professorExiste) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Professor excluido com sucesso"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", "Professor nao existe"));
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> listarPorId( @PathVariable Long id){
        Optional<ProfessorModel> professor = professorService.listarPorId(id);

        if(professor.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(professor);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", "Professor nao encontrado"));
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> atualizarProfessor( @PathVariable Long id,   @RequestBody ProfessorModel professorModel){
        ProfessorModel professor = professorService.atualizarProfessor(id,professorModel);

        if(professor != null){
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Professor atualizado com sucesso!"));
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", "Falha ao atualizar professor. Verifique os dados da requisicao"));
        }
    }
}