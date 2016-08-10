package com.compozed.models;

import com.compozed.util.Mysql;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by localadmin on 8/10/16.
 */
public class ShelterTest {
    @Before
    public void setUp() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        session.createNativeQuery("truncate table shelters").executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateNewShelterAndSave() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Shelter shelter = new Shelter("shelter1", new Date());
        session.save(shelter);
        session.getTransaction().commit();
        session.close();
        assertEquals(1, shelter.getId());
    }

    @Test(expected = org.hibernate.exception.DataException.class)
    public void shouldNotSaveDuetoNameTooLong() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Shelter shelter = new Shelter("jenniferasffffffffffffffffffffffjenniferasffffffffffffffffffffffjenniferasffffffffffffffffffffffjenniferasffffffffffffffffffffffjenniferasffffffffffffffffffffff", new Date());
        try {
            session.save(shelter);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }


    @Test(expected = org.hibernate.exception.ConstraintViolationException.class)
    public void shouldNotSaveDuetoNameNotUnique() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Shelter shelter1 = new Shelter("shelter1", new Date());
        session.save(shelter1);
        Shelter shelter2 = new Shelter("shelter1", new Date());
        try {
            session.save(shelter2);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    @Test(expected = org.hibernate.PropertyValueException.class)
    public void shouldNotSaveDuetoNameIsNull() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Shelter shelter = new Shelter(null, new Date());

        try {
            session.save(shelter);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

}
