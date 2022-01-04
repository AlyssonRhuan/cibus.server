INSERT INTO CIBUS_USERS (EMAIL, IS_EMAIL_CONFIRMED, IS_FIRST_LOGIN, NAME, PASS, PROFILE) VALUES ('admin@cibus.com', TRUE, FALSE, 'Administrador', '$2a$10$HCnZ4/tb0IpiolE6TLRWfuUXxEsMD65FeJeNMO1HbrOjF/mHRfY/e', 'ROLE_ADMIN');
INSERT INTO CIBUS_USERS (EMAIL, IS_EMAIL_CONFIRMED, IS_FIRST_LOGIN, NAME, PASS, PROFILE) VALUES ('seller@cibus.com', TRUE, FALSE, 'Vendedor', '$2a$10$HCnZ4/tb0IpiolE6TLRWfuUXxEsMD65FeJeNMO1HbrOjF/mHRfY/e', 'ROLE_SELLER');
INSERT INTO CIBUS_USERS (EMAIL, IS_EMAIL_CONFIRMED, IS_FIRST_LOGIN, NAME, PASS, PROFILE) VALUES ('client@cibus.com', TRUE, FALSE, 'Cliente', '$2a$10$HCnZ4/tb0IpiolE6TLRWfuUXxEsMD65FeJeNMO1HbrOjF/mHRfY/e', 'ROLE_CLIENT');

INSERT INTO CIBUS_CATEGORYS (ACTIVE, DESCRIPTION, NAME) VALUES (TRUE, 'Assados', 'Assados');
INSERT INTO CIBUS_CATEGORYS (ACTIVE, DESCRIPTION, NAME) VALUES (TRUE, 'Bebidas', 'Bebidas');
INSERT INTO CIBUS_CATEGORYS (ACTIVE, DESCRIPTION, NAME) VALUES (TRUE, 'Sobremesas', 'Sobremesas');

INSERT INTO CIBUS_PRODUCTS (MINIMUM_STOCK, NAME, PRICE, STOCK_QUANTITY, VISIBLE) VALUES (5, 'Esfirra', 1.0, 6, TRUE);
INSERT INTO CIBUS_PRODUCTS (MINIMUM_STOCK, NAME, PRICE, STOCK_QUANTITY, VISIBLE) VALUES (5, 'Coxinha', 1.0, 6, TRUE);
INSERT INTO CIBUS_PRODUCTS (MINIMUM_STOCK, NAME, PRICE, STOCK_QUANTITY, VISIBLE) VALUES (5, 'Kibe', 1.0, 6, TRUE);
INSERT INTO CIBUS_PRODUCTS (MINIMUM_STOCK, NAME, PRICE, STOCK_QUANTITY, VISIBLE) VALUES (5, 'Torta de frango', 1.0, 6, TRUE);
INSERT INTO CIBUS_PRODUCTS (MINIMUM_STOCK, NAME, PRICE, STOCK_QUANTITY, VISIBLE) VALUES (5, 'Empada', 1.0, 6, TRUE);
INSERT INTO CIBUS_PRODUCTS (MINIMUM_STOCK, NAME, PRICE, STOCK_QUANTITY, VISIBLE) VALUES (5, 'Torta de atum', 1.0, 6, TRUE);
INSERT INTO CIBUS_PRODUCTS (MINIMUM_STOCK, NAME, PRICE, STOCK_QUANTITY, VISIBLE) VALUES (5, 'Quiche', 1.0, 6, TRUE);
INSERT INTO CIBUS_PRODUCTS (MINIMUM_STOCK, NAME, PRICE, STOCK_QUANTITY, VISIBLE) VALUES (5, 'Suco', 1.0, 6, TRUE);
INSERT INTO CIBUS_PRODUCTS (MINIMUM_STOCK, NAME, PRICE, STOCK_QUANTITY, VISIBLE) VALUES (5, 'Água', 1.0, 6, TRUE);
INSERT INTO CIBUS_PRODUCTS (MINIMUM_STOCK, NAME, PRICE, STOCK_QUANTITY, VISIBLE) VALUES (5, 'Água de coco', 1.0, 6, TRUE);
INSERT INTO CIBUS_PRODUCTS (MINIMUM_STOCK, NAME, PRICE, STOCK_QUANTITY, VISIBLE) VALUES (5, 'Pudim', 1.0, 6, TRUE);
INSERT INTO CIBUS_PRODUCTS (MINIMUM_STOCK, NAME, PRICE, STOCK_QUANTITY, VISIBLE) VALUES (5, 'Salada de frutas', 1.0, 6, TRUE);
INSERT INTO CIBUS_PRODUCTS (MINIMUM_STOCK, NAME, PRICE, STOCK_QUANTITY, VISIBLE) VALUES (5, 'Bolo', 1.0, 6, TRUE);

