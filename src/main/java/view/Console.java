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
        String dni = InputAsker.askString("User DNI: ", 9); //TODO CAMBIAR USER NAME A DNI
        String pass = InputAsker.askString("Password: ", 8);
        if (hibernateDao.checkUserExists(dni, pass)) {
            UserInterface userInterface = new UserInterface(hibernateDao.getUserByCredentials(dni, pass), hibernateDao);
        } else {
            throw new ExceptionHibernate(ExceptionHibernate.nameOrPassWrong);
        }
    }

}
