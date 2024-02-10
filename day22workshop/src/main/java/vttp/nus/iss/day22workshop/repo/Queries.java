package vttp.nus.iss.day22workshop.repo;

public class Queries {

    public static final String SQL_GET_ALL_RSVPS = """
            select *
            from rsvp
            """;

    public static final String SQL_SEARCH_RSVP_BY_NAME = """
            select *
            from rsvp
            where name = ?
            """;

    public static final String SQL_DROP_AND_ADD_NEW_RSVP = """
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
            """;
    public static final String SQL_UPDATE_RSVP ="""
            update rsvp set
                name = ?,
                phone = ?,
                confirmation_date  ?,
                comments = ?

                where email = ?
            """;

    public static final String SQL_GET_RSVP_COUNT = """
            select count(*)
                from rsvp

            // select distinct email
            //     from rsvp
            """;
}
