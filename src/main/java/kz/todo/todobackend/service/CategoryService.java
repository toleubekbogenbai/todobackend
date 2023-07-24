package kz.todo.todobackend.service;

import jakarta.transaction.Transactional;
import kz.todo.todobackend.entity.Category;
import kz.todo.todobackend.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository repository;

    CategoryService(CategoryRepository repository){ this.repository = repository; }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category add(Category category){
        return repository.save(category);
    }

    public Category update(Category category){
        return repository.save(category);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<Category> findByTitle(String text){
        return repository.findByTitle(text);
    }

    public Category findById(Long id){
        return repository.findById(id).get();
    }

    public List<Category> findAllByOrderByTitleAsc(){
        return repository.findAllByOrderByTitleAsc();
    }

}
