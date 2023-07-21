package kz.todo.todobackend.controller;

import kz.todo.todobackend.entity.Priority;
import kz.todo.todobackend.repository.PriorityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private PriorityRepository priorityRepository;

    public PriorityController(PriorityRepository priorityRepository) {

        this.priorityRepository = priorityRepository;

    }

    @GetMapping("/all")
    public List<Priority> findAll() {

        return priorityRepository.findAllByOrderByIdAsc();

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


        return ResponseEntity.ok(priorityRepository.save(priority));

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

        return ResponseEntity.ok(priorityRepository.save(priority));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Priority> findById(@PathVariable Long id) {

        Priority priority = null;

        try {

            priority = priorityRepository.findById(id).get();

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

            priority = priorityRepository.findById(id).get();
            priorityRepository.deleteById(priority.getId());

        } catch (NoSuchElementException e) {

            e.printStackTrace();
            return new ResponseEntity("id " + id + " not found", HttpStatus.NOT_ACCEPTABLE);

        }

        return ResponseEntity.ok(priority);

    }


}
