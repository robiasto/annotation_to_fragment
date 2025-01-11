create table tt_user
(
    id           uuid                  not null primary key,
    password     varchar(255)          not null,
    first_name   varchar(255)          not null,
    last_name    varchar(255)          not null,
    gender       varchar(255)          not null
        constraint tt_user_gender_check check ((gender)::text = ANY
                   ((ARRAY ['MALE'::character varying, 'FEMALE'::character varying, 'OTHER'::character varying])::text[])),
    birthday     date                  not null,
    email        varchar(255)          not null,
    phone_number varchar(255)          not null,
    coach        boolean default false not null,
    position       varchar(255)          not null
        constraint tt_user_position_check check ((position)::text = ANY
                   ((ARRAY [
                       'POINT_GUARD'::character varying,
                       'SHOOTING_GUARD'::character varying,
                       'SMALL_FORWARD'::character varying,
                       'POWER_FORWARD'::character varying,
                       'CENTER'::character varying
                   ])::text[]))
);

create table user_roles
(
    user_id uuid not null constraint FK_user_roles_to_user references tt_user,
    role    varchar(255)
        constraint user_roles_role_check check ((role)::text = ANY
            ((ARRAY ['ROLE_USER'::character varying, 'ROLE_ADMIN'::character varying])::text[]))
);
