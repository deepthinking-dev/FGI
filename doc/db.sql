﻿/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/2/10 20:35:32                           */
/*==============================================================*/


drop table if exists AlgRelation;

drop table if exists AlgUserRelation;

drop table if exists AlgorithmCondition;

drop table if exists AlgorithmModule;

drop table if exists Module;

drop table if exists ModuleFields;

drop table if exists ModuleFunc;

/*==============================================================*/
/* Table: AlgRelation                                           */
/*==============================================================*/
create table AlgRelation
(
   ID                   varchar(20) not null comment '主键ID',
   AlgorithmID          varchar(20) comment '模块ID',
   PreAlgorithm         varchar(20) comment '前序模块ID',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table AlgRelation comment '算子关系';

/*==============================================================*/
/* Table: AlgUserRelation                                       */
/*==============================================================*/
create table AlgUserRelation
(
   ID                   varchar(20) not null comment '主键ID',
   AlgorithmID          varchar(20) comment '模块ID',
   UserName             varchar(30) comment '用户名',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table AlgUserRelation comment '算子用户关系';

/*==============================================================*/
/* Table: AlgorithmCondition                                    */
/*==============================================================*/
create table AlgorithmCondition
(
   ID                   varchar(20) comment '主键ID',
   RelatID              varchar(20) comment '模块关系ID',
   LogicRelation        varchar(20) comment '逻辑关系',
   LogicValue           numeric comment '逻辑值',
   Remark               varchar(500) comment '备注'
);

alter table AlgorithmCondition comment '算子运行条件';

/*==============================================================*/
/* Table: AlgorithmModule                                       */
/*==============================================================*/
create table AlgorithmModule
(
   ID                   varchar(20) not null comment '主键ID',
   ModuleID             varchar(20),
   AlgorithmName        varchar(200) comment '模块名',
   AlgorithmAuthor      varchar(20) comment '模块作者',
   IsPublic             numeric comment '是否公共模块',
   AlgorithmType        numeric comment '模块类型(算法公式；逻辑条件)',
   AlgorithmFun         varchar(500) comment '公式',
   "Desc"               varchar(500) comment '描述',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table AlgorithmModule comment '算子模块';

/*==============================================================*/
/* Table: Module                                                */
/*==============================================================*/
create table Module
(
   ID                   varchar(20) not null comment '主键ID',
   ModuleName           varchar(50),
   SqlUrl               varchar(200),
   Table                varchar(100),
   ModuleGroup          varchar(100),
   "Desc"               varchar(500),
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Module comment '模板';

/*==============================================================*/
/* Table: ModuleFields                                          */
/*==============================================================*/
create table ModuleFields
(
   ID                   varchar(20) not null,
   ModuleID             varchar(20),
   FieldName            varchar(20),
   FieldType            varchar(20),
   Remark               varchar(500),
   primary key (ID)
);

alter table ModuleFields comment '模板包含字段';

/*==============================================================*/
/* Table: ModuleFunc                                            */
/*==============================================================*/
create table ModuleFunc
(
   ID                   varchar(20) not null comment '主键ID',
   AlgID                varchar(20) comment '模块ID',
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

alter table ModuleFunc comment '公式变量';

alter table AlgRelation add constraint FK_Reference_4 foreign key (AlgorithmID)
      references AlgorithmModule (ID) on delete restrict on update restrict;

alter table AlgUserRelation add constraint FK_Reference_3 foreign key (AlgorithmID)
      references AlgorithmModule (ID) on delete restrict on update restrict;

alter table AlgorithmCondition add constraint FK_Reference_1 foreign key (RelatID)
      references AlgRelation (ID) on delete restrict on update restrict;

alter table AlgorithmModule add constraint FK_Reference_6 foreign key (ModuleID)
      references Module (ID) on delete restrict on update restrict;

alter table ModuleFields add constraint FK_Reference_7 foreign key (ModuleID)
      references Module (ID) on delete restrict on update restrict;

alter table ModuleFunc add constraint FK_Reference_5 foreign key (AlgID)
      references AlgorithmModule (ID) on delete restrict on update restrict;

