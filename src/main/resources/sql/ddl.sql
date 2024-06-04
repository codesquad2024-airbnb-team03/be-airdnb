create table USERS
(
    id                      bigint auto_increment primary key,
    name                    varchar(255),
    profile_img             varchar(255),
    type                    varchar(50) not null default 'NORMAL',
    created_at              timestamp default current_timestamp
);

create table ACCOMMODATIONS
(
    id                      bigint auto_increment primary key,
    name                    varchar(255),
    profile_img             varchar(255),
    address                 varchar(255),
    price                   bigint,
    grade                   double,
    headcount               int,
    amenity_bed_count       int,
    amenity_bedroom_count   int,
    amenity_bathroom_count  int,
    amenity_wifi            boolean DEFAULT 0,
    amenity_kitchen         boolean DEFAULT 0,
    amenity_washing_machine boolean DEFAULT 0,
    amenity_dryer           boolean DEFAULT 0,
    amenity_air_conditioner boolean DEFAULT 0,
    amenity_heating         boolean DEFAULT 0,
    amenity_tv              boolean DEFAULT 0,
    amenity_hair_dryer      boolean DEFAULT 0,
    feature_pool            boolean DEFAULT 0,
    feature_gym             boolean DEFAULT 0,
    feature_barbecue        boolean DEFAULT 0,
    feature_smoking_allowed boolean DEFAULT 0,
    user_id                 bigint,
    review_id               bigint,
    created_at              timestamp default current_timestamp,
    foreign key (user_id) references USERS (USERS.id) ON DELETE CASCADE,
    foreign key (review_id) references REVIEWS (REVIEWS.id) ON DELETE CASCADE
);

create table REVIEWS
(
    id                      bigint auto_increment primary key,
    content                 varchar(255),
    grade                   double,
    user_id                 bigint,
    accommodation_id        bigint,
    created_at              timestamp default current_timestamp,
    foreign key (user_id) references USERS (USERS.id) ON DELETE CASCADE,
    foreign key (accommodation_id) references ACCOMMODATIONS (ACCOMMODATIONS.id) ON DELETE CASCADE
);

create table RESERVATIONS
(
    id                      bigint auto_increment primary key,
    start_date              timestamp,
    end_date                timestamp,
    user_id                 bigint,
    accommodation_id        bigint,
    created_at              timestamp default current_timestamp,
    foreign key (user_id) references USERS (USERS.id) ON DELETE CASCADE,
    foreign key (accommodation_id) references ACCOMMODATIONS (ACCOMMODATIONS.id) ON DELETE CASCADE
);

create table LIKES
(
    id                      bigint auto_increment primary key,
    user_id                 bigint,
    accommodation_id        bigint,
    foreign key (user_id) references USERS (USERS.id) ON DELETE CASCADE,
    foreign key (accommodation_id) references ACCOMMODATIONS (ACCOMMODATIONS.id) ON DELETE CASCADE
);