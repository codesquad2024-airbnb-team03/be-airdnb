create table USERS
(
    id          bigint auto_increment primary key,
    name        varchar(255),
    profile_img varchar(255),
    type        varchar(50) not null default 'NORMAL',
    created_at  timestamp            default current_timestamp
);

create table ACCOMMODATIONS
(
    id          bigint auto_increment primary key,
    name        varchar(255),
    profile_img varchar(255),
    price       bigint,
    grade       int,
    headcount   int,
    user_id    bigint,
    review_id  bigint,
    foreign key (ACCOMMODATIONS.user_id) references USERS (USERS.id) ON DELETE CASCADE,
    foreign key (ACCOMMODATIONS.review_id) references REVIEWS (REVIEWS.id) ON DELETE CASCADE,
    created_at  timestamp default current_timestamp
);

create table REVIEWS
(
    id                bigint auto_increment primary key,
    content           varchar(255),
    grade             int,
    user_id          bigint,
    accommodation_id bigint,
    foreign key (REVIEWS.user_id) references USERS (USERS.id) ON DELETE CASCADE,
    foreign key (REVIEWS.accommodation_id) references ACCOMMODATIONS (ACCOMMODATIONS.id) ON DELETE CASCADE,
    created_at        timestamp default current_timestamp
);

create table RESERVATIONS
(
    id                bigint auto_increment primary key,
    start_date        timestamp,
    end_date          timestamp,
    user_id          bigint,
    accommodation_id bigint,
    foreign key (RESERVATIONS.user_id) references USERS (USERS.id) ON DELETE CASCADE,
    foreign key (RESERVATIONS.accommodation_id) references ACCOMMODATIONS (ACCOMMODATIONS.id) ON DELETE CASCADE,
    created_at        timestamp default current_timestamp
);

create table LIKES
(
    id                bigint auto_increment primary key,
    user_id          bigint,
    accommodation_id bigint,
    foreign key (LIKES.user_id) references USERS (USERS.id) ON DELETE CASCADE,
    foreign key (LIKES.accommodation_id) references ACCOMMODATIONS (ACCOMMODATIONS.id) ON DELETE CASCADE
);