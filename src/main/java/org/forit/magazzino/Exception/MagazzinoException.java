
package org.forit.magazzino.Exception;

public class MagazzinoException extends Exception{

    public MagazzinoException() {
    }

    public MagazzinoException(String message) {
        super(message);
    }

    public MagazzinoException(String message, Throwable cause) {
        super(message, cause);
    }

    public MagazzinoException(Throwable cause) {
        super(cause);
    }

    public MagazzinoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
