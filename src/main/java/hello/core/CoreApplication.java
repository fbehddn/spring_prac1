package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {

		SpringApplication.run(CoreApplication.class, args);
	}

	/*
	~ ❯ sudo lsof -i:8080 -> 8080 포트 조회
	~ ❯ sudo kill -9 "PID" -> 알맞은 PID 대입 후 서버 종료
	 */
}
