package co.com.poli.taller.controller;

import co.com.poli.taller.entity.Project;
import co.com.poli.taller.entity.ProjectTask;
import co.com.poli.taller.service.TaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
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
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping()
    public ResponseEntity<ProjectTask> createTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        if(taskService.createTask(projectTask) == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(projectTask));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProjectTask> findTaskById(@PathVariable("id") Long id){
        if(taskService.findTaskById(id) == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taskService.findTaskById(id));
    }

    @GetMapping("/find")
    public ResponseEntity<List<ProjectTask>> findAllTask(){
        if(taskService.findAllTask().isEmpty()){
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(taskService.findAllTask());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProjectTask> updateTask(@Valid @RequestBody ProjectTask projectTask, @PathVariable("id") Long id, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        if(taskService.findTaskById(id) == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(taskService.updateTask(projectTask, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProjectTask> deleteTaskById(@PathVariable("id") Long id){
        if(taskService.findTaskById(id) == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taskService.deleteTaskById(id));
    }

    @GetMapping("/project/{idProject}")
    public ResponseEntity<List<ProjectTask>> findByProjectIdentifier(@PathVariable("idProject") String idProject){
        if(!taskService.findByProjectIdentifier(idProject).isEmpty()){
            return ResponseEntity.ok(taskService.findByProjectIdentifier(idProject));
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping(value = {"/hours/project/{idProject}", "/hours/project/{idProject}/{status}"})
    public ResponseEntity<Double> countHoursWithoutDeleted(@PathVariable(name = "idProject") String idProject,
                                                           @PathVariable(name = "status", required = false) String status){
        if(taskService.countHoursByStatus(idProject, status) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taskService.countHoursByStatus(idProject, status));
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
