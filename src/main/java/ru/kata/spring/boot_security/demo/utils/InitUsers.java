package ru.kata.spring.boot_security.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
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

        User user1 = new User("Peter", "Surkov", "12345");
        User user2 = new User("Dima", "Dorohov", "54321");
        User user3 = new User("Alexey", "Karpov", "32154");


        user1.addRole(roleService.findByName("ROLE_USER"));
        user2.addRole(roleService.findByName("ROLE_ADMIN"));
        user3.addRole(roleService.findByName("ROLE_USER"));
        user3.addRole(roleService.findByName("ROLE_ADMIN"));

        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);

    }
}
