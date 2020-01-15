package controller;

import model.Usuarios;
import model.Expedientes;
import controller.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alu2018240
 */
public class HibernateDAO {

    private Session session;
    private Transaction tx;

    public HibernateDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public boolean checkUserExists(String name, String pass) {
        Query q = session.createQuery("select u from Usuarios u where u.nombre = '" + name + "' and u.pass = '" + pass + "'");
        Usuarios result = (Usuarios) q.uniqueResult();
        if (result != null)
            return true;
        return false;
    }
    
    public Usuarios getUserByCredentials(String name, String pass) {
        Query q = session.createQuery("select u from Usuarios u where u.nombre = '" + name + "' and u.pass = '" + pass + "'");
        Usuarios result = (Usuarios) q.uniqueResult();
        return result;
    }

    public void setUser(Usuarios user) {
        //session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        //session.close();
    }

    public void setExpediente(Expedientes expediente) {
        //session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.save(expediente);
        tx.commit();
        //session.close();
    }
    
    public List<Usuarios> getAllUsers() {
        Query q = session.createQuery("select u from Usuarios u");
        //Usuarios result = (Usuarios) q.uniqueResult();
        return q.list();
    }
    
    public List<Expedientes> getAllExpedientes() {
        Query q = session.createQuery("select e from Expedientes e");
        //Usuarios result = (Usuarios) q.uniqueResult();
        return q.list();
    }

    public void close() {
        this.session.close();
    }
}
