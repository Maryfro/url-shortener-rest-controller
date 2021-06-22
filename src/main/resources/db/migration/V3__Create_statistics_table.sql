create table if not exists STATISTICS
(
    ID              BIGSERIAL PRIMARY KEY,
    short_url       varchar(200) unique not null,
    long_url        varchar(200) unique not null,
    counter         integer not null
);