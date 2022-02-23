package co.com.poli.taller.repository;

import co.com.poli.taller.entity.Backlog;
import co.com.poli.taller.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    //@Query(value = "SELECT * FROM projects WHERE projects.project_name =:name", nativeQuery = true)
    //public Project findByProjectName(@Param("name") String name);
    public List<Project> findByProjectName(String name);
    public List<Project> findByProjectIdentifier(String identifier);
    //public List<Project> findByBacklog(Backlog backlog);
}
