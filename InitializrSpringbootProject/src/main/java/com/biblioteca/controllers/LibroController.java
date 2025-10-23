/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.biblioteca.controllers;

import com.biblioteca.domain.Libro;
import com.biblioteca.service.LibroService;
import com.biblioteca.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private CategoriaService categoriaService;

    // Listar libros
    @GetMapping
    public String listarLibros(Model model) {
        model.addAttribute("libros", libroService.listarLibros());
        return "libros";
    }

    // Mostrar formulario para nuevo libro
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "libro-form";
    }

    // Guardar libro
    @PostMapping("/guardar")
    public String guardarLibro(@ModelAttribute Libro libro) {
        libroService.guardarLibro(libro);
        return "redirect:/libros";
    }

    // Editar libro existente
    @GetMapping("/editar/{id}")
    public String editarLibro(@PathVariable Long id, Model model) {
        libroService.obtenerLibroPorId(id).ifPresent(l -> model.addAttribute("libro", l));
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "libro-form";
    }

    // Eliminar libro
    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return "redirect:/libros";
    }
}

