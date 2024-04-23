CREATE EXTENSION IF NOT EXISTS postgis;
CREATE EXTENSION IF NOT EXISTS postgis_topology;
CREATE EXTENSION IF NOT EXISTS fuzzystrmatch;
CREATE EXTENSION IF NOT EXISTS postgis_tiger_geocoder;
CREATE EXTENSION IF NOT EXISTS address_standardizer;


CREATE TABLE school
(
    id          BIGINT                                       NOT NULL,
    name        VARCHAR(64)                    DEFAULT ''    NOT NULL,
    type        SMALLINT                       DEFAULT 0     NOT NULL,
    address     VARCHAR(255)                   DEFAULT ''    NOT NULL,
    enable_is   BOOLEAN                        DEFAULT FALSE NOT NULL,
    version     BIGINT                         DEFAULT 0     NOT NULL,
    remark      VARCHAR(255)                   DEFAULT ''    NOT NULL,
    creator_id  BIGINT                         DEFAULT 0     NOT NULL,
    updater_id  BIGINT                         DEFAULT 0     NOT NULL,
    create_time TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    update_time TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    delete_time TIMESTAMP(6) WITHOUT TIME ZONE,
    PRIMARY KEY (id)
);
COMMENT ON TABLE school IS '学校信息';
COMMENT ON COLUMN school.id IS 'id';
COMMENT ON COLUMN school.name IS '名字';
COMMENT ON COLUMN school.type IS '类型';
COMMENT ON COLUMN school.address IS '地址';
COMMENT ON COLUMN school.enable_is IS '是否启用';
COMMENT ON COLUMN school.version IS '版本号';
COMMENT ON COLUMN school.remark IS '备注';
COMMENT ON COLUMN school.creator_id IS '创建人id';
COMMENT ON COLUMN school.updater_id IS '更新人id';
COMMENT ON COLUMN school.create_time IS '创建时间';
COMMENT ON COLUMN school.update_time IS '更新时间';
COMMENT ON COLUMN school.delete_time IS '删除时间';


CREATE TABLE student
(
    id           BIGINT                                             NOT NULL,
    school_id    BIGINT                         DEFAULT 0           NOT NULL,
    teacher_ids  JSONB                          DEFAULT '[]'::JSONB NOT NULL,
    code         VARCHAR(255)                   DEFAULT ''          NOT NULL,
    name         VARCHAR(64)                    DEFAULT ''          NOT NULL,
    sort         INTEGER                        DEFAULT 0           NOT NULL,
    hobby_info   JSONB                          DEFAULT '{}'::JSONB NOT NULL,
    course_infos JSONB                          DEFAULT '[]'::JSONB NOT NULL,
    version      BIGINT                         DEFAULT 0           NOT NULL,
    remark       VARCHAR(255)                   DEFAULT ''          NOT NULL,
    creator_id   BIGINT                         DEFAULT 0           NOT NULL,
    updater_id   BIGINT                         DEFAULT 0           NOT NULL,
    create_time  TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT NOW()       NOT NULL,
    update_time  TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT NOW()       NOT NULL,
    delete_time  TIMESTAMP(6) WITHOUT TIME ZONE,
    PRIMARY KEY (id)
);
COMMENT ON TABLE student IS '学生信息';
COMMENT ON COLUMN student.id IS 'id';
COMMENT ON COLUMN student.school_id IS '学校id';
COMMENT ON COLUMN student.teacher_ids IS '老师id';
COMMENT ON COLUMN student.code IS '编号';
COMMENT ON COLUMN student.name IS '名字';
COMMENT ON COLUMN student.sort IS '排序';
COMMENT ON COLUMN student.hobby_info IS '兴趣爱好信息';
COMMENT ON COLUMN student.course_infos IS '课程信息';
COMMENT ON COLUMN student.version IS '版本号';
COMMENT ON COLUMN student.remark IS '备注';
COMMENT ON COLUMN student.creator_id IS '创建人id';
COMMENT ON COLUMN student.updater_id IS '更新人id';
COMMENT ON COLUMN student.create_time IS '创建时间';
COMMENT ON COLUMN student.update_time IS '更新时间';
COMMENT ON COLUMN student.delete_time IS '删除时间';


