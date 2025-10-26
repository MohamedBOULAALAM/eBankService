package b.m29.ebankservice.mappers;

import b.m29.ebankservice.dto.BankAccountRequestDTO;
import b.m29.ebankservice.dto.BankAccountResponseDTO;
import b.m29.ebankservice.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount) {
        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount, bankAccountResponseDTO);
        return bankAccountResponseDTO;
    }
    /**
     * Convertit un BankAccount en BankAccountResponseDTO
     */
    public BankAccountResponseDTO toResponseDTO(BankAccount bankAccount) {
        return BankAccountResponseDTO.builder()
                .id(bankAccount.getId())
                .createdAT(bankAccount.getCreatedAT())
                .balance(bankAccount.getBalance())
                .currency(bankAccount.getCurrency())
                .type(bankAccount.getType())
                .build();
    }

    /**
     * Convertit un BankAccountRequestDTO en BankAccount
     */
    public BankAccount toEntity(BankAccountRequestDTO requestDTO) {
        return BankAccount.builder()
                .balance(requestDTO.getBalance())
                .currency(requestDTO.getCurrency())
                .type(requestDTO.getType())
                .build();
    }
}