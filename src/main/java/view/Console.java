package view;

import controller.HibernateORM;
import exceptions.ExceptionHibernate;
import model.*;

/**
 *
 * @author alu2018240
 */
public class Console {

    private static HibernateORM hibernateDao;

    public Console() {
        hibernateDao = new HibernateORM();
        //Usuarios user = new Usuarios("test", "test", "123456789", "124567", "mike", 2);
        //hibernateDao.setUser(user);
        //hibernateDao.setExpediente(new Expedientes(user, "mike", "apellido", "123456789X", "calle", Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()), "123456789", 0));
        opcionesMenuPrincipal();
    }

    /**
     * Funcionos basicas del menu de inicio
     */
    public void opcionesMenuPrincipal() {
        int opcion = 0;
        do {
            try {
                System.out.println("|----Main menu----|\n1.- Login.\n0.- Exit.");
                opcion = InputAsker.askInt("Which option will choose?", 0, 1);
                switch (opcion) {
                    case 1:
                        login();
                        break;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (opcion != 0);
        hibernateDao.close();
    }
    
    /**
     * Funcion para hacer login y acceder a las funciones de usuario
     * @throws ExceptionHibernate 
     */
    private void login() throws ExceptionHibernate {
        String name = InputAsker.askString("User name: ", 25); //TODO CAMBIAR USER NAME A DNI
        String pass = InputAsker.askString("Password: ", 8);
        if (hibernateDao.checkUserExists(name, pass)) {
            UserInterface userInterface = new UserInterface(hibernateDao.getUserByCredentials(name, pass), hibernateDao);
        } else {
            throw new ExceptionHibernate(ExceptionHibernate.nameOrPassWrong);
        }
    }

}
