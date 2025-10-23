/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.biblioteca.service;

import com.biblioteca.domain.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    List<Categoria> listarCategorias();

    Optional<Categoria> obtenerCategoriaPorId(Long id);

    Categoria guardarCategoria(Categoria categoria);

    void eliminarCategoria(Long id);
}
