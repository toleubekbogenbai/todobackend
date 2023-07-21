package kz.todo.todobackend.controller;

import kz.todo.todobackend.entity.Category;
import kz.todo.todobackend.entity.Priority;
import kz.todo.todobackend.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/all")
    public List<Category> findAll() {

        return categoryRepository.findAllByOrderByTitleAsc();
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {

        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category) {

        if (category.getId() == null && category.getId() == 0) {
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }


        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {

        Category category = null;
        try {

            category = categoryRepository.findById(id).get();

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity("id " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(category);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Category> deleteByID(@PathVariable Long id) {

        Category category = null;
        try {

            category = categoryRepository.findById(id).get();
            categoryRepository.deleteById(category.getId());

        } catch (NoSuchElementException e) {

            e.printStackTrace();
            return new ResponseEntity("id " + id + " not found", HttpStatus.NOT_ACCEPTABLE);

        }

        return ResponseEntity.ok(category);

    }
}
