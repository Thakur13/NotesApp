package dev.cloudhandson.notesapp.user;

import dev.cloudhandson.notesapp.user.model.UserDTO;
import dev.cloudhandson.notesapp.user.model.UserRequestModel;
import dev.cloudhandson.notesapp.user.model.UserRestModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;

    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserRestModel getUser(@PathVariable("userId") String userId) {
        return modelMapper.map(userService.getUser(userId), UserRestModel.class);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserRestModel createUser(@RequestBody UserRequestModel userRequestModel) {
        UserDTO userDTO = modelMapper.map(userRequestModel, UserDTO.class);
        UserDTO createdUser = userService.createUser(userDTO);
        return modelMapper.map(createdUser, UserRestModel.class);
    }

    @PutMapping(path = "/{userId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserRestModel updateUser(@PathVariable("userId") String userId, @RequestBody UserRequestModel userRequestModel) {
        UserDTO userDTO = modelMapper.map(userRequestModel, UserDTO.class);
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        return modelMapper.map(updatedUser, UserRestModel.class);
    }

    @DeleteMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        Map<String, String> res = new HashMap<>();
        res.put("status", "success");
        return ResponseEntity.ok(res);
    }
}
