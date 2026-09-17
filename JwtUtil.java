@Component
public class JwtUtil {
    @Value("${jwt.secret}") private String secret;
    @Value("${jwt.expiration}") private Long exp;

    public String generateToken(String username){
        return Jwts.builder().setSubject(username)
            .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+exp))
            .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256).compact();
    }
    public String extractUsername(String token){
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build()
            .parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateToken(String token){ try{ extractUsername(token); return true; }catch(Exception e){ return false; } }
}
