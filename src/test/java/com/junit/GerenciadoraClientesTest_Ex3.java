package com.junit;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Gerenciadora de Clientes")
public class GerenciadoraClientesTest_Ex3 {

	private GerenciadoraClientes gerClientes;

	@BeforeEach
	void setUp() {
		/* ========== Montagem do cenário ========== */

		// criando alguns clientes
		Cliente cliente01 = new Cliente(1, "Gustavo Farias", 31, "gugafarias@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(2, "Felipe Augusto", 34, "felipeaugusto@gmail.com", 1, true);

		// inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);

		gerClientes = new GerenciadoraClientes(clientesDoBanco);
	}

	@Test
	@DisplayName("Teste pesquisa cliente por ID")
	public void testPesquisaCliente() {
		/* ========== Execução ========== */
		Cliente cliente = gerClientes.pesquisaCliente(1);
		
		/* ========== Verificações ========== */
		assertEquals(cliente.getId(), 1);
	}
	
	@DisplayName("Teste Remove Cliente")
	@Test
	public void testRemoveCliente() {
		/* ========== Execução ========== */
		boolean clienteRemovido = gerClientes.removeCliente(2);
		
		/* ========== Verificações ========== */
		assertTrue(clienteRemovido);
		assertEquals(gerClientes.getClientesDoBanco().size(), 1);
		assertNull(gerClientes.pesquisaCliente(2));
	}

}
