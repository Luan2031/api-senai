package com.victor.segundaapi.service;

import com.victor.segundaapi.model.AlunoModel;
import com.victor.segundaapi.model.ProfessorModel;
import com.victor.segundaapi.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public List<AlunoModel> listarAlunos() {
        List<AlunoModel> aluno = alunoRepository.findAll();
        return aluno;
    }

    public AlunoModel cadastrarAluno(AlunoModel alunoModel) {
        return alunoRepository.save(alunoModel);
    }

    public boolean deletarAluno(Long id){
        boolean alunoExiste = alunoRepository.existsById(id);

        if(alunoExiste){
            alunoRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public Optional<AlunoModel> listarPorId(Long id){
        return alunoRepository.findById(id);
    }

//    public AlunoModel atualizarAluno(Long id, AlunoModel aluno){
//        boolean alunoExiste = alunoRepository.existsById(id);
//
//        if(alunoExiste){
//            AlunoModel alunoModel = new AlunoModel();
//            alunoModel.setIdAluno(id);
//            alunoModel.setNome(aluno.getNome());
//            alunoModel.setEmail(aluno.getEmail());
//            alunoModel.setEndereco(aluno.getEndereco());
//            return alunoRepository.save(alunoModel);
//        } else{
//            return null;
//        }
//    }
}