package ru.ge.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;

public class HibernateUtil2 {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static Session session = getSessionFactory().openSession();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("rmaccountsbase.cfg.xml");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            return sessionFactory;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("error in building factory");
        }
    }

    public static String getUser() {
        try {
            Connection c = sessionFactory.getSessionFactoryOptions().getServiceRegistry().getService(ConnectionProvider.class).getConnection();
            return c.getMetaData().getUserName();
        } catch (SQLException e) {
            System.out.println("can not load userName");
        }
        return "guest";

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        if (!session.isOpen()) {
            session = getSessionFactory().openSession();

        }
        return session;
    }

    public static void doSomething() {
        System.out.println("hi there");

        Session session = HibernateUtil2.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil2.getSessionFactory().close();
        }

    }

    public static void close() {
        if (session.isOpen()) {
            session.close();
        }
    }

}
