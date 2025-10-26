package b.m29.ebankservice.service;

import b.m29.ebankservice.dto.BankAccountRequestDTO;
import b.m29.ebankservice.dto.BankAccountResponseDTO;
import b.m29.ebankservice.entities.BankAccount;
import b.m29.ebankservice.mappers.AccountMapper;
import b.m29.ebankservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final BankAccountRepository bankAccountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(BankAccountRepository bankAccountRepository, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountMapper = accountMapper;
    }

    /*
        @Override
        public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
            BankAccount bankAccount = BankAccount.builder()
                    .id(UUID.randomUUID().toString())
                    .createdAT(new Date())
                    .balance(bankAccountDTO.getBalance())
                    .type(bankAccountDTO.getType())
                    .currency(bankAccountDTO.getCurrency())
                    .build();
            BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
            BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(savedBankAccount);

            return bankAccountResponseDTO;
        }
     */
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO requestDTO) {
        // Convertir le DTO en entité
        BankAccount bankAccount = accountMapper.toEntity(requestDTO);
        // Appliquer les règles métier
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAT(new Date());
        // Enregistrer l'entité
        BankAccount savedAccount = bankAccountRepository.save(bankAccount);
        // Convertir et retourner le DTO Response
        return accountMapper.toResponseDTO(savedAccount);
    }
/*
    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = BankAccount.builder()
                .id(id)
                .createdAT(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(savedBankAccount);
        return bankAccountResponseDTO;
    }
*/
    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO requestDTO) {
        BankAccount existingAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));

        // Mettre à jour uniquement les champs non nuls
        if (requestDTO.getBalance() != null) existingAccount.setBalance(requestDTO.getBalance());
        if (requestDTO.getCurrency() != null) existingAccount.setCurrency(requestDTO.getCurrency());
        if (requestDTO.getType() != null) existingAccount.setType(requestDTO.getType());

        BankAccount updatedAccount = bankAccountRepository.save(existingAccount);
        return accountMapper.toResponseDTO(updatedAccount);
    }

    @Override
    public List<BankAccountResponseDTO> getAllAccounts() {
        return bankAccountRepository.findAll()
                .stream()
                .map(accountMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    @Override
    public BankAccountResponseDTO getAccountById(String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
        return accountMapper.toResponseDTO(bankAccount);
    }
    @Override
    public void deleteAccount(String id) {
        if (!bankAccountRepository.existsById(id)) {
            throw new RuntimeException("Account not found with id: " + id);
        }
        bankAccountRepository.deleteById(id);
    }
}