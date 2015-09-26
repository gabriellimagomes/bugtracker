package br.com.gabriel.mvc.shiro.helper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HashAuthenticationHelperTest {
	private final String esperado = "Basic Z2FicmllbEBtYWlsLmNvbTpzZW5oYQ==";
	private final String usuario = "gabriel@mail.com";
	private final String senha = "senha";
	
	@Before
	public void setUp() {}

	@Test
	public void deveCriptografar() {
		String recebido = HashAuthenticationHelper.encode(usuario, senha);
		Assert.assertEquals(esperado, recebido);
	}

	@Test
	public void deveDescriptografar() {
		String[] recebido = HashAuthenticationHelper.decode(esperado);
		Assert.assertEquals(usuario, recebido[0]);
		Assert.assertEquals(senha, recebido[1]);
	}
}