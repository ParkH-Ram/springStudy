create database SmartMES;
use smartmes;


drop table proderdetails;
drop table orders;

drop table ingredients;
drop table proder;

drop table product;
drop table productionmanagement;
drop table shipmentmanagement;
drop table mornitoring;
drop table ingredientInput;


-- 수주 관련 테이블 

create table orders(                         	  -- 주문 정보 테이블
    order_id varchar(20),        				  -- 수주 번호
    order_seq int,                 			      -- 수주 항번
    order_customer varchar(50),					  -- 고객 이름
    order_customer_id int,                        --  고객 아이디
    order_date    date,                           -- 주문 날짜
    order_product_id  int,						  -- 고객 주문 제품 ( product_id) 참고 
    order_quantity int,								  -- 주문 수량 ( 단위 : box: )
    order_delivery_date date,                      -- 납품 일자
    order_status      char(1),                    -- 주문상태 (0 : 접수, 1 : 확정, 2: 완료 등)  
    
    PRIMARY key(order_id, order_seq)
);

-- order_product_id   --  product (product_id) 외래키 설정
ALTER table orders
	add CONSTRAINT
    foreign key (order_product_id) REFERENCES product(product_id);



create table ingredients(                                 -- 재료 테이블
    ingredient_id   int primary key auto_increment,             -- 재료 번호
    ingredient_name varchar(20),                           -- 재료 명
    ingredient_product_id int                               -- product 테이블의 product_id 참조
);

-- ingredients -- product(product_id) 외래키 설정
ALTER table ingredients
   add constraint
    foreign key (ingredient_product_id) references product(product_id);

create table proder(                                 -- 발주 테이블
	proder_id varchar(20),			                 -- 발주 번호
	proder_seq int,								     -- 발주 항번
	proder_date date,                                 -- 발주 날짜
	proder_status char(1),                             -- 발주 주문 상태 
    proder_ingredient_id int,							-- 재료 번호 (ingredient_id) 참조 
    proder_quantity int, 								-- 주문 수량
    proder_supplier_id int,								-- 공급업체 번호
    proder_emergency char(1),							-- 긴급 요청 여부
    
    primary key (proder_id, proder_seq)
);

-- proder -- ingredients(ingredient_id) 외래키 설정
ALTER table proder
	add CONSTRAINT
	foreign key (proder_ingredient_id) references ingredients (ingredient_id);




-- 생산관련



create table prodPlan(								-- 생산 계획 테이블
	prodPlan_id	varchar(20),						-- 생산 계획 번호
    prodPlan_seq int,								-- 생산 계획 항번
    prodPlan_dt date,								-- 생산 계획 일자
    prodPlan_item_code varchar(6),					-- 품목명
    prodPlan_quantity int,							-- 계획수량
    prodPlan_order_id	varchar(10),				-- 수주번호
    prodPlan_order_seq	int,						-- 수주항번
    prodPlan_fin_yn		varchar(5),					-- 완료 여부
    
    PRIMARY KEY (prodPlan_id, prodPlan_seq)			-- 중복 primary key
    
);

CREATE table workorder(  						-- 작업 지시 테이블
	work_order_id varchar(10),					-- 작업 지시 번호
    work_order_seq int,							-- 작업 지시 항번
    work_order_dt date,							-- 작업 지시 일자
    work_order_item_code varchar(6),			-- 품목명
    work_order_fac_code varchar(5),				-- 공정
    work_order_quantity int, 					-- 수량

    primary key (work_order_id, work_order_seq)   
);

create  table prodPerform (						-- 생산실적 테이블
	prodPerform_prod_id varchar(12),			-- 생산실적 번호
    prodPerform_prod_seq int,					-- 생산실적 항번
    prodPerform_prod_dt date,					-- 생산실적 일자
    prodPerform_item_code varchar(6),			-- 품목명
    prodPerform_fac_code varchar(5),			-- 공정
    prodPerform_quantity int,					-- 수량
    prodPerform_work_order_id varchar(12),		-- 작업 지시 번호
    prodPerform_work_order_seq int,   			-- 작업 지시 항번
    
    primary key (prodPerform_prod_id, prodPerform_prod_seq)
);


create table shipmentManagement(
   shipment_id   INT   PRIMARY KEY,                  -- 출하관리 번호
   shipment_customer_name   VARCHAR(50),            -- 출하 고객 이름
   shipment_date   DATE,                        -- 출하일
   shipment_product_id   INT,                     -- 출하 제품 번호
   shipment_quantity   INT,                     -- 출하 수량
   shipment_inventory_name   VARCHAR(50),            -- 참조 - inventory_product_name   재고 확인
   shipment_inventory_quantity   INT,               -- 참조 - inventory_quantity   제품의 이름과 수량을 받아서 재고 확인
   shipping_modifiability   Boolean,               -- 출하 수정 가능 여부
   shipment_status   VARCHAR(20)                  -- 출하 상태
);


