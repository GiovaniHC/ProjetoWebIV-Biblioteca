INSERT INTO public.livro(
            id, created, autor, disponivel, edicao, editora, isbn, 
            titulo)
    VALUES (1001, NOW(), 'Autor de Teste', TRUE, '2', 'Editora de Teste', '1234567891234', 'Titulo de Teste'),
           (1002, NOW(), 'Equipe Digerati', TRUE, '3', 'Digerati Books', '9788578731069', 'Hardware Extremo'),
           (1003, NOW(), 'Silvia Campos Ferreira', TRUE, '1', 'Saraiva', '9788536527017', 'Raspberry Pi Descomplicado');


