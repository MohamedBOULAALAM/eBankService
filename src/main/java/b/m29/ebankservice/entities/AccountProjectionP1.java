package b.m29.ebankservice.entities;


import b.m29.ebankservice.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = BankAccount.class, name = "p1")
public interface AccountProjectionP1 {
    public String getId();
    public AccountType getType();
    public Double getBalance();
}