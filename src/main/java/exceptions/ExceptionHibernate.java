package exceptions;

/**
 *
 * @author alu2018240
 */
public class ExceptionHibernate extends Exception{
    public static final String userExists = "ERROR: This DNI is under use.";
    public static final String passDontMatch = "ERROR: Passwords doesn't match.";
    public static final String samePass = "ERROR: This password is the same you are using.";
    public static final String nameOrPassWrong = "ERROR: Name or password is not correct.";
    
    public ExceptionHibernate(String error) {
        super(error);
    }
}