package com.compozed.models;

import com.compozed.util.Mysql;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by localadmin on 8/10/16.
 */
public class ClientTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateNewClientAndSave() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Client c = new Client("jennifer");
        session.save(c);
        session.getTransaction().commit();
        session.close();
    }

}