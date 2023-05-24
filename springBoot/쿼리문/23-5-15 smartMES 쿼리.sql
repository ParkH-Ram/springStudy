DROP PROCEDURE IF EXISTS insertData;



-- 흑마늘 생산량 계산 함수
DELIMITER //
CREATE FUNCTION blackGarlic(quantity INT) RETURNS INT
BEGIN
    DECLARE blackGarlicWeight INT;
    DECLARE blackGarlicProduction INT;

    SET blackGarlicWeight = 1; -- 흑마늘 1kg
    SET blackGarlicProduction = (quantity * 30) / blackGarlicWeight; -- 생산량 계산식: 수주 수량 * 30

    RETURN blackGarlicProduction;
END //
DELIMITER ;

-- CALL insertData();




use smartmes;

-- drop table example_table;

drop table porder;
DESC orders;


INSERT INTO orders (order_no, company_id, delivery_date, order_date, order_price, order_quantity, order_status, product_id)
VALUES ('OD20230516009', 'C111', '2023-05-20', '2023-05-16 10:00:00', 1000, 2000, 'B', 'P004');

update orders set order_status = 'B'
where order_no = 'OD20230516009';

-- 컬럼만 날리는 
-- TRUNCATE orders;

select * from porder;
select * from orders;
select * from ingredients;
-- select * from routing;
-- select * from product;
-- select * from process;
-- select * from ingredients;
select * from process;


select * from prodplan;
select * from workorder;



desc orders;

UPDATE orders SET ordorderser_date = now() WHERE order_no = 'OD202305170001';