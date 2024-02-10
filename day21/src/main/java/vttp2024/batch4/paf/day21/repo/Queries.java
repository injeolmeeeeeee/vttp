package vttp2024.batch4.paf.day21.repo;

public class Queries {

    public static final String SQL_SELECT_BOOK_BY_TITLE = """
            select book_id, title, authors, description, pages, rating, image_url
            from book2018
            where title like ?
            limit ? offset ?
            """;

    public static final String SQL_SELECT_BOOK_PAPERBACK_WITH_RATING = """
            select book_id, title, authors, description, pages, rating, image_url
            from book2018
            where format = ? and rating > ?
            """;
    
    public static final String SQL_SELECT_BOOK_BY_FORMAT = """
            select book_id, title, authors, description, pages, rating, image_url
            from book2018
            where format like ?
        """;

    public static final String SQL_SELECT_BOOKS_ORDER_BY_TITLE = """
        select book_id, title 
        from book2018
        order by title
        limit ? offset ?
    """;

    public static final String SQL_SELECT_BOOK_BY_ID = """
        select book_id, title, authors, description, pages, rating, image_url
        from book2018
        where book_id = ?
            """;
}