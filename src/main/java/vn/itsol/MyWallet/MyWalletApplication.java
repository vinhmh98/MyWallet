package vn.itsol.MyWallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class MyWalletApplication {
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
    	return new WebMvcConfigurer() {
    		@Override
    		public void addCorsMappings(CorsRegistry registry) {
    			registry.addMapping("/*").allowedHeaders("*").allowedOrigins().allowCredentials(true);
    		}
    	};
    }

	public static void main(String[] args) {
		SpringApplication.run(MyWalletApplication.class, args);
	}

}
