DO
$$
BEGIN
    create table if not exists user_table (
        id numeric not null,
        username text not null,
        password text not null,
        role text not null,
        constraint user_pk primary key (id),
        constraint username unique (username)
    );

    create sequence user_table_seq
      start with 1
      increment by 1
      no minvalue
      maxvalue 999999999999999
      cache 1;
END
$$;
