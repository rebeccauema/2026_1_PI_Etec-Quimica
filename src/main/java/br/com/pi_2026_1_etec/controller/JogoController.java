package br.com.pi_2026_1_etec.controller;

import java.sql.SQLException;
import java.util.List;

import br.com.pi_2026_1_etec.dao.PerguntaDAO;
import br.com.pi_2026_1_etec.model.Pergunta;
import br.com.pi_2026_1_etec.view.telas.TelaQuestao1;

public class JogoController {
    public static void iniciarJogoPorNivel(int idNivel) {
        try {
            PerguntaDAO dao = new PerguntaDAO();// cria o dao pra puxar os atributos do BD

            List<Pergunta> listaPerguntas = dao.listarPorNivel(idNivel);// Cria a lista com perguntas separadas pelo
                                                                        // nivel do ID selecionado

            TelaQuestao1 tela = new TelaQuestao1(listaPerguntas);
            tela.setVisible(true);

        } catch (SQLException e) {
        }
    }
}
