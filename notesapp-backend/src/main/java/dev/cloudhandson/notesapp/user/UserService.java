package dev.cloudhandson.notesapp.user;

import dev.cloudhandson.notesapp.user.model.UserDTO;
import dev.cloudhandson.notesapp.user.model.UserEntity;
import dev.cloudhandson.notesapp.utils.ApplicationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ApplicationUtils utils;

    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ApplicationUtils utils, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.utils = utils;
        this.modelMapper = modelMapper;
    }

    public UserDTO getUser(String userId) {
        Optional<UserEntity> optional = userRepository.findByUserId(userId);
        if (optional.isEmpty()) {
            String errorMessage = "User with userId '" + userId + "' does not exist";
            throw new UserNotFoundException(errorMessage);
        } else {
            return modelMapper.map(optional.get(), UserDTO.class);
        }
    }

    public UserDTO createUser(UserDTO userDTO) {
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        userEntity.setUserId(utils.generateUserId(30));
        userEntity.setEncryptedPassword(userDTO.getPassword());
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return modelMapper.map(savedUserEntity, UserDTO.class);
    }

    public UserDTO updateUser(String userId, UserDTO userDTO) {
        Optional<UserEntity> optional = userRepository.findByUserId(userId);
        if (optional.isEmpty()) {
            String errorMessage = "User with userId '" + userId + "' does not exist";
            throw new UserNotFoundException(errorMessage);
        } else {
            UserEntity userEntity = optional.get();
            userEntity.setFirstName(userDTO.getFirstName());
            userEntity.setLastName(userDTO.getLastName());
            UserEntity updateUserEntity = userRepository.save(userEntity);
            return modelMapper.map(updateUserEntity, UserDTO.class);
        }
    }

    public boolean deleteUser(String userId) {
        Optional<UserEntity> optional = userRepository.findByUserId(userId);
        if (optional.isEmpty()) {
            String errorMessage = "User with userId '" + userId + "' does not exist";
            throw new UserNotFoundException(errorMessage);
        } else {
            userRepository.delete(optional.get());
            return true;
        }
    }
}
