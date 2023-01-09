package net.kongmenglorprojects.todolistsproject.services;

import net.kongmenglorprojects.todolistsproject.dto.addListDTO;
import net.kongmenglorprojects.todolistsproject.dto.createDTO;
import net.kongmenglorprojects.todolistsproject.dto.loginDTO;
import net.kongmenglorprojects.todolistsproject.entities.AccountEntity;
import net.kongmenglorprojects.todolistsproject.entities.ListsEntity;
import net.kongmenglorprojects.todolistsproject.repositories.AccountRepository;
import net.kongmenglorprojects.todolistsproject.repositories.ListsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    AccountRepository accountRepository;
    ListsRepository listsRepository;

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

    public List<ListsEntity> addTask(addListDTO dto) {
        Optional<AccountEntity> account = this.accountRepository.findById(dto.id);
        if(account.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        ListsEntity task = new ListsEntity(dto.date,dto.title,dto.description);
        List<ListsEntity> lists = new ArrayList<>();
        if (account.get().getToDoLists().isEmpty()) {
            lists.add(task);
            account.get().setToDoLists(lists);
            this.accountRepository.save(account.get());
            return account.get().getToDoLists();
        }
        lists = account.get().getToDoLists();
        lists.add(task);
        this.accountRepository.save(account.get());
        return account.get().getToDoLists();
    }
}
