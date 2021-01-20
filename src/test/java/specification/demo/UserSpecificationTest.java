package specification.demo;

import org.junit.jupiter.api.AfterEach;
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
    void setUpUsers() {
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

    @AfterEach
    void tearDown() {
        userRepositorySpec.delete(user);
        userRepositorySpec.delete(user2);
    }


    @Test
    void shouldReturnListOfUsersWithSpecicNameLastNameAndEmail() {
        Specification<User> specs = Specification.where(UserSpecification.likeFirstName("aga"))
                .and(UserSpecification.likeLastName("ot"))
                .and(UserSpecification.equalEmail("agaakot@gmail.com"));

        List<User> users = userRepositorySpec.findAll(specs);

        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    void shouldReturnListOfUsersWithSpecificFirstName() {
        List<User> users = userRepositorySpec.findAll(UserSpecification.likeFirstName("aga"));

        assertNotNull(users);
        assertEquals(1, users.size());

    }

    @Test
    void shouldReturnListOfUsersWithSpeciLastName() {
        List<User> users = userRepositorySpec.findAll(UserSpecification.likeLastName("mak"));

        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    void shouldReturnListOfUsersWithSpeciEmail() {
        List<User> users = userRepositorySpec.findAll(UserSpecification.equalEmail("janmak@gmail.com"));

        assertNotNull(users);
        assertEquals(1, users.size());
    }
}