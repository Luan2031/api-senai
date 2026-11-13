package com.victor.segundaapi.service;

import com.victor.segundaapi.model.CursoModel;
import com.victor.segundaapi.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<CursoModel> listarCurso(){
        List<CursoModel> curso = cursoRepository.findAll();
        return curso;
    }

    public CursoModel cadastrarCurso(CursoModel cursoModel) {
        return cursoRepository.save(cursoModel);
    }

    public Optional<CursoModel> listarPorId(Long id){
        return cursoRepository.findById(id);
    }

    public boolean deletarCurso(Long id){
        boolean cursoExiste = cursoRepository.existsById(id);

        if(cursoExiste){
            cursoRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public CursoModel atualizarCurso (Long id, CursoModel curso){
        boolean CursoExiste = cursoRepository.existsById(id);

        if(CursoExiste){
            CursoModel cursoModel = new CursoModel();
            cursoModel.setIdCurso(id);
            cursoModel.setNome(curso.getNome());
            cursoModel.setCarga(curso.getCarga());
            cursoModel.setTotAulas(curso.getTotAulas());
            return cursoRepository.save(cursoModel);
        }else{
            return null;
        }
    }

}
