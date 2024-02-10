drop database if exist mart;

create database mart;

use mart;

create table orders (
    order_id int auto_increment,
    order_date date default current_date,
    customer_name varchar(128) not null,
    ship_address varchar(128) not null,
    notes text,
    tax decimal (2,2) default 0.05
);

create table order_details (
    id int auto_increment,
    product varchar(128) not null,
    unit_price decimal (3,2),
    discount decimal (2,2) default 0.1,
    quantity int not null
);