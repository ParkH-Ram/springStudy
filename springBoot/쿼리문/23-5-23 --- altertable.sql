select * from porder;
select * from ingredient_input;
select * from ingredient_stock;
desc ingredient_stock;

alter table ingredient_stock MODIFY product_date date;
alter table ingredient_stock MODIFY product_Id varchar(20);

DELETE FROM porder WHERE porder_no = 'Pd20230515001';
DELETE FROM ingredient_input WHERE ingredient_in_id = 0;
DELETE FROM ingredient_stock WHERE product_id = 'I001';