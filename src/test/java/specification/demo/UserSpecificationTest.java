package specification.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserSpecificationTest {
    @Autowired
    private UserRepositorySpec userRepositorySpec;
    User user;
    User user2;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("agaakot@gmail.com");
        user.setName("aga");
        user.setLastName("kot");
        userRepositorySpec.save(user);

        user2 = new User();
        user2.setEmail("janmak@gmail.com");
        user2.setName("jan");
        user2.setLastName("mak");
        userRepositorySpec.save(user2);
    }

    @Test
    void search_with_one_spec_should_work() {
        List<User> users = userRepositorySpec.findAll(UserSpecification.likeFirstName("aga"));
        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    void search_with_all_spec_should_work() {
        Specification<User> specs = Specification.where(UserSpecification.likeFirstName("aga"))
                .and(UserSpecification.likeLastName("ot"))
                .and(UserSpecification.equalEmail("agaakot@gmail.com"));
        List<User> users = userRepositorySpec.findAll(specs);
        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    void likeFirstName() {
        List<User> users = userRepositorySpec.findAll(UserSpecification.likeFirstName("aga"));
        assertNotNull(users);
        assertEquals(1, users.size());

    }

    @Test
    void likeLastName() {
        List<User> users = userRepositorySpec.findAll(UserSpecification.likeLastName("mak"));
        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    void equalEmail() {
        List<User> users = userRepositorySpec.findAll(UserSpecification.equalEmail("janmak@gmail.com"));
        assertNotNull(users);
        assertEquals(1, users.size());
    }
}