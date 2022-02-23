package co.com.poli.taller.controller;

import co.com.poli.taller.entity.Project;
import co.com.poli.taller.service.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping()
    public ResponseEntity<Project> createProject(@Valid @RequestBody Project project, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        if(projectService.createProject(project) == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(project));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Project> findProjectById(@PathVariable("id") Long id){
        if(projectService.findProjectById(id) != null){
            return ResponseEntity.ok(projectService.findProjectById(id));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Project>> findAllProjects(){
        if(!projectService.findAllProjects().isEmpty()){
            return ResponseEntity.ok(projectService.findAllProjects());
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public void updateProject(@PathVariable("id") Long id, @RequestBody Project project){
        projectService.updateProject(project, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable("id") Long id){
        if(projectService.deleteProject(id) != null){
            return ResponseEntity.ok(projectService.deleteProject(id));
        }
        return ResponseEntity.notFound().build();
    }

    //controlar los errores
    private String formatMessage(BindingResult result){
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try{
            jsonString = mapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonString;
    }

}
