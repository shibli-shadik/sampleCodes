package codes;

import codes.config.AppProperties;
import codes.model.compositeKey.Account;
import codes.model.compositeKey.AccountId;
import codes.model.compositeKey.AccountRepository;
import codes.model.compositeKey.Employee;
import codes.model.compositeKey.EmployeeId;
import codes.model.compositeKey.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(AppProperties.class)
public class SampleCodesApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SampleCodesApplication.class, args);
    }
    
    @Bean
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }
    
    @Bean
    public CommandLineRunner mappingDemo(AccountRepository accountRepository, EmployeeRepository employeeRepository)
    {
        return args ->
        {
            // ======= `@IdClass` Annotation =======
            // create new accounts
            System.out.println("=====================Insert===========================");
            accountRepository.save(new Account("458666", "Checking", 4588));
            accountRepository.save(new Account("458689", "Checking", 2500));
            accountRepository.save(new Account("424265", "Saving", 100000));
            //accountRepository.save(new Account("666665", "Saving", 999));
            
            // fetch accounts by a given type
            System.out.println("==============================================================");
            List<Account> accounts = accountRepository.findByAccountType("Checking");
            accounts.forEach(System.out::println);
            
            // fetch account by composite key
            System.out.println("==============================================================");
            Optional<Account> account = accountRepository.findById(new AccountId("424265", "Saving"));
            account.ifPresent(System.out::println);
            
            // ======= `@EmbeddedId` Annotation =======
            // create new employees
            employeeRepository.save(new Employee(new EmployeeId(100L, 10L),
                    "John Doe", "john@example.com", "123456"));
            employeeRepository.save(new Employee(new EmployeeId(101L, 20L),
                    "Emma Ali", "emma@example.com", "654321"));
            employeeRepository.save(new Employee(new EmployeeId(102L, 20L),
                    "Sefa Ali", "sefa@example.com", "654344"));
            
            // fetch employees by a given department id
            List<Employee> employees = employeeRepository.findByEmployeeIdDepartmentId(20L);
            employees.forEach(System.out::println);
            
            // fetch employee by composite key
            Optional<Employee> employee = employeeRepository.findById(new EmployeeId(100L, 10L));
            employee.ifPresent(System.out::println);
        };
    }
}
