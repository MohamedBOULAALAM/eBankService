package b.m29.ebankservice.web;

import b.m29.ebankservice.dto.BankAccountRequestDTO;
import b.m29.ebankservice.dto.BankAccountResponseDTO;
import b.m29.ebankservice.service.AccountService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

/*
@RestController
@RequestMapping("/api")
public class BankAccountRestController {

    private BankAccountRepository bankAccountRepository;
    public BankAccountRestController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id) {
        return bankAccountRepository.findById(id).
                orElseThrow(
                        ()-> new RuntimeException(
                                String.format("Bank account with id %s not found", id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccount save(@RequestBody BankAccount bankAccount) {
        if (bankAccount.getId()== null) bankAccount.setId(UUID.randomUUID().toString());
        return bankAccountRepository.save(bankAccount);
    }
    @PutMapping ("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id ,@RequestBody BankAccount bankAccount) {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow();
        if (bankAccount.getBalance()!=null)account.setBalance(bankAccount.getBalance());
        if (bankAccount.getCurrency()!=null)account.setCurrency(bankAccount.getCurrency());
        if (bankAccount.getType()!=null)account.setType(bankAccount.getType());
        if (bankAccount.getCreatedAT()!=null)account.setCreatedAT(new Date());
        return bankAccountRepository.save(account);
    }
    @DeleteMapping ("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }
}

*/

@RestController
@RequestMapping("/bankAccounts")
public class BankAccountRestController {
    private final AccountService accountService;
    public BankAccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

//GET - Consultation de la liste des comptes
    @GetMapping
    public List<BankAccountResponseDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }
//GET - Consultation d'un compte par ID
    @GetMapping("/{id}")
    public ResponseEntity<BankAccountResponseDTO> getAccountById(@PathVariable String id) {
        try {
            BankAccountResponseDTO account = accountService.getAccountById(id);
            return ResponseEntity.ok(account);
        } catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }
//POST - Ajout d'un nouveau compte
    @PostMapping
    public ResponseEntity<BankAccountResponseDTO> addAccount(
            @RequestBody BankAccountRequestDTO requestDTO) {
        BankAccountResponseDTO createdAccount = accountService.addAccount(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }
//PUT - Mise à jour d'un compte
    @PutMapping("/{id}")
    public ResponseEntity<BankAccountResponseDTO> updateAccount(
            @PathVariable String id,
            @RequestBody BankAccountRequestDTO requestDTO) {
        try {
            BankAccountResponseDTO updatedAccount = accountService.updateAccount(id, requestDTO);
            return ResponseEntity.ok(updatedAccount);
        } catch (RuntimeException e) { return ResponseEntity.notFound().build();}
    }
//DELETE - Suppression d'un compte
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
        try {
            accountService.deleteAccount(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {return ResponseEntity.notFound().build();}
    }
}