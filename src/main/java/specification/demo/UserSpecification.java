package specification.demo;


import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> likeFirstName(String firstName) {
        if (firstName == null) {
            return null;
        }
        return (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("name"), "%" + firstName + "%");
        };
    }


    public static Specification<User> likeLastName(String lastName) {
        if (lastName == null) {
            return null;
        }
        return (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("lastname"), "%" + lastName + "%");
        };
    }

    public static Specification<User> equalEmail(String email) {
        if (email == null) {
            return null;
        }
        return (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("email"), email);
        };
    }
}
