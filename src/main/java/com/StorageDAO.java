package com;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class StorageDAO extends DAO<Storage> implements Repository<Storage> {


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

}
