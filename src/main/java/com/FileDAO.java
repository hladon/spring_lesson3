package com;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import javax.management.Query;


public class FileDAO extends DAO<File> implements Repository<File> {

    @Override
    public void delete(long id) {
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

    public long getFreeStorageSpace(Storage storage){
        try {
            session = createSessionFactory().openSession();
            NativeQuery query=session.createNativeQuery("SELECT SUM(FILE_SIZE) FROM FILES WHERE STORAGE_ID=:d  ");
            query.setParameter("d",storage.getId());
            Object obj=query.getSingleResult();
            long sum=0;
            if (obj!=null)
                 sum=(Long) obj;
            return storage.getStorageSize()-sum;
        } catch (Exception e) {
            System.err.println("Estimation of used space is failed");
            System.err.println(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return 0;
    }

}