CREATE TABLE teacher
(
    id           BIGINT                                             NOT NULL,
    school_id    BIGINT                         DEFAULT 0           NOT NULL,
    code         BIGINT                         DEFAULT 0           NOT NULL,
    name         VARCHAR(64)                    DEFAULT ''          NOT NULL,
    status       SMALLINT                       DEFAULT 0           NOT NULL,
    level_types  JSONB                          DEFAULT '[]'::JSONB NOT NULL,
    course_types JSONB                          DEFAULT '[]'::JSONB NOT NULL,
    version      BIGINT                         DEFAULT 0           NOT NULL,
    remark       VARCHAR(255)                   DEFAULT ''          NOT NULL,
    creator_id   BIGINT                         DEFAULT 0           NOT NULL,
    updater_id   BIGINT                         DEFAULT 0           NOT NULL,
    create_time  TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT NOW()       NOT NULL,
    update_time  TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT NOW()       NOT NULL,
    delete_time  TIMESTAMP(6) WITHOUT TIME ZONE,
    PRIMARY KEY (id)
);
COMMENT ON TABLE teacher IS '教师信息';
COMMENT ON COLUMN teacher.id IS 'id';
COMMENT ON COLUMN teacher.school_id IS '学校id';
COMMENT ON COLUMN teacher.code IS '编号';
COMMENT ON COLUMN teacher.name IS '名字';
COMMENT ON COLUMN teacher.status IS '状态';
COMMENT ON COLUMN teacher.level_types IS '等级类型';
COMMENT ON COLUMN teacher.course_types IS '课程类型';
COMMENT ON COLUMN teacher.version IS '版本号';
COMMENT ON COLUMN teacher.remark IS '备注';
COMMENT ON COLUMN teacher.creator_id IS '创建人id';
COMMENT ON COLUMN teacher.updater_id IS '更新人id';
COMMENT ON COLUMN teacher.create_time IS '创建时间';
COMMENT ON COLUMN teacher.update_time IS '更新时间';
COMMENT ON COLUMN teacher.delete_time IS '删除时间';

INSERT INTO public.school (id, name, type, address, enable_is, version, remark, creator_id, updater_id, create_time, update_time, delete_time)
VALUES (538102469964805, '学校1', 1, '新疆维吾尔自治区毕节市江北区', FALSE, 0, '', 1, 1, '2024-04-19 14:44:35.342183', '2024-04-19 14:44:35.342183', NULL);

INSERT INTO public.student (id, school_id, teacher_ids, code, name, sort, hobby_info, course_infos, version, remark, creator_id, updater_id, create_time, update_time, delete_time)
VALUES (538109302425029, 538102469964805, '[538108389811589, 538108543567237]', '44', '学生1', 1, '{"levelType": 1, "achievementTypes": [1, 2], "primaryInterestName": "音乐", "specificInterestNames": ["美术", "运动"]}', '[{"name": "语文", "credit": 60}, {"name": "数学", "credit": 60}]', 0, 'ea elit', 1, 1, '2024-04-19 15:12:23.420131', '2024-04-19 15:12:23.420131', NULL);

INSERT INTO public.teacher (id, school_id, code, name, status, level_types, course_types, version, remark, creator_id, updater_id, create_time, update_time, delete_time)
VALUES (538108389811589, 538102469964805, 37, '老师1', 1, '[1, 2]', '["语文", "英语"]', 0, '', 1, 1, '2024-04-19 15:08:40.606767', '2024-04-19 15:08:40.606767', NULL);
INSERT INTO public.teacher (id, school_id, code, name, status, level_types, course_types, version, remark, creator_id, updater_id, create_time, update_time, delete_time)
VALUES (538108543567237, 538102469964805, 37, '老师2', 1, '[1, 2, 3]', '["语文", "数学"]', 0, '', 1, 1, '2024-04-19 15:09:18.144291', '2024-04-19 15:09:18.144291', NULL);