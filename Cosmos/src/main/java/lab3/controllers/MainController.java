package lab3.controllers;

import lab3.models.Account;
import lab3.models.SpaceObject;
import lab3.repositories.SpaceRepo;
import lab3.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final SpaceRepo spaceRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder enc;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("objects", spaceRepo.findAll());
        model.addAttribute("view", "main");
        return "index";
    }

    @GetMapping("/register")
    public String regForm(Model model) {
        model.addAttribute("view", "register");
        return "index";
    }

    @PostMapping("/register")
    public String register(String username, String password) {
        log.info("РГР: Реєстрація " + username);
        userRepo.save(new Account(null, username, enc.encode(password), "ROLE_USER"));
        return "redirect:/login";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("obj", spaceRepo.findById(id).orElseThrow());
        model.addAttribute("objects", spaceRepo.findAll());
        model.addAttribute("view", "edit");
        return "index";
    }

    @PostMapping("/save")
    public String save(SpaceObject obj) {
        log.info("РГР: Збереження об'єкта " + obj.getName());
        spaceRepo.save(obj);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        log.info("РГР: Видалення об'єкта ID " + id);
        spaceRepo.deleteById(id);
        return "redirect:/";
    }
}