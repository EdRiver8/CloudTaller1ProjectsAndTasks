package co.com.poli.taller.service;


import co.com.poli.taller.entity.ProjectTask;

import java.util.List;

public interface TaskService {

    public ProjectTask createTask(ProjectTask projectTask);
    public ProjectTask findTaskById(Long id);
    public List<ProjectTask> findAllTask();
    public ProjectTask updateTask(ProjectTask projectTask, Long id);
    public ProjectTask deleteTaskById(Long id);
    public List<ProjectTask> findByName(String name);
    public List<ProjectTask> findByProjectIdentifier(String identifier);
    Double countHoursByStatus(String identifier, String status);
}
