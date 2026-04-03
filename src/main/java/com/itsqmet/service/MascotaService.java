package com.itsqmet.service;

import com.itsqmet.entity.Mascota;
import com.itsqmet.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    //Leer
    public List<Mascota> leerMascotas(){
        return mascotaRepository.findAll();
    }

    //Buscar por id
    public Optional<Mascota> buscarMascotaPorID(Long id){
        return mascotaRepository.findById(id);
    }

    //Metodo guardar
    public Mascota guardarMascota(Mascota mascota){
        return mascotaRepository.save(mascota);
    }
    //Actualizar
    public Mascota actualizarMascota(Long id, Mascota mascota){
        Mascota mascotaEncontrada = buscarMascotaPorID(id).orElseThrow(()->new RuntimeException("Mascota no encontrada"));
        return mascotaRepository.save(mascota);
    }
}
