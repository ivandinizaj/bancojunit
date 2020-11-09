package com.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste Gerenciadora Clientes")
public class GerenciadoraClientesTest {
    private GerenciadoraClientes gerClientes;

    @BeforeEach
    void setUp() {
        /* ========== Montagem do cenário ========== */

        // criando alguns clientes
        Cliente cliente01 = new Cliente(1, "Gustavo Farias", 31, "gugafarias@gmail.com", 1, true);
        Cliente cliente02 = new Cliente(2, "Felipe Augusto", 34, "felipeaugusto@gmail.com", 2, true);

        // inserindo os clientes criados na lista de clientes do banco
        List<Cliente> clientesDoBanco = new ArrayList<>();
        clientesDoBanco.add(cliente01);
        clientesDoBanco.add(cliente02);

        gerClientes = new GerenciadoraClientes(clientesDoBanco);
    }

    @AfterEach
    public void tearDown() {
        gerClientes.limpa();
        gerClientes = null;
    }

    @Test
    @DisplayName("Pesquisar cliente pelo ID")
    public void testPesquisaCliente() {
        /* ========== Execução ========== */
        Cliente cliente = gerClientes.pesquisaCliente(1);

        /* ========== Verificações ========== */
        assertEquals(1, cliente.getId());
        assertEquals("gugafarias@gmail.com", cliente.getEmail());
    }

    @Test
    @DisplayName("Pesquisar um cliente inexistente")
    public void testPesquisaClienteInexistente() {
        /* ========== Execução ========== */
        Cliente cliente = gerClientes.pesquisaCliente(1001);

        /* ========== Verificações ========== */
        assertNull(cliente);
    }

    @Test
    @DisplayName("Remover cliente pelo ID")
    public void testRemoveCliente() {
        /* ========== Execução ========== */
        boolean clienteRemovido = gerClientes.removeCliente(2);

        /* ========== Verificações ========== */
        assertTrue(clienteRemovido);
        assertEquals(1, gerClientes.getClientesDoBanco().size());
        assertNull(gerClientes.pesquisaCliente(2));
    }

    @Test
    @DisplayName("Remover cliente inexistente")
    public void testRemoveClienteInexistente() {
        /* ========== Execução ========== */
        boolean clienteRemovido = gerClientes.removeCliente(1001);

        /* ========== Verificações ========== */
        assertFalse(clienteRemovido);
        assertEquals(2, gerClientes.getClientesDoBanco().size());
    }

    @ParameterizedTest
    @DisplayName("Testar se cliente tem idade aceitável")
    @ValueSource(ints = {18, 25, 65})
    public void testClienteIdadeAceitavel(int idade) throws IdadeNaoPermitidaException {
        /* ========== Montagem do Cenário ========== */
        Cliente cliente = new Cliente(1, "Gustavo", idade, "guga@gmail.com", 1, true);

        /* ========== Execução ========== */
        boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

        /* ========== Verificações ========== */
        assertTrue(idadeValida);
    }

    @ParameterizedTest
    @DisplayName("Testar se cliente tem idade inaceitável")
    @ValueSource(ints = {10, 17, 66})
    public void testClienteIdadeInaceitavel(int idade) {
        /* ========== Montagem do Cenário ========== */
        Cliente cliente = new Cliente(3, "Fulano Cicrano", idade, "fulano@emailnaoexiste.com", 3, true);

        /* ========== Execução ========== */
        try {
            gerClientes.validaIdade(cliente.getIdade());
            fail("Não foi validado idade inaceitável");
        } catch (Exception e) {
            /* ========== Verificações ========== */
            assertEquals(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA, e.getMessage());
        }
    }
}
