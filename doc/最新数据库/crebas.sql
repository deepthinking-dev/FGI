/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/4/6 21:38:44                            */
/*==============================================================*/


drop table if exists Table_Algorithm;

drop table if exists Table_AlgorithmCondition;

drop table if exists Table_Func;

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
   ID                   int not null auto_increment comment '����ID',
   AlgorithmName        varchar(200) comment '��������',
   AlgorithmAuthor      varchar(20) comment '��������',
   IsPublic             numeric comment '�Ƿ񹫹�����',
   AlgorithmType        numeric comment '��������(���ã��㷨��ʽ)',
   AlgorithmFun         varchar(500) comment '��ʽ',
   Des                  varchar(500) comment '����',
   UserID               int comment '�û�ID',
   Remark               varchar(500) comment '��ע',
   primary key (ID)
);

alter table Table_Algorithm comment '����ģ��';

/*==============================================================*/
/* Table: Table_AlgorithmCondition                              */
/*==============================================================*/
create table Table_AlgorithmCondition
(
   ID                   int not null auto_increment comment '����ID',
   InterfaceRoleID      int comment '�㷨����ID',
   InterfaceParametersID int comment '�ӿڲ���ID',
   Behavior             varchar(20) comment '��Ϊ',
   ValueSources         int comment 'ֵ��Դ',
   expression           varchar(100) comment '���ʽ',
   Remark               varchar(500) comment '��ע',
   primary key (ID)
);

alter table Table_AlgorithmCondition comment '������붯��';

/*==============================================================*/
/* Table: Table_Func                                            */
/*==============================================================*/
create table Table_Func
(
   ID                   int not null auto_increment comment '����ID',
   AlgorithmID          int comment 'ģ��ID',
   VarName              varchar(20) comment '��������',
   VarType              varchar(20) comment '�������ͣ�
            ����
            ������
            ����ģ�������',
   ValValue             varchar(20) comment '����ֵ��
            ��������Ϊ����ʱ���˴�Ϊ������ֵ
            ����Ϊ������ʱ���˴�ģ�����ֶ�����
            ����Ϊ����������ʱ���˴�Ϊ����ģ���ID��
            ',
   InOrOut              numeric comment '�������',
   Remark               varchar(500) comment '��ע',
   primary key (ID)
);

alter table Table_Func comment '���Ӳ�������';

/*==============================================================*/
/* Table: Table_InterfaceParameters                             */
/*==============================================================*/
create table Table_InterfaceParameters
(
   ID                   varchar(64) not null comment '���ӽӿڲ�����ϢID',
   InterfaceID          varchar(64) comment '�ӿ�ID',
   ParametersSources    varchar(50) comment '������Դ',
   ParametersName       varchar(50) comment '��������',
   inOrOut              numeric comment '�������',
   primary key (ID)
);

alter table Table_InterfaceParameters comment '���ӽӿڲ�����Ϣ��';

/*==============================================================*/
/* Table: Table_InterfaceRole                                   */
/*==============================================================*/
create table Table_InterfaceRole
(
   ID                   int not null auto_increment comment '����ID',
   RoleID               int comment '����ID',
   InterfaceID          varchar(64) comment '�ӿ�ID',
   ParametersID         varchar(64) comment '�ӿڲ���ID',
   PreInterfaceID       varchar(64) comment 'ǰ��ӿ�ID',
   PreParametersID      varchar(64) comment 'ǰ��ӿڲ���ID',
   Des                  varchar(500) comment '����',
   Remark               varchar(500) comment '��ע',
   primary key (ID)
);

alter table Table_InterfaceRole comment '�㷨�ӿڹ�ϵ';

/*==============================================================*/
/* Table: Table_Module                                          */
/*==============================================================*/
create table Table_Module
(
   ID                   int not null auto_increment comment '����ID',
   ModuleName           varchar(50) comment 'ģ������',
   SqlUrl               varchar(200) comment '���ݿ�����',
   ModuleGroup          varchar(20) comment 'ģ����',
   Des                  varchar(500) comment 'ģ������',
   UserID               int comment '�û�ID',
   Remark               varchar(500) comment '��ע',
   primary key (ID)
);

alter table Table_Module comment 'ģ��';

/*==============================================================*/
/* Table: Table_ModuleField                                     */
/*==============================================================*/
create table Table_ModuleField
(
   ID                   int not null auto_increment comment '����ID',
   ModuleID             int not null comment 'ģ��ID',
   FieldName            varchar(20) comment '�ֶ�����',
   FieldType            varchar(20) comment '�ֶ�����',
   TableName            varchar(20) comment '���ݱ�����',
   Remark               varchar(500) comment '��ע',
   primary key (ID)
);

alter table Table_ModuleField comment 'ģ�Ͱ����ֶ�';

/*==============================================================*/
/* Table: Table_ModuleUserRelation                              */
/*==============================================================*/
create table Table_ModuleUserRelation
(
   ID                   int not null auto_increment comment '����ID',
   UserName             varchar(30) comment '�û���',
   Remark               varchar(500) comment '��ע',
   primary key (ID)
);

alter table Table_ModuleUserRelation comment '�����û���ϵ';

/*==============================================================*/
/* Table: Table_OperatorInterface                               */
/*==============================================================*/
create table Table_OperatorInterface
(
   ID                   varchar(64) not null comment '�ӿ�ID',
   RoleID               int comment '����ID',
   AlgorithmID          int comment '����ID',
   InterfaceName        varchar(50) comment '�ӿ�����',
   primary key (ID)
);

alter table Table_OperatorInterface comment '���ӽӿڱ�';

/*==============================================================*/
/* Table: Table_Role                                            */
/*==============================================================*/
create table Table_Role
(
   ID                   int not null auto_increment comment '����ID',
   RoleName             varchar(20) comment '��������',
   Des                  varchar(500) comment '��������',
   Remark               varchar(500) comment '��ע',
   EntranceNote         varchar(500) comment '��ڱ�ע',
   coordinate           text comment '����',
   UuserID              int comment '�û�ID',
   primary key (ID)
);

alter table Table_Role comment '�㷨����';

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

