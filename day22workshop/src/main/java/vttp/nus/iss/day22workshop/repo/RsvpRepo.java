package vttp.nus.iss.day22workshop.repo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.nus.iss.day22workshop.Utils;
import vttp.nus.iss.day22workshop.model.Rsvp;

@Repository
public class RsvpRepo {

    @Autowired
	private JdbcTemplate template;

    public List<Rsvp> getAllRsvp() {
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_GET_ALL_RSVPS);
        List<Rsvp> rsvps = new LinkedList<>();
        while (rs.next()) {
            rsvps.add(Utils.toRsvp(rs));
        }
        return rsvps;
    }

    public List<Rsvp> searchRsvpByName(String name) {
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SEARCH_RSVP_BY_NAME, name);
        List<Rsvp> searchResultRsvp = new LinkedList<>();
        while (rs.next()) {
            searchResultRsvp.add(Utils.toRsvp(rs));
        }
        return searchResultRsvp;
    }

    public void dropRsvp() {
        template.execute(Queries.SQL_DROP_AND_ADD_NEW_RSVP);
    }

    public boolean updateRsvp(String email) {
        int added = template.update(Queries.SQL_UPDATE_RSVP, email);
        return added > 0;
    }

    public int getNoOfRsvps() {
       return template.queryForObject(Queries.SQL_GET_RSVP_COUNT, Integer.class);
    }
}
