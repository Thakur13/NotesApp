package dev.cloudhandson.notesapp.note.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "note")
public class NoteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NOTE_ID", nullable = false, unique = true)
    private String noteId;

    @Column(name = "TITLE", columnDefinition = "TEXT NOT NULL UNIQUE")
    private String title;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

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
