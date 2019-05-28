package com;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;

import javax.xml.transform.Transformer;
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
    }    public void updateList(List<T> list){
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            for (T object:list)
                session.update(object);
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
    }
    public long getFreeStorageSpace(Storage storage) {
        try {
            session = createSessionFactory().openSession();
            NativeQuery query = session.createNativeQuery("SELECT SUM(FILE_SIZE) FROM FILES WHERE STORAGE_ID=:d  ");
            query.setParameter("d", storage.getId());
            Number result =(Number) query.getSingleResult();
            long sum=0;
            if (result!=null)
                sum=result.longValue();
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
            query.setParameter("d", storage.getId()).addEntity(File.class);
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
