/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/4/21 11:05:21                           */
/*==============================================================*/


drop table if exists Table_Algorithm;

drop table if exists Table_AlgorithmCondition;

drop table if exists Table_Func;

drop table if exists Table_GroupData;

drop table if exists Table_InterfaceParameters;

drop table if exists Table_InterfaceRole;

drop table if exists Table_Module;

drop table if exists Table_ModuleField;

drop table if exists Table_ModuleUserRelation;

drop table if exists Table_OperatorInterface;

drop table if exists Table_Role;

/*==============================================================*/
/* Table: Table_Algorithm                                       */
/*==============================================================*/
create table Table_Algorithm
(
   ID                   int not null auto_increment comment '主键ID',
   AlgorithmName        varchar(200) comment '算子名称',
   AlgorithmAuthor      varchar(20) comment '算子作者',
   IsPublic             numeric comment '是否公共算子',
   AlgorithmType        numeric comment '算子类型(引用；算法公式)',
   AlgorithmFun         varchar(500) comment '公式',
   Des                  varchar(500) comment '描述',
   UserID               int comment '用户ID',
   AlgorithmGroup       varchar(100) comment '所属组',
   Remark               varchar(500) comment '备注',
   Status               varchar(50) comment '状态',
   Remark2              text comment '备注2',
   primary key (ID)
);

alter table Table_Algorithm comment '算子模块';

/*==============================================================*/
/* Table: Table_AlgorithmCondition                              */
/*==============================================================*/
create table Table_AlgorithmCondition
(
   ID                   int not null auto_increment comment '主键ID',
   InterfaceRoleID      int comment '算法算子ID',
   InterfaceParametersID varchar(64) comment '接口参数ID',
   Behavior             varchar(20) comment '行为',
   ValueSources         int comment '值来源',
   expression           varchar(100) comment '表达式',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_AlgorithmCondition comment '输出输入动作';

/*==============================================================*/
/* Table: Table_Func                                            */
/*==============================================================*/
create table Table_Func
(
   ID                   int not null auto_increment comment '主键ID',
   ParameterName        varchar(50) comment '参数名称',
   AlgorithmID          int comment '模块ID',
   VarName              varchar(20) comment '变量名称',
   VarType              varchar(20) comment '变量类型：
            常量
            对象
            基本类型',
   ValValue             varchar(20) comment '变量值：
            变量类型为常量时，此处为具体数值
            类型为对象时，此处为对象ID
            类型为基本类型时，此处为基本类型名称
            ',
   InOrOut              numeric comment '输入输出',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_Func comment '算法参数定义';

/*==============================================================*/
/* Table: Table_GroupData                                       */
/*==============================================================*/
create table Table_GroupData
(
   id                   int not null auto_increment comment 'ID',
   groupName            varchar(100) comment '组名称同类型下不能重复',
   groupType            int comment '类型:模型，算法，规则',
   primary key (id)
);

alter table Table_GroupData comment '分组数据';

/*==============================================================*/
/* Table: Table_InterfaceParameters                             */
/*==============================================================*/
create table Table_InterfaceParameters
(
   ID                   varchar(64) not null comment '算子接口参数信息ID',
   InterfaceID          varchar(64) comment '接口ID',
   ParametersSources    varchar(50) comment '参数来源',
   ParametersName       varchar(50) comment '参数名称',
   inOrOut              int comment '输入输出',
   primary key (ID)
);

alter table Table_InterfaceParameters comment '算子接口参数信息表';

/*==============================================================*/
/* Table: Table_InterfaceRole                                   */
/*==============================================================*/
create table Table_InterfaceRole
(
   ID                   int not null auto_increment comment '主键ID',
   RoleID               int comment '规则ID',
   InterfaceID          varchar(64),
   ParametersID         varchar(64) comment '接口参数ID',
   PreInterfaceID       varchar(64) comment '前序接口ID',
   PreParametersID      varchar(64) comment '前序接口参数ID',
   Des                  varchar(500) comment '描述',
   Remark               varchar(500) comment '备注',
   ActionRelation       varchar(500) comment '动作关系式',
   PreActionRelation    varchar(500) comment '前序动作关系式',
   primary key (ID)
);

alter table Table_InterfaceRole comment '算法算子关系';

/*==============================================================*/
/* Table: Table_Module                                          */
/*==============================================================*/
create table Table_Module
(
   ID                   int not null auto_increment comment '主键ID',
   ModuleName           varchar(50) comment '模型名称',
   SqlUrl               varchar(200) comment '数据库连接',
   ModuleGroup          varchar(100) comment '模型组',
   Des                  varchar(500) comment '模型描述',
   UserID               int comment '用户ID',
   Status               varchar(100) comment '状态',
   Remark               varchar(500) comment '备注',
   Remark2              varchar(500) comment '备注2',
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
   EnglishName          varchar(20) comment '英文名称',
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
   UserName             varchar(30) comment '用户名',
   Remark               varchar(500) comment '备注',
   primary key (ID)
);

alter table Table_ModuleUserRelation comment '算子用户关系';

/*==============================================================*/
/* Table: Table_OperatorInterface                               */
/*==============================================================*/
create table Table_OperatorInterface
(
   ID                   varchar(64) not null comment '接口ID',
   RoleID               int comment '规则ID',
   AlgorithmID          int comment '算子ID',
   InterfaceName        varchar(50) comment '接口名称',
   primary key (ID)
);

alter table Table_OperatorInterface comment '算子接口表';

/*==============================================================*/
/* Table: Table_Role                                            */
/*==============================================================*/
create table Table_Role
(
   ID                   int not null auto_increment comment '主键ID',
   RoleName             varchar(200) comment '规则名称',
   Des                  varchar(500) comment '规则描述',
   EntranceNote         varchar(500) comment '入口备注',
   coordinate           text comment '坐标',
   UuserID              int comment '用户ID',
   RoleGroup            varchar(100) comment '所属组',
   Status               varchar(100) comment '状态',
   Remark               varchar(500) comment '备注',
   Remark2              varchar(500) comment '备注2',
   Remark3              varchar(500) comment '备注3',
   primary key (ID)
);

alter table Table_Role comment '算法规则';

alter table Table_AlgorithmCondition add constraint FK_Reference_1 foreign key (InterfaceRoleID)
      references Table_InterfaceRole (ID) on delete restrict on update restrict;

alter table Table_Func add constraint FK_Reference_2 foreign key (AlgorithmID)
      references Table_Algorithm (ID) on delete restrict on update restrict;

alter table Table_InterfaceParameters add constraint FK_Reference_6 foreign key (InterfaceID)
      references Table_OperatorInterface (ID) on delete restrict on update restrict;

alter table Table_InterfaceRole add constraint FK_Reference_7 foreign key (InterfaceID)
      references Table_OperatorInterface (ID) on delete restrict on update restrict;

alter table Table_ModuleField add constraint FK_Reference_3 foreign key (ModuleID)
      references Table_Module (ID) on delete restrict on update restrict;

alter table Table_OperatorInterface add constraint FK_Reference_4 foreign key (RoleID)
      references Table_Role (ID) on delete restrict on update restrict;

alter table Table_OperatorInterface add constraint FK_Reference_5 foreign key (AlgorithmID)
      references Table_Algorithm (ID) on delete restrict on update restrict;

