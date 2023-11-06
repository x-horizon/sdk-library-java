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
    relation_id    BIGINT                                             NOT NULL,
    student_id     BIGINT                                             NOT NULL,
    class_id       BIGINT                                             NOT NULL,
    detail_info    JSONB                          DEFAULT '{}'::JSONB NOT NULL,
    class_infos    JSONB                          DEFAULT '[]'::JSONB NOT NULL,
    version        INT                            DEFAULT 0           NOT NULL,
    creator_id     BIGINT                         DEFAULT 0           NOT NULL,
    create_time    TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT NOW()       NOT NULL,
    row_is_deleted BOOLEAN                        DEFAULT FALSE       NOT NULL,
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

INSERT INTO public.school_student (student_id, detail_info, class_infos, student_type, version, creator_id, create_time,
                                   row_is_delete)
VALUES (1, '{"name": "test1"}', '[{"id": 1, "name": "test1"}, {"id": 2, "name": "test2"}]', 0, 0, 0,
        '2023-08-30 23:49:54.025353', FALSE);
INSERT INTO public.school_student (student_id, detail_info, class_infos, student_type, version, creator_id, create_time,
                                   row_is_delete)
VALUES (8, '{"name": "test1"}', '[{"id": 1, "name": "test1"}, {"id": 2, "name": "test2"}]', 0, 0, 0,
        '2023-08-30 23:55:52.070587', FALSE);
INSERT INTO public.school_student (student_id, detail_info, class_infos, student_type, version, creator_id, create_time,
                                   row_is_delete)
VALUES (3, '{"name": "test1"}', '[{"id": 1, "name": "test1"}, {"id": 2, "name": "test2"}]', 0, 0, 0,
        '2023-08-30 23:47:02.853256', FALSE);
INSERT INTO public.school_student (student_id, detail_info, class_infos, student_type, version, creator_id, create_time,
                                   row_is_delete)
VALUES (7, '{"name": "test1"}', '[{"id": 1, "name": "test1"}, {"id": 2, "name": "test2"}]', 0, 0, 0,
        '2023-08-30 23:42:03.585351', FALSE);
INSERT INTO public.school_student (student_id, detail_info, class_infos, student_type, version, creator_id, create_time,
                                   row_is_delete)
VALUES (6, '{"name": "test1"}', '[{"id": 1, "name": "test1"}, {"id": 2, "name": "test2"}]', 0, 0, 0,
        '2023-08-30 23:43:34.537252', FALSE);
INSERT INTO public.school_student (student_id, detail_info, class_infos, student_type, version, creator_id, create_time,
                                   row_is_delete)
VALUES (2, '{"name": "test1"}', '[{"id": 1, "name": "test1"}, {"id": 2, "name": "test2"}]', 0, 0, 0,
        '2023-08-31 00:13:10.614811', FALSE);
INSERT INTO public.school_student (student_id, detail_info, class_infos, student_type, version, creator_id, create_time,
                                   row_is_delete)
VALUES (5, '{"name": "test1"}', '[{"id": 1, "name": "test1"}, {"id": 2, "name": "test2"}]', 0, 0, 0,
        '2023-08-30 23:55:06.476489', FALSE);
INSERT INTO public.school_student (student_id, detail_info, class_infos, student_type, version, creator_id, create_time,
                                   row_is_delete)
VALUES (4, '{"name": "test1"}', '[{"id": 1, "name": "test1"}, {"id": 2, "name": "test2"}]', 0, 0, 0,
        '2023-08-30 23:52:48.861301', FALSE);


INSERT INTO public.school_student_class (relation_id, student_id, class_id, detail_info, class_infos, version,
                                         creator_id, create_time, row_is_deleted)
VALUES (1, 1, 1, '{"name": "test1"}', '[{"id": 1, "name": "test1"}, {"id": 2, "name": "test2"}]', 0, 0,
        '2023-11-06 14:44:13.214734', FALSE);
INSERT INTO public.school_student_class (relation_id, student_id, class_id, detail_info, class_infos, version,
                                         creator_id, create_time, row_is_deleted)
VALUES (2, 2, 2, '{"name": "test2"}', '[{"id": 3, "name": "test3"}, {"id": 4, "name": "test4"}]', 0, 0,
        '2023-11-06 14:44:13.214734', FALSE);
