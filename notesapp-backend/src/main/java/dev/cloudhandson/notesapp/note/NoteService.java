package dev.cloudhandson.notesapp.note;

import dev.cloudhandson.notesapp.note.model.NoteDTO;
import dev.cloudhandson.notesapp.utils.ApplicationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    private final ApplicationUtils utils;

    private final ModelMapper modelMapper;


    @Autowired
    public NoteService(NoteRepository noteRepository, ApplicationUtils utils, ModelMapper modelMapper) {
        this.noteRepository = noteRepository;
        this.utils = utils;
        this.modelMapper = modelMapper;
    }

    public NoteDTO getNote(String noteId) {
        Optional<NoteEntity> optional = noteRepository.findByNoteId(noteId);
        if (optional.isEmpty()) {
            String errorMessage = "Note with noteId '" + noteId + "' does not exist";
            throw new NoteNotFoundException(errorMessage);
        } else {
            return modelMapper.map(optional.get(), NoteDTO.class);
        }
    }

    public NoteDTO createNote(NoteDTO noteDTO) {
        NoteEntity noteEntity = modelMapper.map(noteDTO, NoteEntity.class);
        noteEntity.setNoteId(utils.generateNoteId(30));
        NoteEntity savedNoteEntity = noteRepository.save(noteEntity);
        return modelMapper.map(savedNoteEntity, NoteDTO.class);
    }

    public NoteDTO updateNote(String noteId, NoteDTO noteDTO) {
        Optional<NoteEntity> optional = noteRepository.findByNoteId(noteId);
        if (optional.isEmpty()) {
            String errorMessage = "Note with noteId '" + noteId + "' does not exist";
            throw new NoteNotFoundException(errorMessage);
        } else {
            NoteEntity noteEntity = optional.get();
            noteEntity.setTitle(noteDTO.getTitle());
            noteEntity.setDescription(noteDTO.getDescription());
            NoteEntity updateNoteEntity = noteRepository.save(noteEntity);
            return modelMapper.map(updateNoteEntity, NoteDTO.class);
        }
    }

    public boolean deleteNote(String noteId) {
        Optional<NoteEntity> optional = noteRepository.findByNoteId(noteId);
        if (optional.isEmpty()) {
            String errorMessage = "Note with noteId '" + noteId + "' does not exist";
            throw new NoteNotFoundException(errorMessage);
        } else {
            noteRepository.delete(optional.get());
        }
        return true;
    }
}
