package vttp.nus.iss.day22.repo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.nus.iss.day22.Utils;
import vttp.nus.iss.day22.model.TaskSummary;

@Repository
public class TasksRepo {

    @Autowired
    private JdbcTemplate template;

    public List<TaskSummary> getTasksAsSummaries() {

        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_TASK_AS_SUMMARY);
        List<TaskSummary> results = new LinkedList<>();
        while (rs.next())
            results.add(Utils.toTaskSummary(rs));
        return results;
    }

       public boolean updateTaskStatus(int taskId, boolean completed) {
        return template.update(Queries.SQL_UPDATE_TASK_COMPLETED_BY_ID, completed, taskId) > 0;
    }
}
