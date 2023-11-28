package dev.cloudhandson.notesapp.note;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/notes")
public class NoteController {

    @GetMapping
    public String getNote() {
        return "getNote was called";
    }

    @PostMapping
    public String createNote() {
        return "createNote was called";
    }

    @PutMapping
    public String updateNote() {
        return "updateNote was called";
    }

    @DeleteMapping
    public String deleteNote() {
        return "deleteNote was called";
    }
}
