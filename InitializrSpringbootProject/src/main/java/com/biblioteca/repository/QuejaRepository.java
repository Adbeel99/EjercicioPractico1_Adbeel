/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.biblioteca.domain.Queja;
import java.util.List;

@Repository
public interface QuejaRepository extends JpaRepository<Queja, Long> {

    // Buscar quejas o sugerencias por tipo
    List<Queja> findByTipo(Queja.TipoQueja tipo);

    // Buscar si ya fue tratada o no
    List<Queja> findByTratado(Boolean tratado);
}

