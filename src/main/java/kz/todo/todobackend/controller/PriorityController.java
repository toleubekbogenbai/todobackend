package kz.todo.todobackend.controller;

import kz.todo.todobackend.entity.Category;
import kz.todo.todobackend.entity.Priority;
import kz.todo.todobackend.repository.PriorityRepository;
import kz.todo.todobackend.search.CategorySearchValues;
import kz.todo.todobackend.search.PrioritySearchValues;
import kz.todo.todobackend.service.PriorityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {

        this.priorityService = priorityService;

    }

    @GetMapping("/all")
    public List<Priority> findAll() {

        return priorityService.findAll();

    }

    @PostMapping("/add")
    public ResponseEntity<Priority> add(@RequestBody Priority priority) {

        if (priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getColor() == null || priority.getColor().trim().length() == 0) {
            return new ResponseEntity("missed param: color", HttpStatus.NOT_ACCEPTABLE);
        }


        return ResponseEntity.ok(priorityService.add(priority));

    }

    @PutMapping("/update")
    public ResponseEntity<Priority> update(@RequestBody Priority priority) {

        if (priority.getId() == null && priority.getId() == 0) {
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getColor() == null || priority.getColor().trim().length() == 0) {
            return new ResponseEntity("missed param: color", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityService.update(priority));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Priority> findById(@PathVariable Long id) {

        Priority priority = null;

        try {

            priority = priorityService.findById(id);

        } catch (NoSuchElementException e) {

            e.printStackTrace();
            return new ResponseEntity("id " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priority);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Priority> deleteByID(@PathVariable Long id) {

        Priority priority = null;
        try {

            priority = priorityService.findById(id);
            priorityService.deleteById(priority.getId());

        } catch (NoSuchElementException e) {

            e.printStackTrace();
            return new ResponseEntity("id " + id + " not found", HttpStatus.NOT_ACCEPTABLE);

        }

        return ResponseEntity.ok(priority);

    }
    @PostMapping("/search")
    private ResponseEntity<List<Priority>> search(@RequestBody PrioritySearchValues categorySearchValue){

        return ResponseEntity.ok(priorityService.findByTitle(categorySearchValue.getText()));
    }


}
