package net.kongmenglorprojects.todolistsproject.services;

import net.kongmenglorprojects.todolistsproject.dto.createDTO;
import net.kongmenglorprojects.todolistsproject.dto.loginDTO;
import net.kongmenglorprojects.todolistsproject.entities.AccountEntity;
import net.kongmenglorprojects.todolistsproject.repositories.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AccountService {
    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountEntity createAccount(createDTO dto) {
        if (dto.password.isBlank() || dto.email.isBlank() || dto.bDate == -1 || dto.fName.isBlank() || dto.lName.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return this.accountRepository.save(new AccountEntity(dto.fName,dto.lName,dto.email,dto.password, dto.bDate));
    }

    public AccountEntity login(String email, String password) {
        if (email.isBlank() || password.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Optional<AccountEntity> optional = this.accountRepository.findByEmailAndPassword(email,password);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optional.get();
    }
}
