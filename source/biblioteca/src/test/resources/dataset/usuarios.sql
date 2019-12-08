INSERT INTO public.usuario(
            id, created, account_activate_token, account_activate_token_expiration, 
            ativo, celular, cpf, email, nome, /**password_reset_token, password_reset_token_expiration,**/ 
            perfil, senha)
    VALUES (1001, NOW(), 'f357364b-0790-4f2f-bad6-be07de0c864f', '2019-12-09', TRUE, 
            '045998698574', '12345678914', 'chgiovani5@gmail.com', 'Giovani',/** ?, ?,**/ 1, 
            '$2a$10$PqgosXXKYhsMipBjMMeZxOLVDo8CLdq9zW/SUO9VKqojro7WEopq.'),
           (1002, NOW(), 'f8307769-3784-43d5-9fb2-93c676eb90da', '2019-12-09', TRUE, 
            '045988785214', '15878985245', 'nomeg46677@email1.pro', 'Jose Junior',/** ?, ?,**/ 0, 
            '$2a$10$.74yUCyDJrbEpqjTVMRAxOYI3Ah5uHj/CDOLO1JbC5zPTEesYvAjy'),
           (1003, NOW(), '8a969c35-fadd-4146-91da-7272a9aec99b', '2019-12-09', TRUE, 
            '045999997855', '18996336999', 'wecofih281@imail1.net', 'Maria de Souza',/** ?, ?,**/ 0, 
            '$2a$10$me43BJkLEfsM4xVy5YG39u7B40vXgybmIpmY3RH3UJsW1qX7x8xZu');

