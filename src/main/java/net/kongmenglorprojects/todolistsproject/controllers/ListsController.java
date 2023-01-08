package net.kongmenglorprojects.todolistsproject.controllers;

import net.kongmenglorprojects.todolistsproject.dto.addListDTO;
import net.kongmenglorprojects.todolistsproject.entities.ListsEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/tasks")
@RestController
@CrossOrigin
public class ListsController {
    @PostMapping
    public List<ListsEntity> addTask(@RequestBody addListDTO dto) {

    }
}
