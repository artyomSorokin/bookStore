package net.sorokin.dao.daoexceptions;


public class NotUniqueUserEmailException extends Exception{

    public NotUniqueUserEmailException(String message) {
        super(message);
    }
}
