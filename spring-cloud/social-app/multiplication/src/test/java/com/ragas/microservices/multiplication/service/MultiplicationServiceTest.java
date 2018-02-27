/**
 * 
 */
package com.ragas.microservices.multiplication.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ragas.microservices.multiplication.domain.Multiplication;

/**
 * @author Chandra Jagarlamudi
 *
 */
// @RunWith(SpringRunner.class)
// @SpringBootTest
@Ignore
public class MultiplicationServiceTest {

	@MockBean
	private RandomGeneratorService randomGeneratorService;

	@Autowired
	private MultiplicationService multiplicationService;

	@Test
	public void createRandomMultiplicationTest() {
		// given (our mocked Random Generator service will return first 50, then 30)
		given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

		// when
		Multiplication multiplication = multiplicationService.createRandomMultiplication();

		// assert
		assertThat(multiplication.getFactorA()).isEqualTo(50);
		assertThat(multiplication.getFactorB()).isEqualTo(30);
		//assertThat(multiplication.getResult()).isEqualTo(1500);
	}

}
