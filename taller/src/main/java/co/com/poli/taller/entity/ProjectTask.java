package co.com.poli.taller.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "project_tasks")
public class ProjectTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Debe contener un nombre de la tarea a realizar")
    @Column(name = "task_name")
    private String name;

    @NotEmpty(message = "Debe contener informacion sobre lo que se va a realizar")
    private String summary;

    @Column(name = "acceptance_criteria")
    private String acceptanceCriteria;

    private String status;//started, in progress, completed or deleted

    @Min(value = 1, message = "La prioridad no puede ser menor que 1")
    @Max(value = 5, message = "La prioridad no debe ser mayor que 5")
    private Integer priority;

    @Min(value = 1, message = "La cantidad de horas no puede ser menor que 1")
    @Max(value = 8, message = "La cantidad de horas no debe ser mayor a 8")
    private Double hours;

    @Column(name = "start_date_task")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date_task")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @NotEmpty(message = "Debe contener un identificador del proyecto asociado")
    @Column(name = "project_identifier", updatable = false)
    private String projectIdentifier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_backlog")
    //@JsonBackReference
    private Backlog backlog;


}
