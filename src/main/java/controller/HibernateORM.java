package controller;

import model.Usuarios;
import model.Expedientes;
import controller.HibernateUtil;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alu2018240
 */
public class HibernateORM {

    private Session session;
    private Transaction tx;

    public HibernateORM() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public boolean checkUserExists(String name, String pass) {
        Query q = session.createQuery("select u from Usuarios u where u.nombre = '" + name + "' and u.pass = '" + pass + "'");
        Usuarios result = (Usuarios) q.uniqueResult();
        if (result != null)
            return true;
        return false;
    }
    
    public boolean checkMatriculaExists(String matricula) {
        Query q = session.createQuery("select u from Usuarios u where u.matricula = '" + matricula+ "'");
        Usuarios result = (Usuarios) q.uniqueResult();
        if (result != null)
            return false;
        return true;
    }
    
    public boolean checkDNIExists(String dni) {
        Query q = session.createQuery("select u from Usuarios u where u.dni = '" + dni+ "'");
        Usuarios result = (Usuarios) q.uniqueResult();
        if (result != null)
            return false;
        return true;
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
    
    public void deleteUser(Usuarios user) {
        tx = session.beginTransaction();
        deleteAllExpedientesByUser(user);
        session.delete(user);
        tx.commit();
    }
    
    public void updateAccessDate(Usuarios user) {
        tx = session.beginTransaction();
        user.setUltimoacceso(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        session.update(user);
        tx.commit();
    }

    public void setExpediente(Expedientes expediente) {
        tx = session.beginTransaction();
        session.save(expediente);
        tx.commit();
    }
    
    public void deleteExpediente(Expedientes expediente) {
        session.delete(expediente);
    }
    
    public void deleteAllExpedientesByUser(Usuarios usuario) {
        Query q = session.createQuery("select e from Expedientes e where e.usuarios = :usuarios");
        q.setParameter("usuarios", usuario);
        List<Expedientes> list = q.list();
        for (Expedientes e: list) {
            System.out.println(e.getUsuarios().toString());
            deleteExpediente(e);
        }
    }
    
    public List<Usuarios> getAllUsers() {
        Query q = session.createQuery("select u from Usuarios u");
        return q.list();
    }
    
    public List<Expedientes> getAllExpedientes() {
        Query q = session.createQuery("select e from Expedientes e");
        return q.list();
    }

    public void close() {
        this.session.close();
    }

    public Transaction getTx() {
        return this.tx;
    }
}
