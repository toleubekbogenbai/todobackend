package kz.todo.todobackend.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Priority {
    private Long id;
    private String title;
    private String color;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }


    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }

}
