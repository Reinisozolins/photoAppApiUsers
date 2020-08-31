package lv.ozo.photoAppApiUsers.ui.controllers;

import lv.ozo.photoAppApiUsers.shared.UserDto;
import lv.ozo.photoAppApiUsers.ui.model.CreateUserRequestModel;
import lv.ozo.photoAppApiUsers.ui.model.CreateUserResponseModel;
import lv.ozo.photoAppApiUsers.ui.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
// /users/status/check
// http://localhost:8011/users-ws/users/status/check
public class UserController {

    @Autowired
    private Environment env;

    @Autowired
    UserService userService;

    @GetMapping("/status/check")
    public String status()
    {
        return "Users Working on port"+ env.getProperty("local.server.port");
    }

//    @PostMapping
//    public String createUser(@Valid @RequestBody CreateUserRequestModel userDetails)
//    {
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        UserDto userDto= modelMapper.map(userDetails, UserDto.class);
//        userService.createUser(userDto);
//
//    return "Create user method is called";
//    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}

    )
    public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody CreateUserRequestModel userDetails)
    {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto= modelMapper.map(userDetails, UserDto.class);
        UserDto createUser= userService.createUser(userDto);

//        new object
//        used mapper to coppy value from dao
        CreateUserResponseModel returnValue = modelMapper.map(createUser, CreateUserResponseModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
}
