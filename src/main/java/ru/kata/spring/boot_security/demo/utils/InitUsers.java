package ru.kata.spring.boot_security.demo.utils;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;

@Component
public class InitUsers {
    private final UserService userService;
    private final RoleService roleService;

    public InitUsers(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void createUsers() {

        Role user = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");

        roleService.save(user);
        roleService.save(admin);

        User user1 = new User("user", "user", "200");
        User user2 = new User("admin", "admin", "200");


        user1.addRole(roleService.findByName("ROLE_USER"));
        user2.addRole(roleService.findByName("ROLE_ADMIN"));

        userService.saveUser(user1);
        userService.saveUser(user2);

    }
}
