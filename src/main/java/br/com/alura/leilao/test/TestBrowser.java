package br.com.alura.leilao.test;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestBrowser {

    private LoginPage paginaDeLogin;


    @BeforeEach
    public void beforeEach(){
        this.paginaDeLogin = new LoginPage();
    }

    @AfterEach
    public void aftereEach(){
        this.paginaDeLogin.fechar();

    }

    @Test
    public void EfetuarLoginValido(){

        paginaDeLogin.preencherFormularioDeLogin("fulano","pass");
        paginaDeLogin.efetuaLogin();

        Assert.assertFalse(paginaDeLogin.isPaginaDeLoginIvalidos());
        Assert.assertEquals("fulano",paginaDeLogin.getNomeUsuarioLogado());

    }

    @Test
    public void TentarLoginInvalido(){

        paginaDeLogin.preencherFormularioDeLogin("Teste22","321456");
        paginaDeLogin.efetuaLogin();

        Assert.assertTrue(paginaDeLogin.isPaginaDeLoginIvalidos());
        Assert.assertNull(paginaDeLogin.getNomeUsuarioLogado());
        Assert.assertFalse(paginaDeLogin.contemTexto("Usuario e senha inválidos."));

    }

    @Test
    public void TentaAcessoPaginaRestritaSemLogin(){

        paginaDeLogin.navegaParaPaginaDeLances();

        Assert.assertTrue(paginaDeLogin.isPaginaDeLogin());
        Assert.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));
    }




}
