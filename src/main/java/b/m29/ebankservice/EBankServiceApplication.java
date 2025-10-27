package b.m29.ebankservice;

import b.m29.ebankservice.entities.BankAccount;
import b.m29.ebankservice.entities.Customer;
import b.m29.ebankservice.enums.AccountType;
import b.m29.ebankservice.repositories.BankAccountRepository;
import b.m29.ebankservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

import static graphql.introspection.IntrospectionQueryBuilder.build;

@SpringBootApplication
public class EBankServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EBankServiceApplication.class, args);
	}

	@Bean
	/*
	CommandLineRunner start(BankAccountRepository bankAccountRepository){
		return args -> {
			BankAccount bankAccount = null;
			for (int i = 0; i < 10; i++) {
				bankAccount = BankAccount.builder()
						.id(UUID.randomUUID().toString())
						.type(Math.random() > 0.29 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVINGS_ACCOUNT)
						.balance(588 + Math.random() * 29555)
						.createdAT(new Date())
						.currency("MAD")
						.build();
				bankAccountRepository.save(bankAccount);
			}
		};
	}
	*/
	CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository) {
		return args -> {
			Stream.of("Mohamed","Yassin","Hanae").forEach(c -> {
				Customer customer =  Customer.builder()
						.name(c)
						.build();
				customerRepository.save(customer);
			});
			customerRepository.findAll().forEach(customer -> {
				BankAccount bankAccount = null;
				for (int i = 0; i < 10; i++) {
					bankAccount = BankAccount.builder()
							.id(UUID.randomUUID().toString())
							.type(Math.random() > 0.29 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVINGS_ACCOUNT)
							.balance(588 + Math.random() * 29555)
							.createdAT(new Date())
							.currency("MAD")
							.customer(customer)
							.build();
					bankAccountRepository.save(bankAccount);
				}
			});
		};
	}
}
