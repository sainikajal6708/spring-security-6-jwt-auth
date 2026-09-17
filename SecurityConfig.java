@Configuration @EnableWebSecurity
public class SecurityConfig {
    @Autowired JwtAuthFilter jwtFilter; @Autowired AuthEntryPoint entryPoint;
    @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(a -> a.requestMatchers("/api/auth/**").permitAll().anyRequest().authenticated())
            .exceptionHandling(e -> e.authenticationEntryPoint(entryPoint))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
    @Bean public PasswordEncoder encoder(){ return new BCryptPasswordEncoder(); }
    @Bean public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception { return config.getAuthenticationManager(); }
}