-- 모니터링 관련 테이블
create table processes(                     			   --  공정 정보 테이블
   process_id int primary key auto_increment,    		   -- 공정 이이디 
    process_name varchar(20),              				   -- 공정 명
    process_content varchar(50),          				   -- 공정 내용
    process_lead_time date,									-- 리드 타임
    process_time date, 										-- 작업 시간
    process_capacity int 									-- 생산 능력    
);

create table routing(
	routing_id int primary key AUTO_INCREMENT,				-- 라우팅 아이디
    routing_name varchar(50),								-- 라우팅 이름
    routing_product_id int,								   -- 제품 기보닠
    routing_description varchar(100)                       -- 라우티 설명
);


create table bom(
	bom_id int primary key  auto_increment,						-- BOM 아이디
    bom_product_id int,											-- 제품 아이디
    bom_ingredient_id int,										-- 원자재, 부품 아이디
    bom_quantity int											-- 소요량
);

create table company(										-- 업체 정보
	company_id	int primary key AUTO_INCREMENT,				-- 업체 아이디
    company_name varchar(50),								-- 업체 이름
    company_address varchar(100)							-- 업체 주소
);

create table product(
	product_id int primary key auto_increment,					--  제품 아이디 
    product_name varchar(50)									-- 제품 명
);


-- 재고 관련 테이블 
create table finProduct(							-- 제품 목록 및 제품 재고
	finproduct_id int PRIMARY key,					-- 제품 id
	finproduct_date date, 							-- 제조 일자
    finproduct_name varchar(50),					-- 제품 이름
    finproduct_count INT							-- 제품 수량
);


-- finproduct -- proder(proder_id, proder_seq) 외래키 설정  ( 발주 테이블 )

alter table finProduct
		add CONSTRAINT
        FOREIGN KEY (finproduct_id)  references product(product_id);
    

create table ingredientInput(									-- 자재 입고 테이블
	input_ingredient_id VARCHAR(12),							-- 자재 id
    input_pd_id		int,										-- 발주 상세 번호 ( 발주 항번 )
	input_ingredient_name varchar(50),							-- 자재 이름
	input_date date,											-- 자재 입고 날짜
    input_count int ,											-- 자재 입고 수량
    
    PRIMARY key ( input_ingredient_id, input_pd_id)
);

-- ingredientInput -- proder(proder_id, proder_seq) 외래키 설정  ( 발주 테이블 )
alter table ingredientInput
	add CONSTRAINT
    FOREIGN KEY (input_ingredient_id, input_pd_id) references proder(proder_id, proder_seq);



create table ingredientOut (										-- 자재 출고 테이블
	out_ingredient_id int primary key auto_increment,				-- 자재 id
    out_count int,													-- 자재 출하 수량
    out_position varchar(100),										-- 자재 이동 정보
    out_consumption int,											-- 자재 사용량
    out_faulty_count int,											-- 자재 손실량
    out_inventory_count int,										-- 재공 재고량
    out_ingredient_quantity int										-- 현재 자재량
);

create table inventory(
	inventory_id int PRIMARY key AUTO_INCREMENT,			-- 재고 아이디
    inventory_product_id int,								-- 제품 id
    inventory_date date,								-- 재고 일자
    inventory_quantity int 									-- 재고량
);






create table mornitoring(
	mornitoring_prod_id	varchar(12),						-- 생산실적 번호
    mornitoring_prod_seq int,								-- 생산실적 항번
    mornitoring_inventory int,								-- 재고 아이디
    mornitoring_time	DATE,		-- 모니터링 날짜
    
    primary key (mornitoring_prod_id, mornitoring_prod_seq)
);


-- mornitoring -- prodPerform(prodPerform_prod_id, prodPerform_prod_seq) 외래키 설정  ( 생산 실적 )

alter table mornitoring
	add CONSTRAINT
    FOREIGN KEY (mornitoring_prod_id, mornitoring_prod_seq) references prodPerform(prodPerform_prod_id, prodPerform_prod_seq);
    
-- mornitoring -- inventory(inventory_id) 외래키 설정  ( 재공 재고 )    
alter table mornitoring
	add constraint
	FOREIGN KEY (mornitoring_inventory) references inventory(inventory_id);    

    