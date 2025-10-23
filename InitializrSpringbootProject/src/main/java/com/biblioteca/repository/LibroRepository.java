/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.biblioteca.domain.Libro;
import com.biblioteca.domain.Categoria;
import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Buscar libros por categoría
    List<Libro> findByCategoria(Categoria categoria);

    // Buscar por título (contiene texto)
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
}
