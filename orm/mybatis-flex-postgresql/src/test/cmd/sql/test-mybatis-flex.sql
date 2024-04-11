CREATE EXTENSION IF NOT EXISTS postgis;
CREATE EXTENSION IF NOT EXISTS postgis_topology;
CREATE EXTENSION IF NOT EXISTS fuzzystrmatch;
CREATE EXTENSION IF NOT EXISTS postgis_tiger_geocoder;
CREATE EXTENSION IF NOT EXISTS address_standardizer;


CREATE TABLE home
(
    id             BIGINT                                       NOT NULL,
    name           VARCHAR(50)                    DEFAULT ''    NOT NULL,
    creator_id     BIGINT                         DEFAULT 0     NOT NULL,
    create_time    TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    row_is_deleted BOOLEAN                        DEFAULT FALSE NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE people
(
    id             BIGINT                                       NOT NULL,
    home_id        BIGINT                                       NOT NULL,
    name           VARCHAR(50)                    DEFAULT ''    NOT NULL,
    version        BIGINT                         DEFAULT 0     NOT NULL,
    creator_id     BIGINT                         DEFAULT 0     NOT NULL,
    create_time    TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    row_is_deleted BOOLEAN                        DEFAULT FALSE NOT NULL,
    PRIMARY KEY (id)
);