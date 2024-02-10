package vttp2024.batch4.paf.day21.repo;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2024.batch4.paf.day21.model.Book;
import vttp2024.batch4.paf.day21.model.BookSummary;

@Repository
public class BookRepo {

    @Autowired
    private JdbcTemplate template;

    public void findBooksByTitile(String keyword) {

        String toSearch = "%" + keyword + "%";

        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_BOOK_BY_TITLE, toSearch, 10, 0);

        while (rs.next()) {
            String bookId = rs.getString("book_id");
            String title = rs.getString("title");
            int pages = rs.getInt("pages");
            float rating = rs.getFloat("rating");
            System.out.printf("%s, %s, %d, %.3f\n", bookId, title, pages, rating);
        }
        System.out.println("query completed");
    }

    public List<BookSummary> findBooksOrderByTitle(int limit, int offset) {
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_BOOKS_ORDER_BY_TITLE, limit, offset);

        List<BookSummary> summary = new LinkedList<>();
        while (rs.next()) {
            summary.add(toBookSummary(rs));
        }

        return summary;
    }

    public void findBookByPaperbackAndRating(String format, int rating) {
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_BOOK_PAPERBACK_WITH_RATING, format, rating);

        while (rs.next()) {
            String bookId = rs.getString("book_id");
            String title = rs.getString("title");
            String image = rs.getString("image_url");
            int pages = rs.getInt("pages");
            float _rating = rs.getFloat("rating");
            System.out.printf("%s, %s, %d, %.3f, %s\n", bookId, title, pages, _rating, image);
        }
    }

    public void getResultsByFormat(String format) {

        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_BOOK_BY_FORMAT, format);

        while (rs.next()) {
            String bookId = rs.getString("book_id");
            String title = rs.getString("title");
            String image = rs.getString("image_url");
            int pages = rs.getInt("pages");
            float _rating = rs.getFloat("rating");
            System.out.println(format);
            System.out.printf("%s, %s, %d, %.3f, %s\n", bookId, title, pages, _rating, image);
        }
    }

    public Optional<Book> findBookById(String bookId) {
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_BOOK_BY_ID , bookId);

        if (!rs.next())
            return Optional.empty();

        return Optional.of(toBook(rs));
   }

    public static BookSummary toBookSummary(SqlRowSet rs) {
      BookSummary summary = new BookSummary();
      summary.setBookId(rs.getString("book_id"));
      summary.setTitle(rs.getString("title"));
      return summary;
   }

   public static Book toBook(SqlRowSet rs) {
      Book summary = new Book();
      summary.setBookId(rs.getString("book_id"));
      summary.setTitle(rs.getString("title"));
      summary.setAuthors(rs.getString("authors"));
      summary.setDescription(rs.getString("description"));
      summary.setPages(rs.getInt("pages"));
      summary.setRating(rs.getFloat("rating"));
      summary.setImage(rs.getString("image_url"));
      return summary;
   }
}