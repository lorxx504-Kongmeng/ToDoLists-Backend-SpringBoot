package net.kongmenglorprojects.todolistsproject.controllers;
import net.kongmenglorprojects.todolistsproject.dto.createDTO;
import net.kongmenglorprojects.todolistsproject.entities.AccountEntity;
import org.springframework.web.bind.annotation.*;
import net.kongmenglorprojects.todolistsproject.services.AccountService;

@RestController
@CrossOrigin
@RequestMapping("/api/account")
public class AccountController {
    AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping
    public AccountEntity createAccount(@RequestBody createDTO dto) {
        return this.accountService.createAccount(dto);
    }
}
