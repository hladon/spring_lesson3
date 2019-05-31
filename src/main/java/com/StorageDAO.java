package com;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class StorageDAO extends DAO<Storage> implements Repository<Storage> {


    @Override
    public void delete(long id) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();
            Storage persistentInstance = session.load(Storage.class, id);
            if (persistentInstance != null) {
                session.delete(persistentInstance);
            }
            tr.commit();
            System.out.println("Delete is done!");
        } catch (Exception e) {
            System.err.println("Delete is failed");
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        }
    }


    @Override
    public Storage findById(long id) {

        try(Session session = createSessionFactory().openSession())  {
            Storage storage = session.get(Storage.class, id);
            return storage;
        } catch (Exception e) {
            System.err.println("Search is failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

}
