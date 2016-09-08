package net.sorokin.dao.daoexceptions;


public class NotUniqueBookInDBException extends Exception{

    public NotUniqueBookInDBException(String message) {
        super(message);
    }
}
