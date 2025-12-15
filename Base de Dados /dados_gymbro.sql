-- =================================================================
-- SCRIPT CORRIGIDO E FINAL: find_partners_db
-- =================================================================

-- 1. CONFIGURAÇÃO INICIAL (Nuclear)
-- -----------------------------------------------------------------
SET FOREIGN_KEY_CHECKS = 0; -- Desliga verificação para limpar tudo sem erros
CREATE DATABASE IF NOT EXISTS find_partners_db;
USE find_partners_db;

-- 2. LIMPEZA TOTAL
-- -----------------------------------------------------------------
DROP TABLE IF EXISTS chat_mensagem;
DROP TABLE IF EXISTS candidatura;
DROP TABLE IF EXISTS oferta_disponibilidade; -- (Lixo antigo)
DROP TABLE IF EXISTS utilizador_disponibilidade;
DROP TABLE IF EXISTS utilizador_interesses;
DROP TABLE IF EXISTS oferta;
DROP TABLE IF EXISTS utilizador;
DROP TABLE IF EXISTS tipo_treino;
DROP TABLE IF EXISTS objetivo_fitness;
DROP TABLE IF EXISTS nivel_treino;
DROP TABLE IF EXISTS dia_semana;
DROP TABLE IF EXISTS periodo_dia;
DROP TABLE IF EXISTS localizacao;

-- 3. CRIAÇÃO DAS TABELAS (Versão Simplificada)
-- =================================================================

CREATE TABLE localizacao (
    localizacao_id INT PRIMARY KEY AUTO_INCREMENT,
    distrito VARCHAR(100) NOT NULL,
    concelho VARCHAR(100) NOT NULL
);

