package codes;

import codes.config.AppProperties;
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
}
