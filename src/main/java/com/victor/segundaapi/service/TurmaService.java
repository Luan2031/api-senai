package com.victor.segundaapi.service;

import com.victor.segundaapi.repository.TurmarRepository;
import com.victor.segundaapi.model.TurmaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    TurmarRepository turmaRepository;

    public List<TurmaModel> listarTurmas() {
        List<TurmaModel> turma = turmaRepository.findAll();
        return turma;
    }

    public TurmaModel cadastrarTurma(TurmaModel turmaModel) {
        return turmaRepository.save(turmaModel);
    }

    public Optional<TurmaModel> listarPorId(Long id){
        return turmaRepository.findById(id);
    }

    public boolean deletarTurma(Long id){
        boolean turmaExiste = turmaRepository.existsById(id);

        if(turmaExiste){
            turmaRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public TurmaModel atualizarTurma (Long id, TurmaModel turma){
        boolean TurmaExiste = turmaRepository.existsById(id);

        if(TurmaExiste){
            TurmaModel turmaModel = new TurmaModel();
            turmaModel.setIdTurma(id);
            turmaModel.setSala(turma.getSala());
            turmaModel.setTurno(turma.getTurno());
            return turmaRepository.save(turmaModel);
        }else{
            return null;
        }
    }

}
