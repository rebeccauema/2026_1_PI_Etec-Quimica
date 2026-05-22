package br.com.pi_2026_1_etec.config;

import java.sql.Connection;

public class ConnectionTest {
        public static void main(String[] args) {

        try {

            Connection conexao = ConexaoBD.obterConexao();

            if (conexao != null) {
                System.out.println("Conexão com MySQL realizada.");
            }

            conexao.close();

        } catch (Exception e) {

            System.out.println("Erro ao conectar no banco:");
            e.printStackTrace();
        }
    }
}