UPDATE CIBUS_PRODUCTS SET IMAGE = 'https://chocomaxbolos.com.br/wp-content/uploads/2019/10/esfirra-carne-2.png' WHERE ID = 1;
UPDATE CIBUS_PRODUCTS SET IMAGE = 'https://imagensemoldes.com.br/wp-content/uploads/2020/05/Salgado-Coxinha-PNG.png' WHERE ID = 2;
UPDATE CIBUS_PRODUCTS SET IMAGE = 'https://canopussalgados.com.br/wp-content/uploads/2016/07/kibe.png' WHERE ID = 3;
UPDATE CIBUS_PRODUCTS SET IMAGE = 'http://bocadoforno.com.br/wp-content/uploads/torta-frango1.png' WHERE ID = 4;
UPDATE CIBUS_PRODUCTS SET IMAGE = 'http://www.empadadovale.com.br/uploaded/images/l2c1.png' WHERE ID = 5;
UPDATE CIBUS_PRODUCTS SET IMAGE = 'https://www.extrabom.com.br/uploads/produtos/350x350/112286_20200918152628_thumb_112286_Torta_Sufle_Atum.png' WHERE ID = 6;
UPDATE CIBUS_PRODUCTS SET IMAGE = 'https://www.saladices.com.br/wp-content/uploads/2019/11/quiche-de-alho-poro-integral-saladices.png' WHERE ID = 7;
UPDATE CIBUS_PRODUCTS SET IMAGE = 'https://i.pinimg.com/originals/ed/b3/3b/edb33b2cab5e563ac124c091f15e9ab6.png' WHERE ID = 8;
UPDATE CIBUS_PRODUCTS SET IMAGE = 'https://static.clubeextra.com.br/img/uploads/1/937/639937.png' WHERE ID = 9;
UPDATE CIBUS_PRODUCTS SET IMAGE = 'https://www.casadococoverde.com.br/images/slide/inicio-slides-cocos.png' WHERE ID = 10;
UPDATE CIBUS_PRODUCTS SET IMAGE = 'https://www.tortamania.com.br/files/product/image/87/xvga_torta-mania-doces-pudim.png' WHERE ID = 11;
UPDATE CIBUS_PRODUCTS SET IMAGE = 'https://www.saladices.com.br/wp-content/uploads/2019/11/salada-de-frutas-com-iogurte.png' WHERE ID = 12;
UPDATE CIBUS_PRODUCTS SET IMAGE = 'https://www.pngkit.com/png/full/354-3542995_bolos-massa-de-chocolate-topo-de-bolo-para.png' WHERE ID = 13;

UPDATE CIBUS_PRODUCTS SET DESCRIPTION = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.';

UPDATE CIBUS_PRODUCTS SET QUICK_DESCRIPTION = 'Esfirra de carne' WHERE ID = 1;
UPDATE CIBUS_PRODUCTS SET QUICK_DESCRIPTION = 'Coxinha de frango' WHERE ID = 2;
UPDATE CIBUS_PRODUCTS SET QUICK_DESCRIPTION = 'Kibe' WHERE ID = 3;
UPDATE CIBUS_PRODUCTS SET QUICK_DESCRIPTION = 'Torta de frango' WHERE ID = 4;
UPDATE CIBUS_PRODUCTS SET QUICK_DESCRIPTION = 'Empada de frango' WHERE ID = 5;
UPDATE CIBUS_PRODUCTS SET QUICK_DESCRIPTION = 'Torta de atum' WHERE ID = 6;
UPDATE CIBUS_PRODUCTS SET QUICK_DESCRIPTION = 'Quiche de frango' WHERE ID = 7;
UPDATE CIBUS_PRODUCTS SET QUICK_DESCRIPTION = 'Suco' WHERE ID = 8;
UPDATE CIBUS_PRODUCTS SET QUICK_DESCRIPTION = 'Água' WHERE ID = 9;
UPDATE CIBUS_PRODUCTS SET QUICK_DESCRIPTION = 'Água de coco' WHERE ID = 10;
UPDATE CIBUS_PRODUCTS SET QUICK_DESCRIPTION = 'Pudim' WHERE ID = 11;
UPDATE CIBUS_PRODUCTS SET QUICK_DESCRIPTION = 'Salada de frutas' WHERE ID = 12;
UPDATE CIBUS_PRODUCTS SET QUICK_DESCRIPTION = 'Bolo' WHERE ID = 13;

