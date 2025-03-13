DROP TABLE IF EXISTS school;

CREATE TABLE school
(
    id           BIGINT PRIMARY KEY NOT NULL,
    name         VARCHAR(64)        NOT NULL,
    type         SMALLINT           NOT NULL,
    address      VARCHAR(255)       NOT NULL,
    remark       VARCHAR(255)       NOT NULL,
    creator_name VARCHAR(64)        NOT NULL,
    updater_name VARCHAR(64)        NOT NULL
);
COMMENT ON TABLE school IS '学校信息';
COMMENT ON COLUMN school.id IS 'id';
COMMENT ON COLUMN school.name IS '名字';
COMMENT ON COLUMN school.type IS '类型';
COMMENT ON COLUMN school.address IS '地址';
COMMENT ON COLUMN school.remark IS '备注';
COMMENT ON COLUMN school.creator_name IS '创建人名字';
COMMENT ON COLUMN school.updater_name IS '更新人名字';

INSERT INTO SYSDBA.SCHOOL (ID, NAME, TYPE, ADDRESS, REMARK, CREATOR_NAME, UPDATER_NAME) VALUES (1, '王1', 1, '西藏自治区茂名市兴国县', '备注', 'admin', 'admin');
INSERT INTO SYSDBA.SCHOOL (ID, NAME, TYPE, ADDRESS, REMARK, CREATOR_NAME, UPDATER_NAME) VALUES (2, '王2', 1, '西藏自治区茂名市兴国县', '备注', 'admin', 'admin');
INSERT INTO SYSDBA.SCHOOL (ID, NAME, TYPE, ADDRESS, REMARK, CREATOR_NAME, UPDATER_NAME) VALUES (3, '王3', 1, '西藏自治区茂名市兴国县', '备注', 'admin', 'admin');