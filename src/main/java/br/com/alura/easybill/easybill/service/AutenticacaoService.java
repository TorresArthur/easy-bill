package br.com.alura.easybill.easybill.service;

import br.com.alura.easybill.easybill.model.Usuario;
import br.com.alura.easybill.easybill.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {
    private UsuarioRepository usuarioRepository;

    public AutenticacaoService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(username);
        System.out.println(usuarioOptional.get());
        if (usuarioOptional.isPresent()) return usuarioOptional.get();


        throw new UsernameNotFoundException("Dados Inválidos");

    }
}
