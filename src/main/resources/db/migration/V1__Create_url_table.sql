create table if not exists URL
(
    ID              BIGSERIAL PRIMARY KEY,
    short_url       varchar(200) not null,
    long_url        varchar(200) not null,
    expiration_date timestamp DEFAULT now() + interval '3 days'
);

/*ALTER TABLE url
    ADD uuid BIGINT    DEFAULT 0;*/