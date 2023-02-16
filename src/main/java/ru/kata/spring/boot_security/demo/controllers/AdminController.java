package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService,
                           RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String viewUser(Principal principal, Model model) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleService.findAll());
        return "admin-panel";
    }


    @PostMapping
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam("roles") Set<Role> roles) {
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.deleteById(user.getId());
        return "redirect:/admin";
    }

    @PatchMapping
    public String updateUser(@ModelAttribute("user") User user, @ModelAttribute("roles") List<Integer> rolesId) {
        Set<Role> roles = new HashSet<>();
        for (int roleId : rolesId) {
            roles.add(roleService.getRoleById(roleId));
        }
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }

}