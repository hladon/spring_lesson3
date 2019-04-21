package com;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class FileDAO extends DAO<File> implements Repository<File> {

    @Override
    public void delete(long id) {
        Session session = null;
        Transaction tr = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            File persistentInstance = session.load(File.class, id);
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
    public File findById(long id) {
        Session session = null;
        try {
            session = createSessionFactory().openSession();
            File file  = session.get(File.class, id);
            return file;
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
