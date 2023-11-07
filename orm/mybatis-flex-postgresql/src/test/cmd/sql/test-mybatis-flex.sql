CREATE EXTENSION IF NOT EXISTS postgis;
CREATE EXTENSION IF NOT EXISTS postgis_topology;
CREATE EXTENSION IF NOT EXISTS fuzzystrmatch;
CREATE EXTENSION IF NOT EXISTS postgis_tiger_geocoder;
CREATE EXTENSION IF NOT EXISTS address_standardizer;

CREATE TABLE student
(
    id             BIGINT                                             NOT NULL,
    family_ids     JSONB                          DEFAULT '[]'::JSONB NOT NULL,
    detail_info    JSONB                          DEFAULT '{}'::JSONB NOT NULL,
    class_infos    JSONB                          DEFAULT '[]'::JSONB NOT NULL,
    job_types      JSONB                          DEFAULT '[]'::JSONB NOT NULL,
    version        BIGINT                         DEFAULT 0           NOT NULL,
    creator_id     BIGINT                         DEFAULT 0           NOT NULL,
    create_time    TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT NOW()       NOT NULL,
    row_is_deleted BOOLEAN                        DEFAULT FALSE       NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO student (id, family_ids, detail_info, class_infos, job_types, version, creator_id, create_time, row_is_deleted) VALUES (1, '[1, 2, 3]', '{"age": 18, "name": "myName1"}', '[{"id": 1, "name": "myClass1"}, {"id": 2, "name": "myClass2"}]', '[1, 2, 3]', 0, 0, '2023-11-07 13:31:10.032700', false);
INSERT INTO student (id, family_ids, detail_info, class_infos, job_types, version, creator_id, create_time, row_is_deleted) VALUES (2, '[4, 5, 6]', '{"age": 18, "name": "myName2"}', '[{"id": 1, "name": "myClass1"}, {"id": 2, "name": "myClass2"}]', '[1, 2, 3]', 0, 0, '2023-11-07 13:32:18.644600', false);
INSERT INTO student (id, family_ids, detail_info, class_infos, job_types, version, creator_id, create_time, row_is_deleted) VALUES (3, '[]', '{}', '[]', '[]', 0, 0, '2023-11-07 13:32:18.644600', false);
