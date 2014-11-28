/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setv.bean.session;

import br.com.setv.entidades.Habito;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Afonso
 */
@Stateless
public class HabitoFacade extends AbstractFacade<Habito> {
    @PersistenceContext(unitName = "SETV_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HabitoFacade() {
        super(Habito.class);
    }
    
}
