package co.com.poli.taller.repository;

import co.com.poli.taller.entity.Backlog;
import co.com.poli.taller.entity.Project;
import co.com.poli.taller.entity.ProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Long> {
    public List<Backlog> findByProjectIdentifier(String identifier);
}
