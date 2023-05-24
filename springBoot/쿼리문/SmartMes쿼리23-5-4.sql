-- drop table order_management;
-- drop table monitoring;
-- drop table production_management;
-- drop table shipment_management;
-- drop table inventory_management;

use smartmes;


-- order 주문 정보

create table orders(							-- 주문 정보 테이블
	order_id int PRIMARY key AUTO_INCREMENT,	-- 수주 번호 
    order_customer varchar(50),					-- 고객 이름
    order_date	date,							-- 주문 날짜
    order_delivery_date date,					-- 납품 일자
    order_status	char(1)						-- 주문상태 (0: 주문접수, 1: 진행중, 2: 완료)
);






create table order_details(						--  주문 상세 정보
	order_detail_id	int PRIMARY key,			-- 주문 상세 번호 (자식 번호)
    order_id int,								-- 수주 번호 (부모 번호)
    order_product_id int,						-- 제품 번호
    order_quantity INT,							-- 주문 수량
    price int,									-- 전체 가격
	
   
    foreign key (order_id) references orders(order_id) 
);
