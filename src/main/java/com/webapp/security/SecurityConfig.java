package com.webapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public AppUserDetailsService userDetailsService() {
		return new AppUserDetailsService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
        .userDetailsService(userDetailsService())
        .passwordEncoder(new BCryptPasswordEncoder());
	}
/*	
	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
	    builder
	        .inMemoryAuthentication()
	        .withUser("admin").password("123")
	            .roles("ADMINISTRADORES")
	        .and()
	        .withUser("tacito").password("123")
	            .roles("USUARIOS");
	}
*/	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		JsfLoginUrlAuthenticationEntryPoint jsfLoginEntry = new JsfLoginUrlAuthenticationEntryPoint();
		jsfLoginEntry.setLoginFormUrl("/Login_.xhtml");
		jsfLoginEntry.setRedirectStrategy(new JsfRedirectStrategy());

		JsfAccessDeniedHandler jsfDeniedEntry = new JsfAccessDeniedHandler();
		jsfDeniedEntry.setLoginPath("/403_.xhtml");
		jsfDeniedEntry.setContextRelative(true);

		http.csrf().disable().headers().frameOptions().sameOrigin().and()

				.authorizeRequests().antMatchers("/Login_.xhtml", "/404_.xhtml", "/500_.xhtml", "/javax.faces.resource/**")
				.permitAll().antMatchers("/Dashboard_.xhtml", "/403_.xhtml").authenticated().and()

				.formLogin().loginPage("/Login_.xhtml").failureUrl("/Login_.xhtml?invalid=true").and()

				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).and()

				.exceptionHandling().accessDeniedPage("/403_.xhtml").authenticationEntryPoint(jsfLoginEntry)
				.accessDeniedHandler(jsfDeniedEntry);
	}

}