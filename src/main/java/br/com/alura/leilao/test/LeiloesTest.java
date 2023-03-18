package br.com.alura.leilao.test;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LeiloesTest {

    private LeiloesPage paginaDeLeiloes;
    private CadastroLeilaoPage paginaDeCadastro;


    @BeforeEach
    public void beforeEach(){
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencherFormularioDeLogin("fulano","pass");
        this.paginaDeLeiloes = paginaDeLogin.efetuaLogin();
        this.paginaDeCadastro = paginaDeLeiloes.carregarFormulario();
    }

    @AfterEach
    public void aftereEach(){
        this.paginaDeLeiloes.fechar();

    }

    @Test
    public void cadastrarLeilao(){

        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao do dia " + hoje;
        String valor = "500.00";

        this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);
        Assert.assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome,valor,hoje));

    }

    @Test
    public void ValidarCadastroDeLeilao(){
        this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao(" ", " ", " ");

        Assert.assertFalse(paginaDeCadastro.isPaginaAtual());
        Assert.assertTrue(paginaDeLeiloes.isPaginaAtual());
        Assert.assertFalse(paginaDeCadastro.isMsgDeValidacaoVisiveis());
    }



}
