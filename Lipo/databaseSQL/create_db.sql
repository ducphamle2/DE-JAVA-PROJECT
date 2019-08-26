create table Lipo_user (
	user_id char(6) NOT NULL PRIMARY KEY,
	username varchar(30) NOT NULL,
	email varchar(30) NOT NULL,
	pass_word varchar(30) NOT NULL,
	age integer,
	gender varchar(6),
	city varchar(30),
	created_at time without time zone NOT NULL,
	updated_at time without time zone NOT NULL
);

create table Rating (
	rating_id char(6) NOT NULL PRIMARY KEY,
	rating_header varchar(100),
	rating_content varchar(10000),
	rating_point integer,
	created_at time without time zone NOT NULL,
	updated_at time without time zone NOT NULL
);

alter table Rating add constraint fk_user_id foreign key (user_id) references Lipo_user (user_id);

alter table Lipo_user add rating_id char(6) NOT NULL;

alter table Lipo_user add constraint fk_rating_id foreign key (rating_id) references Rating (rating_id);

create table Liquor (
	liquor_id char(6) NOT NULL PRIMARY KEY,
	liquor_type varchar(30),
	liquor_price integer,
	quan_in_stock integer
);

create table Liquor_selling (
	sell_id char(6) not null primary key,
	total_amount integer,
	sell_date date,
	user_id char(6) not null,
	liquor_id char(6) not null
);


alter table Liquor_selling add constraint fk_liquor_id foreign key (liquor_id) references Liquor (liquor_id);
alter table Liquor_selling add constraint fk_user_id foreign key (user_id) references Lipo_user (user_id);

create table Post (
	post_id char(6) not null primary key,
	post_header varchar(100),
	post_content varchar(100000),
	temp_content varchar(100000),
	created_at time without time zone NOT NULL,
	updated_at time without time zone NOT NULL,
	user_id char(6) not null,
	constraint fk_user_id foreign key (user_id) references Lipo_user (user_id)
);

create table picture (
	picture_id char(6) not null primary key,
	created_at time without time zone NOT NULL,
	updated_at time without time zone NOT NULL,
	post_id char(6) not null,
	constraint fk_post_id foreign key (post_id) references Post (post_id)
);

create table income (
	income_id char(6) not null primary key,
	total_sale bigint,
	total_profit bigint,
	income_month bigint,
	sell_id char(6) not null,
	constraint fk_sell_id foreign key (sell_id) references Liquor_selling (sell_id)
);