-- =================================================================
-- SCRIPT DE CRIAÇÃO DA BASE DE DADOS: GYM BRO



-- 1. CRIAR E SELECIONAR A BASE DE DADOS

CREATE DATABASE IF NOT EXISTS find_partners_db;
USE find_partners_db;

-- 2. LIMPEZA (DROP TABLES) 
DROP TABLE IF EXISTS chat_mensagem;
DROP TABLE IF EXISTS candidatura;
DROP TABLE IF EXISTS oferta_disponibilidade;
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


-- 3. CONSTRUÇÃO (CREATE TABLES) - 

-- GRUPO 1: As "Tabelas-Lista"


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

-- GRUPO 2: A Tabela "Mãe"

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
    
    CONSTRAINT fk_utilizador_localizacao
        FOREIGN KEY (localizacao_id) REFERENCES localizacao(localizacao_id),
    CONSTRAINT fk_utilizador_nivel
        FOREIGN KEY (nivel_id) REFERENCES nivel_treino(nivel_id),
    CONSTRAINT fk_utilizador_objetivo
        FOREIGN KEY (objetivo_id) REFERENCES objetivo_fitness(objetivo_id)
);

-- GRUPO 3: As Tabelas "Ponte" e "Ação"


CREATE TABLE utilizador_interesses (
    utilizador_id INT NOT NULL,
    tipo_treino_id INT NOT NULL,
    PRIMARY KEY (utilizador_id, tipo_treino_id), 
    CONSTRAINT fk_interesses_utilizador
        FOREIGN KEY (utilizador_id) REFERENCES utilizador(utilizador_id),
    CONSTRAINT fk_interesses_tipo
        FOREIGN KEY (tipo_treino_id) REFERENCES tipo_treino(tipo_treino_id)
);

CREATE TABLE utilizador_disponibilidade (
    utilizador_id INT NOT NULL,
    dia_id INT NOT NULL,
    periodo_id INT NOT NULL,
    PRIMARY KEY (utilizador_id, dia_id, periodo_id),
    CONSTRAINT fk_disp_utilizador_user
        FOREIGN KEY (utilizador_id) REFERENCES utilizador(utilizador_id),
    CONSTRAINT fk_disp_utilizador_dia
        FOREIGN KEY (dia_id) REFERENCES dia_semana(dia_id),
    CONSTRAINT fk_disp_utilizador_periodo
        FOREIGN KEY (periodo_id) REFERENCES periodo_dia(periodo_id)
);

CREATE TABLE oferta (
    oferta_id INT PRIMARY KEY AUTO_INCREMENT,
    criador_id INT NOT NULL, 
    tipo_treino_id INT NOT NULL, 
    localizacao_id INT NOT NULL,
    nivel_id INT NOT NULL,
    
    titulo VARCHAR(100) NOT NULL,
    descricao MEDIUMTEXT NULL,
    status_oferta ENUM('ABERTA', 'FECHADA') NOT NULL DEFAULT 'ABERTA',
    data_criacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_oferta_criador
        FOREIGN KEY (criador_id) REFERENCES utilizador(utilizador_id),
    CONSTRAINT fk_oferta_tipo
        FOREIGN KEY (tipo_treino_id) REFERENCES tipo_treino(tipo_treino_id),
    CONSTRAINT fk_oferta_localizacao
        FOREIGN KEY (localizacao_id) REFERENCES localizacao(localizacao_id),
    CONSTRAINT fk_oferta_nivel
        FOREIGN KEY (nivel_id) REFERENCES nivel_treino(nivel_id)
);

CREATE TABLE oferta_disponibilidade (
    oferta_id INT NOT NULL,
    dia_id INT NOT NULL,
    periodo_id INT NOT NULL,
    PRIMARY KEY (oferta_id, dia_id, periodo_id),
    CONSTRAINT fk_disp_oferta_oferta
        FOREIGN KEY (oferta_id) REFERENCES oferta(oferta_id),
    CONSTRAINT fk_disp_oferta_dia
        FOREIGN KEY (dia_id) REFERENCES dia_semana(dia_id),
    CONSTRAINT fk_disp_oferta_periodo
        FOREIGN KEY (periodo_id) REFERENCES periodo_dia(periodo_id)
);

-- GRUPO 4: As Tabelas "dependentes"


CREATE TABLE candidatura (
    candidatura_id INT PRIMARY KEY AUTO_INCREMENT,
    oferta_id INT NOT NULL,
    candidato_id INT NOT NULL,
    status ENUM('PENDENTE', 'ACEITE', 'REJEITADA') NOT NULL DEFAULT 'PENDENTE',
    comentario_inicial TEXT NULL,
    data_envio DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_candidatura_oferta
        FOREIGN KEY (oferta_id) REFERENCES oferta(oferta_id),
    CONSTRAINT fk_candidatura_candidato
        FOREIGN KEY (candidato_id) REFERENCES utilizador(utilizador_id)
);

