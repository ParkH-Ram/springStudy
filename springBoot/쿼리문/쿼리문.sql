
Create table CabbageJuice(    -- 양배추즙 BOM 1개당 
	Cabbage int(2) null,
    Purifiedwater int(2) null	
);








CREATE TABLE order_management (					-- 수주 관리 테이블
  order_id INT PRIMARY KEY AUTO_INCREMENT,    	-- order_id   int  기본키 설정  id 자동 증가
  order_customer VARCHAR(50),					-- 고객 이름 
  order_date DATE,								-- 주문 날짜
  order_delivery_date DATE,						-- 배송 날짜
  order_product_id INT,							-- 제품 번호 ( 주문에 포함된 제품 번호 )
  order_quantity INT,							-- 주문 수량
  price int,									-- 상품 가격
  order_status VARCHAR(20)						-- 주문 상태
);


CREATE TABLE production_management (						-- 생산관리 테이블
  production_id INT PRIMARY KEY AUTO_INCREMENT,				--  production_
  production_product_id INT, 								-- 제품 번호 ( 생산에 포함된 제품 번호 )
  production_date DATE,										-- 생산일
  production_quantity INT,									-- 생산 수량
  production_status VARCHAR(20)								-- 생산 상태
);


CREATE TABLE inventory_management (								-- 재고관리 테이블  - 재고 관련 정보 저장 
  inventory_product_id INT PRIMARY KEY auto_increment ,			-- 재고 제품 id 
  inventory_product_name VARCHAR(50),							-- 재고 제품 이름
  inventory_quantity INT,										-- 재고 제품 수량
  inventory_location VARCHAR(50)								-- 위치 
);



CREATE TABLE shipment_management (							-- 출하관리 테이블 
  shipment_id INT PRIMARY KEY AUTO_INCREMENT,				-- 출하관리 번호 
  shipment_customer_name VARCHAR(50),						-- 출하 고객 이름
  shipment_date DATE,										-- 출하일
  shipment_product_id INT,									-- 출하 제품 번호 
  shipment_quantity INT,									-- 출하 수량
  shipment_status VARCHAR(20)								-- 출하 상태
);


CREATE TABLE monitoring (								-- 모니터링 테이블
  monitoring_id INT PRIMARY KEY auto_increment,			-- 모니터링 아이디
  process_name VARCHAR(50),								-- 공정 이름			
  start_time DATETIME,									-- 모니터링 시작 일자 (에러 발생 일자)
  end_time DATETIME,									-- 모니터링 마감 일자 (공정 마감 일자)
  monitoring_status varchar(10000)						-- 모니터링 상태 현황 ( 모니터링 상태 상세 점검 )
);