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
        Session session = Mysql.getSession();
        session.beginTransaction();
        session.createNativeQuery("truncate table clients").executeUpdate();

        session.getTransaction().commit();
        session.close();
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

        assertEquals(1, c.getId());
    }

    @Test(expected = org.hibernate.exception.DataException.class)
    public void shouldNotSaveDuetoNameTooLong() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Client client = new Client("jenniferasffffffffffffffffffffffjenniferasffffffffffffffffffffffjenniferasffffffffffffffffffffffjenniferasffffffffffffffffffffffjenniferasffffffffffffffffffffff");
        try {
            session.save(client);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

}