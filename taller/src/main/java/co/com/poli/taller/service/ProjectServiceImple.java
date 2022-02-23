package co.com.poli.taller.service;

import co.com.poli.taller.entity.Backlog;
import co.com.poli.taller.entity.Project;
import co.com.poli.taller.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectServiceImple implements ProjectService{

    //inyeccion de dependencias por medio del constructor con la anotacion Requi...
    private final ProjectRepository projectRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Project createProject(Project project) {
        //sino esta vacio, es porque ya fue creado
        if(!findProjectByIdentifier(project.getProjectIdentifier()).isEmpty() ||
            !findProjectByName(project.getProjectName()).isEmpty()){
            return null;
        }
        //si esta vacio:
        return projectRepository.save(project);
    }

    @Override
    public Project findProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public Project updateProject(Project project, Long id) {
        if(findProjectById(id) != null){
            Project projectDB = Project.builder()
                    .id(id)//sino se pone el id, es como crear uno nuevo y no se actualiza
                    .projectIdentifier(project.getProjectIdentifier())
                    .projectName(project.getProjectName())
                    .description(project.getDescription())
                    .startDate(project.getStartDate())
                    .endDate(project.getEndDate())
                    .build();
            return projectRepository.save(projectDB);
        }
        return null;
    }

    @Override
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> findProjectByName(String name) {
        return projectRepository.findByProjectName(name);
    }

    @Override
    public List<Project> findProjectByIdentifier(String identifier) {
        return projectRepository.findByProjectIdentifier(identifier);
    }

    @Override
    public Project deleteProject(Long id) {
        Project project = findProjectById(id);
        if(project != null){
            projectRepository.delete(project);
        }
        return null;
    }

    /*
    @Override
    public List<Project> findByBacklog(Backlog backlog) {
        return projectRepository.findByBacklog(backlog);
    }
         */
}
