package view;

import controller.HibernateDAO;
import java.util.List;
import model.*;

/**
 *
 * @author alu2018240
 */
public class UserInterface {

    private Usuarios user;
    private HibernateDAO hibernateDAO;

    public UserInterface(Usuarios user, HibernateDAO hibernateDAO) {
        this.user = user;
        this.hibernateDAO = hibernateDAO;
        this.opcionesUsuario();
    }

    private int getOptionsByType() {
        System.out.println("|----" + this.user.getNombre() + "----|");
        switch (this.user.getTipousuario()) {
            case 1:
                System.out.println("1.- Check expediente.\n0.- Exit.");
                return 1;
            case 2:
                System.out.println("1.- Check expediente.\n2.- Register expediente\n3.- Delete expediente\n4.- Edit expediente\n0.- Exit.");
                return 4;
            case 3:
                System.out.println("1.- Check expediente.\n2.- Register expediente\n3.- Delete expediente\n4.- Edit expediente\n5.- Register user\n6.- Delete user\n7.- Edit user\n8.- Check users\n0.- Exit.");
                return 8;
        }
        return 0;
    }

    private void opcionesUsuario() {
        try {
            int opcion = 0;
            do {
                opcion = InputAsker.askInt("Which option will choose?", 0, getOptionsByType());
                switch (opcion) {
                    case 1:
                        checkExpedientes();
                        break;
                    case 2:
                        registerExpediente();
                        break;
                    case 3:
                        deleteExpediente();
                        break;
                    case 4:
                        editExpediente();
                        break;
                    case 5:
                        registerUser();
                        break;
                    case 6:
                        deleteUser();
                        break;
                    case 7:
                        editUser();
                        break;
                    case 8:
                        checkUsers();
                        break;
                }
            } while (opcion != 0);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void checkExpedientes() {
        List<Expedientes> expedientesList = hibernateDAO.getAllExpedientes();
        for (Expedientes e: expedientesList) {
            System.out.println(e.toString());
        }
    }

    private void registerExpediente() {
    }

    private void deleteExpediente() {
    }

    private void editExpediente() {
    }

    private void registerUser() {
    }

    private void deleteUser() {
    }

    private void editUser() {
    }

    private void checkUsers() {
        List<Usuarios> userList = hibernateDAO.getAllUsers();
        for (Usuarios u: userList) {
            System.out.println(u.toString());
        }
    }

}
