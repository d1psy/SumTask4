package persistence;

/**
 * @author Даня
 *
 */
public class DBException extends Exception {

    /**
     * This method is used for Data Base exceptions.
     * @param string
     */
    public DBException(final String string) {
        super(string);
    }

    private static final long serialVersionUID = -356764257683260217L;

}
