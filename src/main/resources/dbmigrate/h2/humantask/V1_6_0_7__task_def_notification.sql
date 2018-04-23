

-------------------------------------------------------------------------------
--  task def notification
-------------------------------------------------------------------------------
CREATE TABLE TASK_DEF_NOTIFICATION(
	ID BIGINT NOT NULL,
	EVENT_NAME VARCHAR(100),
	RECEIVER VARCHAR(200),
	TYPE VARCHAR(50),
	TEMPLATE_CODE VARCHAR(200),
	BASE_ID BIGINT,
        CONSTRAINT PK_TASK_DEF_NOTIFICATION PRIMARY KEY(ID),
        CONSTRAINT FK_TASK_DEF_NOTIFICATION_BASE FOREIGN KEY(BASE_ID) REFERENCES TASK_DEF_BASE(ID)
);

COMMENT ON TABLE TASK_DEF_NOTIFICATION IS '任务定义提醒';
COMMENT ON COLUMN TASK_DEF_NOTIFICATION.ID IS '主键';
COMMENT ON COLUMN TASK_DEF_NOTIFICATION.EVENT_NAME IS '事件名称';
COMMENT ON COLUMN TASK_DEF_NOTIFICATION.RECEIVER IS '接收人';
COMMENT ON COLUMN TASK_DEF_NOTIFICATION.TYPE IS '类型';
COMMENT ON COLUMN TASK_DEF_NOTIFICATION.TEMPLATE_CODE IS '模板编号';
COMMENT ON COLUMN TASK_DEF_NOTIFICATION.BASE_ID IS '外键，任务定义';


