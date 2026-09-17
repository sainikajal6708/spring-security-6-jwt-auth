@RestController @RequestMapping("/api/auth")
public class AuthController {
    @Autowired UserRepository repo; @Autowired PasswordEncoder enc; @Autowired JwtUtil jwtUtil; @Autowired AuthenticationManager authManager;
    @PostMapping("/register") public ResponseEntity<?> reg(@RequestBody User user){ user.setPassword(enc.encode(user.getPassword())); repo.save(user); return ResponseEntity.ok("Registered"); }
    @PostMapping("/login") public ResponseEntity<?> login(@RequestBody Map<String,String> body){
        authManager.authenticate(new UsernamePasswordAuthenticationToken(body.get("username"), body.get("password")));
        String token = jwtUtil.generateToken(body.get("username")); return ResponseEntity.ok(Map.of("token", token));
    }
    @GetMapping("/hello") public String hello(){ return "Secured Hello!"; }
}
