create table if not exists movement_reason
(
    id          bigserial primary key,
    created_at  timestamp    not null default now(),
    updated_at  timestamp,
    scenario_id bigint       not null,
    intro       varchar(255) not null
);

create table if not exists movement_point
(
    id          bigserial primary key,
    created_at  timestamp     not null default now(),
    updated_at  timestamp,
    scenario_id bigint        not null,
    intro       varchar(4096) not null
);

create table if not exists movement_scenario
(
    id         bigserial primary key,
    created_at timestamp    not null default now(),
    updated_at timestamp,
    reason_id  bigint,
    point_id   bigint,
    intro      varchar(255) not null

);

insert into movement_scenario(intro)
values ('Злое зло');

insert into movement_reason(created_at, scenario_id, intro)
values (now(), (select id from movement_scenario where intro = 'Злое зло'),
        'ощущая урчание в животе, вы отправляетесь в путь.');

insert into movement_point(created_at, scenario_id, intro)
values (now(), (select id from movement_scenario where intro = 'Злое зло'),
        'Лучше быть последним — первым, чем первым — последним');

insert into movement_point(created_at, scenario_id, intro)
values (now(), (select id from movement_scenario where intro = 'Злое зло'),
        'На случай, если буду нужен, то я там же, где и был, когда был не нужен');

insert into movement_point(created_at, scenario_id, intro)
values (now(), (select id from movement_scenario where intro = 'Злое зло'),
        'На случай, если буду нужен, то я там же, где и был, когда был не нужен');

insert into movement_point(created_at, scenario_id, intro)
values (now(), (select id from movement_scenario where intro = 'Злое зло'),
        'Если волк молчит то лучше его не перебивай');

insert into movement_point(created_at, scenario_id, intro)
values (now(), (select id from movement_scenario where intro = 'Злое зло'),
        'Каждый в цирке думает, что знает в цирке, но не каждый, что в цирке знает, что в цирке не каждый знает думает');

update movement_scenario
set reason_id = (select id from movement_reason where intro = 'ощущая урчание в животе, вы отправляетесь в путь.'),
    point_id  = (select id from movement_point where intro = 'Лучше быть последним — первым, чем первым — последним')
where intro = 'Злое зло'
