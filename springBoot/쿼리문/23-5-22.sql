use testmobul;

select  * from orders;


INSERT INTO orders (order_no, company_id, delivery_date, order_date, order_price, order_quantity, order_status, product_id)
VALUES ('OD202305160010', 'C111', '2023-05-20', '2023-05-16 10:00:00', 1000, 2000, 'B', 'P004');