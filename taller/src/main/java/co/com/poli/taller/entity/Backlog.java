package co.com.poli.taller.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor @Builder
@Table(name = "backlogs")
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Debe contener un identificador del proyecto asociado")
    @Column(name = "project_identifier", updatable = false)
    private String projectIdentifier;

    //recibe como fk la pk de projects
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_project")
    //@JsonBackReference
    private Project project;

    /*
    @OneToMany
    @JoinColumn(name = "id_project_task")
    @JsonManagedReference
    private List<ProjectTask> projectTask;

    */
}
