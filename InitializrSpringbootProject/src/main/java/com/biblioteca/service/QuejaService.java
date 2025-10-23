/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.biblioteca.service;

import com.biblioteca.domain.Queja;
import java.util.List;
import java.util.Optional;

public interface QuejaService {

    List<Queja> listarQuejas();

    Optional<Queja> obtenerQuejaPorId(Long id);

    Queja guardarQueja(Queja queja);

    void eliminarQueja(Long id);

    List<Queja> listarPorTipo(Queja.TipoQueja tipo);

    List<Queja> listarPorTratado(Boolean tratado);
}
