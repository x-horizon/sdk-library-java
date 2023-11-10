CREATE EXTENSION IF NOT EXISTS postgis;
CREATE EXTENSION IF NOT EXISTS postgis_topology;
CREATE EXTENSION IF NOT EXISTS fuzzystrmatch;
CREATE EXTENSION IF NOT EXISTS postgis_tiger_geocoder;
CREATE EXTENSION IF NOT EXISTS address_standardizer;

CREATE TABLE student
(
    id             BIGINT                                             NOT NULL,
    teacher_id     UUID,
    class_id       CHAR(36),
    family_ids     JSONB                          DEFAULT '[]'::JSONB NOT NULL,
    detail_info    JSONB                          DEFAULT '{}'::JSONB NOT NULL,
    class_infos    JSONB                          DEFAULT '[]'::JSONB NOT NULL,
    book_infos     JSONB                          DEFAULT '[]'::JSONB NOT NULL,
    job_types      JSONB                          DEFAULT '[]'::JSONB NOT NULL,
    version        BIGINT                         DEFAULT 0           NOT NULL,
    creator_id     BIGINT                         DEFAULT 0           NOT NULL,
    create_time    TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT NOW()       NOT NULL,
    row_is_deleted BOOLEAN                        DEFAULT FALSE       NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO student (id, teacher_id, class_id, family_ids, detail_info, class_infos, book_infos, job_types, version, creator_id, create_time, row_is_deleted) VALUES (3, null, null, '[]', '{}', '[]', '[]', '[]', 0, 0, '2023-11-07 13:32:18.644600', false);
INSERT INTO student (id, teacher_id, class_id, family_ids, detail_info, class_infos, book_infos, job_types, version, creator_id, create_time, row_is_deleted) VALUES (1, 'b9d8e022-dd38-42df-90b0-05055447910c', '08a735d7-6192-4ba7-89bb-85d63ce9cd48', '[1, 2, 3]', '{"age": 18, "name": "myName1"}', '[{"id": 1, "name": "myClass1"}, {"id": 2, "name": "myClass2"}]', '[{"id": 1, "name": "myBook1"}, {"id": 2, "name": "myBook2"}]', '[1, 2, 3]', 0, 0, '2023-11-07 13:31:10.032700', false);
INSERT INTO student (id, teacher_id, class_id, family_ids, detail_info, class_infos, book_infos, job_types, version, creator_id, create_time, row_is_deleted) VALUES (2, 'b9d8e022-dd38-42df-90b0-05055447910c', '08a735d7-6192-4ba7-89bb-85d63ce9cd48', '[4, 5, 6]', '{"age": 18, "name": "myName2"}', '[{"id": 1, "name": "myClass1"}, {"id": 2, "name": "myClass2"}]', '[{"id": 1, "name": "myBook1"}, {"id": 2, "name": "myBook2"}]', '[1, 2, 3]', 0, 0, '2023-11-07 13:32:18.644600', false);
