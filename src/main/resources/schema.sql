create table if not exists product
(
   id integer not null auto_increment,
   name varchar(255) not null,
   product_type varchar(255) not null,
   primary key(id)
);