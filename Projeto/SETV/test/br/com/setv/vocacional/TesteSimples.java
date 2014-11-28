/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setv.vocacional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Afonso
 */
public class TesteSimples {

    public static void main(String[] args) {
        //PrologService prologService = lookupPrologServiceBean();
//        PrologService prologService = new PrologService();
//        System.out.println(VocacionalEnum.PATH_PL.getValue());
//        System.out.println(VocacionalEnum.PATH_PL);
//        try {
//            System.out.println(prologService.executeAnalise(new Aluno(new BigDecimal(3), 23, "aluno4")));
//        } catch (Exception ex) {
//            Logger.getLogger(VocacionalEnumTest.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        String a = "Aluno 4";
//        System.out.println(a.toLowerCase().replace(" ", ""));
//        
//        String str = "qwerty-1qwerty-2 455 f0gfg 4";
//        str = str.replaceAll("[^-?0-9]+", " ");
//        final List<String> asList = Arrays.asList(str.trim().split(" "));
//        System.out.println(asList.get(asList.size()-1));
        List<String> lista = new ArrayList<>();
        if (!lista.isEmpty()) {
            if (lista.get(0) != null) {
                System.out.println("as");
            }
        }

    }

    private static PrologService lookupPrologServiceBean() {
        try {
            Context c = new InitialContext();
            return (PrologService) c.lookup("java:global/SETV/PrologService!br.com.setv.vocacional.PrologService");
        } catch (NamingException ne) {
            //Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
