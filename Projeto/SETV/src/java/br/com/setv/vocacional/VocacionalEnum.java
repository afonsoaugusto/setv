/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setv.vocacional;

/**
 *
 * @author afonso.rodrigues
 */
public enum VocacionalEnum {
    
    PATH_PL("C:\\Java\\Projetos\\prolog\\pl\\"),
    TERM_CONSULT("opcaoEscolhida");
    
    private final String value;
    private VocacionalEnum(String s){
            value = s;
    }
    
    public String getValue(){
        return value;
    }
}
