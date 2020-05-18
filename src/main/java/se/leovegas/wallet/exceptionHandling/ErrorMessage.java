package se.leovegas.wallet.exceptionHandling;

public class ErrorMessage {

    public static final String NO_ACCOUNT_FOUND = "No account with id '%d' exists in the system.";
    public static final String NOT_UNIQUE_TRANSACTION_REF_FOUND = "transaction ref has been already used";
    public static final String IS_MANDATORY_FIELD = "player name is mandatory";
    public static final String PLAYER_NAME_RESTRICTION = "player name is should be 5 characters of more";
    public static final String REFRESH_TO_GET_UPDATED_PLAYERS = "refresh your page to get updated players";
    public static final String NOT_ENOUGH_FUNDS = "player's balance is %.2f and cannot perform a transaction of %.2f ";
}
