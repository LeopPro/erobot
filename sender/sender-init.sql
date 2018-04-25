drop schema if exists erobot_sender_a;

create schema erobot_sender_a DEFAULT CHARSET utf8mb4;

drop table if exists erobot_sender_a.e_task;

create table erobot_sender_a.e_task
(
	id bigint unsigned not null comment '任务ID'
		primary key,
	name varchar(64) not null comment '任务名',
	cron varchar(64) not null comment '任务的Cron',
	subject varchar(64) not null comment '标题',
	content varchar(10240) not null comment '信息',
	receiver varchar(128) not null comment '接收者email，以空格分隔',
  attachment_name varchar(64)  null comment '附件名，无附件时为空',
	attachment_path varchar(128) null comment '附件路径'
)
comment '任务' engine=InnoDB DEFAULT CHARSET=utf8mb4;

