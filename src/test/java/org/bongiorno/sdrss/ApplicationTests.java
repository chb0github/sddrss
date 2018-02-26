package org.bongiorno.sdrss;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bongiorno.sdrss.domain.resources.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	private final ParameterizedTypeReference<Resource<Employee>> type = new ParameterizedTypeReference<Resource<Employee>>() {
	};

	@Test
	public void fetchAHateosObject() {
		Employee expected = new Employee("Christian","christian.bongiorno@sterlingts.com");
		// a java legacy
		ResponseEntity<Resource<Employee>> actual = restTemplate.exchange("/employees/1", HttpMethod.GET, null, type);

		assertThat(actual.getBody().getContent()).isEqualTo(expected);

	}

	@Test
	public void testInvalidPayload() {
		Employee input = new Employee("Christian", "not and email");
		// a java legacy

		ResponseEntity<ValidationFailure> reponse = restTemplate
				.postForEntity("/employees", input, ValidationFailure.class);

		assertThat(reponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

		ValidationFailure expected = new ValidationFailure(new ArrayList<>(
				singletonList(new ValidationError("Employee", "email", input.getEmail()))));

		assertThat(reponse.getBody()).isEqualTo(expected);


	}

	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	@EqualsAndHashCode
	public static class ValidationFailure {

		@JsonProperty("errors")
		List<ValidationError> errors;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	@EqualsAndHashCode
	public static class ValidationError {

		public String entity;
		public String property;
		public Object invalidValue;
		// don't care what the message is
//		public String message;
	}

}
