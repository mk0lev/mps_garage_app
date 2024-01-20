package com.example.mps.controllers.TLcontrollers;

import com.example.mps.model.DTO.UserDTO;
import com.example.mps.model.mappers.UserDTOMapper;
import com.example.mps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class RegistrationController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private UserDetailsManager userDetailsManager;
    private UserDTOMapper userDTOMapper;

    @Autowired
    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder, UserDetailsManager userDetailsManager, UserDTOMapper userDTOMapper) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsManager = userDetailsManager;
        this.userDTOMapper = userDTOMapper;
    }

    @GetMapping("/register")
    public String showLoginPage(Model model){
        model.addAttribute(new UserDTO());
        model.addAttribute(userService.getAllWorkshops());
        return "sign_up";
    }

    @PostMapping("/registerClient")
    public String registerClient(@ModelAttribute UserDTO user){
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        org.springframework.security.core.userdetails.User newUser =
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        passwordEncoder.encode(user.getPassword()),
                        authorities);
        userDetailsManager.createUser(newUser);

        userService.updateUser(userDTOMapper.mapDtoToUser(user));

        return "redirect:/";
    }

    @PostMapping("/registerWorker")
    public String registerWorker(@ModelAttribute UserDTO user){
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        org.springframework.security.core.userdetails.User newUser =
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        passwordEncoder.encode(user.getPassword()),
                        authorities);
        userDetailsManager.createUser(newUser);

        userService.updateUser(userDTOMapper.mapDtoToUser(user));

        return "redirect:/";
    }
}