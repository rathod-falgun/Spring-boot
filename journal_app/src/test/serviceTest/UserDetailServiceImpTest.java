
import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.journal_app.repository.UserRepository;
import com.example.journal_app.services.UserDetailServiceImp;

public class UserDetailServiceImpTest {

    @InjectMocks
    private UserDetailServiceImp userDetailServiceImp;

    @Mock
    private UserRepository userRepository;

    @Test
    void loadUserByUsernameTest() {
        when(userRepository.findByUsername(anyString()))
                .thenReturn((com.example.journal_app.entity.User) User.builder().username("xeon").password("xeon").build());
        UserDetails user = userDetailServiceImp.loadUserByUsername("xeon");
        assertNotNull(user);
        assertEquals("xeon",user.getUsername());
    }
}
