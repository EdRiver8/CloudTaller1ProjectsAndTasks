package co.com.poli.taller.service;

import co.com.poli.taller.entity.ProjectTask;
import co.com.poli.taller.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
@AllArgsConstructor
public class TaskServiceImple implements TaskService{

    private final TaskRepository taskRepository;

    @Override
    public ProjectTask createTask(ProjectTask projectTask) {
        if(findByName(projectTask.getName()).isEmpty()
            || !findByProjectIdentifier(projectTask.getProjectIdentifier()).isEmpty()){
            return null;
        }
        projectTask.setStatus("Started");
        projectTask.setStartDate(new Date());
        return taskRepository.save(projectTask);
    }

    @Override
    public ProjectTask findTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public List<ProjectTask> findAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public ProjectTask updateTask(ProjectTask projectTask, Long id) {
        if(findTaskById(id) != null){
            ProjectTask projectTask1 = findTaskById(id);
             projectTask1.setName(projectTask.getName());
             projectTask1.setAcceptanceCriteria(projectTask.getAcceptanceCriteria());
             projectTask1.setSummary(projectTask.getSummary());
             projectTask1.setPriority(projectTask.getPriority());
             projectTask1.setHours(projectTask.getHours());
             projectTask1.setStatus("In Progress");
             projectTask1.setEndDate(projectTask.getEndDate());
             return taskRepository.save(projectTask1);
        }
        return null;
    }

    @Override
    public ProjectTask deleteTaskById(Long id) {
        if(findTaskById(id) != null){
            ProjectTask projectTask = findTaskById(id);
            projectTask.setEndDate(new Date());
            projectTask.setStatus("Deleted");
            return taskRepository.save(projectTask);
        }
        return null;
    }

    @Override
    public List<ProjectTask> findByName(String name) {
        return taskRepository.findByName(name);
    }

    @Override
    public List<ProjectTask> findByProjectIdentifier(String identifier) {
        return taskRepository.findByProjectIdentifier(identifier);
    }

    @Override
    public Double countHoursByStatus(String identifier, String status) {
        if(taskRepository.findByProjectIdentifier(identifier).isEmpty()){
            return null;
        }
        List <ProjectTask> projectTasks = taskRepository.findByProjectIdentifier(identifier);
        Double countHours = 0.0;
        for (ProjectTask projectTask : projectTasks) {
            //Cuenta las horas del project sumando todos los status menos el deleted
            if(status == null){
                if(projectTask.getStatus() != "Deleted"){
                    countHours += projectTask.getHours();
                }
            } else {//cuenta las horas segun el proyecto y el status enviado
                if(projectTask.getStatus().equals(status)){
                    countHours += projectTask.getHours();
                }
            }
        }
        return countHours;
    }
}
