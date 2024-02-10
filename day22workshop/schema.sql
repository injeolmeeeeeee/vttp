drop database RSVP if exist;

create RSVP;

use RSVP;

create table rsvp(
    name varchar(128) not null,
    email varchar(128) not null,
    phone varchar(8) not null,
    confirmation_date timestamp default current_timestamp,
    comments varchar(128)

    primary key(email)
);

