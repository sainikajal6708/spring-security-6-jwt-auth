@Entity
@Data
public class User {
    @Id @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String role = "ROLE_USER";
}
