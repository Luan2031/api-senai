package com.victor.segundaapi.service;

import com.victor.segundaapi.model.AlunoModel;
import com.victor.segundaapi.repository.AlunoRepository;
import org.mindrot.jbcrypt.BCrypt;
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

    public AlunoModel cadastrarAluno(AlunoModel alunoModel){
        boolean alunoExiste = alunoRepository.existsByEmail(alunoModel.getEmail());

        if(alunoExiste || alunoModel.getSenha() == null || alunoModel.getSenha().isEmpty()){
            return null;
        }
        String senhaCriptografada = BCrypt.hashpw(alunoModel.getSenha(), BCrypt.gensalt());
        alunoModel.setSenha(senhaCriptografada);
        return alunoRepository.save(alunoModel);
    }

    public boolean autenticar(String email, String senha){
        return alunoRepository.existsByEmailAndSenha(email, senha);
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

    public AlunoModel atualizarAluno(Long id, AlunoModel aluno){
        boolean alunoExiste = alunoRepository.existsById(id);

        if(alunoExiste){
            AlunoModel alunoModel = new AlunoModel();
            alunoModel.setIdAluno(id);
            alunoModel.setNome(aluno.getNome());
            alunoModel.setEmail(aluno.getEmail());
            return alunoRepository.save(alunoModel);
        } else{
            return null;
        }
    }
}