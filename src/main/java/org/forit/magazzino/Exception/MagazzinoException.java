/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.Exception;

/**
 *
 * @author forIT
 */
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
