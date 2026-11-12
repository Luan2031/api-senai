package com.victor.segundaapi.service;

import com.victor.segundaapi.model.ProfessorModel;
import com.victor.segundaapi.repository.ProfessorRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;


    public List<ProfessorModel> listarProfessores(){
        List<ProfessorModel> professores = professorRepository.findAll();
        return professores;
    }

    public ProfessorModel cadastrarProfessor(ProfessorModel professorModel){
        boolean professorExists = professorRepository.existsByEmail(professorModel.getEmail());

        if(professorExists || professorModel.getSenha() == null || professorModel.getSenha().isEmpty()){
            return null;
        }
        String senhaCriptografada = BCrypt.hashpw(professorModel.getSenha(), BCrypt.gensalt());
        professorModel.setSenha(senhaCriptografada);
        return professorRepository.save(professorModel);
    }

    public boolean autenticar(String email, String senha){
        return professorRepository.existsByEmailAndSenha(email, senha);
    }

    public boolean deletarProfessor(Long id){
        boolean professorExiste = professorRepository.existsById(id);

        if(professorExiste){
            professorRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public Optional<ProfessorModel> listarPorId(Long id){
        return professorRepository.findById(id);
    }


    public ProfessorModel atualizarProfessor(Long id, ProfessorModel professor){
        boolean professorExiste = professorRepository.existsById(id);

        if(professorExiste){
            ProfessorModel professorModel = new ProfessorModel();
            professorModel.setIdProfessor(id);
            professorModel.setNome(professor.getNome());
            professorModel.setEmail(professor.getEmail());
            professorModel.setCpf(professor.getCpf());
            return professorRepository.save(professorModel);
        } else{
            return null;
        }
    }



}
