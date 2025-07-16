CREATE TABLE topico_atualizacao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,                                -- Identificador único da atualização
    titulo VARCHAR(255) NOT NULL,                                         -- Título da atualização
    mensagem TEXT NOT NULL,                                               -- Mensagem da atualização
    data DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,                     -- Data da atualização (data atual)

    editor_id BIGINT,                                                     -- Referência ao usuário que fez a edição
    topico_id BIGINT NOT NULL,                                            -- Referência ao tópico original

    CONSTRAINT fk_atualizacao_editor FOREIGN KEY (editor_id)
        REFERENCES usuarios(id),

    CONSTRAINT fk_atualizacao_topico FOREIGN KEY (topico_id)
        REFERENCES topicos(id)
);