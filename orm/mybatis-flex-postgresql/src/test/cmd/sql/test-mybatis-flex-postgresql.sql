DROP TABLE IF EXISTS home;
DROP TABLE IF EXISTS people;

CREATE TABLE home
(
    id           BIGINT                                       NOT NULL,
    name         VARCHAR(64)                    DEFAULT ''    NOT NULL,
    remark       VARCHAR(255)                   DEFAULT ''    NOT NULL,
    creator_id   BIGINT                         DEFAULT 0     NOT NULL,
    creator_name VARCHAR(64)                    DEFAULT ''    NOT NULL,
    updater_id   BIGINT                         DEFAULT 0     NOT NULL,
    updater_name VARCHAR(64)                    DEFAULT ''    NOT NULL,
    create_time  TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    update_time  TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    delete_time  TIMESTAMP(6) WITHOUT TIME ZONE,
    PRIMARY KEY (id)
);


CREATE TABLE people
(
    id           BIGINT                                       NOT NULL,
    home_id      BIGINT                         DEFAULT 0     NOT NULL,
    name1        VARCHAR(64)                    DEFAULT ''    NOT NULL,
    name2        VARCHAR(64)                    DEFAULT ''    NOT NULL,
    name3        VARCHAR(64)                    DEFAULT ''    NOT NULL,
    name4        VARCHAR(64)                    DEFAULT ''    NOT NULL,
    name5        VARCHAR(64)                    DEFAULT ''    NOT NULL,
    name6        VARCHAR(64)                    DEFAULT ''    NOT NULL,
    name7        VARCHAR(64)                    DEFAULT ''    NOT NULL,
    version      BIGINT                         DEFAULT 0     NOT NULL,
    remark       VARCHAR(255)                   DEFAULT ''    NOT NULL,
    creator_id   BIGINT                         DEFAULT 0     NOT NULL,
    creator_name VARCHAR(64)                    DEFAULT ''    NOT NULL,
    updater_id   BIGINT                         DEFAULT 0     NOT NULL,
    updater_name VARCHAR(64)                    DEFAULT ''    NOT NULL,
    create_time  TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    update_time  TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    delete_time  TIMESTAMP(6) WITHOUT TIME ZONE,
    PRIMARY KEY (id)
);