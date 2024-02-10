package vttp.nus.iss.day22;


import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.nus.iss.day22.model.TaskSummary;


public class Utils {

	public static TaskSummary toTaskSummary(SqlRowSet rs) {
		TaskSummary summary = new TaskSummary();
		summary.setTaskId(rs.getInt("task_id"));
		summary.setTitle(rs.getString("title"));
		summary.setCompleted(rs.getBoolean("completed"));
		return summary;
	}

	public static JsonObject toJson(TaskSummary t) {
		return Json.createObjectBuilder()
			.add("taskId", t.getTaskId())
			.add("title", t.getTitle())
			.add("completed", t.isCompleted())
			.build();
	}
}