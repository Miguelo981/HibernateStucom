package view;

import controller.HibernateDAO;
import model.*;

/**
 *
 * @author alu2018240
 */
public class Console {

    private static HibernateDAO hibernateDao;

    public Console() {
        hibernateDao = new HibernateDAO();
        Usuarios user = new Usuarios("admin", "admin", "12345678D", "124567", "admin", 3);
        //hibernateDao.setUser(user);
        opcionesMenuPrincipal();
        //Usuarios user = new Usuarios("mike", "mike", "123456789", "124567", "mike", 0);
        //hibernateDao.setUser(user);
        //hibernateDao.setExpediente(new Expedientes(user, "mike", "apellido", "123456789X", "calle", Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()), "123456789", 0));
    }

    /**
     * Funcionos basicas del menu de inicio
     */
    public void opcionesMenuPrincipal() {
        try {
            int opcion = 0;
            do {
                System.out.println("|----Main menu----|\n1.- Login.\n0.- Exit.");
                opcion = InputAsker.askInt("Which option will choose?", 0, 1);
                switch (opcion) {
                    case 1:
                        login();
                        break;
                }
            } while (opcion != 0);
            hibernateDao.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    /**
     * 
     */
    private void login() {
        String name = InputAsker.askString("User name: ", 25);
        String pass = InputAsker.askString("Password: ", 8);
        if (hibernateDao.checkUserExists(name, pass)) {
            UserInterface userInterface = new UserInterface(hibernateDao.getUserByCredentials(name, pass), hibernateDao);
        } else {
            System.out.println("Name or password is not correct.");
        }
    }

}
