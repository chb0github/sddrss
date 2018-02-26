package org.bongiorno.sdrss;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.bongiorno.sdrss.domain.resources.Employee;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void fetchAHateosObject() {
		Employee expected = new Employee("Christian","christian.bongiorno@sterlingts.com");
		// a java legacy
		ParameterizedTypeReference<Resource<Employee>>  type =  new ParameterizedTypeReference<Resource<Employee>>() {};
		ResponseEntity<Resource<Employee>> actual = restTemplate.exchange("/employees/1", HttpMethod.GET, null, type);

		assertThat(actual.getBody().getContent()).isEqualTo(expected);

	}

}
