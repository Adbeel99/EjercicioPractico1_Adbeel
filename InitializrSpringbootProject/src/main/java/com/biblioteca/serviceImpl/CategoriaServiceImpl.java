/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.biblioteca.serviceImpl;

import com.biblioteca.domain.Categoria;
import com.biblioteca.repository.CategoriaRepository;
import com.biblioteca.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        // DEBUG: Ver qué está llegando
        System.out.println("DEBUG - Guardando categoría: " + categoria);
        System.out.println("DEBUG - ID: " + categoria.getId());
        System.out.println("DEBUG - Nombre: " + categoria.getNombre());
        
        // Si la categoría tiene ID, es una actualización
        if (categoria.getId() != null) {
            System.out.println("DEBUG - Modo: ACTUALIZACIÓN");
            // Buscar la categoría existente en la base de datos
            return categoriaRepository.findById(categoria.getId())
                .map(categoriaExistente -> {
                    // Actualizar solo los campos que han cambiado
                    categoriaExistente.setNombre(categoria.getNombre());
                    categoriaExistente.setDescripcion(categoria.getDescripcion());
                    categoriaExistente.setUpdatedAt(java.time.LocalDateTime.now());
                    return categoriaRepository.save(categoriaExistente);
                })
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + categoria.getId()));
        } else {
            System.out.println("DEBUG - Modo: NUEVA CATEGORÍA");
            // Es una nueva categoría
            return categoriaRepository.save(categoria);
        }
    }

    @Override
    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}
