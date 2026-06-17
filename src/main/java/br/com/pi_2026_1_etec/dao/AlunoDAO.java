package br.com.pi_2026_1_etec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.pi_2026_1_etec.config.ConexaoBD;
import br.com.pi_2026_1_etec.model.Aluno;

public class AlunoDAO {


    
public ArrayList<Aluno> listarAlunos() {
    ArrayList<Aluno> lista = new ArrayList<>();
    String sql = "SELECT p.nome, a.email, a.questoes_respondidas, a.taxa_acertos, a.pessoa_idpessoa "
               + "FROM aluno a "
               + "LEFT JOIN pessoa p ON a.pessoa_idpessoa = p.idpessoa"; 

    try (java.sql.Connection con = br.com.pi_2026_1_etec.config.ConexaoBD.obterConexao();
         java.sql.PreparedStatement ps = con.prepareStatement(sql);
         java.sql.ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Aluno aluno = new Aluno();
            
            // Define o nome vindo da tabela Pessoa e o email do Aluno
            aluno.setNome(rs.getString("nome"));
            aluno.setEmail(rs.getString("email"));
            int respondidas = rs.getInt("questoes_respondidas");
            int acertos = rs.getInt("taxa_acertos"); 
            aluno.setQuestoesRespondidas(respondidas);
            aluno.setTaxaAcertos(acertos);
            aluno.setErros(respondidas - acertos);//atb de erro é local, então pode so fzr uma sub do acerto menos as repsondidas.
            lista.add(aluno);
        }
        
    } catch (java.sql.SQLException e) {
        System.out.println("ERRO AO CARREGAR ALUNOS: " + e.getMessage());
        e.printStackTrace();
    }
    
    return lista;
}
// Método auxiliar simples para buscar o nome do aluno sem quebrar a query principal
private String buscarNomePorIdPessoa(java.sql.Connection con, int idPessoa) {
    String sql = "SELECT nome FROM pessoa WHERE idpessoa = ?"; // Ajuste "idpessoa" se no seu banco for "id"
    try (java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idPessoa);
        try (java.sql.ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("nome");
            }
        }
    } catch (java.sql.SQLException e) {
        System.out.println("Erro ao buscar nome: " + e.getMessage());
    }
    return null;
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

        try (
            Connection conn = ConexaoBD.obterConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            String busca = "%" + textoBusca + "%";

            stmt.setString(1, busca);
            stmt.setString(2, busca);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Aluno aluno = montarAluno(rs);
                    alunos.add(aluno);
                }
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

        try (
            Connection conn = ConexaoBD.obterConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return montarAluno(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno por email: " + e.getMessage());
        }

        return null;
    }

    private Aluno montarAluno(ResultSet rs) throws SQLException {
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

public void registrarResposta(int idPessoa, boolean acertou) {
    // COALESCE evita que o valor suma caso o banco esteja como NULL
    String sql = "UPDATE aluno SET "
               + " questoes_respondidas = COALESCE(questoes_respondidas, 0) + 1, "
               + " taxa_acertos = COALESCE(taxa_acertos, 0) + " + (acertou ? "1" : "0")
               + " WHERE pessoa_idpessoa = ?";

    try (java.sql.Connection con = br.com.pi_2026_1_etec.config.ConexaoBD.obterConexao();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setInt(1, idPessoa);
        ps.executeUpdate();
        
    } catch (java.sql.SQLException e) {
        System.out.println("Erro ao registrar resposta: " + e.getMessage());
    }
}

    public double obterTaxaAcertoGeral() {
    String sql = "SELECT SUM(respostas_corretas) as corretas, SUM(perguntas_respondidas) as totais FROM aluno";//puxa acertos e total respondido do db
    try (Connection con = ConexaoBD.obterConexao();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int totais = rs.getInt("totais");
            int corretas = rs.getInt("corretas");
            
            if (totais > 0) {//evita divisao por 0
                return ((double) corretas / totais) * 100;
            }
        }
    } catch (SQLException e) {
        System.err.println("Erro ao calcular taxa de acerto geral: " + e.getMessage());
    }
    return 0.0;
}
}
