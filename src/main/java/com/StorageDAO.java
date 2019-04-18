package com;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StorageDAO implements Repository<Storage> {
    private static SessionFactory sessionFactory;
    @Override
    public Storage save(Storage object) {
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
        Session session = null;
        Transaction tr = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            Storage persistentInstance = session.load(Storage.class, id);
            if (persistentInstance != null) {
                session.delete(persistentInstance);
            }
            tr.commit();
        } catch (Exception e) {
            System.err.println("Delete is failed");
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
    }

    @Override
    public Storage update(Storage object) {
        Session session = null;
        Transaction tr = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            session.update(object);
            tr.commit();
        } catch (Exception e) {
            System.err.println("Update is failed");
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }

        return object;
    }

    @Override
    public Storage findById(long id) {
        Session session = null;
        try {
            session = createSessionFactory().openSession();
            Storage storage  = session.get(Storage.class, id);
            return storage;
        } catch (Exception e) {
            System.err.println("Search is failed");
            System.err.println(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return null;
    }
    protected static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
