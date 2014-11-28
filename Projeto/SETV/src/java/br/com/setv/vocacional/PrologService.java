/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setv.vocacional;

import br.com.setv.entidades.Aluno;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import static org.eclipse.persistence.config.PersistenceUnitProperties.*;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.spi.PersistenceUnitTransactionType;
import jpl.Atom;
import jpl.Query;
import jpl.Term;
import jpl.Variable;
import org.eclipse.persistence.config.TargetServer;

/**
 *
 * @author afonso.rodrigues
 */
@Stateless
public class PrologService {

    @PersistenceContext(unitName = "SETV_PU")
    private EntityManager em;

    public PrologService() {
    }

    public List<String> executeAnalise(Aluno a11) throws Exception {
        initEntityManager();
        initQueryPl(a11);
        List<String> lista = executeQuery(a11);
        return lista;
    }

    private void initQueryPl(Aluno a11) throws Exception {
        updateFilePl(em, a11);
        Query q1 = new Query("consult", new Term[]{new Atom(getPathPl(a11))});
        if (!q1.query()) {
            throw new Exception("Arquivo pl não carregado corretamente");
        }
    }

    private void updateFilePl(EntityManager em1, Aluno a11) throws Exception {
        this.em = em1;
//        EntityTransaction etx = em.getTransaction();
//        etx.begin();
        final javax.persistence.Query createNativeQuery = em1.createNativeQuery("BEGIN REGRA_PKG.spu_generate_file_id(?); END;");
        createNativeQuery.setParameter(1, a11.getId());
        createNativeQuery.executeUpdate();
//        etx.commit();
//        em.close();
    }

    private void initEntityManager() {
        if (this.em == null) {
            this.em = rtgetEntityManagerT();
        }
    }

    private List<String> executeQuery(Aluno a11) {
        List<String> lista = new ArrayList<>();
        Map<String, String> mapa = new HashMap<>();
        Variable X = new Variable("X");
        Query q2 = new Query(
                VocacionalEnum.TERM_CONSULT.getValue(),
                new Term[]{new Atom(a11.getNome().toLowerCase().replace(" ", "")), X}
        );
        for (Hashtable solution : q2.allSolutions()) {
            mapa.putAll(solution);
        }
        //lista.addAll((Collection<? extends String>) mapa);
        for (Map.Entry<String, String> entrySet : mapa.entrySet()) {
            lista.add(entrySet.getValue());
        }
        return lista;
    }

    private static String getPathPl(Aluno a11) {
        return VocacionalEnum.PATH_PL.getValue() + "A" + a11.getId() + ".pl";
    }

    private EntityManager rtgetEntityManagerT() {
        Map properties = new HashMap();

        // Ensure RESOURCE_LOCAL transactions is used.
        properties.put(TRANSACTION_TYPE, PersistenceUnitTransactionType.RESOURCE_LOCAL.name());

        // Configure the internal connection pool
        properties.put(JDBC_DRIVER, "oracle.jdbc.OracleDriver");
        properties.put(JDBC_URL, "jdbc:oracle:thin:@localhost:1521:XE");
        properties.put(JDBC_USER, "REGRA");
        properties.put(JDBC_PASSWORD, "REGRA");

        // Configure logging. FINE ensures all SQL is shown
        properties.put(LOGGING_LEVEL, "FINE");
        properties.put(LOGGING_TIMESTAMP, "false");
        properties.put(LOGGING_THREAD, "false");
        properties.put(LOGGING_SESSION, "false");

        // Ensure that no server-platform is configured
        properties.put(TARGET_SERVER, TargetServer.None);
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("SETV_PU", properties);
        return emf.createEntityManager();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
}
