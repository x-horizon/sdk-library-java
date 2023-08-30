CREATE EXTENSION IF NOT EXISTS postgis;
CREATE EXTENSION IF NOT EXISTS postgis_topology;
CREATE EXTENSION IF NOT EXISTS fuzzystrmatch;
CREATE EXTENSION IF NOT EXISTS postgis_tiger_geocoder;
CREATE EXTENSION IF NOT EXISTS address_standardizer;

CREATE TABLE school_student
(
    student_id     BIGINT                                             NOT NULL,
    detail_info    JSONB                          DEFAULT '{}'::JSONB NOT NULL,
    class_infos    JSONB                          DEFAULT '[]'::JSONB NOT NULL,
    student_type   SMALLINT                       DEFAULT 0           NOT NULL,
    version        INT                            DEFAULT 0           NOT NULL,
    creator_id     BIGINT                         DEFAULT 0           NOT NULL,
    create_time    TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT NOW()       NOT NULL,
    row_is_deleted BOOLEAN                        DEFAULT FALSE       NOT NULL,
    PRIMARY KEY (student_id)
);

CREATE TABLE school_class
(
    class_id       BIGINT                                       NOT NULL,
    class_name     VARCHAR(50)                    DEFAULT ''    NOT NULL,
    location       GEOMETRY                                     NULL,
    version        INT                            DEFAULT 0     NOT NULL,
    creator_id     BIGINT                         DEFAULT 0     NOT NULL,
    create_time    TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    row_is_deleted BOOLEAN                        DEFAULT FALSE NOT NULL,
    PRIMARY KEY (class_id)
);

CREATE TABLE school_student_class
(
    relation_id    BIGINT                                       NOT NULL,
    student_id     BIGINT                                       NOT NULL,
    class_id       BIGINT                                       NOT NULL,
    version        INT                            DEFAULT 0     NOT NULL,
    creator_id     BIGINT                         DEFAULT 0     NOT NULL,
    create_time    TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    row_is_deleted BOOLEAN                        DEFAULT FALSE NOT NULL,
    PRIMARY KEY (relation_id)
);

CREATE TABLE school_book
(
    book_id        VARCHAR                                      NOT NULL,
    student_id     BIGINT                                       NOT NULL,
    book_name      VARCHAR(50)                    DEFAULT ''    NOT NULL,
    version        INT                            DEFAULT 0     NOT NULL,
    creator_id     BIGINT                         DEFAULT 0     NOT NULL,
    create_time    TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    row_is_deleted BOOLEAN                        DEFAULT FALSE NOT NULL,
    PRIMARY KEY (book_id)
);

