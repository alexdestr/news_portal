package test.ru.vegd;


import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@TestConfiguration
@ComponentScan(basePackages = { "ru.vegd.dao", "ru.vegd.service" })
@TestPropertySource("database.properties")
@EnableTransactionManagement
public class TestConfig {

}
