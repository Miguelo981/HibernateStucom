package view;

import controller.HibernateORM;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;
import model.*;

/**
 *
 * @author alu2018240
 */
public class UserInterface {

    private Usuarios user;
    private HibernateORM hibernateDAO;
    private Random random;

    public UserInterface(Usuarios user, HibernateORM hibernateDAO) {
        this.user = user;
        this.hibernateDAO = hibernateDAO;
        this.random = new Random();
        this.hibernateDAO.updateAccessDate(this.user);
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
            hibernateDAO.getTx().commit();
        }
    }

    private void checkExpedientes() {
        List<Expedientes> expedientesList = hibernateDAO.getAllExpedientes();
        if (expedientesList.size() > 0) {
            for (Expedientes e : expedientesList) {
                System.out.println(e.toString());
            }
        } else {
            System.out.println("No expedientes created yet.");
        }
    }

    private void registerExpediente() {
        List<Usuarios> userList = hibernateDAO.getAllUsers();
        int counter = 1;
        for (Usuarios u : userList) {
            System.out.println(counter + ".- " + u.toString());
            counter++;
        }
        int id = InputAsker.askInt("Choose one user ", 1, userList.size());
        int petNumber = InputAsker.askInt("Number of pets: ", 1, 1000000000);
        String cp = InputAsker.askPostalCode("Postal code: ");
        String phoneNumber = String.valueOf(InputAsker.askIntEqual("Telephone number: ", 9));
        hibernateDAO.setExpediente(new Expedientes(this.user, userList.get(id - 1).getNombre(), userList.get(id - 1).getApellidos(), userList.get(id - 1).getDni(), cp, Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()), phoneNumber, petNumber));
        System.out.println("Expediente registered successfully!");
    }

    private void deleteExpediente() {
        List<Expedientes> expedientesList = hibernateDAO.getAllExpedientes();
        int counter = 1;
        for (Expedientes e : expedientesList) {
            System.out.println(counter + ".- " + e.toString());
            counter++;
        }
        int id = InputAsker.askInt("Choose one expediente ", 1, expedientesList.size());
        if (InputAsker.askString("Are you sure? (Yes/No)").equalsIgnoreCase("yes")) {
            hibernateDAO.deleteExpediente(expedientesList.get(id - 1));
            System.out.println("Expediente deleted successfully!");
        }
    }

    private void editExpediente() {
    }

    private void registerUser() {
        String name = InputAsker.askString("Insert name: ", 25);
        String surName = InputAsker.askString("Insert surname: ", 25);
        String dni = "";
        do {
            dni = InputAsker.askDNI("Insert DNI: ");
            if (hibernateDAO.checkDNIExists(dni)) {
                System.out.println("Introduced DNI is in use");
            }
        } while (!hibernateDAO.checkDNIExists(dni));
        String pass = "", pass2 = "";
        do {
            pass = InputAsker.askString("Insert password: (8 digits maximum)", 8);
            pass2 = InputAsker.askString("Confirm password: ", 8);
            if (!pass.equals(pass2)) {
                System.out.println("Passwords does not match.");
            }
        } while (!pass.equals(pass2));
        int userType = InputAsker.askInt("1.- Auxiliar.\n2.- Veterinario\n3.- Administrador\nSelect one.", 1, 3);
        hibernateDAO.setUser(new Usuarios(name, surName, dni, generateMatricula(), pass, userType));
        System.out.println("User registered successfully!");
    }

    private String generateMatricula() {
        String matricula;
        do {
            matricula = String.valueOf((int) (Math.random() * 900000 + 100000));
        } while (!hibernateDAO.checkMatriculaExists(matricula));
        return matricula;
    }

    private void deleteUser() {
        List<Usuarios> userList = hibernateDAO.getAllUsers();
        int counter = 1;
        for (Usuarios u : userList) {
            System.out.println(counter + ".- " + u.toString());
            counter++;
        }
        int id = InputAsker.askInt("Choose one user ", 1, userList.size());
        if (InputAsker.askString("Are you sure? (Yes/No)").equalsIgnoreCase("yes")) {
            hibernateDAO.deleteUser(userList.get(id - 1));
            System.out.println("User deleted successfully!");
        }
    }

    private void editUser() {
    }

    private void checkUsers() {
        List<Usuarios> userList = hibernateDAO.getAllUsers();
        for (Usuarios u : userList) {
            System.out.println(u.toString());
        }
    }
}
