package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 이승수
 * 프로젝트 설정
 * @see DBConfig
 */
@Configuration
@ComponentScan(basePackages = { "dao" , "service"})
@Import(DBConfig.class)
public class ApplicationConfig {
}
