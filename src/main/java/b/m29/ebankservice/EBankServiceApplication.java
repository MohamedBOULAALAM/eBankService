package b.m29.ebankservice;

import b.m29.ebankservice.entities.BankAccount;
import b.m29.ebankservice.enums.AccountType;
import b.m29.ebankservice.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

import static graphql.introspection.IntrospectionQueryBuilder.build;

@SpringBootApplication
public class EBankServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EBankServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository){
		return args -> {
			BankAccount bankAccount = BankAccount.builder()
					.id(UUID.randomUUID().toString())
					.type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT : AccountType.SAVINGS_ACCOUNT)
					.balance( 588 + Math.random() * 29555)
					.currency("MAD")
					.createdAT(new Date())
					.build();
			bankAccountRepository.save(bankAccount);
		};
	}

}
