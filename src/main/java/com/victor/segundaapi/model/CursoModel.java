package com.victor.segundaapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "curso")
public class CursoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;

    @Column
    private String nome;

    @Column
    private int totAulas;

    @Column
    private int carga;

    public CursoModel() {

    }


    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTotAulas() {
        return totAulas;
    }

    public void setTotAulas(int totAulas) {
        this.totAulas = totAulas;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }
}
