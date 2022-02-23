package co.com.poli.taller.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Debe contener un nombre para el proyecto")
    @Column(name = "project_name")
    private String projectName;

    @NotEmpty(message = "Debe contener un identificador del proyecto asociado")
    @Column(name = "project_identifier" , updatable = false, nullable = false)
    @Size(min = 5, max = 7, message = "El identificador debe tener entre 5 y 7 Caracteres")
    private String projectIdentifier;

    @NotEmpty(message = "Debe contener una descripcion")
    private String description;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    /*
    //recibe como fk la pk de Backlog
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_backlog")
    @JsonManagedReference
    private Backlog backlog;
    */

}
