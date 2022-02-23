package co.com.poli.taller.controller;

import co.com.poli.taller.entity.Backlog;
import co.com.poli.taller.service.BacklogService;
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
@RequestMapping("/backlogs")
public class BacklogController {

    @Autowired
    private BacklogService backlogService;

    @PostMapping()
    public ResponseEntity<Backlog> createBacklog(@Valid @RequestBody Backlog backlog, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        if(backlogService.createBacklog(backlog) != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(backlogService.createBacklog(backlog));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/find/{id}")
    public Backlog findBacklogById(@PathVariable("id") Long id){
        return backlogService.findBacklogById(id);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Backlog>> findAllBacklog(){
        if(backlogService.findAllBacklog().isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(backlogService.findAllBacklog());
    }

    @PutMapping("/update/{id}")
    public void updateBacklog(@RequestBody Backlog backlog, @PathVariable("id") Long id){
        backlogService.updateBacklog(backlog, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBacklog(@PathVariable("id") Long id){
        backlogService.deleteBacklog(id);
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
