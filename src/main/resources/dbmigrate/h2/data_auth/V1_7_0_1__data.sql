

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(306,'device','设备管理',31,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(306,'module','device','设备管理','/user/account-device-list.do',306,31,306);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(306,3);


INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(604,'book','图书管理',61,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(604,'module','book','图书管理','/book/book-info-list.do',604,61,604);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(604,6);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(605,'officesupply','办公用品管理',61,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(605,'module','officesupply','办公用品管理','/officesupply/officesupply-info-list.do',605,61,605);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(605,6);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(606,'visitor','访客管理',61,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(606,'module','visitor','访客管理','/visitor/visitor-info-list.do',606,61,606);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(606,6);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(607,'card','工卡管理',61,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(607,'module','card','工卡管理','/card/card-info-list.do',607,61,607);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(607,6);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(608,'seat','工位管理',61,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(608,'module','seat','工位管理','/seat/seat-info-list.do',608,61,608);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(608,6);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(609,'stamp','印章管理',61,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(609,'module','stamp','印章管理','/stamp/stamp-info-list.do',609,61,609);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(609,6);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(610,'asset','资产管理',61,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(610,'module','asset','资产管理','/asset/asset-info-list.do',610,61,610);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(610,6);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(611,'activity','活动管理',61,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(611,'module','activity','活动管理','/activity/activity-info-list.do',611,61,611);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(611,6);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(612,'sign','签到管理',61,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(612,'module','sign','签到管理','/sign/sign-info-list.do',612,61,612);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(612,6);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(613,'vote','投票管理',61,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(613,'module','vote','投票管理','/vote/vote-info-list.do',613,61,613);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(613,6);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(705,'employee','人员管理',71,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(705,'module','employee','人员管理','/employee/employee-info-list.do',705,71,705);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(705,7);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(706,'contract','合同管理',71,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(706,'module','contract','合同管理','/contract/contract-info-list.do',706,71,706);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(706,7);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(707,'salary','薪酬管理',71,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(707,'module','salary','薪酬管理','/salary/salary-info-list.do',707,71,707);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(707,7);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(708,'socialsecurity','社保管理',71,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(708,'module','socialsecurity','社保管理','/socialsecurity/socialsecurity-info-list.do',708,71,708);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(708,7);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(709,'recruit','招聘管理',71,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(709,'module','recruit','招聘管理','/recruit/recruit-info-list.do',709,71,709);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(709,7);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(710,'train','培训管理',71,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(710,'module','train','培训管理','/train/train-info-list.do',710,71,710);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(710,7);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(711,'performance','绩效管理',71,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(711,'module','performance','绩效管理','/performance/performance-info-list.do',711,71,711);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(711,7);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(712,'attendance','考勤管理',71,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(712,'module','attendance','考勤管理','/attendance/attendance-info-list.do',712,71,712);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(712,7);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(713,'leave','休假管理',71,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(713,'module','leave','休假管理','/leave/leave-info-list.do',713,71,713);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(713,7);

INSERT INTO AUTH_ROLE_DEF(ID,NAME,TENANT_ID) VALUES(9,'财务管理员','1');
INSERT INTO AUTH_ROLE(ID,NAME,TENANT_ID,ROLE_DEF_ID) VALUES(9,'财务管理员',1,'9');
INSERT INTO AUTH_PERM_TYPE(ID,NAME,TYPE,PRIORITY,TENANT_ID) VALUES(91,'财务管理',0,91,'1');
INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(901,'finance','财务管理',91,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(91,'system','finance','财务管理','/expense/expense-info-list.do',91,21,901);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(901,9);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(902,'expense','报销管理',91,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(902,'module','expense','报销管理','/expense/expense-info-list.do',902,91,902);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(902,9);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(903,'budget','预算管理',91,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(903,'module','budget','预算管理','/budget/budget-info-list.do',903,91,903);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(903,9);

INSERT INTO AUTH_ROLE_DEF(ID,NAME,TENANT_ID) VALUES(10,'CRM管理员','1');
INSERT INTO AUTH_ROLE(ID,NAME,TENANT_ID,ROLE_DEF_ID) VALUES(10,'CRM管理员',1,'10');
INSERT INTO AUTH_PERM_TYPE(ID,NAME,TYPE,PRIORITY,TENANT_ID) VALUES(101,'CRM',0,101,'1');
INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(1001,'crm','CRM',101,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(101,'system','crm','CRM','/customer/customer-info-list.do',101,21,1001);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(1001,10);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(1002,'customer','客户管理',101,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(1002,'module','customer','客户管理','/customer/customer-info-list.do',1002,101,1002);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(1002,10);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(1003,'product','产品管理',101,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(1003,'module','product','产品管理','/product/product-info-list.do',1003,101,1003);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(1003,10);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(1004,'business','业务管理',101,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(1004,'module','business','业务管理','/business/business-info-list.do',1004,101,1004);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(1004,10);

INSERT INTO AUTH_ROLE_DEF(ID,NAME,TENANT_ID) VALUES(11,'进销存管理员','1');
INSERT INTO AUTH_ROLE(ID,NAME,TENANT_ID,ROLE_DEF_ID) VALUES(11,'进销存管理员',1,'11');
INSERT INTO AUTH_PERM_TYPE(ID,NAME,TYPE,PRIORITY,TENANT_ID) VALUES(111,'进销存',0,111,'1');
INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(1101,'retail','进销存',111,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(111,'system','retail','进销存','/sale/sale-info-list.do',111,21,1101);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(1101,11);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(1102,'sale','销售管理',111,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(1102,'module','sale','销售管理','/sale/sale-info-list.do',1102,111,1102);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(1102,11);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(1103,'purchase','采购管理',111,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(1103,'module','purchase','采购管理','/purchase/purchase-info-list.do',1103,111,1103);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(1103,11);

INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(1104,'inventory','库存管理',111,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(1104,'module','inventory','库存管理','/inventory/inventory-info-list.do',1104,111,1104);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(1104,11);

UPDATE AUTH_MENU SET PRIORITY=9999 WHERE ID=81;


INSERT INTO AUTH_PERM(ID,CODE,NAME,PERM_TYPE_ID,TENANT_ID) VALUES(108,'bpm','流程中心',11,'1');
--INSERT INTO AUTH_MENU(ID,TYPE,CODE,TITLE,URL,PRIORITY,PARENT_ID,PERM_ID) VALUES(17,'module','bpm','流程中心','/bpm/workspace-home.do',12,11,108);
INSERT INTO AUTH_PERM_ROLE_DEF(PERM_ID,ROLE_DEF_ID) VALUES(108,8);

UPDATE AUTH_MENU SET PARENT_ID=11,URL='/pim/pim-schedule-list.do' WHERE ID=13;
UPDATE AUTH_MENU SET PARENT_ID=11 WHERE ID=14;
UPDATE AUTH_MENU SET PARENT_ID=11 WHERE ID=15;

DELETE FROM AUTH_MENU WHERE ID=12;

UPDATE AUTH_MENU SET DISPLAY='true';