INSERT INTO CIBUS_PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (1, 1);
INSERT INTO CIBUS_PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (2, 1);
INSERT INTO CIBUS_PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (3, 1);
INSERT INTO CIBUS_PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (4, 1);
INSERT INTO CIBUS_PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (5, 1);
INSERT INTO CIBUS_PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (6, 1);
INSERT INTO CIBUS_PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (7, 1);
INSERT INTO CIBUS_PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (8, 2);
INSERT INTO CIBUS_PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (9, 2);
INSERT INTO CIBUS_PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (10, 2);
INSERT INTO CIBUS_PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (11, 3);
INSERT INTO CIBUS_PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (12, 3);
INSERT INTO CIBUS_PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (13, 3);

INSERT INTO CIBUS_CASHS (DESCRIPTION, USER_ID, OPEN_DATE, START_VALUE, CURRENT_VALUE) VALUES ('Assados', 1, '2021-03-25', 10.0, 100.0);
INSERT INTO CIBUS_CASHS (DESCRIPTION, USER_ID, OPEN_DATE, START_VALUE, CURRENT_VALUE) VALUES ('Vendedor 1', 2, '2021-03-25', 10.0, 100.0);
INSERT INTO CIBUS_CASHS (DESCRIPTION, USER_ID, OPEN_DATE, CLOSE_DATE, START_VALUE, CURRENT_VALUE) VALUES ('Outros', 1, '2021-03-24', '2021-03-24', 10.0, 100.0);

INSERT INTO CIBUS_NOTIFICATION (NOTIFICATION, DATE, USER_ID) VALUES ('O produto Esfirra está com o estoque no valor minimo.', '2021-03-26', 1);
INSERT INTO CIBUS_NOTIFICATION (NOTIFICATION, DATE, USER_ID) VALUES ('O caixa do dia 22/03/2021 ainda está aberto.', '2021-03-26', 1);
INSERT INTO CIBUS_NOTIFICATION (NOTIFICATION, DATE, USER_ID) VALUES ('O seu saldo está abaixo de R$ 10,00, faça uma recarga.', '2021-03-26', 1);
INSERT INTO CIBUS_NOTIFICATION (NOTIFICATION, DATE, USER_ID) VALUES ('O produto Coca está com o estoque no valor minimo.', '2021-03-26', 2);

INSERT INTO CIBUS_PAYMENTS (PAYMENT, DESCRIPTION, VISIBLE, IS_CASH_MOVIMENT) VALUES ('Cartão de crédito', '', true, false);
INSERT INTO CIBUS_PAYMENTS (PAYMENT, DESCRIPTION, VISIBLE, IS_CASH_MOVIMENT) VALUES ('Cartão de débito', '', true, false);
INSERT INTO CIBUS_PAYMENTS (PAYMENT, DESCRIPTION, VISIBLE, IS_CASH_MOVIMENT) VALUES ('Conta', 'Será debitado do saldo vinculado a sua conta', true, false);
INSERT INTO CIBUS_PAYMENTS (PAYMENT, DESCRIPTION, VISIBLE, IS_CASH_MOVIMENT) VALUES ('Dinheiro', 'Á vista', true, true);

INSERT INTO CIBUS_REPORTS (IS_FAV, REQUEST, SIZE, TITLE, TYPE, ICON, PREFIX, SUFFIX) VALUES (true, 'report/valueInSales', 1, 'Valor em vendas', 'SIMPLE_DATA', '', 'R$', '');