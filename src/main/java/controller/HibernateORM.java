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
    /**
     * Funcion para saber si el usuario recibido existe o no
     * @param name
     * @param pass
     * @return si existe o no
     */
    public boolean checkUserExists(String dni, String pass) {
        Query q = session.createQuery("select u from Usuarios u where u.dni = '" + dni + "' and u.pass = '" + pass + "'");
        Usuarios result = (Usuarios) q.uniqueResult();
        if (result != null)
            return true;
        return false;
    }
    
    /**
     * Funcion para comprobar si la matricula generada ya esta en uso o no
     * @param matricula
     * @retur si existe o no 
     */
    public boolean checkMatriculaExists(String matricula) {
        Query q = session.createQuery("select u from Usuarios u where u.matricula = '" + matricula+ "'");
        Usuarios result = (Usuarios) q.uniqueResult();
        if (result != null)
            return false;
        return true;
    }
    /**
     * Funcion para averiguar si el dni recibiendo ya esta en uso o no
     * @param dni
     * @return si existe o no 
     */
    public boolean checkDNIExists(String dni) {
        Query q = session.createQuery("select u from Usuarios u where u.dni = '" + dni+ "'");
        Usuarios result = (Usuarios) q.uniqueResult();
        if (result != null)
            return true;
        return false;
    }
    /**
     * Funcion para obtener el usuario segun sus credenciales
     * @param name
     * @param pass
     * @return usuario
     */
    public Usuarios getUserByCredentials(String dni, String pass) {
        Query q = session.createQuery("select u from Usuarios u where u.dni = '" + dni + "' and u.pass = '" + pass + "'");
        Usuarios result = (Usuarios) q.uniqueResult();
        return result;
    }
    /**
     * Funcion para insertar un usuario a la base de datos
     * @param user 
     */
    public void setUser(Usuarios user) {
        //session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        //session.close();
    }
    /**
     * Funcion para borrar un usuario en la base de datos
     * @param user 
     */
    public void deleteUser(Usuarios user) {
        tx = session.beginTransaction();
        deleteAllExpedientesByUser(user);
        session.delete(user);
        tx.commit();
    }
    /**
     * Funcion para actualizar la fecha de ultimo acceso del usuario logeado
     * @param user 
     */
    public void updateAccessDate(Usuarios user) {
        tx = session.beginTransaction();
        user.setUltimoacceso(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        session.update(user);
        tx.commit();
    }
    /**
     * Funcion para actuar al usuario en cuestion de la base de datos
     * @param user 
     */
    public void updateUser(Usuarios user) {
        tx = session.beginTransaction();
        session.update(user);
        tx.commit();
    }
    /**
     * Funcion para insertar un expediente en la base de datos
     * @param expediente 
     */
    public void setExpediente(Expedientes expediente) {
        tx = session.beginTransaction();
        session.save(expediente);
        tx.commit();
    }
    /**
     * Funcion para borrar un expediente de la base de datos
     * @param expediente 
     */
    public void deleteExpediente(Expedientes expediente) {
        session.delete(expediente);
    }
    /**
     * Funcion para borrar todos los expedientes asociados a un usuario
     * @param usuario 
     */
    public void deleteAllExpedientesByUser(Usuarios usuario) {
        Query q = session.createQuery("select e from Expedientes e where e.usuarios = :usuarios");
        q.setParameter("usuarios", usuario);
        List<Expedientes> list = q.list();
        for (Expedientes e: list) {
            System.out.println(e.getUsuarios().toString());
            deleteExpediente(e);
        }
    }
    /**
     * Funcion para actualizar un expediente
     * @param expedientes 
     */
    public void updateExpediente(Expedientes expedientes) {
        tx = session.beginTransaction();
        session.update(expedientes);
        tx.commit();
    }
    /**
     * Funcion para obtener todos los usuarios
     * @return 
     */
    public List<Usuarios> getAllUsers() {
        Query q = session.createQuery("select u from Usuarios u");
        return q.list();
    }
    /**
     * Funcion para obtener todos los usuarios menos el que reciba por parametro
     * @param user
     * @return 
     */
    public List<Usuarios> getAllUsersExcept(Usuarios user) {
        Query q = session.createQuery("select u from Usuarios u where u.dni != '"+user.getDni()+"'");
        return q.list();
    }
    /**
     * Funcion para obtener todos los expedientes
     * @return 
     */
    public List<Expedientes> getAllExpedientes() {
        Query q = session.createQuery("select e from Expedientes e");
        return q.list();
    }
    /**
     * Funcion para cerrar la sesion de hibernate
     */
    public void close() {
        this.session.close();
    }
    /**
     * Funcion para obtener la transaccion
     * @return 
     */
    public Transaction getTx() {
        return this.tx;
    }
}
