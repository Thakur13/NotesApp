package dev.cloudhandson.notesapp.note;

import dev.cloudhandson.notesapp.note.model.NoteDTO;
import dev.cloudhandson.notesapp.note.model.NoteRequestModel;
import dev.cloudhandson.notesapp.note.model.NoteRestModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/notes")
public class NoteController {

    private final NoteService noteService;

    private final ModelMapper modelMapper;

    @Autowired
    public NoteController(NoteService noteService, ModelMapper modelMapper) {
        this.noteService = noteService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(path = "/{noteId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public NoteRestModel getNote(@PathVariable("noteId") String noteId) {
        return modelMapper.map(noteService.getNote(noteId), NoteRestModel.class);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public NoteRestModel createNote(@RequestBody NoteRequestModel noteRequestModel) {
        NoteDTO noteDTO = modelMapper.map(noteRequestModel, NoteDTO.class);
        NoteDTO createdNote = modelMapper.map(noteService.createNote(noteDTO), NoteDTO.class);
        return modelMapper.map(createdNote, NoteRestModel.class);
    }

    @PutMapping(path = "/{noteId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public NoteRestModel updateNote(@PathVariable("noteId") String noteId, @RequestBody NoteRequestModel noteRequestModel) {
        NoteDTO noteDTO = modelMapper.map(noteRequestModel, NoteDTO.class);
        NoteRestModel res = modelMapper.map(noteService.updateNote(noteId, noteDTO), NoteRestModel.class);
        return res;
    }

    @DeleteMapping(path = "/{noteId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteNote(@PathVariable("noteId") String noteId) {
        noteService.deleteNote(noteId);
        Map<String, String> res = new HashMap<>();
        res.put("status", "success");
        return ResponseEntity.ok(res);
    }
}
