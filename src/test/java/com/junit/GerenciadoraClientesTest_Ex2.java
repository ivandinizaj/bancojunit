package com.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GerenciadoraClientesTest_Ex2 {

	private GerenciadoraClientes gerClientes;

	@BeforeEach
	void setUp() {
		// criando alguns clientes
		Cliente cliente01 = new Cliente(1, "Gustavo Farias", 31, "gugafarias@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(2, "Felipe Augusto", 34, "felipeaugusto@gmail.com", 2, true);

		// inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);

		gerClientes = new GerenciadoraClientes(clientesDoBanco);
	}

	@Test
	public void testPesquisaCliente() {
		Cliente cliente = gerClientes.pesquisaCliente(1);
		
		assertEquals(cliente.getId(), 1);
	}

	@Test
	public void testRemoveCliente() {
		boolean clienteRemovido = gerClientes.removeCliente(2);
		
		assertTrue(clienteRemovido);
		assertEquals(gerClientes.getClientesDoBanco().size(), 1);
		assertNull(gerClientes.pesquisaCliente(2));
	}

}
