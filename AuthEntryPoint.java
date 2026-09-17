@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException ex) throws IOException {
        res.setStatus(401); res.setContentType("application/json");
        res.getWriter().write("{\"error\":\"Unauthorized - Token missing or invalid\"}");
    }
}
