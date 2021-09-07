package ru.kolesnichenko.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kolesnichenko.springmvc.model.Role;
import ru.kolesnichenko.springmvc.model.User;
import ru.kolesnichenko.springmvc.service.RoleService;
import ru.kolesnichenko.springmvc.service.UserService;

import javax.validation.Valid;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/admin")
    public String printUsers(Model model) {
        model.addAttribute("user", userService.getUsers());
        return "/admin";
    }

    @GetMapping("/admin/add")
    public String addUserFrom(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("role", roleService.getAllRoles());
        return "/admins/add";
    }

    @PostMapping("/admin")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                          @RequestParam(value = "select_role", required = false) String[] roles) {
        if (bindingResult.hasErrors()) {
            return "/admins/add";
        }
        Set<Role> role = new HashSet<>();
        role.add(roleService.getAllRoles().get(1));
        for (String s : roles) {
            if(s.equals("ROLE_ADMIN")) {
                role.add(roleService.getAllRoles().get(0));
            }

        }
        user.setRoles(role);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/admins/edit";
    }

    @PatchMapping("/admin/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admins/edit";
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }
}
