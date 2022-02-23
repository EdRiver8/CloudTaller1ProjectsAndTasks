package co.com.poli.taller.service;

import co.com.poli.taller.entity.Backlog;
import co.com.poli.taller.entity.Project;

import java.util.List;

public interface ProjectService {

    public Project createProject(Project project);
    public Project findProjectById(Long id);
    public List<Project> findAllProjects();
    public Project updateProject(Project project, Long id);
    public List<Project> findProjectByName(String name);
    public List<Project> findProjectByIdentifier(String identifier);

    /**
     *
     * @param id
     * @return Project
     */
    public Project deleteProject(Long id);
    //public List<Project> findByBacklog(Backlog backlog);

}
