package vttp.nus.iss.day22.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.nus.iss.day22.model.TaskSummary;
import vttp.nus.iss.day22.repo.TasksRepo;

@Service
public class TasksService {

    @Autowired
    private TasksRepo tasksRepo;

    public boolean updateTaskStatus(int taskId, boolean completed) {
        return this.tasksRepo.updateTaskStatus(taskId, completed);
    }

    public List<TaskSummary> getTasksAsSummaries() {
        return this.tasksRepo.getTasksAsSummaries();
    }

}
