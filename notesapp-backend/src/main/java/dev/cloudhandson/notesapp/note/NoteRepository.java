package dev.cloudhandson.notesapp.note;

import dev.cloudhandson.notesapp.note.model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {

    Optional<NoteEntity> findByNoteId(String noteId);
}
