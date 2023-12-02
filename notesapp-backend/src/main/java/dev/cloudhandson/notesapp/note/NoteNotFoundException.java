package dev.cloudhandson.notesapp.note;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException() {
    }

    public NoteNotFoundException(String message) {
        super(message);
    }
}
