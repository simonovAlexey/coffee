--
-- Изменяйте типы для тестовой БД.
-- Для MySQL юзайте InnoDB
--
-- !!!: Сохраняйте регистр в мэппингах для Hibernate (на mysql под linux это важно).
--

--
-- Сорт кофе
--
create table CoffeeType (
  id int not null, -- pk
  type_name varchar(200) not null, -- название
  price double not null, -- цена
  disabled char(1), -- если disabled = 'Y', то не показывать данный сорт в списке доступных сортов
  primary key (id)
);

create index CT_I on CoffeeType (
  id asc
);

--
-- Заказ
--
create table CoffeeOrder (
  id int not null, -- pk
  order_date datetime not null, -- время заказа
  name varchar(100), -- имя заказчика
  delivery_address varchar(200) not null, -- куда доставлять
  cost double, -- сколко стоит (алгоритм подсчёта может поменяться, поэтому надо хранить стоимость)
  primary key (id)
);

create index CO_I1 on CoffeeOrder (
  id asc
);

--
-- Одна позиция заказа
--
create table CoffeeOrderItem (
  id int not null, -- pk
  type_id int not null, -- сорт кофе
  order_id int not null, -- к какому заказу принадлежит
  quantity int, -- сколько чашек
  primary key (id)
);

create index COI_I on CoffeeOrderItem (
  order_id asc
);

create index COI_3 on CoffeeOrderItem (
  type_id asc
);

alter table CoffeeOrderItem
  add constraint COI_CO foreign key (order_id)
    references CoffeeOrder (id);


alter table CoffeeOrderItem
  add constraint COI_CT foreign key (type_id)
    references CoffeeType (id);

--
-- Конфигурация
--
create table Configuration (
  id varchar(20) not null, -- pk, название свойства
  value varchar(30), -- значение
  primary key (id)
);


