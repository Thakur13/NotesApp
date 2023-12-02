package dev.cloudhandson.notesapp.note.model;

import java.io.Serializable;

public class NoteDTO {

    private Long id;
    private String noteId;
    private String title;
    private String description;

    public NoteDTO() {
    }

    public NoteDTO(Long id, String noteId, String title, String description) {
        this.id = id;
        this.noteId = noteId;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
