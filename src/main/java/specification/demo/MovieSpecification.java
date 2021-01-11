package specification.demo;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public final class MovieSpecification {
    public static Specification<Movie> hasTitle (@NonNull String title) {
        return (Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder cb)
                -> cb.equal(root.get("title"), title);
    }

    public static Specification<Movie> hasReleaseYear(int releaseYear) {
        return (Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder cb)
                -> cb.equal(root.get("year"), releaseYear);
    }
    public static Specification<Movie> hasRating(double rating) {
        return (Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder cb)
                -> cb.equal(root.get("rating"), rating);
    }
}
