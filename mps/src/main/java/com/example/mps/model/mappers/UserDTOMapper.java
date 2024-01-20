package com.example.mps.model.mappers;

import com.example.mps.model.DTO.UserDTO;
import com.example.mps.model.User;
import com.example.mps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {
    private final UserService userService;

    @Autowired
    public UserDTOMapper(UserService userService) {
        this.userService = userService;
    }

    public User mapDtoToUser(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(user.getPassword());
        user.setEnabled(user.isEnabled());

        if (user.getServices() != null) {
            user.setServices(userDTO.getServices());
        }

        if(userDTO.getWorkshopName() != null)
        user.setWorkshop(userService.getWorkshopByName(userDTO.getWorkshopName()));

        return user;
    }
}
