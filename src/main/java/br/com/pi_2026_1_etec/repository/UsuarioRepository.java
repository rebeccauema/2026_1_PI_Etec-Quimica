package br.com.pi_2026_1_etec.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.pi_2026_1_etec.model.Usuario;
import br.com.pi_2026_1_etec.util.ConexaoBD;

public class UsuarioRepository {

    public void cadastrar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection con = ConexaoBD.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.executeUpdate();
        }
    }   //Insere um novo usuário no banco de dados e verifica se o email já está cadastrado

    public boolean emailJaExiste(String email) throws SQLException {
        String sql = "SELECT id FROM usuario WHERE email = ?";

        try (Connection con = ConexaoBD.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next(); 
        }
    }   
    public Usuario buscarPorEmail(String email) throws SQLException {
    String sql = "SELECT id, nome, email, senha FROM usuario WHERE email = ?";//prepara o comando pro sql
     try (Connection con = ConexaoBD.getConexao();
         PreparedStatement ps = con.prepareStatement(sql)) {
             ps.setString(1, email);//troca o placeholder pelo email recebido
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Usuario u = new Usuario();
            u.setId(rs.getInt("id"));
            u.setNome(rs.getString("nome"));
            u.setEmail(rs.getString("email"));
            u.setSenha(rs.getString("senha"));
            return u;
             } //retorna o usuario com os atributos puxados do sql
        return null;//se não ter usuario no BD, retorna nulo.
            }
        }
    }

