package net.kongmenglorprojects.todolistsproject.services;

import net.kongmenglorprojects.todolistsproject.dto.createDTO;
import net.kongmenglorprojects.todolistsproject.entities.AccountEntity;
import net.kongmenglorprojects.todolistsproject.repositories.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AccountService {
    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountEntity createAccount(createDTO dto) {
        if (dto.password.isBlank() || dto.email.isBlank() || dto.bDate == -1 || dto.image.isBlank() || dto.fname.isBlank() || dto.lname.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return this.accountRepository.save(new AccountEntity(dto.fname,dto.lname,dto.email,dto.password, dto.bDate, dto.image));
    }
}
