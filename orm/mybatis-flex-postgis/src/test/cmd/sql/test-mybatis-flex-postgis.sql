CREATE EXTENSION IF NOT EXISTS postgis;
CREATE EXTENSION IF NOT EXISTS postgis_topology;
CREATE EXTENSION IF NOT EXISTS fuzzystrmatch;
CREATE EXTENSION IF NOT EXISTS postgis_tiger_geocoder;
CREATE EXTENSION IF NOT EXISTS address_standardizer;


CREATE TABLE home
(
    id           BIGINT                                       NOT NULL,
    name         VARCHAR(64)                    DEFAULT ''    NOT NULL,
    location     GEOMETRY,
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