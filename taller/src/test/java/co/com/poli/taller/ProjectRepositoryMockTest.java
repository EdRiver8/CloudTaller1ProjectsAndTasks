package co.com.poli.taller;

import co.com.poli.taller.entity.Backlog;
import co.com.poli.taller.entity.Project;
import co.com.poli.taller.entity.ProjectTask;
import co.com.poli.taller.repository.ProjectRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProjectRepositoryMockTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void whenFindByBacklog_thenReturnListProjects(){
        Project project1 = Project.builder()
                .projectName("Nube")
                .projectIdentifier("99999A")
                .description("test de prueba")
                .startDate(new Date())
                .endDate(new Date())
                .build();
        projectRepository.save(project1);

        List<Project> projectFound = projectRepository.findByProjectIdentifier(project1.getProjectIdentifier());
        Assertions.assertThat(projectFound.size()).isEqualTo(4);
    }

}


