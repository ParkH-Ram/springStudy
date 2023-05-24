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
select * from prodplan;
select * from shipment_managementworkorderwork_order;
-- TRUNCATE orders;
select * from ingredient_stock;
select * from ingredient_input;
select * from porder;

INSERT INTO porder (porder_no, porder_date, ingredient_id  , porder_quantity, supplier_id, porder_status)
VALUES ('PD202305230115', now() , 'I006' ,  33000 , '파우치공장', '입고대기');

INSERT INTO porder (porder_no, porder_date, ingredient_id  , porder_quantity, supplier_id, porder_status)
VALUES ('PD20230515033', now() , 'I001' ,  33000 , '양배추농장', '입고대기');

DELETE FROM porder WHERE porder_no = 'Pd20230515001';


SELECT MAX(RIGHT(i.stock_no,4)) FROM ingredient_stock AS i
WHERE (select date_format(product_date, '%Y%m%d')) = 
(Select date_format(sysdate(), '%Y%m%d'));
	

select * from orders;
select * from ingredients;
select * from ingredient_input;
-- select * from routing;
-- select * from product;
-- select * from process;
-- select * from iningredientsingredient_stockshipment_managementgredients;
select * from process;

desc ingredient_input;
desc ingredient_stock;

alter table ingredient_input drop ingredient_name;
alter table porder drop ingredient_name;


select * from product;
select * from shipment_management;



select * from prod_plan;
select * from work_order;
select * from workorder;
select * from prodperform;



desc orders;

UPDATE orders SET ordorderser_date = now() WHERE order_no = 'OD202305170001';