DROP TABLE IF EXISTS school;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS student;


CREATE TABLE school
(
    id           BIGINT       NOT NULL PRIMARY KEY COMMENT 'id',
    name         VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '名字',
    type         SMALLINT     NOT NULL DEFAULT 0 COMMENT '类型',
    address      VARCHAR(255) NOT NULL DEFAULT '' COMMENT '地址',
    enable_is    BOOLEAN      NOT NULL DEFAULT FALSE COMMENT '是否启用',
    version      BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    remark       VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注',
    creator_id   BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人id',
    creator_name VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '创建人名字',
    updater_id   BIGINT       NOT NULL DEFAULT 0 COMMENT '更新人id',
    updater_name VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '更新人名字',
    create_time  DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    update_time  DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
    delete_time  DATETIME(6)  NULL COMMENT '删除时间'
) COMMENT '学校信息' ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;


CREATE TABLE teacher
(
    id           BIGINT       NOT NULL PRIMARY KEY COMMENT 'id',
    school_id    BIGINT       NOT NULL DEFAULT 0 COMMENT '学校id',
    code         BIGINT       NOT NULL DEFAULT 0 COMMENT '编号',
    name         VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '名字',
    status       SMALLINT     NOT NULL DEFAULT 0 COMMENT '状态',
    level_types  JSON         NOT NULL DEFAULT (JSON_ARRAY()) COMMENT '等级类型',
    course_types JSON         NOT NULL DEFAULT (JSON_ARRAY()) COMMENT '课程类型',
    version      BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    remark       VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注',
    creator_id   BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人id',
    creator_name VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '创建人名字',
    updater_id   BIGINT       NOT NULL DEFAULT 0 COMMENT '更新人id',
    updater_name VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '更新人名字',
    create_time  DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    update_time  DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
    delete_time  DATETIME(6)  NULL COMMENT '删除时间'
) COMMENT '教师信息' ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;



CREATE TABLE student
(
    id           BIGINT       NOT NULL PRIMARY KEY COMMENT 'id',
    school_id    BIGINT       NOT NULL DEFAULT 0 COMMENT '学校id',
    teacher_ids  JSON         NOT NULL DEFAULT (JSON_ARRAY()) COMMENT '老师id',
    code         VARCHAR(255) NOT NULL DEFAULT '' COMMENT '编号',
    name         VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '名字',
    sort         INT          NOT NULL DEFAULT 0 COMMENT '排序',
    hobby_info   JSON         NOT NULL DEFAULT (JSON_OBJECT()) COMMENT '兴趣爱好信息',
    course_infos JSON         NOT NULL DEFAULT (JSON_ARRAY()) COMMENT '课程信息',
    version      BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    remark       VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注',
    creator_id   BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人id',
    creator_name VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '创建人名字',
    updater_id   BIGINT       NOT NULL DEFAULT 0 COMMENT '更新人id',
    updater_name VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '更新人名字',
    create_time  DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    update_time  DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
    delete_time  DATETIME(6)  NULL COMMENT '删除时间'
) COMMENT '学生信息' ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;


