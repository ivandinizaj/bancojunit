package com.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste Gerenciadora Contas")
public class GerenciadoraContasTest {
    private GerenciadoraContas gerContas;

    @ParameterizedTest
    @DisplayName("Testar transferência de fundos entre 2 contas")
    @CsvSource({"200,0,100,100,100", "500,253,250,250,503"})
    public void testTransfereValor(int valor1, int valor2, int quantiaTransferida, int quantiaFinalConta1, int quantiaFinalConta2) {
        /* ========== Montagem do cenário ========== */

        // criando alguns clientes
        ContaCorrente conta01 = new ContaCorrente(1, valor1, true);
        ContaCorrente conta02 = new ContaCorrente(2, valor2, true);

        // inserindo os clientes criados na lista de clientes do banco
        List<ContaCorrente> contasDoBanco = new ArrayList<>();
        contasDoBanco.add(conta01);
        contasDoBanco.add(conta02);

        gerContas = new GerenciadoraContas(contasDoBanco);

        /* ========== Execução ========== */
        boolean sucesso = gerContas.transfereValor(1, quantiaTransferida, 2);

        /* ========== Verificações ========== */
        assertTrue(sucesso);
        assertEquals(quantiaFinalConta1, conta01.getSaldo());
        assertEquals(quantiaFinalConta2, conta02.getSaldo());
    }

    @ParameterizedTest
    @DisplayName("Testar transferência de fundos entre 2 contas com saldo insuficiente")
    @CsvSource({"100,0,200,-100,200", "500,253,600,-100,853"})
    public void testTransfereValor_SaldoInsuficiente(int valor1, int valor2, int quantiaTransferida, int quantiaFinalConta1, int quantiaFinalConta2) {
        /* ========== Montagem do cenário ========== */

        // criando alguns clientes
        int idConta01 = 1;
        int idConta02 = 2;
        ContaCorrente conta01 = new ContaCorrente(idConta01, valor1, true);
        ContaCorrente conta02 = new ContaCorrente(idConta02, valor2, true);

        // inserindo os clientes criados na lista de clientes do banco
        List<ContaCorrente> contasDoBanco = new ArrayList<>();
        contasDoBanco.add(conta01);
        contasDoBanco.add(conta02);

        gerContas = new GerenciadoraContas(contasDoBanco);

        /* ========== Execução ========== */
        boolean sucesso = gerContas.transfereValor(idConta01, quantiaTransferida, idConta02);

        /* ========== Verificações ========== */
        assertTrue(sucesso);
        assertEquals(quantiaFinalConta1, conta01.getSaldo());
        assertEquals(quantiaFinalConta2, conta02.getSaldo());
    }

    @ParameterizedTest
    @DisplayName("Testar transferência de fundos entre 2 contas com saldo negativo")
    @CsvSource({"-100,0,200,-300,200", "-500,253,600,-1100,853"})
    public void testTransfereValor_SaldoNegativo(int valor1, int valor2, int quantiaTransferida, int quantiaFinalConta1, int quantiaFinalConta2) {
        /* ========== Montagem do cenário ========== */

        // criando alguns clientes
        int idConta01 = 1;
        int idConta02 = 2;
        ContaCorrente conta01 = new ContaCorrente(idConta01, valor1, true);
        ContaCorrente conta02 = new ContaCorrente(idConta02, valor2, true);

        // inserindo os clientes criados na lista de clientes do banco
        List<ContaCorrente> contasDoBanco = new ArrayList<>();
        contasDoBanco.add(conta01);
        contasDoBanco.add(conta02);

        gerContas = new GerenciadoraContas(contasDoBanco);

        /* ========== Execução ========== */
        boolean sucesso = gerContas.transfereValor(idConta01, quantiaTransferida, idConta02);

        /* ========== Verificações ========== */
        assertTrue(sucesso);
        assertEquals(quantiaFinalConta1, conta01.getSaldo());
        assertEquals(quantiaFinalConta2, conta02.getSaldo());
    }

    @ParameterizedTest
    @DisplayName("Testar transferência de fundos entre 2 contas, ambas com saldo negativo")
    @CsvSource({"-100,-100,200,-300,100", "-500,-253,600,-1100,347"})
    public void testTransfereValor_SaldoNegativoParaNegativo(int valor1, int valor2, int quantiaTransferida, int quantiaFinalConta1, int quantiaFinalConta2) {
        /* ========== Montagem do cenário ========== */

        // criando alguns clientes
        int idConta01 = 1;
        int idConta02 = 2;
        ContaCorrente conta01 = new ContaCorrente(idConta01, valor1, true);
        ContaCorrente conta02 = new ContaCorrente(idConta02, valor2, true);

        // inserindo os clientes criados na lista de clientes do banco
        List<ContaCorrente> contasDoBanco = new ArrayList<>();
        contasDoBanco.add(conta01);
        contasDoBanco.add(conta02);

        gerContas = new GerenciadoraContas(contasDoBanco);

        /* ========== Execução ========== */
        boolean sucesso = gerContas.transfereValor(idConta01, quantiaTransferida, idConta02);

        /* ========== Verificações ========== */
        assertTrue(sucesso);
        assertEquals(quantiaFinalConta1, conta01.getSaldo());
        assertEquals(quantiaFinalConta2, conta02.getSaldo());
    }
}
