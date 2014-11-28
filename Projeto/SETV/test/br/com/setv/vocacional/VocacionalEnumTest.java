/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setv.vocacional;

import br.com.setv.entidades.Aluno;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author afonso.rodrigues
 */
public class VocacionalEnumTest {

    public VocacionalEnumTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void Constante() {
        System.out.println(VocacionalEnum.PATH_PL.getValue());
        System.out.println(VocacionalEnum.PATH_PL);
        try {
           // PrologCore pC = new PrologCore(new Aluno(new BigDecimal(3), 23, "aluno4"));
            //System.out.println(pC.executeAnalise());
        } catch (Exception ex) {
            Logger.getLogger(VocacionalEnumTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
