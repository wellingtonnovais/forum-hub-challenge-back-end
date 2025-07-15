-- Criação da tabela de usuários do sistema (quem pode criar tópicos, responder etc.)
CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,                  -- ID único (chave primária)
    nome VARCHAR(100) NOT NULL,                            -- Nome completo do usuário
    email VARCHAR(100) NOT NULL UNIQUE,                    -- Email (único no sistema)
    senha VARCHAR(255) NOT NULL                            -- Senha criptografada
);

-- Criação da tabela de perfis (ex: ROLE_USER, ROLE_ADMIN)
CREATE TABLE perfis (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,                  -- ID do perfil
    nome VARCHAR(50) NOT NULL                              -- Nome do perfil (com ROLE_)
);

-- Tabela de associação entre usuários e perfis (relacionamento N:N)
CREATE TABLE usuarios_perfis (
    usuario_id BIGINT NOT NULL,                            -- FK para usuário
    perfil_id BIGINT NOT NULL,                             -- FK para perfil
    PRIMARY KEY (usuario_id, perfil_id),                   -- Chave composta (um perfil por usuário)
    CONSTRAINT fk_usuario_perfil_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_usuario_perfil_perfil FOREIGN KEY (perfil_id) REFERENCES perfis(id)
);

-- Criação da tabela de cursos (ex: Java, Python, HTML etc.)
CREATE TABLE cursos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,                  -- ID do curso
    nome_curso VARCHAR(100) NOT NULL,                      -- Nome para exibição
    categoria VARCHAR(50) NOT NULL                         -- Categoria do curso (baseada no enum)
);

-- Criação da tabela de tópicos (perguntas feitas pelos usuários)
CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,                  -- ID do tópico
    titulo VARCHAR(255) NOT NULL,                          -- Título do tópico
    mensagem TEXT NOT NULL,                                -- Mensagem detalhada
    data_criacao DATETIME NOT NULL,                        -- Data e hora em que foi criado
    status VARCHAR(50),                                    -- Status do tópico (opcional, ex: ABERTO, FECHADO)
    autor_id BIGINT,                                       -- FK para o autor do tópico
    curso_id BIGINT,                                       -- FK para o curso relacionado
    CONSTRAINT fk_topico_autor FOREIGN KEY (autor_id) REFERENCES usuarios(id),
    CONSTRAINT fk_topico_curso FOREIGN KEY (curso_id) REFERENCES cursos(id)
);

-- Criação da tabela de respostas (respostas aos tópicos)
CREATE TABLE respostas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,                  -- ID da resposta
    mensagem TEXT NOT NULL,                                -- Conteúdo da resposta
    data_criacao DATETIME NOT NULL,                        -- Data e hora da resposta
    autor_id BIGINT,                                       -- FK para o autor da resposta
    topico_id BIGINT,                                      -- FK para o tópico respondido
    CONSTRAINT fk_resposta_autor FOREIGN KEY (autor_id) REFERENCES usuarios(id),
    CONSTRAINT fk_resposta_topico FOREIGN KEY (topico_id) REFERENCES topicos(id)
);