import com.example.library_system.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitUserPasswords {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void encodeExistingPasswords() {
        // Loop over all users in the database
        userRepository.findAll().forEach(user -> {
            String rawPassword = user.getPassword();
            // Check if password is not already encoded (does not start with $2a$)
            if (!rawPassword.startsWith("$2a$")) {
                // Encode the raw password using BCrypt
                user.setPassword(passwordEncoder.encode(rawPassword));
                // Save the updated user with encoded password
                userRepository.save(user);
            }
        });
    }
}
