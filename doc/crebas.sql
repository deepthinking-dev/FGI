/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/2/24 22:04:23                           */
/*==============================================================*/


drop table if exists Table_Algorithm;

drop table if exists Table_AlgorithmCondition;

drop table if exists Table_AlgorithmIF;

drop table if exists Table_AlgorithmRole;

drop table if exists Table_Func;

drop table if exists Table_Module;

drop table if exists Table_ModuleField;

drop table if exists Table_ModuleUserRelation;

drop table if exists Table_Role;

/*==============================================================*/
/* Table: Table_Algorithm                                       */
/*==============================================================*/
create table Table_Algorithm
(
   ID                   int not null comment '主键ID',
   ModuleID             int comment '模板ID',
   AlgorithmName        varchar(200) comment '算子名称',
   AlgorithmAuthor      varchar(20) comment '算子作者',
   IsPublic             numeric comment '是否公共算子',
   AlgorithmType        numeric comment '算子类型(算法公式；逻辑条件)',
   AlgorithmFun         varchar(500) comment '公式',
   Des                  varchar(500) comment '描述',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_Algorithm comment '算子模块';

/*==============================================================*/
/* Table: Table_AlgorithmCondition                              */
/*==============================================================*/
create table Table_AlgorithmCondition
(
   ID                   int not null comment '主键ID',
   AlgorithmRoleID      int comment '算法算子ID',
   LogicRelation        varchar(20) comment '逻辑关系',
   LogicValue           numeric comment '逻辑值',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_AlgorithmCondition comment '算子运行条件';

/*==============================================================*/
/* Table: Table_AlgorithmIF                                     */
/*==============================================================*/
create table Table_AlgorithmIF
(
   ID                   int not null comment '主键ID',
   ModuleID             int comment '算子ID',
   IFName               varchar(20) comment '接口名称',
   IFType               varchar(20) comment '接口类型：',
   "InOut"              numeric comment '输入输出
            ',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_AlgorithmIF comment '算子接口';

/*==============================================================*/
/* Table: Table_AlgorithmRole                                   */
/*==============================================================*/
create table Table_AlgorithmRole
(
   ID                   int not null comment '主键ID',
   RoleID               int comment '规则ID',
   AlgorithmID          int comment '算子ID',
   PreAlgorithmID       int comment '前序算子ID',
   Des                  varchar(500) comment '描述',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_AlgorithmRole comment '算法算子关系';

/*==============================================================*/
/* Table: Table_Func                                            */
/*==============================================================*/
create table Table_Func
(
   ID                   int not null auto_increment comment '主键ID',
   ModuleID             int comment '模块ID',
   VarName              varchar(20) comment '变量名称',
   VarType              varchar(20) comment '变量类型：
            常量
            数据项
            其他模块计算结果',
   ValValue             varchar(20) comment '变量值：
            变量类型为常量时，此处为具体数值
            类型为数据项时，此处模块中字段名称
            类型为其他计算结果时，此处为其他模块的ID。
            ',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_Func comment '公式变量';

/*==============================================================*/
/* Table: Table_Module                                          */
/*==============================================================*/
create table Table_Module
(
   ID                   int not null auto_increment comment '主键ID',
   ModuleName           varchar(50) comment '模板名称',
   SqlUrl               varchar(200) comment '数据库连接',
   Tab                  varchar(100) comment '对应物理表',
   ModuleGroup          varchar(20) comment '模板组',
   Des                  varchar(500) comment '模板描述',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_Module comment '模板';

/*==============================================================*/
/* Table: Table_ModuleField                                     */
/*==============================================================*/
create table Table_ModuleField
(
   ID                   int not null auto_increment comment '主键ID',
   ModuleID             int comment '模板ID',
   FieldName            varchar(20) comment '字段名称',
   FieldType            varchar(20) comment '字段类型',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_ModuleField comment '模板包含字段';

/*==============================================================*/
/* Table: Table_ModuleUserRelation                              */
/*==============================================================*/
create table Table_ModuleUserRelation
(
   ID                   int not null auto_increment comment '主键ID',
   ModuleID             int comment '算子ID',
   UserName             varchar(30) comment '用户名',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_ModuleUserRelation comment '算子用户关系';

/*==============================================================*/
/* Table: Table_Role                                            */
/*==============================================================*/
create table Table_Role
(
   ID                   int not null auto_increment comment '主键ID',
   RoleName             varchar(20) comment '规则名称',
   Des                  varchar(500) comment '规则描述',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_Role comment '算法规则';

CREATE TABLE `table_algorithmcoordinate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `RoleId` int(11) NOT NULL,
  `CoordinateInfo` blob,
  PRIMARY KEY (`id`),
  KEY `RoleIDF` (`RoleId`),
  CONSTRAINT `RoleIDF` FOREIGN KEY (`RoleId`) REFERENCES `table_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



alter table Table_Algorithm add constraint FK_Reference_6 foreign key (ModuleID)
      references Table_Module (ID) on delete restrict on update restrict;

alter table Table_AlgorithmCondition add constraint FK_Reference_1 foreign key (AlgorithmRoleID)
      references Table_AlgorithmRole (ID) on delete restrict on update restrict;

alter table Table_AlgorithmIF add constraint FK_Reference_10 foreign key (ModuleID)
      references Table_Algorithm (ID) on delete restrict on update restrict;

alter table Table_AlgorithmRole add constraint FK_Reference_8 foreign key (RoleID)
      references Table_Role (ID) on delete restrict on update restrict;

alter table Table_AlgorithmRole add constraint FK_Reference_9 foreign key (AlgorithmID)
      references Table_Algorithm (ID) on delete restrict on update restrict;

alter table Table_Func add constraint FK_Reference_5 foreign key (ModuleID)
      references Table_Algorithm (ID) on delete restrict on update restrict;

alter table Table_ModuleField add constraint FK_Reference_7 foreign key (ModuleID)
      references Table_Module (ID) on delete restrict on update restrict;

alter table Table_ModuleUserRelation add constraint FK_Reference_3 foreign key (ModuleID)
      references Table_Algorithm (ID) on delete restrict on update restrict;

