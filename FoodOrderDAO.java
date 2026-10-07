package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class FoodOrderDAO {

    private final SessionFactory sessionFactory;

    public FoodOrderDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // CREATE
    public void save(FoodOrder order) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(order);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    // READ one
    public FoodOrder findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(FoodOrder.class, id);
        }
    }

    // READ all
    public List<FoodOrder> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from FoodOrder", FoodOrder.class).list();
        }
    }

    // UPDATE
    public boolean updateStatus(Long id, String newStatus) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            FoodOrder order = session.get(FoodOrder.class, id);
            if (order == null) {
                tx.rollback();
                return false;
            }
            order.setStatus(newStatus);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    // DELETE
    public boolean delete(Long id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            FoodOrder order = session.get(FoodOrder.class, id);
            if (order == null) {
                tx.rollback();
                return false;
            }
            session.remove(order);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
