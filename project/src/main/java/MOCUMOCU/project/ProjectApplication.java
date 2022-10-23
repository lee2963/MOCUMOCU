package MOCUMOCU.project;

import MOCUMOCU.project.customer.Customer;
import MOCUMOCU.project.customer.CustomerService;
import MOCUMOCU.project.customer.Gender;
import MOCUMOCU.project.owner.Owner;
import MOCUMOCU.project.owner.OwnerService;
import javafx.util.converter.LocalDateStringConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}


	@Bean
	CommandLineRunner run(OwnerService ownerService, CustomerService customerService) {
		return args -> {
			Owner owner = new Owner();
			Privacy privacy = new Privacy();
			privacy.setName("여민수");
			privacy.setPassword("1234qwer!");
			privacy.setEmail("yeo@owner.com");
			privacy.setPhoneNum("01069696969");

			owner.setPrivacy(privacy);

			ownerService.join(owner);

			Customer customer = new Customer();
			customer.setGender(Gender.MALE);
			customer.setBirthDate("1997-08-20");
			customer.setLastDate(LocalDate.now());
			privacy.setName("여민수12");
			privacy.setPassword("1234qwer!");
			privacy.setEmail("yeo@customer.com");
			privacy.setPhoneNum("01074747474");
			customer.setPrivacy(privacy);

			customerService.join(customer);
		};
	}
}
