package vttp.nus.iss.day22.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.nus.iss.day22.model.Contact;

@Repository
public class BffRepo {

    @Autowired
    private JdbcTemplate template;

    public boolean insertContact(Contact contact) {
        return template.update(Queries.SQL_INSERT_BFF, 
            contact.getEmail(),
            contact.getName(),
            contact.getDob(),
            contact.getPhone(),
            contact.getComments()) >=1 ;
    }

    public boolean contactExists(String email) {
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_COUNT_EMAIL, email);
        if (!rs.next()) {
            return false;
        }
        int emailCount = rs.getInt("email_count");
        return emailCount > 0;
    }
    
}
