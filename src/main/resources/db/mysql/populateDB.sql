INSERT IGNORE INTO coffeeorder (id, order_date, name, delivery_address, cost) VALUES
  (1, '2017-07-25 12:30:00', 'Simonov', 'Minsk', 10.0),
  (2, '2017-07-24 10:00:00', 'Petrov', 'Minsk2', 15.0),
  (3, '2017-07-23 12:30:00', 'Simonov', 'Minsk3', 20.50);


INSERT IGNORE INTO coffeetype (id, type_name, price, disabled) VALUES
(1, 'Java', 2.0, 'N'),
(2, 'Капучино', 1.0, 'N'),
(3, 'Мокачино', 3.75, 'N'),
(4, 'Disabled Java', 3.45, 'Y');


INSERT IGNORE INTO coffeeorderitem (id, type_id, order_id, quantity) VALUES
  (1, 1, 1, 2),
  (2, 2, 1, 2),

  (3, 1, 2, 2),
  (4, 2, 2, 4),
  (5, 3, 2, 3),

  (6, 1, 3, 10),
  (7, 2, 3, 4),
  (8, 3, 3, 2);

INSERT IGNORE INTO CONFIGURATION (id, VALUE ) VALUES
('n', 5),
('x', 10),
('m', 2);