package com.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de teste criada para garantir o funcionamento das principais operações
 * sobre clientes, realizadas pela classe {@link GerenciadoraClientes}.
 * 
 * @author Gustavo Farias
 * @date 21/01/2035 
 */
public class GerenciadoraClientesTest_Ex4 {

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

	/**
	 * Teste básico da pesquisa de um cliente a partir do seu ID.
	 * 
	 * @author Gustavo Farias
	 * @date 21/01/2035
	 */
	@Test
	public void testPesquisaCliente() {
		/* ========== Execução ========== */
		Cliente cliente = gerClientes.pesquisaCliente(1);
		
		/* ========== Verificações ========== */
		assertEquals(cliente.getId(), 1);
		
	}
	
	/**
	 * Teste básico da remoção de um cliente a partir do seu ID.
	 * 
	 * @author Gustavo Farias
	 * @date 21/01/2035
	 */
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

//Documentação e comentários