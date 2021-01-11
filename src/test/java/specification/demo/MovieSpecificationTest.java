package specification.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
class MovieSpecificationTest {
    @Autowired
    private MovieRepository movieRepository;
    Movie movie;
    Movie movie2;
    Movie movie3;
    Movie movie4;
    Movie movie5;
    Movie movie6;
    Movie movie7;

    @BeforeEach
    void setUp() {
        movie = new Movie("Troy", "Drama", 7.2, 196, 2004);
        movieRepository.save(movie);
        movie2 = new Movie("The Godfather", "Crime", 9.2, 178, 1972);
        movie3 = new Movie("Invictus", "Sport", 7.3, 135, 2009);
        movie4 = new Movie("Black Panther", "Action", 7.3, 135, 2018);
        movie5 = new Movie("Joker", "Drama", 8.9, 122, 2018);

        movie7 = new Movie();
        movie7.setGenre("Action");
        movie7.setTitle("Joker");
        movie7.setRating(8);
        movie7.setWatchTime(100);
        movie7.setYear(2008);

        movie6 = new Movie();
        movie6.setGenre("Action");
        movie6.setTitle("Joker");
        movie6.setRating(8);
        movie6.setWatchTime(100);
        movie6.setYear(2009);
        movieRepository.save(movie2);
        movieRepository.save(movie3);
        movieRepository.save(movie4);
        movieRepository.save(movie5);
        movieRepository.save(movie6);
        movieRepository.save(movie7);


    }

    @AfterEach
    void tearDown() {
        movieRepository.deleteAll();
    }

    @Test
    void basic() {

        // search movies by `genre`
        MovieSpecification msGenre = new MovieSpecification();

        //msGenre.add(new SearchCriteria("genre", "Action", SearchOperation.EQUAL));

        List<Movie> msGenreList = movieRepository.findAll(MovieSpecification.hasTitle("Joker"));

        assertNotNull(msGenreList);
        msGenreList.forEach(System.out::println);


        Assertions.assertEquals(msGenreList.size(), 3);
    }
    @Test
    public void or() {
        List<Movie> movieList = movieRepository.findAll(MovieSpecification.hasTitle("Joker").or(MovieSpecification.hasTitle("Troy")));
        assertThat(movieList, hasSize(4));
    }

    @Test
    public void and() {

        List<Movie> movieList = movieRepository.findAll(MovieSpecification.hasTitle("Joker").and(MovieSpecification.hasReleaseYear(2009)));
        assertThat(movieList, hasSize(1));
    }
    @Test
    public void andRating() {

        List<Movie> movieList = movieRepository.findAll(MovieSpecification.hasRating( 7.30));
        assertThat(movieList, hasSize(2));
    }
    @Test
    public void andRating2() {

        List<Movie> movieList = movieRepository.findAll(MovieSpecification.hasRating( 7.30));
        assertThat(movieList, hasSize(2));
    }
    @Test
    public void andRatingAndTitle() {

        List<Movie> movieList = movieRepository.findAll(MovieSpecification.hasRating( 7.30).and(MovieSpecification.hasTitle("Black Panther")));
        assertThat(movieList, hasSize(1));


 }
    @Test
    public void searchMoviesByReleaseYear2010AndRating() {

        List<Movie> movieList = movieRepository.findAll(MovieSpecification.hasRating(8.00).and(MovieSpecification.hasReleaseYear(2009)));
        assertThat(movieList, hasSize(1));


    }
/*
        // search movies by release year < 2010 and rating > 8
        MovieSpecification msYearRating = new MovieSpecification();
        msYearRating.add(new SearchCriteria("releaseYear", 2010, SearchOperation.LESS_THAN));
        msYearRating.add(new SearchCriteria("rating", 8, SearchOperation.GREATER_THAN));
        List<Movie> msYearRatingList = movieRepository.findAll(msYearRating);
        msYearRatingList.forEach(System.out::println);

        // search movies by watch time >= 150 and sort by `title`
        MovieSpecification msWatchTime = new MovieSpecification();
        msWatchTime.add(new SearchCriteria("watchTime", 150, SearchOperation.GREATER_THAN_EQUAL));
        List<Movie> msWatchTimeList = movieRepository.findAll(msWatchTime, Sort.by("title"));
        msWatchTimeList.forEach(System.out::println);

        // search movies by `title` <> 'white' and paginate results
        MovieSpecification msTitle = new MovieSpecification();
        msTitle.add(new SearchCriteria("title", "white", SearchOperation.NOT_EQUAL));

        Pageable pageable = PageRequest.of(0, 3, Sort.by("releaseYear").descending());
        Page<Movie> msTitleList = movieRepository.findAll(msTitle, pageable);

        msTitleList.forEach(System.out::println);
*/
    }




