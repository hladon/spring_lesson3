package com;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class FileDAO implements DAO {
    private static SessionFactory sessionFactory;
    @Override
    public File save(File object) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.save(object);
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            if (transaction != null)
                transaction.rollback();
        } finally {
            if (session != null)
                session.close();
        }

        return object;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Object update(Object object) {
        return null;
    }

    @Override
    public void findById(long id) {

    }
    protected static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
