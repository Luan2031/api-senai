package com.victor.segundaapi.repository;

import com.victor.segundaapi.model.TurmaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmarRepository extends JpaRepository<TurmaModel, Long> {
}
