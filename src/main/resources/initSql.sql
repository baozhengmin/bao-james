-- auto-generated definition
create table activity_event_info
(
  id          int(11) unsigned auto_increment
  comment '主键'
    primary key,
  source      tinyint(1) unsigned default '0'    not null
  comment '0:wcs-cloud  1:wcs-warehouse',
  event_level tinyint(1) unsigned default '0'    not null
  comment '0:紧急 1:重要 2:一般',
  event_code  int unsigned default '0'           not null
  comment '1:网络不可用',
  event_desc  varchar(255) default '""'          not null
  comment '事件描述',
  create_time datetime default CURRENT_TIMESTAMP not null
  comment '创建时间',
  update_time datetime                           not null
  comment '更新时间',
  deleted     tinyint(4) unsigned default '0'    not null
  comment '是否被删除(1:是 0:否)'
);


-- auto-generated definition
create table hello_es
(
  type    varchar(60) null,
  descrip text        null,
  price   decimal     null,
  id      int auto_increment
    primary key,
  constraint hello_es_id_uindex
  unique (id)
);

