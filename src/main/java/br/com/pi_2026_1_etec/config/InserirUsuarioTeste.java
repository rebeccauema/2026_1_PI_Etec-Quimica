package br.com.pi_2026_1_etec.config;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InserirUsuarioTeste {

    public static void main(String[] args) {
        try {
            Connection conexao = ConexaoBD.obterConexao();
            System.out.println("Conexão realizada, inserindo usuário de teste...");

            PreparedStatement ps1 = conexao.prepareStatement(
                "INSERT INTO pessoa (idpessoa, nome, senha) VALUES (1, 'Professor Teste', '123456')"
            );
            ps1.executeUpdate();
            System.out.println("Pessoa inserida com sucesso!");

            PreparedStatement ps2 = conexao.prepareStatement(
                "INSERT INTO professor (email, pessoa_idpessoa) VALUES ('professor@teste.com', 1)"
            );
            ps2.executeUpdate();
            System.out.println("Professor inserido com sucesso!");

            System.out.println("Teste concluído!");

            ps1.close();
            ps2.close();
            conexao.close();

        } catch (Exception e) {
            System.out.println("Erro ao inserir usuário:");
            e.printStackTrace();
        }
    }
}
