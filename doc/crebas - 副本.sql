/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/2/11 18:40:53                           */
/*==============================================================*/


drop table if exists Table_Algorithm;

drop table if exists Table_AlgorithmCondition;

drop table if exists Table_AlgorithmRelation;

drop table if exists Table_Func;

drop table if exists Table_Module;

drop table if exists Table_ModuleField;

drop table if exists Table_ModuleUserRelation;

/*==============================================================*/
/* Table: Table_Algorithm                                       */
/*==============================================================*/
create table Table_Algorithm
(
   ID                   varchar(20) not null comment '主键ID',
   ModuleID             varchar(20),
   AlgorithmName        varchar(200) comment '模块名',
   AlgorithmAuthor      varchar(20) comment '模块作者',
   IsPublic             numeric comment '是否公共模块',
   AlgorithmType        numeric comment '模块类型(算法公式；逻辑条件)',
   AlgorithmFun         varchar(500) comment '公式',
   Des           varchar(500) comment '描述',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_Algorithm comment '算子模块';

/*==============================================================*/
/* Table: Table_AlgorithmCondition                              */
/*==============================================================*/
create table Table_AlgorithmCondition
(
   ID                   varchar(20) not null comment '主键ID',
   AlgorithmRelationID  varchar(20) comment '模块关系ID',
   LogicRelation        varchar(20) comment '逻辑关系',
   LogicValue           numeric comment '逻辑值',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_AlgorithmCondition comment '算子运行条件';

/*==============================================================*/
/* Table: Table_AlgorithmRelation                               */
/*==============================================================*/
create table Table_AlgorithmRelation
(
   ID                   varchar(20) not null comment '主键ID',
   AlgorithmID          varchar(20) comment '模块ID',
   PreAlgorithmID       varchar(20) comment '前序模块ID',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_AlgorithmRelation comment '算子关系';

/*==============================================================*/
/* Table: Table_Func                                            */
/*==============================================================*/
create table Table_Func
(
   ID                   varchar(20) not null comment '主键ID',
   ModuleID             varchar(20) comment '模块ID',
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
   ID                   varchar(20) not null comment '主键ID',
   ModuleName           varchar(50),
   SqlUrl               varchar(200),
   Tab                varchar(100),
   FieldName            varchar(50) comment '字段名称',
   FieldType            varchar(20) comment '字段类型',
   ModuleGroup          varchar(100),
   Des           varchar(500),
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_Module comment '模板';

/*==============================================================*/
/* Table: Table_ModuleField                                     */
/*==============================================================*/
create table Table_ModuleField
(
   ID                   varchar(20) not null,
   ModuleID             varchar(20),
   FieldName            varchar(20),
   FieldType            varchar(20),
   Remark               varchar(500),
   primary key (ID)
);

alter table Table_ModuleField comment '模板包含字段';

/*==============================================================*/
/* Table: Table_ModuleUserRelation                              */
/*==============================================================*/
create table Table_ModuleUserRelation
(
   ID                   varchar(20) not null comment '主键ID',
   ModuleID             varchar(20) comment '模块ID',
   UserName             varchar(30) comment '用户名',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_ModuleUserRelation comment '算子用户关系';

alter table Table_Algorithm add constraint FK_Reference_6 foreign key (ModuleID)
      references Table_Module (ID) on delete restrict on update restrict;

alter table Table_AlgorithmCondition add constraint FK_Reference_1 foreign key (AlgorithmRelationID)
      references Table_AlgorithmRelation (ID) on delete restrict on update restrict;

alter table Table_AlgorithmRelation add constraint FK_Reference_4 foreign key (AlgorithmID)
      references Table_Algorithm (ID) on delete restrict on update restrict;

alter table Table_Func add constraint FK_Reference_5 foreign key (ModuleID)
      references Table_Algorithm (ID) on delete restrict on update restrict;

alter table Table_ModuleField add constraint FK_Reference_7 foreign key (ModuleID)
      references Table_Module (ID) on delete restrict on update restrict;

alter table Table_ModuleUserRelation add constraint FK_Reference_3 foreign key (ModuleID)
      references Table_Algorithm (ID) on delete restrict on update restrict;

