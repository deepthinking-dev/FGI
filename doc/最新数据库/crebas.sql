/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/3/27 16:23:15                           */
/*==============================================================*/


drop table if exists Table_Algorithm;

drop table if exists Table_AlgorithmCondition;

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
   ModuleID             int comment '模型ID',
   AlgorithmName        varchar(200) comment '算子名称',
   AlgorithmAuthor      varchar(20) comment '算子作者',
   IsPublic             numeric comment '是否公共算子',
   AlgorithmType        numeric comment '算子类型(引用；算法公式；逻辑条件)',
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
   FuncID               int comment '参数ID',
   Behavior             varchar(20) comment '行为',
   ValueSources         numeric comment '值来源',
   expression           varchar(100) comment '表达式',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_AlgorithmCondition comment '输出输入动作';

/*==============================================================*/
/* Table: Table_AlgorithmRole                                   */
/*==============================================================*/
create table Table_AlgorithmRole
(
   ID                   int not null comment '主键ID',
   RoleID               int comment '规则ID',
   AlgorithmID          int comment '算子ID',
   FuncID               int comment '参数ID',
   PreAlgorithmID       int comment '前序算子ID',
   PreFuncID            int comment '前序算子参数ID',
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
   ID                   int not null comment '主键ID',
   AlgorithmID          int comment '模块ID',
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
   InOrOut              numeric comment '输入输出',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_Func comment '算子参数定义';

/*==============================================================*/
/* Table: Table_Module                                          */
/*==============================================================*/
create table Table_Module
(
   ID                   int not null auto_increment comment '主键ID',
   ModuleName           varchar(50) comment '模型名称',
   SqlUrl               varchar(200) comment '数据库连接',
   ModuleGroup          varchar(20) comment '模型组',
   Des                  varchar(500) comment '模型描述',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_Module comment '模型';

/*==============================================================*/
/* Table: Table_ModuleField                                     */
/*==============================================================*/
create table Table_ModuleField
(
   ID                   int not null auto_increment comment '主键ID',
   ModuleID             int not null comment '模型ID',
   FieldName            varchar(20) comment '字段名称',
   FieldType            varchar(20) comment '字段类型',
   TableName            varchar(20) comment '数据表名称',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_ModuleField comment '模型包含字段';

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
   EntranceNote         varchar(500) comment '入口备注',
   coordinate           text comment '坐标',
   primary key (ID)
);

alter table Table_Role comment '算法规则';

alter table Table_Algorithm add constraint FK_Reference_6 foreign key (ModuleID)
      references Table_Module (ID) on delete restrict on update restrict;

alter table Table_AlgorithmCondition add constraint FK_Reference_1 foreign key (AlgorithmRoleID)
      references Table_AlgorithmRole (ID) on delete restrict on update restrict;

alter table Table_AlgorithmCondition add constraint FK_Reference_10 foreign key (FuncID)
      references Table_Func (ID) on delete restrict on update restrict;

alter table Table_AlgorithmRole add constraint FK_Reference_8 foreign key (RoleID)
      references Table_Role (ID) on delete restrict on update restrict;

alter table Table_AlgorithmRole add constraint FK_Reference_9 foreign key (AlgorithmID)
      references Table_Algorithm (ID) on delete restrict on update restrict;

alter table Table_Func add constraint FK_Reference_5 foreign key (AlgorithmID)
      references Table_Algorithm (ID) on delete restrict on update restrict;

alter table Table_ModuleField add constraint FK_Reference_7 foreign key (ModuleID)
      references Table_Module (ID) on delete restrict on update restrict;

alter table Table_ModuleUserRelation add constraint FK_Reference_3 foreign key (ModuleID)
      references Table_Algorithm (ID) on delete restrict on update restrict;