CREATE TABLE nivel_treino (
    nivel_id INT PRIMARY KEY AUTO_INCREMENT,
    nivel_nome VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE objetivo_fitness (
    objetivo_id INT PRIMARY KEY AUTO_INCREMENT,
    objetivo_nome VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE tipo_treino (
    tipo_treino_id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE dia_semana (
    dia_id INT PRIMARY KEY AUTO_INCREMENT,
    nome_dia VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE periodo_dia (
    periodo_id INT PRIMARY KEY AUTO_INCREMENT,
    periodo_nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE utilizador (
    utilizador_id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    nome_completo VARCHAR(150) NOT NULL,
    biografia TEXT NULL,
    url_foto_perfil VARCHAR(512) NULL,
    localizacao_id INT NULL,
    nivel_id INT NULL,
    objetivo_id INT NULL,
    CONSTRAINT fk_utilizador_localizacao FOREIGN KEY (localizacao_id) REFERENCES localizacao(localizacao_id),
    CONSTRAINT fk_utilizador_nivel FOREIGN KEY (nivel_id) REFERENCES nivel_treino(nivel_id),
    CONSTRAINT fk_utilizador_objetivo FOREIGN KEY (objetivo_id) REFERENCES objetivo_fitness(objetivo_id)
);

CREATE TABLE utilizador_interesses (
    utilizador_id INT NOT NULL,
    tipo_treino_id INT NOT NULL,
    PRIMARY KEY (utilizador_id, tipo_treino_id), 
    CONSTRAINT fk_interesses_utilizador FOREIGN KEY (utilizador_id) REFERENCES utilizador(utilizador_id),
    CONSTRAINT fk_interesses_tipo FOREIGN KEY (tipo_treino_id) REFERENCES tipo_treino(tipo_treino_id)
);

CREATE TABLE utilizador_disponibilidade (
    utilizador_id INT NOT NULL,
    dia_id INT NOT NULL,
    periodo_id INT NOT NULL,
    PRIMARY KEY (utilizador_id, dia_id, periodo_id),
    CONSTRAINT fk_disp_utilizador_user FOREIGN KEY (utilizador_id) REFERENCES utilizador(utilizador_id),
    CONSTRAINT fk_disp_utilizador_dia FOREIGN KEY (dia_id) REFERENCES dia_semana(dia_id),
    CONSTRAINT fk_disp_utilizador_periodo FOREIGN KEY (periodo_id) REFERENCES periodo_dia(periodo_id)
);

-- AQUI ESTAVA O ERRO: Agora só existe ESTA definição (a correta)
CREATE TABLE oferta (
    oferta_id INT PRIMARY KEY AUTO_INCREMENT,
    criador_id INT NOT NULL, 
    tipo_treino_id INT NOT NULL, 
    localizacao_id INT NOT NULL,
    nivel_id INT NOT NULL,
    
    -- Colunas NOVAS obrigatórias
    dia_id INT NOT NULL,
    periodo_id INT NOT NULL,
    
    titulo VARCHAR(100) NOT NULL,
    descricao MEDIUMTEXT NULL,
    status_oferta ENUM('ABERTA', 'FECHADA') NOT NULL DEFAULT 'ABERTA',
    data_criacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_oferta_criador FOREIGN KEY (criador_id) REFERENCES utilizador(utilizador_id),
    CONSTRAINT fk_oferta_tipo FOREIGN KEY (tipo_treino_id) REFERENCES tipo_treino(tipo_treino_id),
    CONSTRAINT fk_oferta_localizacao FOREIGN KEY (localizacao_id) REFERENCES localizacao(localizacao_id),
    CONSTRAINT fk_oferta_nivel FOREIGN KEY (nivel_id) REFERENCES nivel_treino(nivel_id),
    
    -- Ligações NOVAS
    CONSTRAINT fk_oferta_dia FOREIGN KEY (dia_id) REFERENCES dia_semana(dia_id),
    CONSTRAINT fk_oferta_periodo FOREIGN KEY (periodo_id) REFERENCES periodo_dia(periodo_id)
);

CREATE TABLE candidatura (
    candidatura_id INT PRIMARY KEY AUTO_INCREMENT,
    oferta_id INT NOT NULL,
    candidato_id INT NOT NULL,
    status ENUM('PENDENTE', 'ACEITE', 'REJEITADA') NOT NULL DEFAULT 'PENDENTE',
    comentario_inicial TEXT NULL,
    data_envio DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_candidatura_oferta FOREIGN KEY (oferta_id) REFERENCES oferta(oferta_id),
    CONSTRAINT fk_candidatura_candidato FOREIGN KEY (candidato_id) REFERENCES utilizador(utilizador_id)
);

CREATE TABLE chat_mensagem (
    mensagem_id INT PRIMARY KEY AUTO_INCREMENT,
    candidatura_id INT NOT NULL,
    remetente_id INT NOT NULL,
    texto_mensagem MEDIUMTEXT NOT NULL,
    data_envio DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_msg_candidatura FOREIGN KEY (candidatura_id) REFERENCES candidatura(candidatura_id),
    CONSTRAINT fk_msg_remetente FOREIGN KEY (remetente_id) REFERENCES utilizador(utilizador_id)
);

-- 4. DADOS INICIAIS
-- -----------------------------------------------------------------
INSERT INTO localizacao (distrito, concelho) VALUES ('Lisboa', 'Lisboa'), ('Lisboa', 'Sintra'), ('Porto', 'Porto');
INSERT INTO nivel_treino (nivel_nome) VALUES ('Iniciante'), ('Intermédio'), ('Avançado');
INSERT INTO objetivo_fitness (objetivo_nome) VALUES ('Perda de Peso'), ('Ganho de Massa Muscular'), ('Manutenção');
INSERT INTO tipo_treino (nome) VALUES ('Push'), ('Pull'), ('Legs'), ('Upper Body'), ('Lower Body'), ('Full Body'), ('Cardio');
INSERT INTO dia_semana (nome_dia) VALUES ('Segunda-feira'), ('Terça-feira'), ('Quarta-feira'), ('Quinta-feira'), ('Sexta-feira'), ('Sábado'), ('Domingo');
INSERT INTO periodo_dia (periodo_nome) VALUES ('Manhã (08h-12h)'), ('Almoço (12h-14h)'), ('Tarde (14h-18h)'), ('Noite (18h-22h)');

INSERT INTO utilizador (email, password_hash, nome_completo, localizacao_id, nivel_id, objetivo_id) VALUES 
('ana@gmail.com', '$2a$10$DUMMYHASH', 'Ana Silva', 1, 2, 2), 
('bruno@gmail.com', '$2a$10$DUMMYHASH', 'Bruno Costa', 3, 1, 1);

-- TESTE DE OFERTA (Já adaptado ao novo formato)
INSERT INTO oferta (criador_id, tipo_treino_id, localizacao_id, nivel_id, dia_id, periodo_id, titulo, descricao, status_oferta) 
VALUES (1, 1, 1, 2, 1, 1, 'Parceiro para Treino de Push', 'Procuro alguém...', 'ABERTA');

-- REATIVAR CHECKS E FIM
SET FOREIGN_KEY_CHECKS = 1;
SELECT 'Base de Dados RECRIADA COM SUCESSO e pronta para o Java!' AS status;