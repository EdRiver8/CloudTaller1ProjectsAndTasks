package co.com.poli.taller.repository;

import co.com.poli.taller.entity.ProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<ProjectTask, Long> {
    public List<ProjectTask> findByName(String name);
    public List<ProjectTask> findByProjectIdentifier(String identifier);
}
