# 🎮 Jogo Educacional de Química Experimental

## 📌 Descrição do Projeto

Este projeto consiste no desenvolvimento de um jogo educacional voltado para alunos do 1º ano do ensino médio técnico em Química.

O objetivo é auxiliar na identificação de materiais de laboratório, suas funções e aplicações em sistemas experimentais, por meio de uma abordagem interativa com perguntas e desafios.

---

## 🎯 Funcionalidades

* Perguntas de múltipla escolha com imagens
* Associação entre materiais e funções
* Níveis progressivos de dificuldade
* Sistema de pontuação
* Ajudas durante o jogo (dicas, eliminar alternativas)
* Cadastro e login de usuários (aluno/professor)
* Cadastro e gerenciamento de questões pelo professor
* Relatórios de desempenho dos alunos

---

## 🧱 Estrutura do Projeto

O projeto segue o padrão de arquitetura em camadas (similar ao MVC), visando organização, manutenção e escalabilidade.

### 📂 `app`

Contém o ponto de entrada da aplicação.

* `Main.java`: classe principal responsável por iniciar o sistema.

---

### 📂 `controller`

Responsável por intermediar a comunicação entre a interface (view) e a lógica de negócio (service).

* `JogoController.java`: controla o fluxo do jogo
* `UsuarioController.java`: gerencia login e cadastro
* `QuestaoController.java`: gerencia as questões

---

### 📂 `service`

Contém as regras de negócio da aplicação.

* `JogoService.java`: lógica do jogo (pontuação, progresso, ajudas)
* `UsuarioService.java`: validação e autenticação de usuários
* `QuestaoService.java`: regras relacionadas às questões

---

### 📂 `model`

Representa as entidades do sistema (dados).

* `Usuario.java`
* `Questao.java`
* `Alternativa.java`
* `MaterialLaboratorio.java`
* `Pontuacao.java`

---

### 📂 `repository` (ou `dao`)

Responsável pelo acesso ao banco de dados (MySQL).

* `UsuarioRepository.java`
* `QuestaoRepository.java`
* `PontuacaoRepository.java`

---

### 📂 `view`

Contém a interface gráfica do sistema (Swing ou JavaFX).

#### 📁 `telas`

* `TelaLogin.java`
* `TelaJogo.java`
* `TelaRelatorio.java`

#### 📁 `componentes`

Componentes reutilizáveis da interface (botões, painéis, etc.).

---

### 📂 `util`

Classes utilitárias reutilizáveis.

* `ConexaoBD.java`: conexão com o banco de dados
* `Validador.java`: validações gerais
* `HashSenha.java`: criptografia de senha

---

### 📂 `config`

Configurações da aplicação.

* `DatabaseConfig.java`: parâmetros de conexão com o banco

---

### 📂 `resources`

Arquivos externos ao código.

#### 📁 `imagens`

* `materiais/`: imagens dos materiais de laboratório
* `icones/`: ícones da interface

#### 📁 `sql`

* `schema.sql`: script de criação do banco de dados

#### 📄 `application.properties`

Arquivo de configuração geral (ex: acesso ao banco).

---

## 🗄️ Banco de Dados

O sistema utiliza MySQL para armazenamento de dados.

### Principais tabelas:

* `usuario`
* `questao`
* `alternativa`
* `material_laboratorio`
* `pontuacao`
* `partida`

---

## 🧪 Observações Importantes

* As imagens dos materiais são armazenadas na pasta `resources` e apenas o caminho é salvo no banco.
* As senhas dos usuários devem ser armazenadas com criptografia.
* O sistema segue boas práticas de separação de responsabilidades.

---

## 🚀 Possíveis Melhorias Futuras

* Ranking global de alunos
* Exportação de relatórios
* Versão web do sistema
* Integração com banco de imagens online
* Sistema de conquistas (gamificação)

---

## 👨‍🏫 Público-Alvo

Alunos do ensino médio técnico em Química, especialmente iniciantes em práticas laboratoriais.

---

## 📄 Licença

Projeto acadêmico sem fins comerciais.
