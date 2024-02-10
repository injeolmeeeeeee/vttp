package vttp.nus.iss.day22workshop;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import vttp.nus.iss.day22workshop.model.Rsvp;

public class Utils {

    public static Rsvp toRsvp(SqlRowSet rs) {
        Rsvp rsvp = new Rsvp();
        rsvp.setName(rs.getString("name"));
        rsvp.setEmail(rs.getString("email"));
        rsvp.setPhone(rs.getString("phone"));
        rsvp.setConfirmationDate(rs.getDate("confirmation_date"));

        return rsvp;
    }
    
}
