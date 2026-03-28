package com.itsqmet.service;

import com.itsqmet.entity.Usuario;
import com.itsqmet.repository.UsuarioRepository;
import com.itsqmet.role.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    //Inyeccion de dependencias por campo
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Metodo leer
    public List<Usuario> leerUsuarios(){
        return usuarioRepository.findAll();
    }

    //Buscar por ID
    public Optional<Usuario> buscarUsuarioPorID(Long id){
        return usuarioRepository.findById(id);
    }

    //Metodo guardar
    public Usuario guardarUsuario(Usuario usuario){
        //Encriptar la cotrasena que el usuario ingrese
        String passwordEncriptada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordEncriptada);
        //Asignar un rol por default
        usuario.setRol(Rol.ROLE_VETERINARIO);
        return usuarioRepository.save(usuario);
    }

    //Actualizar
    public Usuario actualizarUsuario(Long id, Usuario usuario){
        Usuario usuarioEncontrado = buscarUsuarioPorID(id)
                .orElseThrow(()->new RuntimeException("Usuario no existe"));
        usuarioEncontrado.setNombre(usuario.getNombre());
        usuarioEncontrado.setEmail(usuario.getEmail());
        usuarioEncontrado.setRol(usuario.getRol());

        //Validar para que la contraseña se actualice en el
        if(usuario.getPassword()!=null&& !usuario.getPassword().equals(usuarioEncontrado.getPassword())){
            usuarioEncontrado.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        return usuarioRepository.save(usuarioEncontrado);
    }

    //Eliminar
    public void eliminarUsuario(Long id){
        Usuario usuarioEncontrado = buscarUsuarioPorID(id).orElseThrow(()-> new RuntimeException("Usuuario no encontrado"));
        usuarioRepository.delete(usuarioEncontrado);
    }

    //Autenticación
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .authorities("ROLE_"+usuario.getRol().name())
                .build();
    }

}
