/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/2/11 18:12:33                           */
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
   ID                   varchar(20) not null comment '����ID',
   ModuleID             varchar(20),
   AlgorithmName        varchar(200) comment 'ģ����',
   AlgorithmAuthor      varchar(20) comment 'ģ������',
   IsPublic             numeric comment '�Ƿ񹫹�ģ��',
   AlgorithmType        numeric comment 'ģ������(�㷨��ʽ���߼�����)',
   AlgorithmFun         varchar(500) comment '��ʽ',
   "Desc"               varchar(500) comment '����',
   Remark               varchar(500) comment '��ע',
   primary key (ID)
);

alter table Table_Algorithm comment '����ģ��';

/*==============================================================*/
/* Table: Table_AlgorithmCondition                              */
/*==============================================================*/
create table Table_AlgorithmCondition
(
   ID                   varchar(20) not null comment '����ID',
   AlgorithmRelationID  varchar(20) comment 'ģ���ϵID',
   LogicRelation        varchar(20) comment '�߼���ϵ',
   LogicValue           numeric comment '�߼�ֵ',
   Remark               varchar(500) comment '��ע',
   primary key (ID)
);

alter table Table_AlgorithmCondition comment '������������';

/*==============================================================*/
/* Table: Table_AlgorithmRelation                               */
/*==============================================================*/
create table Table_AlgorithmRelation
(
   ID                   varchar(20) not null comment '����ID',
   AlgorithmID          varchar(20) comment 'ģ��ID',
   PreAlgorithmID       varchar(20) comment 'ǰ��ģ��ID',
   Remark               varchar(500) comment '��ע',
   primary key (ID)
);

alter table Table_AlgorithmRelation comment '���ӹ�ϵ';

/*==============================================================*/
/* Table: Table_Func                                            */
/*==============================================================*/
create table Table_Func
(
   ID                   varchar(20) not null comment '����ID',
   ModuleID             varchar(20) comment 'ģ��ID',
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
   Remark               varchar(500) comment '��ע',
   primary key (ID)
);

alter table Table_Func comment '��ʽ����';

/*==============================================================*/
/* Table: Table_Module                                          */
/*==============================================================*/
create table Table_Module
(
   ID                   varchar(20) not null comment '����ID',
   ModuleName           varchar(50),
   SqlUrl               varchar(200),
   Table                varchar(100),
   FieldName            varchar(50) comment '�ֶ�����',
   FieldType            varchar(20) comment '�ֶ�����',
   ModuleGroup          varchar(100),
   "Desc"               varchar(500),
   Remark               varchar(500) comment '��ע',
   primary key (ID)
);

alter table Table_Module comment 'ģ��';

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

alter table Table_ModuleField comment 'ģ������ֶ�';

/*==============================================================*/
/* Table: Table_ModuleUserRelation                              */
/*==============================================================*/
create table Table_ModuleUserRelation
(
   ID                   varchar(20) not null comment '����ID',
   ModuleID             varchar(20) comment 'ģ��ID',
   UserName             varchar(30) comment '�û���',
   Remark               varchar(500) comment '��ע',
   primary key (ID)
);

alter table Table_ModuleUserRelation comment '�����û���ϵ';

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

