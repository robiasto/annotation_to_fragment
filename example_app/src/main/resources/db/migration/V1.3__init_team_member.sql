create table member
(
    id uuid not null primary key,
    added_date date not null,
    removed_date date,
    team_id      uuid,
    user_id      uuid,
    position       varchar(255)          not null
        constraint tt_user_position_check check ((position)::text = ANY
                                                 ((ARRAY [
                                                     'POINT_GUARD'::character varying,
                                                     'SHOOTING_GUARD'::character varying,
                                                     'SMALL_FORWARD'::character varying,
                                                     'POWER_FORWARD'::character varying,
                                                     'CENTER'::character varying
                                                     ])::text[])),

    coach        boolean default false not null
);


ALTER TABLE member ADD CONSTRAINT FK_member_to_team FOREIGN KEY (team_id) REFERENCES team;
ALTER TABLE member ADD CONSTRAINT FK_member_to_user FOREIGN KEY (user_id) REFERENCES tt_user;
