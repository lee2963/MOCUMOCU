package MOCUMOCU.project;

import MOCUMOCU.project.customer.entity.Customer;
import MOCUMOCU.project.customer.service.CustomerService;
import MOCUMOCU.project.customer.entity.Gender;
import MOCUMOCU.project.customize.entity.Customize;
import MOCUMOCU.project.customize.entity.Type;
import MOCUMOCU.project.customize.service.CustomizeService;
import MOCUMOCU.project.owner.entity.Owner;
import MOCUMOCU.project.owner.service.OwnerService;
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
	CommandLineRunner run(OwnerService ownerService, CustomerService customerService, CustomizeService customizeService) {
		return args -> {
			Owner owner = new Owner();
			Privacy privacy = new Privacy();
			privacy.setName("여민수");
			privacy.setPassword("1234qwer!");
			privacy.setEmail("yeo@owner.com");
			privacy.setPhoneNum("010-6969-6969");

			owner.setPrivacy(privacy);

			ownerService.join(owner);

			Customer customer = new Customer();
			customer.setGender(Gender.MALE);
			customer.setBirthDate("1997-08-20");
			customer.setAttendance(LocalDate.now().minusDays(1));
			privacy.setName("여민수12");
			privacy.setPassword("1234qwer!");
			privacy.setEmail("yeo@customer.com");
			privacy.setPhoneNum("010-7474-7474");
			customer.setPrivacy(privacy);

			customerService.join(customer);

			Customize board1 = new Customize();
			board1.setCustomizePoint(30);
			board1.setBigImageUrl("https://mocumocu-bucket.s3.ap-northeast-2.amazonaws.com/customize/1/large1.png");
			board1.setSmallImageUrl("https://mocumocu-bucket.s3.ap-northeast-2.amazonaws.com/customize/1/small1.png");
			board1.setType(Type.BOARD);

			customizeService.saveCustomize(board1);

			Customize board2 = new Customize();
			board2.setCustomizePoint(30);
			board2.setBigImageUrl("https://mocumocu-bucket.s3.ap-northeast-2.amazonaws.com/customize/2/large2.png");
			board2.setSmallImageUrl("https://mocumocu-bucket.s3.ap-northeast-2.amazonaws.com/customize/2/small2.png\n");
			board2.setType(Type.BOARD);
			customizeService.saveCustomize(board2);

			Customize board3 = new Customize();
			board3.setCustomizePoint(30);
			board3.setBigImageUrl("https://mocumocu-bucket.s3.ap-northeast-2.amazonaws.com/customize/3/large3.png");
			board3.setSmallImageUrl("https://mocumocu-bucket.s3.ap-northeast-2.amazonaws.com/customize/3/small3.png");
			board3.setType(Type.BOARD);
			customizeService.saveCustomize(board3);

			Customize board4 = new Customize();
			board4.setCustomizePoint(30);
			board4.setBigImageUrl("https://mocumocu-bucket.s3.ap-northeast-2.amazonaws.com/customize/4/large4.png");
			board4.setSmallImageUrl("https://mocumocu-bucket.s3.ap-northeast-2.amazonaws.com/customize/4/small4.png");
			board4.setType(Type.BOARD);
			customizeService.saveCustomize(board4);

			Customize board5 = new Customize();
			board5.setCustomizePoint(30);
			board5.setBigImageUrl("https://mocumocu-bucket.s3.ap-northeast-2.amazonaws.com/customize/5/large5.png");
			board5.setSmallImageUrl("https://mocumocu-bucket.s3.ap-northeast-2.amazonaws.com/customize/5/small5.png");
			board5.setType(Type.BOARD);
			customizeService.saveCustomize(board5);

			Customize stamp1 = new Customize();
			stamp1.setCustomizePoint(10);
			stamp1.setSmallImageUrl("https://mocumocu-bucket.s3.ap-northeast-2.amazonaws.com/stamp/stamp1.png");
			stamp1.setType(Type.STAMP);
			customizeService.saveCustomize(stamp1);

			Customize stamp2 = new Customize();
			stamp2.setCustomizePoint(10);
			stamp2.setSmallImageUrl("https://mocumocu-bucket.s3.ap-northeast-2.amazonaws.com/stamp/stamp2.png");
			stamp2.setType(Type.STAMP);
			customizeService.saveCustomize(stamp2);

			Customize stamp3 = new Customize();
			stamp3.setCustomizePoint(10);
			stamp3.setSmallImageUrl("https://mocumocu-bucket.s3.ap-northeast-2.amazonaws.com/stamp/stamp3.png");
			stamp3.setType(Type.STAMP);
			customizeService.saveCustomize(stamp3);

			Customize stamp4 = new Customize();
			stamp4.setCustomizePoint(10);
			stamp4.setSmallImageUrl("https://mocumocu-bucket.s3.ap-northeast-2.amazonaws.com/stamp/stamp4.png");
			stamp4.setType(Type.STAMP);
			customizeService.saveCustomize(stamp4);
		};
	}
}
