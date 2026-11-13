package com.victor.segundaapi.controller;

import com.victor.segundaapi.model.CursoModel;
import com.victor.segundaapi.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/curso")
@CrossOrigin(value = "*")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<CursoModel>> listarCurso() {
        List<CursoModel> lista = cursoService.listarCurso();
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<CursoModel> cadastrarCurso(@RequestBody CursoModel curso) {
        CursoModel cursoSalvo = cursoService.cadastrarCurso(curso);

        return ResponseEntity.status(HttpStatus.CREATED).body(cursoSalvo);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> listarPorId( @PathVariable Long id){
        Optional<CursoModel> curso = cursoService.listarPorId(id);

        if(curso.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(curso);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", "Turma não encontrado"));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Long id) {
        boolean cursoExiste = cursoService.deletarCurso(id);

        if (cursoExiste) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Curso excluido com sucesso"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", "Curso não existe"));
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> atualizarCurso(@PathVariable Long id, @RequestBody CursoModel cursoModel) {
        CursoModel curso = cursoService.atualizarCurso(id, cursoModel);

        if(curso != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Map.of("mensagem", "Curso atualizado com sucesso!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensagem", "Falha ao atualizar curso."));
        }
    }
}
