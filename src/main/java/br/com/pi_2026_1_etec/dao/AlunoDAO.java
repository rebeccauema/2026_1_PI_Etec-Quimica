package br.com.pi_2026_1_etec.dao;

import br.com.pi_2026_1_etec.config.ConexaoBD;
import br.com.pi_2026_1_etec.model.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlunoDAO {

    public ArrayList<Aluno> listarAlunos() {
        ArrayList<Aluno> alunos = new ArrayList<>();

        String sql = """
            SELECT
                p.idpessoa,
                p.nome,
                p.senha,
                a.email,
                a.questoes_respondidas,
                a.taxa_acertos,
                a.professor_email,
                a.pessoa_idpessoa
            FROM aluno a
            INNER JOIN pessoa p ON a.pessoa_idpessoa = p.idpessoa
            ORDER BY p.nome
        """;

        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new Aluno();

                aluno.setId(rs.getInt("idpessoa"));
                aluno.setNome(rs.getString("nome"));
                aluno.setSenha(rs.getString("senha"));
                aluno.setEmail(rs.getString("email"));
                aluno.setQuestoesRespondidas(rs.getInt("questoes_respondidas"));
                aluno.setTaxaAcertos(rs.getInt("taxa_acertos"));
                aluno.setProfessorEmail(rs.getString("professor_email"));
                aluno.setPessoaIdPessoa(rs.getInt("pessoa_idpessoa"));

                alunos.add(aluno);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos: " + e.getMessage());
        }

        return alunos;
    }

    public ArrayList<Aluno> buscarAlunos(String textoBusca) {
        ArrayList<Aluno> alunos = new ArrayList<>();

        String sql = """
            SELECT
                p.idpessoa,
                p.nome,
                p.senha,
                a.email,
                a.questoes_respondidas,
                a.taxa_acertos,
                a.professor_email,
                a.pessoa_idpessoa
            FROM aluno a
            INNER JOIN pessoa p ON a.pessoa_idpessoa = p.idpessoa
            WHERE p.nome LIKE ? OR a.email LIKE ?
            ORDER BY p.nome
        """;

        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String busca = "%" + textoBusca + "%";

            stmt.setString(1, busca);
            stmt.setString(2, busca);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno aluno = new Aluno();

                aluno.setId(rs.getInt("idpessoa"));
                aluno.setNome(rs.getString("nome"));
                aluno.setSenha(rs.getString("senha"));
                aluno.setEmail(rs.getString("email"));
                aluno.setQuestoesRespondidas(rs.getInt("questoes_respondidas"));
                aluno.setTaxaAcertos(rs.getInt("taxa_acertos"));
                aluno.setProfessorEmail(rs.getString("professor_email"));
                aluno.setPessoaIdPessoa(rs.getInt("pessoa_idpessoa"));

                alunos.add(aluno);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar alunos: " + e.getMessage());
        }

        return alunos;
    }

    public Aluno buscarAlunoPorEmail(String email) {
        String sql = """
            SELECT
                p.idpessoa,
                p.nome,
                p.senha,
                a.email,
                a.questoes_respondidas,
                a.taxa_acertos,
                a.professor_email,
                a.pessoa_idpessoa
            FROM aluno a
            INNER JOIN pessoa p ON a.pessoa_idpessoa = p.idpessoa
            WHERE a.email = ?
        """;

        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Aluno aluno = new Aluno();

                aluno.setId(rs.getInt("idpessoa"));
                aluno.setNome(rs.getString("nome"));
                aluno.setSenha(rs.getString("senha"));
                aluno.setEmail(rs.getString("email"));
                aluno.setQuestoesRespondidas(rs.getInt("questoes_respondidas"));
                aluno.setTaxaAcertos(rs.getInt("taxa_acertos"));
                aluno.setProfessorEmail(rs.getString("professor_email"));
                aluno.setPessoaIdPessoa(rs.getInt("pessoa_idpessoa"));

                return aluno;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno por email: " + e.getMessage());
        }

        return null;
    }

    public int contarAlunosAtivos() {
        String sql = "SELECT COUNT(*) AS total FROM aluno";
        try (Connection con = ConexaoBD.obterConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt("total") : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
