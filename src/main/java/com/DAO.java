package com;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.LongType;

import java.util.List;


abstract class DAO<T> {

    private static SessionFactory sessionFactory;
    protected static Session session = null;
    protected static Transaction tr = null;

    public T save(T object) {
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            session.save(object);
            tr.commit();
            System.out.println("Save is done!");
        } catch (Exception e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
            throw e;
        } finally {
            if (session != null)
                session.close();
        }
        return object;
    }

    public T update(T object) {
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            session.update(object);
            tr.commit();
            System.out.println("Update is done!");
        } catch (Exception e) {
            System.err.println("Update is failed");
            if (tr != null)
                tr.rollback();
            throw e;
        } finally {
            if (session != null)
                session.close();
        }
        return object;
    }

    public long getFreeStorageSpace(Storage storage) {
        try {
            session = createSessionFactory().openSession();
            NativeQuery query = session.createNativeQuery("SELECT SUM(FILE_SIZE) as count FROM FILES WHERE STORAGE_ID=:d  ").addScalar("count", LongType.INSTANCE);
            query.setParameter("d", storage.getId());
            Long sum = (Long) query.getSingleResult();
            return storage.getStorageSize() - sum;
        } catch (Exception e) {
            System.err.println("Estimation of used space is failed");
            throw e;
        } finally {
            if (session != null)
                session.close();
        }
    }

    public List<File> getFilesByStorage(Storage storage) {
        try {
            session = createSessionFactory().openSession();
            NativeQuery query = session.createNativeQuery("SELECT * FROM FILES WHERE STORAGE_ID=:d  ");
            query.setParameter("d", storage.getId());
            List<File> list = query.getResultList();
            return list;
        } catch (Exception e) {
            System.err.println("File search is failed!");
            throw e;
        } finally {
            if (session != null)
                session.close();
        }

    }


    protected static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
