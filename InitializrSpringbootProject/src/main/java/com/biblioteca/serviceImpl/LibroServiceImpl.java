/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.biblioteca.serviceImpl;

import com.biblioteca.domain.Libro;
import com.biblioteca.domain.Categoria;
import com.biblioteca.repository.LibroRepository;
import com.biblioteca.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @Override
    public Optional<Libro> obtenerLibroPorId(Long id) {
        return libroRepository.findById(id);
    }

    @Override
    public Libro guardarLibro(Libro libro) {
        if (libro.getId() != null) {
            return libroRepository.findById(libro.getId())
                .map(libroExistente -> {
                    libroExistente.setTitulo(libro.getTitulo());
                    libroExistente.setAutor(libro.getAutor());
                    libroExistente.setIsbn(libro.getIsbn());
                    libroExistente.setDescripcion(libro.getDescripcion());
                    libroExistente.setCategoria(libro.getCategoria());
                    libroExistente.setFechaPublicacion(libro.getFechaPublicacion());
                    libroExistente.setDisponible(libro.getDisponible());
                    libroExistente.setPrecio(libro.getPrecio());
                    libroExistente.setUpdatedAt(java.time.LocalDateTime.now());
                    return libroRepository.save(libroExistente);
                })
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + libro.getId()));
        } else {
            return libroRepository.save(libro);
        }
    }

    @Override
    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }

    @Override
    public List<Libro> buscarPorCategoria(Categoria categoria) {
        return libroRepository.findByCategoria(categoria);
    }

    @Override
    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }
}