DROP TABLE IF EXISTS school;

CREATE TABLE school
(
    id           BIGINT                                    NOT NULL PRIMARY KEY,
    name         VARCHAR(64)  DEFAULT ''                   NOT NULL,
    type         SMALLINT     DEFAULT 0                    NOT NULL,
    address      VARCHAR(255) DEFAULT ''                   NOT NULL,
    enable_is    BOOLEAN      DEFAULT FALSE                NOT NULL,
    version      BIGINT       DEFAULT 0                    NOT NULL,
    remark       VARCHAR(255) DEFAULT ''                   NOT NULL,
    creator_id   BIGINT       DEFAULT 0                    NOT NULL,
    creator_name VARCHAR(64)  DEFAULT ''                   NOT NULL,
    updater_id   BIGINT       DEFAULT 0                    NOT NULL,
    updater_name VARCHAR(64)  DEFAULT ''                   NOT NULL,
    create_time  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) NOT NULL,
    update_time  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) NOT NULL,
    delete_time  TIMESTAMP(6)                              NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='学校信息';

INSERT INTO school (id, name, type, address, enable_is, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (1, '学校1', 1, '西藏自治区茂名市兴国县', TRUE, 0, '', 1, '', 1, '', '2024-04-24 17:57:26.102855', '2024-04-24 17:57:26.102855', NULL);
INSERT INTO school (id, name, type, address, enable_is, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (2, '学校2', 2, '西藏自治区茂名市兴国县', TRUE, 0, '', 1, '', 1, '', '2024-04-24 17:57:32.326921', '2024-04-24 17:57:32.326921', NULL);
INSERT INTO school (id, name, type, address, enable_is, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (3, '学校3', 3, '西藏自治区茂名市兴国县', TRUE, 0, '', 1, '', 1, '', '2024-04-24 17:57:37.165764', '2024-04-24 17:57:37.165764', NULL);