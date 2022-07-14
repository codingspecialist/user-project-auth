package com.getinthere.evaluate;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.getinthere.evaluate.domain.user.Users;
import com.getinthere.evaluate.domain.user.UsersRepository;

@SpringBootApplication
public class EvaluateApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaluateApplication.class, args);
	}

	@Profile("dev")
	@Bean
	public CommandLineRunner initAdminUser(UsersRepository usersRepository) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return (args) -> {

			Optional<Users> usersOP = usersRepository.findByUsername("ssar");
			if (usersOP.isEmpty()) {
				Users user = Users.builder()
						.username("ssar")
						.password(encoder.encode("1234"))
						.email("getinthere@naver.com")
						.role("ROLE_ADMIN")
						.createDate(LocalDateTime.now())
						.updateDate(LocalDateTime.now())
						.build();

				usersRepository.save(user);
			}

		};
	}

}
