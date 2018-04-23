

-------------------------------------------------------------------------------
--  menu
-------------------------------------------------------------------------------
CREATE TABLE AUTH_MENU(
        ID BIGINT NOT NULL,
	TYPE VARCHAR(50),
	CODE VARCHAR(50),
	TITLE VARCHAR(50),
	URL VARCHAR(200),
	PRIORITY INTEGER,
	DESCN VARCHAR(200),
	PARENT_ID BIGINT,
	PERM_ID BIGINT,
	TENANT_ID VARCHAR(64),
	CONSTRAINT PK_AUTH_MENU PRIMARY KEY(ID)
)ENGINE=INNODB CHARSET=UTF8













