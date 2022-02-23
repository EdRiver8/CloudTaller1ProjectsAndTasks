package co.com.poli.taller.service;

import co.com.poli.taller.entity.Backlog;
import co.com.poli.taller.entity.Project;
import co.com.poli.taller.entity.ProjectTask;

import java.util.List;

public interface BacklogService {

    public Backlog createBacklog(Backlog backlog);
    public Backlog findBacklogById(Long id);
    public List<Backlog> findAllBacklog();
    public Backlog updateBacklog(Backlog backlog, Long id);
    public Backlog deleteBacklog(Long id);
    public List<Backlog> findByProjectIdentifier(String identifier);

}
