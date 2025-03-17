@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/find")
    public List<User> findUser(@RequestParam String username) {
        List<User> users = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + username + "'")) {

            while (rs.next()) {
                users.add(new User(rs.getString("username"), rs.getString("email")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
