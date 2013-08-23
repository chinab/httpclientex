create table SCHOOL_RENTAL_HOUSE 
(
   ID                   NUMBER               not null,
   RENTAL_HOUSE_ID      NUMBER,
   CREATE_USER          VARCHAR2(32)         not null,
   UPDATE_USER          VARCHAR2(32),
   CREATE_DATE          DATE                 not null,
   UPDATE_DATE          DATE,
   constraint PK_SCHOOL_RENTAL_HOUSE primary key (ID)
);

comment on column SCHOOL_RENTAL_HOUSE.ID is
'ID';

comment on column SCHOOL_RENTAL_HOUSE.RENTAL_HOUSE_ID is
'出租房ID';

comment on column SCHOOL_RENTAL_HOUSE.CREATE_USER is
'创建用户';

comment on column SCHOOL_RENTAL_HOUSE.UPDATE_USER is
'更新用户名';

comment on column SCHOOL_RENTAL_HOUSE.CREATE_DATE is
'创建日期';

comment on column SCHOOL_RENTAL_HOUSE.UPDATE_DATE is
'更新用户';