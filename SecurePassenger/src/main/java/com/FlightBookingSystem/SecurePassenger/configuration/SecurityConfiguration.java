package com.FlightBookingSystem.SecurePassenger.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.FlightBookingSystem.SecurePassenger.service.MongoUserDetailsService;


@Configuration
@EnableConfigurationProperties
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Autowired
  MongoUserDetailsService userDetailsService;
 
  
  @Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.POST, "/api/v1/users");
		//web.ignoring().antMatchers(HttpMethod.DELETE, "/api/v1/delete/{id}");
	//	web.ignoring().antMatchers(HttpMethod.GET, "/api/v1/");

	}
  
  
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
	  http.cors();
    http
      .csrf().disable()
      .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "**/**").permitAll()
      .antMatchers("/api/v1/findAllUsers", "/api/v1/", "/api/v1/delete/{id}").hasAnyAuthority("USER","ADMIN") .anyRequest().authenticated()
        .and()
        .httpBasic();
		
  }
 
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { auth.inMemoryAuthentication().withUser("Java").password(
	 * "$2a$04$hRZ5qVzQ6HqW.fuGzfcIxuaMEu9Cd/amavqKh8s/cfzoDDuUHGY.G").roles("ADMIN"
	 * ); //auth.userDetailsService(userDetailsService); }
	 */
	
  
  
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
  	return new BCryptPasswordEncoder();
  }
 
  @Override
  public void configure(AuthenticationManagerBuilder builder) throws Exception {
	 builder.inMemoryAuthentication().withUser("Java").password("$2a$04$hRZ5qVzQ6HqW.fuGzfcIxuaMEu9Cd/amavqKh8s/cfzoDDuUHGY.G").roles("ADMIN");
    builder.userDetailsService(userDetailsService);
  }
}


	/*@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	DataSource dataSource;

	
	
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * http .authorizeRequests() .antMatchers( "/registerpassenger").permitAll()
	 * .antMatchers("/findAllUser").hasAnyRole("ADMIN").anyRequest().authenticated()
	 * .and().formLogin(); }
	 
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder());
	}

	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager() throws Exception {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(dataSource);
		return jdbcUserDetailsManager;
	}

	  
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
	  
	  @Override
		protected void configure(HttpSecurity http) throws Exception {
			http.cors();
			http.authorizeRequests().antMatchers("/registerpassenger").permitAll().antMatchers("/welcome")
					.hasAnyRole("USER", "ADMIN").antMatchers("/login")
					.hasAnyRole("USER")
					.antMatchers("/findAllUser").hasAnyRole( "ADMIN", "USER")
					.antMatchers("/addNewEmployee").hasAnyRole("ADMIN").anyRequest()
					  .authenticated()
					  .and()
					  .httpBasic();
			

			http.csrf().disable();
		}

 
	  
	  
	  
	  @Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser("Java").password("$2a$04$hRZ5qVzQ6HqW.fuGzfcIxuaMEu9Cd/amavqKh8s/cfzoDDuUHGY.G").roles("ADMIN");
			//auth.userDetailsService(userDetailsService);
		}
		
		
		
		
	  
	  
	
	@Bean
	public AuthenticationProvider authProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	*/
	
	/*
	 * @Bean
	 * 
	 * @Override protected UserDetailsService userDetailsService() {
	 * 
	 * List<UserDetails> users = new ArrayList<>();
	 * users.add(User.withDefaultPasswordEncoder().username("vachi").password("1234"
	 * ).roles("USER").build());
	 * 
	 * 
	 * return new InMemoryUserDetailsManager(users); }
	 */
		// TODO Auto-generated method stub
