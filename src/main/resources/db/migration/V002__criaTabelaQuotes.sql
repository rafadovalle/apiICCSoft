create table quotes(
id bigint not null auto_increment,
stock_id bigint,
valor double not null,

primary key(id)
);

alter table quotes add constraint fk_quotes_stock
foreign key (stock_id) references stock(id);