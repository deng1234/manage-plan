create database xiaoD;


--所有目标
create table target (
	targetID int auto_increment primary key,  --ID
	description text,			              --目标描述
	period varchar(5) not null,				  --周期
	postTime datetime  			  			  --制定时间
)


--目标详情
create table targetDetail (      
	detailID int auto_increment primary key,  --ID
	targetID int not null,                    --目标ID
	description text,                         --描述
	postTime datetime,                        --提交时间
	foreign key(targetID) references target(targetID)
)

--迫在眉睫和持之以恒
create table task (
	taskID int auto_increment primary key,    --ID
	type varchar(20) not null,                --类型
	description text,                         --描述
	postTime datetime,                        --提交时间
)

--计划和评价
create table plan (
	planID int auto_increment primary key,    
	planTime datetime,                         
	score float default 0,                      
	summary text                              
)

--计划的详细细节
create table planDetail (
	detailID int auto_increment primary key,   --ID
	planID int not null,                       --计划的ID
	description text,                          --描述
	postTime datetime,                          --提交时间
	foreign key(planID) references plan(planID)
)