package com.DesafioItau.DesafioBackEndItau.services;

import com.DesafioItau.DesafioBackEndItau.dtos.StatisticsResponse;
import com.DesafioItau.DesafioBackEndItau.dtos.TransactionDTO;
import com.DesafioItau.DesafioBackEndItau.dtos.TransactionValueDTO;
import com.DesafioItau.DesafioBackEndItau.entities.Transaction;
import com.DesafioItau.DesafioBackEndItau.exceptions.excep.TransactionError;
import com.DesafioItau.DesafioBackEndItau.repository.RepositoryTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {


    @Mock
    private RepositoryTransaction repositoryTransaction;

    @InjectMocks
    private StatisticsService statisticsService;

    @InjectMocks
    private TransactionService transactionService;

    private TransactionValueDTO dtoValueValid;
    private TransactionValueDTO dtoValueInvalid;
    private TransactionValueDTO dtoValueNull;

    @BeforeEach
    void setupValuesTransaction(){
        dtoValueValid = new TransactionValueDTO(100.0);
        dtoValueInvalid = new TransactionValueDTO(-100.0);
        dtoValueNull = new TransactionValueDTO(0.0);
    }
    //Classe de teste para o metodo postTransaction
    @Nested
    class postTransactionTest{

    @DisplayName("Testando Transacao em caso de sucesso")
    @Test
        void realizarTransacaoSucessTeste(){

            //Metodo que sera testado
            TransactionDTO dtoTransactio = transactionService.postTransaction(dtoValueValid);

            //Verificacoes
            assertNotNull(dtoTransactio);
            assertEquals(100.0, dtoTransactio.valor());
            assertNotNull(dtoTransactio.dataHora());

            //Verificando se o metodo do repositorio foi chamado 1 vez
            verify(repositoryTransaction, times(1)).addTransaction(any(Transaction.class));

        }
        @DisplayName("Lanca excecao quando o valor da transacao é negativo")
        @Test
        void excecaoComNumeroNegativo(){

            //Simulando lancamento de excecao em caso de valor negativo
            TransactionError exception = assertThrows(TransactionError.class, () -> transactionService.postTransaction(dtoValueInvalid));

            //Verificando o retorno da mensagem de erro
            assertEquals("Valor invalido!", exception.getMessage());

            //Verificando que o metodo nao foi chamado
            verify(repositoryTransaction, never()).addTransaction(any(Transaction.class));

        }

        @Test
        @DisplayName("Lanca excecao quando o valor da transacao for igual a 0")
        void excecaoComNumero0(){

            //Simulando lancamento de excecao em caso de transaco com valor igual a 0
            TransactionError exception = assertThrows(TransactionError.class, () -> transactionService.postTransaction(dtoValueNull));

            //Verificando o retoro na mensagem de erro
            assertEquals("Valor invalido!", exception.getMessage());

            //Verificando que o metodo nao foi chamao
            verify(repositoryTransaction, never()).addTransaction(any(Transaction.class));
        }

    }

    //Classe de teste para o metodo getTransaction
    @Nested
    class listarTrasacoesTeste{

        @DisplayName("Listar todas as transacoes")
        @Test
        void listarEmCasoSucesso(){
            //Simulacoes de valores para o teste
            OffsetDateTime dataHora = OffsetDateTime.now( ZoneOffset.UTC);
            List<Transaction> transactionsMocks = List.of(
                    new Transaction(120.0, dataHora.minusSeconds(10), UUID.randomUUID().toString()),
                    new Transaction(1190.0, dataHora.minusSeconds(5), UUID.randomUUID().toString())
            );
            //Simulando o retorno do metodo
             when(repositoryTransaction.getTransactions()).thenReturn(transactionsMocks);

             //Chamada do metodo
             var output = transactionService.getTransaction();

             //Verificacao do metodo
             assertNotNull(output);
             assertEquals(2, output.size());
             assertEquals(120.0, output.get(0).getValor());
             assertEquals(1190.0, output.get(1).getValor());

             verify(repositoryTransaction, times(1)).getTransactions();
        }

    }

    @Nested
    class StatisticsMethodTest{

        @DisplayName("Gerar estatisticas caso de sucesso")
        @Test
        void estatisticasCasoSucesso(){
            OffsetDateTime dataHora = OffsetDateTime.now(ZoneOffset.UTC);
            //Simulando lista para metodo findByLast60s
            List<Transaction> transactionsMocks = List.of(
                    new Transaction(100.0, dataHora.minusSeconds(10), UUID.randomUUID().toString()),
                    new Transaction(100.0, dataHora.minusSeconds(5), UUID.randomUUID().toString())
            );
            //Simulando retorno das transacoes
            when(repositoryTransaction.findByLast60s()).thenReturn(transactionsMocks);
            //Chamada do metodo
            StatisticsResponse statistics = statisticsService.statistics();

            //Verificacoes dos valores
            assertNotNull(statistics);
            assertEquals(2, statistics.count());
            assertEquals(200.0, statistics.sum());
            assertEquals(100.0, statistics.average());
            assertEquals(100.0, statistics.max());
            assertEquals(100.0, statistics.min());

            verify(repositoryTransaction, times(1)).findByLast60s();
        }


        //Caso de teste caso a lista vazia
        @DisplayName("Retornar valores zerados e a listar estiver vazia")
        @Test
        void listarEmCasoErroListaVazia(){
            //Simulando retorno do metedo (lista vazia)
            when(repositoryTransaction.findByLast60s()).thenReturn(Collections.emptyList());

            //Chamada do metodo
            StatisticsResponse statistics = statisticsService.statistics();

            //Verificacoes
            assertEquals(0.0, statistics.count());
            assertEquals(0.0, statistics.sum());
            assertEquals(0.0, statistics.average());
            assertEquals(0.0, statistics.max());
            assertEquals(0.0, statistics.min());

            verify(repositoryTransaction, times(1)).findByLast60s();
        }
    }
}