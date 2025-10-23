/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.biblioteca.service;

import com.biblioteca.domain.Libro;
import com.biblioteca.domain.Categoria;
import java.util.List;
import java.util.Optional;

public interface LibroService {

    List<Libro> listarLibros();

    Optional<Libro> obtenerLibroPorId(Long id);

    Libro guardarLibro(Libro libro);

    void eliminarLibro(Long id);

    List<Libro> buscarPorCategoria(Categoria categoria);

    List<Libro> buscarPorTitulo(String titulo);
}
