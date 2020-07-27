insert into `account_plan` (`id`, `account_plan`) values
(1, 'standard'),
(2, 'soft'),
(3, 'premium');

insert into `role` (`id`, `name`) values
(1, 'ADMIN'),
(2, 'USER');

insert into `verification_token` (`id`, `token`, `token_expiry_date`, `used`) values
(1, 'e91f1dc4-b567-4142-86cb-5a4e5e5f71da', current_timestamp, 1),
(2, 'e91f1dc4-b567-4142-1111-5a4e5e5f71da', current_timestamp, 1);

insert into `user` (`id`, `password`, `email_adress`, `city`, `username`, `created_date`, `enabled`, `account_plan_id`, `token`) values
(1, '$2a$11$S6wM7B3HMWrl54nhOP2fIuo8lY.97tOOWVeHVxrRjfIOCvtMj6C3i', 'admin@admin.pl', 'DDZ', 'admin', current_timestamp, 0, 1, 1),
(2, '$2a$11$cRhDVCjJHSkhXRV9.DrDUe19QTr1U./Yowq6Ga97QGfNb6MTYPltW', 'user@user.pl', 'DDZ', 'user', current_timestamp, 1, 1, 2);

insert into `user_roles` (`user_id`, `role_id`) values
(1, 1),
(2, 2);

insert into `country` (`name`) values
('Zakopane'),
('Dzierżoniów'),
('Wrocław');