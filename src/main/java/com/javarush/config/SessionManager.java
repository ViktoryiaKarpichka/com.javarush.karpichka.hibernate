package com.javarush.config;

import org.hibernate.Session;

public class SessionManager implements AutoCloseable {
    private static final ThreadLocal<Session> currentSession = new ThreadLocal<>();
    private final Session session;

    public SessionManager() {
        this.session = HibernateConfig.getSessionFactory().openSession();
        currentSession.set(session);
    }

    public static Session getCurrentSession() {
        Session session = currentSession.get();
        if (session == null) {
            throw new IllegalStateException("No session bound to current thread");
        }
        return session;
    }

    @Override
    public void close() {
        if (session != null && session.isOpen()) {
            session.close();
        }
        currentSession.remove();
    }
}

