-- we don't know how to generate schema erobot_admin (class Schema) :(
create table e_account
(
	id bigint unsigned auto_increment comment '帐号ID'
		primary key,
	username varchar(64) not null comment '用户名',
	password binary(64) not null comment '密码Hash值',
	salt bigint not null comment '盐值'
)
comment '管理员帐号' engine=InnoDB
;

create table e_group
(
	id bigint unsigned auto_increment comment '接收组id'
		primary key,
	name varchar(64) not null comment '接收组名称'
)
comment '接收组' engine=InnoDB
;

create table e_message
(
	id bigint unsigned auto_increment comment '信息ID'
		primary key,
	subject varchar(64) not null comment '信息标题',
	content varchar(10240) not null comment '消息内容',
	attachment_name varchar(64) null comment '附件名，无附件时为空',
	attachment_path varchar(64) null comment '附件地址，无附件时为空'
)
comment '信息' engine=InnoDB
;

create table e_receiver
(
	id bigint unsigned auto_increment comment '接收者ID'
		primary key,
	name varchar(64) not null comment '接收者名称',
	email varchar(64) not null comment '接收者Email'
)
comment '接收者' engine=InnoDB
;

create table e_task
(
	id bigint unsigned auto_increment comment '任务ID'
		primary key,
	name varchar(64) not null comment '任务名',
	cron varchar(64) not null comment '任务Cron',
	sender_ip varchar(15) null comment '发送器ip',
	message_id bigint unsigned not null comment '信息id',
	failure_times int unsigned not null comment '失败次数',
	constraint e_task_e_message_id_fk
		foreign key (message_id) references e_message (id)
)
comment '任务' engine=InnoDB
;

create index e_task_e_message_id_fk
	on e_task (message_id)
;

create table l_group_task
(
	group_id bigint unsigned not null comment '发送组id',
	task_id bigint unsigned not null comment '任务id',
	constraint l_group_task_uq
		unique (group_id, task_id),
	constraint l_group_task_e_group_id_fk
		foreign key (group_id) references e_group (id),
	constraint l_group_task_e_task_id_fk
		foreign key (task_id) references e_task (id)
)
comment '任务与发送组连接表' engine=InnoDB
;

create index l_group_task_e_task_id_fk
	on l_group_task (task_id)
;

create table l_receiver_group
(
	receiver_id bigint unsigned not null comment '接收者Id',
	group_id bigint unsigned not null comment '接收组Id',
	constraint l_receiver_group_uq
		unique (receiver_id, group_id),
	constraint l_receiver_group_receiver_id_fk
		foreign key (receiver_id) references e_receiver (id)
			on delete cascade,
	constraint l_receiver_group_group_id_fk
		foreign key (group_id) references e_group (id)
			on delete cascade
)
comment '接收者与接收组连接表' engine=InnoDB
;

create index l_receiver_group_group_id_fk
	on l_receiver_group (group_id)
;

