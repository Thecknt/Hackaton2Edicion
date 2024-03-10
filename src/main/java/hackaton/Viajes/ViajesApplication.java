package hackaton.Viajes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"hackaton.Viajes"})
public class ViajesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViajesApplication.class, args);
	}

}
