
# Code à recopier

## main
~~~java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
~~~

## Security

~~~java
package glg203.securityDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(
	// prePostEnabled = true (valeur par défaut)
	jsr250Enabled = true,
	securedEnabled = true
) 
public class WebSecurityConfig  {

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (WebSecurity web) -> 
			web.ignoring()					
				.requestMatchers("/css/**");
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception  {	
		http.authorizeHttpRequests(
			authRequest -> {
				authRequest.requestMatchers("/admin/**").hasRole("ADMIN");
				authRequest.requestMatchers("/zoneConnectee").authenticated();
				authRequest.anyRequest().denyAll();
			}
		)
		.formLogin(Customizer.withDefaults())
		.anonymous(Customizer.withDefaults()) 
		// .csrf(cust -> {cust.disable();}); // désactiverait la protection csrf!!
		;

		return http.build();
	}
}
~~~
## application.properties

~~~
# Active un profil donné
# spring.profiles.active=development

# autorise les redéfinitions de beans
# spring.main.allow-bean-definition-overriding=true

# Active ou non la console h2
spring.h2.console.enabled=false

# Base de données
spring.datasource.driver-class-name=org.h2.Driver
# Quelques configurations possibles (en garder une seule)
spring.datasource.url=jdbc:h2:~/mabase
spring.datasource.url=jdbc:h2:mem:demo;DB_CLOSE_ON_EXIT=false

spring.datasource.username=sa
spring.datasource.password=

# JPA
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect


# Configuration de Flyway
spring.flyway.enabled=true

# Gestion à distance (vraiment très très permissive !!!)
management.endpoints.web.exposure.include=*

# Divers/admin
info.app.name=Mes Dvd
info.app.description=Une application simple de gestion de dvds.
info.app.version=1.0.0

# Logging (extraits trouvés dans nos différents exemples ; cet ensemble n'est ni cohérent ni conseillé)
# logging.level.org.springframework.ws.*=trace
#logging.level.com=off
#logging.level.glg203=off
#logging.level.org.springframework.jdbc.*=trace  
#logging.level.org.springframework.transaction.interceptor.TransactionInterceptor=trace
#logging.level.org=off
logging.level.com.atomikos=debug
logging.level.glg203.*=off
logging.level.org.*=error
logging.level.org.apache.activemq=info
logging.level.org.flywaydb=DEBUG
logging.level.org.glg203.dvd.ui=INFO
logging.level.org.hibernate.SQL=INFO
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
logging.level.org.springframework.jdbc.*=trace  
logging.level.org.springframework.orm.jpa=DEBUG
logging.level.org.springframework.transaction.interceptor.TransactionInterceptor=trace
logging.level.org.springframework.transaction.interceptor=TRACE
logging.level.org.springframework.transaction=DEBUG
logging.level.org.springframework.web=INFO
logging.level.org.springframework.ws.*=trace
logging.level.root=INFO
logging.level.web=DEBUG
logging.level.web=INFO

~~~

## Logging dans une classe

1. Définir un logger pour la classe
~~~
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

...
 /**
  * Un logger log4j pour tester.
  */
  Logger logger = LogManager.getLogger(DvdController.class);

...

// L'utiliser :
logger.info("informations");
~~~

2. Niveaux de logging

Soit dans le application.properties (voir ci-dessus), soit comme arguments de la VM :
~~~
-Dlogging.level.org.springframework=TRACE
~~~

3. Destination du logging

Dans application.properties
~~~
logging.file=...
~~~

## Inspiration

(quelques projets des démos de glg 203 qui peuvent servir)

- Exemple de templates thymeleaf : 09_spring_jpa2/01_spring_jpa

