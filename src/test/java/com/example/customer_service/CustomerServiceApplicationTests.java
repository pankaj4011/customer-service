package com.example.customer_service;

import com.example.customer_service.client.MovieClient;
import com.example.customer_service.domain.Genre;
import com.example.customer_service.dto.CustomerDto;
import com.example.customer_service.dto.GenreUpdateRequestDto;
import com.example.customer_service.dto.MovieDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.ProblemDetail;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;

@Import(TestcontainersConfiguration.class)
@MockitoBean(types ={RestClient.class, MovieClient.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class CustomerServiceApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private  MovieClient movieClient;

	@Test
	public void testHealth(){
		var response = testRestTemplate.getForEntity("/actuator/health",Object.class);
		log.info("response {}",response.getBody());
		Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
	}

	@Test
	void customerWithMovies(){
		Mockito.when(movieClient.getMovies(Mockito.any(Genre.class))).thenReturn(
				List.of(
						new MovieDto(1,"movie-1",1990,Genre.ACTION),
						new MovieDto(2,"movie-2",1991,Genre.CRIME)
						)
		);

		var response = testRestTemplate.getForEntity("/api/customers/1", CustomerDto.class);
		Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals("SAM",response.getBody().name());
		Assertions.assertEquals(2,response.getBody().recommendedMovies().size());
	}

	@Test
	void customerNotFound(){
		var response  = testRestTemplate.getForEntity("/api/customers/10", ProblemDetail.class);

		Assertions.assertTrue(response.getStatusCode().is4xxClientError());

		Assertions.assertNotNull(response.getBody());

		Assertions.assertEquals("Customer not found",response.getBody().getTitle());
	}

	@Test
	void updateGenre(){
		var genreUpdateRequest  = new GenreUpdateRequestDto(Genre.DRAMA);
		var requestEntity = new RequestEntity<>(genreUpdateRequest, HttpMethod.PATCH, URI.create("/api/customers/1/genre"));
		var response = this.testRestTemplate.exchange(requestEntity,Void.class);
		Assertions.assertEquals(204,response.getStatusCode().value());
	}


}
