package br.com.pi_2026_1_etec.service;

import br.com.pi_2026_1_etec.model.Usuario;
import br.com.pi_2026_1_etec.repository.UsuarioRepository;

public class UsuarioService {

    private UsuarioRepository repository = new UsuarioRepository();

    public String cadastrar(String nome, String email, String senha) {
        if (nome.isBlank() || email.isBlank() || senha.isBlank()) {
            return "Preencha todos os campos!"; 
        }
        if (senha.length() < 6) {
            return "A senha deve ter pelo menos 6 caracteres!";
        }
        boolean ehAluno     = email.matches("^[^@]+@aluno\\.cps\\.sp\\.gov\\.br$");
        boolean ehProfessor = email.matches("^[^@]+@cps\\.sp\\.gov\\.br$"); 

        if (!ehAluno && !ehProfessor){return "Use um email institucional válido.";}

        try {
            if (repository.emailJaExiste(email)) {
                return "E-mail já cadastrado!";
            }

            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);

            if (ehAluno==true){
                repository.cadastrarAluno(usuario);
            }
            else{
                repository.cadastrarProfessor(usuario);
            }
            
            return "sucesso";

        } catch (Exception e) {
            return "Erro ao cadastrar: " + e.getMessage();
        }
    }
}