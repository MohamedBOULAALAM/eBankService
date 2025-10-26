package b.m29.ebankservice.service;

import b.m29.ebankservice.dto.BankAccountRequestDTO;
import b.m29.ebankservice.dto.BankAccountResponseDTO;
import java.util.List;

public interface AccountService {
    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
    BankAccountResponseDTO getAccountById(String id);
    List<BankAccountResponseDTO> getAllAccounts();
    void deleteAccount(String id);
}