INSERT INTO school (id, name, type, address, enable_is, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (1, '学校1', 1, '西藏自治区茂名市兴国县', TRUE, 0, '', 1, '', 1, '', '2024-04-24 17:57:26.102855', '2024-04-24 17:57:26.102855', NULL);
INSERT INTO school (id, name, type, address, enable_is, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (2, '学校2', 2, '西藏自治区茂名市兴国县', TRUE, 0, '', 1, '', 1, '', '2024-04-24 17:57:32.326921', '2024-04-24 17:57:32.326921', NULL);
INSERT INTO school (id, name, type, address, enable_is, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (3, '学校3', 3, '西藏自治区茂名市兴国县', TRUE, 0, '', 1, '', 1, '', '2024-04-24 17:57:37.165764', '2024-04-24 17:57:37.165764', NULL);


INSERT INTO teacher (id, school_id, code, name, status, level_types, course_types, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (1, 1, 1, '学校1-老师1', 1, '[
  1,
  2
]', '[
  "语文",
  "数学"
]', 0, '', 1, '', 1, '', '2024-04-24 17:59:30.886752', '2024-04-24 17:59:30.886752', NULL);
INSERT INTO teacher (id, school_id, code, name, status, level_types, course_types, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (2, 1, 2, '学校1-老师2', 2, '[
  2,
  3
]', '[
  "数学",
  "英语"
]', 0, '', 1, '', 1, '', '2024-04-24 18:00:03.400597', '2024-04-24 18:00:03.400597', NULL);
INSERT INTO teacher (id, school_id, code, name, status, level_types, course_types, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (3, 2, 3, '学校2-老师3', 2, '[
  2,
  3
]', '[
  "数学",
  "英语"
]', 0, '', 1, '', 1, '', '2024-04-24 18:00:11.428400', '2024-04-24 18:00:16.060970', NULL);
INSERT INTO teacher (id, school_id, code, name, status, level_types, course_types, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (4, 2, 4, '学校2-老师4', 2, '[
  2,
  3
]', '[
  "数学",
  "英语"
]', 0, '', 1, '', 1, '', '2024-04-24 18:00:14.428000', '2024-04-24 18:00:11.428400', NULL);
INSERT INTO teacher (id, school_id, code, name, status, level_types, course_types, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (5, 3, 5, '学校3-老师5', 2, '[
  2,
  3
]', '[
  "数学",
  "英语"
]', 0, '', 1, '', 1, '', '2024-04-24 18:00:56.350731', '2024-04-24 18:00:56.350731', NULL);
INSERT INTO teacher (id, school_id, code, name, status, level_types, course_types, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (6, 3, 6, '学校3-老师6', 2, '[
  1,
  2,
  3
]', '[
  "语文",
  "数学",
  "英语"
]', 0, '', 1, '', 1, '', '2024-04-24 18:01:14.598488', '2024-04-24 18:01:14.598488', NULL);


INSERT INTO student (id, school_id, teacher_ids, code, name, sort, hobby_info, course_infos, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (1, 1, '[
  1,
  2
]', '1', '学校1-老师1、2-学生1', 1, '{
  "bookInfo": {
    "name": "学生1-爱好书本"
  },
  "levelType": 1,
  "toolInfos": [
    {
      "name": "学生1-爱好工具1"
    },
    {
      "name": "学生1-爱好工具2"
    }
  ],
  "achievementTypes": [
    1,
    2
  ],
  "primaryInterestName": "学生1-主要兴趣",
  "specificInterestNames": [
    "学生1-具体兴趣1",
    "学生1-具体兴趣2"
  ]
}', '[
  {
    "name": "学生1-课程",
    "credit": 60,
    "bookInfo": {
      "name": "学生1-课程书本"
    },
    "toolInfos": [
      {
        "name": "学生1-课程工具1"
      },
      {
        "name": "学生1-课程工具2"
      }
    ]
  }
]', 0, '', 1, '', 1, '', '2024-04-24 18:06:54.925134', '2024-04-24 18:06:54.925134', NULL);
INSERT INTO student (id, school_id, teacher_ids, code, name, sort, hobby_info, course_infos, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (2, 1, '[
  2,
  3
]', '2', '学校1-老师2、3-学生2', 2, '{
  "bookInfo": {
    "name": "学生2-爱好书本"
  },
  "levelType": 2,
  "toolInfos": [
    {
      "name": "学生2-爱好工具1"
    },
    {
      "name": "学生2-爱好工具2"
    }
  ],
  "achievementTypes": [
    2,
    3
  ],
  "primaryInterestName": "学生2-主要兴趣",
  "specificInterestNames": [
    "学生2-具体兴趣1",
    "学生2-具体兴趣2"
  ]
}', '[
  {
    "name": "学生2-课程",
    "credit": 70,
    "bookInfo": {
      "name": "学生2-课程书本"
    },
    "toolInfos": [
      {
        "name": "学生2-课程工具1"
      },
      {
        "name": "学生2-课程工具2"
      }
    ]
  }
]', 0, '', 1, '', 1, '', '2024-04-24 18:07:57.140849', '2024-04-24 18:07:57.140849', NULL);
INSERT INTO student (id, school_id, teacher_ids, code, name, sort, hobby_info, course_infos, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (3, 2, '[
  3,
  4
]', '3', '学校2-老师3、4-学生3', 3, '{
  "bookInfo": {
    "name": "学生3-爱好书本"
  },
  "levelType": 3,
  "toolInfos": [
    {
      "name": "学生3-爱好工具1"
    },
    {
      "name": "学生3-爱好工具2"
    }
  ],
  "achievementTypes": [
    1,
    2,
    3
  ],
  "primaryInterestName": "学生3-主要兴趣",
  "specificInterestNames": [
    "学生3-具体兴趣1",
    "学生3-具体兴趣2"
  ]
}', '[
  {
    "name": "学生3-课程",
    "credit": 80,
    "bookInfo": {
      "name": "学生3-课程书本"
    },
    "toolInfos": [
      {
        "name": "学生3-课程工具1"
      },
      {
        "name": "学生3-课程工具2"
      }
    ]
  }
]', 0, '', 1, '', 1, '', '2024-04-24 18:08:41.293787', '2024-04-24 18:08:41.293787', NULL);
INSERT INTO student (id, school_id, teacher_ids, code, name, sort, hobby_info, course_infos, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (4, 2, '[
  4,
  5
]', '4', '学校2-老师4、5-学生4', 4, '{
  "bookInfo": {
    "name": "学生4-爱好书本"
  },
  "levelType": 1,
  "toolInfos": [
    {
      "name": "学生4-爱好工具1"
    },
    {
      "name": "学生4-爱好工具2"
    }
  ],
  "achievementTypes": [
    1
  ],
  "primaryInterestName": "学生4-主要兴趣",
  "specificInterestNames": [
    "学生4-具体兴趣1",
    "学生4-具体兴趣2"
  ]
}', '[
  {
    "name": "学生4-课程",
    "credit": 90,
    "bookInfo": {
      "name": "学生4-课程书本"
    },
    "toolInfos": [
      {
        "name": "学生4-课程工具1"
      },
      {
        "name": "学生4-课程工具2"
      }
    ]
  }
]', 0, '', 1, '', 1, '', '2024-04-24 18:08:42.852000', '2024-04-24 18:10:31.852359', NULL);
INSERT INTO student (id, school_id, teacher_ids, code, name, sort, hobby_info, course_infos, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (5, 3, '[
  5,
  6
]', '5', '学校3-老师5、6-学生5', 5, '{
  "bookInfo": {
    "name": "学生5-爱好书本"
  },
  "levelType": 2,
  "toolInfos": [
    {
      "name": "学生5-爱好工具1"
    },
    {
      "name": "学生5-爱好工具2"
    }
  ],
  "achievementTypes": [
    1,
    2
  ],
  "primaryInterestName": "学生5-主要兴趣",
  "specificInterestNames": [
    "学生5-具体兴趣1",
    "学生5-具体兴趣2"
  ]
}', '[
  {
    "name": "学生5-课程",
    "credit": 100,
    "bookInfo": {
      "name": "学生5-课程书本"
    },
    "toolInfos": [
      {
        "name": "学生5-课程工具1"
      },
      {
        "name": "学生5-课程工具2"
      }
    ]
  }
]', 0, '', 1, '', 1, '', '2024-04-24 18:09:38.017089', '2024-04-24 18:09:38.017089', NULL);
INSERT INTO student (id, school_id, teacher_ids, code, name, sort, hobby_info, course_infos, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (6, 3, '[
  1,
  2,
  3,
  4,
  5,
  6
]', '6', '学校3-老师1、2、3、4、5、6-学生6', 6, '{
  "bookInfo": {
    "name": "学生6-爱好书本"
  },
  "levelType": 3,
  "toolInfos": [
    {
      "name": "学生6-爱好工具1"
    },
    {
      "name": "学生6-爱好工具2"
    }
  ],
  "achievementTypes": [
    3
  ],
  "primaryInterestName": "学生6-主要兴趣",
  "specificInterestNames": [
    "学生6-具体兴趣1",
    "学生6-具体兴趣2"
  ]
}', '[
  {
    "name": "学生6-课程",
    "credit": 110,
    "bookInfo": {
      "name": "学生6-课程书本"
    },
    "toolInfos": [
      {
        "name": "学生6-课程工具1"
      },
      {
        "name": "学生6-课程工具2"
      }
    ]
  }
]', 0, '', 1, '', 1, '', '2024-04-24 18:10:02.705476', '2024-04-24 18:10:02.705476', NULL);