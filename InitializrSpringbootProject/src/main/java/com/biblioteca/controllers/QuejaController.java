/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.biblioteca.controllers;

import com.biblioteca.domain.Queja;
import com.biblioteca.service.QuejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/quejas")
public class QuejaController {

    @Autowired
    private QuejaService quejaService;

    // Mostrar formulario de quejas
    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("queja", new Queja());
        model.addAttribute("tipos", Queja.TipoQueja.values());
        model.addAttribute("quejas", quejaService.listarQuejas());
        return "quejas";
    }

    // Guardar queja o sugerencia
    @PostMapping("/guardar")
    public String guardarQueja(@ModelAttribute Queja queja) {
        quejaService.guardarQueja(queja);
        return "redirect:/quejas";
    }

    // Marcar una queja como tratada
    @GetMapping("/tratar/{id}")
    public String marcarComoTratada(@PathVariable Long id) {
        quejaService.obtenerQuejaPorId(id).ifPresent(q -> {
            q.setTratado(true);
            quejaService.guardarQueja(q);
        });
        return "redirect:/quejas";
    }

    // Eliminar una queja
    @GetMapping("/eliminar/{id}")
    public String eliminarQueja(@PathVariable Long id) {
        quejaService.eliminarQueja(id);
        return "redirect:/quejas";
    }
}