CREATE TABLE chat_mensagem (
    mensagem_id INT PRIMARY KEY AUTO_INCREMENT,
    candidatura_id INT NOT NULL,
    remetente_id INT NOT NULL,
    texto_mensagem MEDIUMTEXT NOT NULL,
    data_envio DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_msg_candidatura
        FOREIGN KEY (candidatura_id) REFERENCES candidatura(candidatura_id),
    CONSTRAINT fk_msg_remetente
        FOREIGN KEY (remetente_id) REFERENCES utilizador(utilizador_id)
);

-- 4. POPULAR (INSERTs)

INSERT INTO localizacao (distrito, concelho) VALUES 
('Lisboa', 'Lisboa'), ('Lisboa', 'Sintra'), ('Porto', 'Porto');

INSERT INTO nivel_treino (nivel_nome) VALUES 
('Iniciante'), ('Intermédio'), ('Avançado');

INSERT INTO objetivo_fitness (objetivo_nome) VALUES 
('Perda de Peso'), ('Ganho de Massa Muscular'), ('Manutenção');

INSERT INTO tipo_treino (nome) VALUES
('Push'), ('Pull'), ('Legs'), ('Upper Body'), ('Lower Body'), ('Full Body'), ('Cardio');

INSERT INTO dia_semana (nome_dia) VALUES
('Segunda-feira'), ('Terça-feira'), ('Quarta-feira'), ('Quinta-feira'), ('Sexta-feira'), ('Sábado'), ('Domingo');

INSERT INTO periodo_dia (periodo_nome) VALUES
('Manã (08h-12h)'), ('Almoço (12h-14h)'), ('Tarde (14h-18h)'), ('Noite (18h-22h)');


-- 5. Dados para teste: dois utilizadores, três ofertas abertas e uma fechada 
    -- Criar 2 utilizadores de teste
INSERT INTO utilizador (email, password_hash, nome_completo, localizacao_id, nivel_id, objetivo_id)
VALUES 
('ana@gmail.com', 'dummy_hash_123', 'Ana Silva', 1, 2, 2), -- Ana (ID 1), mora em 'Lisboa'(1), é 'Intermédio'(2), quer 'Ganho de Massa'(2)
('bruno@gmail.com', 'dummy_hash_456', 'Bruno Costa', 3, 1, 1); -- Bruno (ID 2), mora no 'Porto'(3), é 'Iniciante'(1), quer 'Perda de Peso'(1)

    -- Criar Oferta 1 (ABERTA) - Vai aparecer na API
INSERT INTO oferta (
    criador_id, tipo_treino_id, localizacao_id, nivel_id, 
    titulo, descricao, status_oferta
) 
VALUES (
    1, -- criador_id (ID da Ana)
    1, -- tipo_treino_id (ID de 'Push')
    1, -- localizacao_id (ID de 'Lisboa - Lisboa')
    2, -- nivel_id (ID de 'Intermédio')
    'Parceiro para Treino de Push (Peito/Ombros)',
    'Procuro alguém para treinar Push às Segundas e Quintas. Foco total!',
    'ABERTA' 
);

    -- Criar Oferta 2 (FECHADA) - NÃO vai aparecer na API (para testar o filtro)
INSERT INTO oferta (
    criador_id, tipo_treino_id, localizacao_id, nivel_id, 
    titulo, descricao, status_oferta
) 
VALUES (
    1, -- criador_id (ID da Ana)
    3, -- tipo_treino_id (ID de 'Legs')
    1, -- localizacao_id (ID de 'Lisboa - Lisboa')
    1, -- nivel_id (ID de 'Iniciante')
    'Ajudem-me a treinar pernas!',
    'Odeio treinar pernas sozinho/a. Preciso de motivação.',
    'FECHADA' 
);

    -- Criar Oferta 3 (ABERTA) 
INSERT INTO oferta (
    criador_id, tipo_treino_id, localizacao_id, nivel_id, 
    titulo, descricao, status_oferta
) 
VALUES (
    2, -- criador_id (ID do Bruno)
    2, -- tipo_treino_id (ID de 'Pull')
    3, -- localizacao_id (ID de 'Porto - Porto')
    1, -- nivel_id (ID de 'Iniciante')
    'Parceiro de Pull (Costas/Bíceps) - Solinca',
    'Sou iniciante e procuro alguém para treinar costas/bíceps no Porto.',
    'ABERTA' 
);

    -- Criar Oferta 4 (ABERTA) 
INSERT INTO oferta (
    criador_id, tipo_treino_id, localizacao_id, nivel_id, 
    titulo, descricao, status_oferta
) 
VALUES (
    1, -- criador_id (ID da Ana)
    6, -- tipo_treino_id (ID de 'Full Body')
    2, -- localizacao_id (ID de 'Lisboa - Sintra')
    2, -- nivel_id (ID de 'Intermédio')
    'Treino Full Body 3x/semana - Holmes Place Sintra',
    'Procuro parceiro(a) intermédio para rotina Full Body. Foco em consistência.',
    'ABERTA'
);

-- Confirmação Final
-- -----------------------------------------------------------------
SELECT 'Script v2.2 (snake_case) com DADOS DE TESTE (3 Abertas) executado com sucesso!' AS status;