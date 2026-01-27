package com.example.demo.modules.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CustomerTest {

	@Test
	void lomboGetterAndSetterCustomers() {
		Customer customer = new Customer();
		customer.setName("Célio");
		customer.setCpf("1234567");

		Viacep viacep = new Viacep();
		viacep.setCep("16078273");
		viacep.setLogradouro("Rua Jose Mauricio");
		viacep.setComplemento("Rua um");
		viacep.setUnidade("A");
		viacep.setBairro("Aguas Claras");
		viacep.setLocalidade("Araçatuba");
		viacep.setUf("SP");
		viacep.setEstado("SP");
		viacep.setRegiao("Sudoeste");
		viacep.setIbge("77789");

		customer.setAddres(viacep);

		assertEquals("Célio", customer.getName());
		assertEquals("1234567", customer.getCpf());
		assertEquals("16078273", customer.getAddres().getCep());
		assertEquals("Rua Jose Mauricio", customer.getAddres().getLogradouro());
		assertEquals("Rua um", customer.getAddres().getComplemento());
		assertEquals("A", customer.getAddres().getUnidade());
		assertEquals("Aguas Claras", customer.getAddres().getBairro());
		assertEquals("Araçatuba", customer.getAddres().getLocalidade());
		assertEquals("SP", customer.getAddres().getUf());
		assertEquals("SP", customer.getAddres().getEstado());
		assertEquals("Sudoeste", customer.getAddres().getRegiao());
		assertEquals("77789", customer.getAddres().getIbge());
	}
}
