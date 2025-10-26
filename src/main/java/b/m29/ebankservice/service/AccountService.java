package b.m29.ebankservice.service;

import b.m29.ebankservice.dto.BankAccountRequestDTO;
import b.m29.ebankservice.dto.BankAccountResponseDTO;

public interface AccountService {
    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
}