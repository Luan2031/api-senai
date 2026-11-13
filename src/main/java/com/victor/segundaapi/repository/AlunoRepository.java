package com.victor.segundaapi.repository;

import com.victor.segundaapi.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndSenha(String email, String senha);
}
