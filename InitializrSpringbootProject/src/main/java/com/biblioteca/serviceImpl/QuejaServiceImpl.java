/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.biblioteca.serviceImpl;

import com.biblioteca.domain.Queja;
import com.biblioteca.repository.QuejaRepository;
import com.biblioteca.service.QuejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuejaServiceImpl implements QuejaService {

    @Autowired
    private QuejaRepository quejaRepository;

    @Override
    public List<Queja> listarQuejas() {
        return quejaRepository.findAll();
    }

    @Override
    public Optional<Queja> obtenerQuejaPorId(Long id) {
        return quejaRepository.findById(id);
    }

    @Override
    public Queja guardarQueja(Queja queja) {
        if (queja.getId() != null) {
            return quejaRepository.findById(queja.getId())
                .map(quejaExistente -> {
                    quejaExistente.setNombreCliente(queja.getNombreCliente());
                    quejaExistente.setEmail(queja.getEmail());
                    quejaExistente.setTelefono(queja.getTelefono());
                    quejaExistente.setTipo(queja.getTipo());
                    quejaExistente.setAsunto(queja.getAsunto());
                    quejaExistente.setMensaje(queja.getMensaje());
                    quejaExistente.setTratado(queja.getTratado());
                    return quejaRepository.save(quejaExistente);
                })
                .orElseThrow(() -> new RuntimeException("Queja no encontrada con id: " + queja.getId()));
        } else {
            return quejaRepository.save(queja);
        }
    }

    @Override
    public void eliminarQueja(Long id) {
        quejaRepository.deleteById(id);
    }

    @Override
    public List<Queja> listarPorTipo(Queja.TipoQueja tipo) {
        return quejaRepository.findByTipo(tipo);
    }

    @Override
    public List<Queja> listarPorTratado(Boolean tratado) {
        return quejaRepository.findByTratado(tratado);
    }
}
