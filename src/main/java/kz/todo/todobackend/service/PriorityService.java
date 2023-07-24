package kz.todo.todobackend.service;

import jakarta.transaction.Transactional;
import kz.todo.todobackend.entity.Category;
import kz.todo.todobackend.entity.Priority;
import kz.todo.todobackend.repository.PriorityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PriorityService {

    private final PriorityRepository repository;

    PriorityService( PriorityRepository repository ){
        this.repository = repository;
    }

    public List<Priority> findAll() {
        return repository.findAll();
    }

    public Priority add(Priority priority){
        return repository.save(priority);
    }

    public Priority update(Priority priority){
        return repository.save(priority);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<Priority> findByTitle(String text){
        return repository.findByTitle(text);
    }

    public Priority findById(Long id){
        return repository.findById(id).get();
    }

}
