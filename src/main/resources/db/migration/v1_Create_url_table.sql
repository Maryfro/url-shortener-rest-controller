create table IF NOT EXISTS URL (
                                   ID BIGSERIAL PRIMARY KEY,
                                   short_url varchar(200) not null,
    long_url varchar(200) not null,
    expiration_date timestamp DEFAULT now() + interval '3 days',
    consumer_id  BIGINT DEFAULT 0
    );