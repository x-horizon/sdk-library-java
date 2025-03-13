IF OBJECT_ID('school', 'U') IS NOT NULL
DROP TABLE school;


GO

CREATE TABLE dbo.school
(
    id           BIGINT                              NOT NULL
        PRIMARY KEY,
    name         NVARCHAR(64)  DEFAULT ''            NOT NULL,
    type         SMALLINT      DEFAULT 0             NOT NULL,
    address      NVARCHAR(255) DEFAULT ''            NOT NULL,
    enable_is    BIT           DEFAULT 0             NOT NULL,
    version      BIGINT        DEFAULT 0             NOT NULL,
    remark       NVARCHAR(255) DEFAULT ''            NOT NULL,
    creator_id   BIGINT        DEFAULT 0             NOT NULL,
    creator_name NVARCHAR(64)  DEFAULT ''            NOT NULL,
    updater_id   BIGINT        DEFAULT 0             NOT NULL,
    updater_name NVARCHAR(64)  DEFAULT ''            NOT NULL,
    create_time  DATETIME2(6)  DEFAULT SYSDATETIME() NOT NULL,
    update_time  DATETIME2(6)  DEFAULT SYSDATETIME() NOT NULL,
    delete_time  DATETIME2(6)
)
    GO

EXEC sp_addextendedproperty 'MS_Description', N'学校信息', 'SCHEMA', 'dbo', 'TABLE', 'school'
GO

EXEC sp_addextendedproperty 'MS_Description', 'id', 'SCHEMA', 'dbo', 'TABLE', 'school', 'COLUMN', 'id'
GO

EXEC sp_addextendedproperty 'MS_Description', N'名字', 'SCHEMA', 'dbo', 'TABLE', 'school', 'COLUMN', 'name'
GO

EXEC sp_addextendedproperty 'MS_Description', N'类型', 'SCHEMA', 'dbo', 'TABLE', 'school', 'COLUMN', 'type'
GO

EXEC sp_addextendedproperty 'MS_Description', N'地址', 'SCHEMA', 'dbo', 'TABLE', 'school', 'COLUMN', 'address'
GO

EXEC sp_addextendedproperty 'MS_Description', N'是否启用', 'SCHEMA', 'dbo', 'TABLE', 'school', 'COLUMN', 'enable_is'
GO

EXEC sp_addextendedproperty 'MS_Description', N'版本号', 'SCHEMA', 'dbo', 'TABLE', 'school', 'COLUMN', 'version'
GO

EXEC sp_addextendedproperty 'MS_Description', N'备注', 'SCHEMA', 'dbo', 'TABLE', 'school', 'COLUMN', 'remark'
GO

EXEC sp_addextendedproperty 'MS_Description', N'创建人id', 'SCHEMA', 'dbo', 'TABLE', 'school', 'COLUMN', 'creator_id'
GO

EXEC sp_addextendedproperty 'MS_Description', N'创建人名字', 'SCHEMA', 'dbo', 'TABLE', 'school', 'COLUMN',
     'creator_name'
GO

EXEC sp_addextendedproperty 'MS_Description', N'更新人id', 'SCHEMA', 'dbo', 'TABLE', 'school', 'COLUMN', 'updater_id'
GO

EXEC sp_addextendedproperty 'MS_Description', N'更新人名字', 'SCHEMA', 'dbo', 'TABLE', 'school', 'COLUMN',
     'updater_name'
GO

EXEC sp_addextendedproperty 'MS_Description', N'创建时间', 'SCHEMA', 'dbo', 'TABLE', 'school', 'COLUMN', 'create_time'
GO

EXEC sp_addextendedproperty 'MS_Description', N'更新时间', 'SCHEMA', 'dbo', 'TABLE', 'school', 'COLUMN', 'update_time'
GO

EXEC sp_addextendedproperty 'MS_Description', N'删除时间', 'SCHEMA', 'dbo', 'TABLE', 'school', 'COLUMN', 'delete_time'
GO

INSERT INTO school (id, name, type, address, enable_is, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (1, '学校1', 1, '西藏自治区茂名市兴国县', 1, 0, '', 1, '', 1, '', '2024-04-24 17:57:26.102855', '2024-04-24 17:57:26.102855', NULL);
INSERT INTO school (id, name, type, address, enable_is, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (2, '学校2', 2, '西藏自治区茂名市兴国县', 1, 0, '', 1, '', 1, '', '2024-04-24 17:57:32.326921', '2024-04-24 17:57:32.326921', NULL);
INSERT INTO school (id, name, type, address, enable_is, version, remark, creator_id, creator_name, updater_id, updater_name, create_time, update_time, delete_time)
VALUES (3, '学校3', 3, '西藏自治区茂名市兴国县', 1, 0, '', 1, '', 1, '', '2024-04-24 17:57:37.165764', '2024-04-24 17:57:37.165764', NULL);