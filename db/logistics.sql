
DROP TABLE IF EXISTS bid;
DROP TABLE IF EXISTS waybill;
DROP TABLE IF EXISTS carrier;
DROP TABLE IF EXISTS shipper;
DROP TABLE IF EXISTS usr;

-----------------------------------
-- 用户表
-----------------------------------
CREATE TABLE usr
(
    id		VARCHAR(20),
    name	VARCHAR(100) NOT NULL,
    CONSTRAINT pk_usr PRIMARY KEY(id)
);

INSERT INTO usr(id, name) VALUES('zhangsan', '张三');
INSERT INTO usr(id, name) VALUES('carriertest1', '承运方1');

-----------------------------------
-- 货主表
-----------------------------------
CREATE TABLE shipper
(
    id		VARCHAR(20),
    address	VARCHAR(100) NOT NULL,
    CONSTRAINT pk_shipper PRIMARY KEY(id),
    CONSTRAINT fk_shipper_usr FOREIGN KEY(id) REFERENCES usr(id)
);

INSERT INTO shipper(id, address) VALUES('zhangsan', '富春路3号创业楼707');

-----------------------------------
-- 承运方表
-----------------------------------
CREATE TABLE carrier
(
    id		VARCHAR(20),
    point	SMALLINT,
    CONSTRAINT pk_carrier PRIMARY KEY(id),
    CONSTRAINT fk_carrier_usr FOREIGN KEY(id) REFERENCES usr(id)
);

INSERT INTO carrier(id, point) VALUES('carriertest1', 90);

-----------------------------------
-- 运单表
-----------------------------------
CREATE TABLE waybill
(
    id			CHAR(12),               -- 运单id 170929000001（主键）
    goods_name          VARCHAR(50),            -- 货物名称
    loading_address     VARCHAR(100),           -- 装货地址
    unloading_address   VARCHAR(100),           -- 卸货地址
    freight             NUMERIC(9, 2),          -- 运费
    order_time          TIMESTAMP,              -- 下单时间
    shipper_id          VARCHAR(20),            -- 货主id（外键）
    carrier_id          VARCHAR(20),            -- 承运方id（外键）
    CONSTRAINT pk_waybill PRIMARY KEY(id),
    CONSTRAINT fk_waybill_shipper FOREIGN KEY(shipper_id) REFERENCES shipper(id),
    CONSTRAINT fk_waybill_carrier FOREIGN KEY(carrier_id) REFERENCES carrier(id)
);

INSERT INTO waybill(id, goods_name, loading_address, unloading_address,
	freight, order_time, shipper_id, carrier_id)
VALUES ('180410000001', '钢材原料', '天津市', '杭州市', 2000.5, '2018-04-10 20:18'::timestamp,
	'zhangsan', 'carriertest1');

-----------------------------------
-- 报价表
-----------------------------------
CREATE TABLE bid
(
    id                 SERIAL,             -- 竞价id（主键）
    waybill_id         CHAR(12),           -- 运单id（外键）
    carrier_id         VARCHAR(20),        -- 承运方id（外键）
    bid_time           TIMESTAMP,          -- 竞价提交时间
    price              NUMERIC(9, 2),      -- 价格
    CONSTRAINT pk_bid PRIMARY KEY(id),
    CONSTRAINT fk_bid_waybill FOREIGN KEY(waybill_id) REFERENCES waybill(id),
    CONSTRAINT fk_bid_carrier FOREIGN KEY(carrier_id) REFERENCES carrier(id)
);

INSERT INTO bid(waybill_id, carrier_id, bid_time, price)
VALUES ('180410000001', 'carriertest1', '2018-04-10 10:18'::timestamp, 2000.5);

SELECT * FROM usr;
SELECT * FROM shipper;
SELECT * FROM carrier;
SELECT * FROM waybill;
SELECT * FROM bid;
