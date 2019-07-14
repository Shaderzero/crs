package ru.ge.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Session session = getSessionFactory().openSession();

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.configure("crsbase.cfg.xml");
                // Create SessionFactory
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("error in building factory");
            }
        }
        return sessionFactory;
    }

    public static Session getSession() {
        if (!session.isOpen()) {
            session = getSessionFactory().openSession();
        }
        return session;
    }

    public static void close() {
        if (session.isOpen()) {
            session.close();
        }
    }

    public static void save(Object entity) {
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
//            session.close();
        }
    }

//
//
//    private static final SessionFactory sessionFactory = buildSessionFactory();
//    private static Session session = getSessionFactory().openSession();
//
//    private static SessionFactory buildSessionFactory() {
//        try {
//            Configuration configuration = new Configuration();
//            configuration.configure("crsbase.cfg.xml");
//            SessionFactory sessionFactory = configuration.buildSessionFactory();
//            return sessionFactory;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("error in building factory");
//        }
//    }
//
//    public static String getUser() {
//        try {
//            Connection c = sessionFactory.getSessionFactoryOptions().getServiceRegistry().getService(ConnectionProvider.class).getConnection();
//            return c.getMetaData().getUserName();
//        } catch (SQLException e) {
//            System.out.println("can not load userName");
//        }
//        return "guest";
//
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    public static Session getSession() {
//        if (!session.isOpen()) {
//            session = getSessionFactory().openSession();
//        }
//        return session;
//    }

}
