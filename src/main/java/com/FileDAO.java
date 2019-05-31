package com;


import org.hibernate.Session;

public class FileDAO extends DAO<File> implements Repository<File> {

    @Override
    public void delete(long id) {
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();
            File persistentInstance = session.load(File.class, id);
            if (persistentInstance != null) {
                session.delete(persistentInstance);
            }
            tr.commit();
            System.out.println("Delete is done!");
        } catch (Exception e) {
            System.err.println("Delete is failed");
            if (tr != null)
                tr.rollback();
            throw e;
        }
    }


    @Override
    public File findById(long id) {
        try(Session session = createSessionFactory().openSession())  {
            File file = session.get(File.class, id);
            return file;
        } catch (Exception e) {
            System.err.println("Search is failed");
            throw e;
        }
    }


}
