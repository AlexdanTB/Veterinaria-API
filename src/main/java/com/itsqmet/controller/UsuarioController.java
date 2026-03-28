package com.itsqmet.controller;

import com.itsqmet.entity.Usuario;
import com.itsqmet.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    //Leer
    @GetMapping
    public List<Usuario> getUsuarios(){
        return usuarioService.leerUsuarios();
    }

    //Guardar
    @PostMapping("/guardarUsuario")
    public Usuario postUsuario(@RequestBody Usuario usuario){
        return usuarioService.guardarUsuario(usuario);
    }

    //Actualizar
    @PutMapping("/actualizarUsuario/{id}")
    public Usuario putUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        return usuarioService.actualizarUsuario(id, usuario);
    }

    //Eliminar
    @DeleteMapping("/eliminarUsuario/{id}")
    public void deleteUsuario(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
    }
}
