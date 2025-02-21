package gamesleague;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * WordLeaguePortal interface. The no-argument constructor of a class
 * implementing this interface should initialise the GamesLeaguePortal
 * with no initial users or leagues within it.
 * 
 *
 * @author Philip Lewis
 * @version 0.1
 *
 */

class IDInvalidException; extends RuntimeException {
    public IDInvalidException;(String m) {
        super(m);
    }
}


class IllegalOperationException; extends RuntimeException {
    public IllegalOperationException;(String m) {
        super(m);
    }
}

class InvalidNameException extends RuntimeException {
    public InvalidNameException(String m) {
        super(m);
    }
}

class IllegalEmailException extends RuntimeException {
    public IllegalEmailException(String m) {
        super(m);
    }
}

class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String m) {
        super(m);
    }
}

public interface GamesLeaguePortal extends Serializable {


    // Time 

    /**
     * Get the current epoch day. For this test application 
     * when a GamesLeaguePortal is initialised, the current day value should be set to the
     * current (real) epoch day, but it may be altered using setCurrentDay
     * incrementDay methods for testing purposes.
     * 
     * @return The current epoch day.
     */
    int getCurrentDay();

    /**
     * set the current epoch day (for testing purposes).
     *
     * @param day The current epoch day.
     * @throws IllegalOperationException If the day is less than current day.
     */ 
     void setCurrentDay(int day) throws IllegalOperationException; 

    /**
     * increment the current epoch day (for testing purposes).
     *
     * @param day The current epoch day.
     */ 
    void incrementDay(); 


    // Players

    /**
    * Get the players currently created in the platform.
    *
    * @return An array of player IDs in the system or an empty array if none exists.
    */
    int[] getPlayerIds();


    /**
    * Creates a player entry.
    * <p>
    * The state of this WordLeaguePortal must be unchanged if any
    * exceptions are thrown.
    *
    * @param name The name of the player.
    * @param phone The contact phone number of the player or empty string.
    * @param email The email of the player (unique).
    * @param displayName The name of the player.
    * @return The ID of the rider in the system.
    * @throws InvalidNameException If the displayName or name is null or starts/ends with whitespace, 
    *                              or if the name is less than 5 characters or more than 50 characters.
    *                              or if displayName is less than 1 characters or more than 20 characters.
    * @throws InvalidEmailException If the email is null, empty, or does not contain an '@' character,
    * @throws IllegalEmailException if it duplicates an existing email of a player

    */
    int createPlayer(String displayName, String email) 
        throws  InvalidNameException, 
                InvalidEmailException,   
                IllegalEmailException;


    /**
     * Permenantly deactivates player account.
     * <p>
     * Note to preserve the integrity of the league tables the removal process should follow:
     * i) all personal player anonymised with playerId placeholders
     * ii) all player gameplay reports are set to empty strings
     * iii) player is set to inactive in all league memberships
     *  
     * The state of this WordLeaguePortal must be unchanged if any
     * exceptions are thrown.
     *
     * @param playerId The ID of the player to be deactivated.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     * @throws IllegalOperationException If the player is the sole owner of a league.
     */
    void deactivatePlayer(int playerId) throws IDInvalidException, IllegalOperationException;


    /**
     * check if player has been deactivated.
     * 
     * @return true if player has been deactivated or false otherwise 
     */
    boolean isDeactivatedPlayer();


    /**
     * Updates the player's display name.
     * <p>
     * The state of this WordLeaguePortal must be unchanged if any
     * exceptions are thrown.
     * 
     * @param playerId The ID of the player to be updated.
     * @param displayName The new display name of the player.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     * @throws InvalidNameException If the name is null, starts/ends with whitespace, 
     *                              is less than 1 characters or more than 20 characters.
     */
    void updatePlayerDisplayName(int playerId, String displayName) 
        throws  IDInvalidException, 
                InvalidNameException;

    /**
    * Get the player id from the email.
    *
    * @param email The email of the player.
    * @return The ID of the player in the system or null if the player does not exist.
    */
    int getPlayerId(String email);


    /**
     * Get the player's display name.
     * 
     * @param playerId The ID of the player being queried.
     * @return The display name of the player.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     * 
     */
    String getPlayerDisplayName(int playerId) throws IDInvalidException;


    /**
     * Get the player's email.
     * 
     * @param playerId The ID of the player being queried.
     * @return The email of the player.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     * 
     */
    String getPlayerEmail(int playerId) throws IDInvalidException;


    /*
     * Get the in progress leagues a player is currently a member.
     * 
     * @param playerId The ID of the player being queried.
     * @return An array of league IDs the player is in or an empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     */

     int[] getPlayerLeagues(int playerId) throws IDInvalidException;


    /* 
     * Get the leagues a player owns.
     * 
     * @param playerId The ID of the player being queried.
     * @return An array of league IDs the player owns or an empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     */

     int[] getPlayerOwnedLeagues(int playerId) throws IDInvalidException;

    /*
     * Get the user's invites
     * 
     * @param playerId The ID of the player being queried.
     * @return An array of league IDs the player has invites to or an empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     */

     int[] getPlayerInvites(int playerId) throws IDInvalidException;

    /*
     * Get the user's rounds played game stat across all leagues
     * (includes closed/removed leagues)
     * 
     * @param playerId The ID of the player being queried.
     * @return number of rounds played by the player.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     */

     int getPlayerRoundsPlayed(int playerId) throws IDInvalidException;


    /*
     * Get the user's round participation percentage stat 
     * across all leagues (includes closed/removed leagues)
     * 
     * @param playerId The ID of the player being queried.
     * @return percentage of rounds (0-100) played by the player across all leagues.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     */

     double getPlayerRoundsPercentage(int playerId) throws IDInvalidException;


    // Leagues

    /**
    * Get the leagues currently created in the platform.
    *
    * @return An array of leagues IDs in the system or an empty array if none exists.
    */
    int[] getLeagueIds();

    /**
     * Creates a league.
     * <p>
     * The state of this WordLeaguePortal must be unchanged if any
     * exceptions are thrown.
     *
     * @param owner PlayerId of the league owner.
     * @param name The name of the league.
     * @param gameType The game for which the league is set up.
     * @return The ID of the league in the system.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     * @throws InvalidNameException If the name is null, starts/ends with whitespace, 
     *                              is less than 1 characters or more than 20 characters.
     * @throws IllegalNameException if it duplicates an existing league name
     */

    int createLeague(int owner, String name, GameType gameType ) throws IDInvalidException;

    /**
     * Removes a league and all associated game data from the system.
     * <p>
     * The state of this WordLeaguePortal must be unchanged if any
     * exceptions are thrown.
     *
     * @param leagueId The ID of the league to be removed.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */

    void removeLeague(int leagueId) throws IDInvalidException;

    /*
     * Get name of league
     * 
     * @param leagueId The ID of the league being queried.
     * @return The name of the league.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */

    String getLeagueName(int leagueId) throws IDInvalidException;

    /*
     * Update the name of a league
     * 
     * @param leagueId The ID of the league to be updated.
     * @param newName The new name of the league.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidNameException If the name is null, starts/ends with whitespace, 
     *                              is less than 1 characters or more than 20 characters.
     * @throws IllegalNameException if it duplicates an existing league name.
     * 
     */

    void updateLeagueName(int leagueId, String newName) 
        throws IDInvalidException, 
               InvalidNameException, 
               IllegalNameException;


    /**
     * Invites a player to a league.
     * <p>
     * 
     * @param leagueId The ID of the league to invite the player to.
     * @param email The email of the player to be invited.
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws InvalidEmailException If the email is null, empty, or does not contain an '@' character.
     */

    void invitePlayerToLeague(int leagueId, String email) throws IDInvalidException, InvalidEmailException;

    /**
     * Accepts an invite to a league.
     * <p>
     * The state of this WordLeaguePortal must be unchanged if any
     * exceptions are thrown.
     *
     * @param leagueId The ID of the league to accept the invite to.
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     */

    void acceptInviteToLeague(int leagueId) throws IDInvalidException;

    /*
     * Removes invite from league
     * 
     * <p>
     * 
     * @param leagueId The ID of the league to remove the invite from.
     * @param email The email of the player to remove the invite from.
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws IllegalEmailException If the email does not have an active invitation.
     */

    void removeInviteFromLeague(int leagueId, String email) 
        throws IDInvalidException, IllegalEmailException;


    /*
     * Get league invitations for non-existing players (email addresses)
     * 
     * @param leagueId The ID of the league being queried.
     * @return An array of email addresses of players with pending invites or an empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */

    String[] getLeagueEmailInvites(int leagueId) throws IDInvalidException;


    /*
     * Get league invitations made to existing players (player IDs)
     * 
     * @param leagueId The ID of the league being queried.
     * @return An array of player IDs who have pending invites or an empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */

     Int[] getLeaguePlayerInvites(int leagueId) throws IDInvalidException;


    /**
     * Get the players in a league.
     * 
     * @param leagueId The ID of the league being queried.
     * @return An array of player IDs in the league or an empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */

    int[] getLeaguePlayers(int leagueId) throws IDInvalidException;


    /**
     * Check if league player is set to inactivate.
     * 
     * @param leagueId The ID of the league being queried.
     * @return true if player is inactivate in league or false otherwise 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
     boolean isInactivePlayer(int leagueId) throws IDInvalidException;

    /*
     * Get the owners of a league.
     * 
     * @param leagueId The ID of the league being queried.
     * @return An array of player IDs that are owners of the league or an empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    int[] getLeagueOwners(int leagueId) throws IDInvalidException;

    /*
     * Get the status of a league 
     * 
     * 
     * @param leagueId The ID of the league being queried.
     * @param day The epoch day being queried.
     * 
     * @return The status of the league as enum
     *          PENDING       not yet played
     *          IN_PROGRESS   active 
     *          CLOSED        ended  
     *  
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    Status getLeagueStatus(int leagueId ) throws IDInvalidException;


    /*
     * Start league
     *
     * @param leagueId The ID of the league to start.
     * @param currentDay Should be set to the current epoch day.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @thows InvalidLeagueStateException If the league is already started.
     */
    void startLeague(int leagueId ) throws IDInvalidException, InvalidLeagueStateException;


    /* 
     * Close league
     * 
     * @param leagueId The ID of the league to close.
     * @param currentDay Should be set to the current epoch day.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidLeagueStateException If the league is already closed.
     */
    void closeLeague(int leagueId) throws IDInvalidException;


    /**
     * Get the league start date (as epoch day).
     * 
     * @param leagueId The ID of the league being queried.
     * @return The start date of the league as epoch day.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
     int getLeagueStartDate(int leagueId) throws IDInvalidException;


    /**
     * Get the date a closed league closed date (as epoch day).
     * 
     * @param leagueId The ID of the league being queried.
     * @return The closure date of the league as epoch day or -1 if not closed.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    int getLeagueCloseDate(int leagueId) throws IDInvalidException;


    /*
     * Reset league by removing all gameplay history i.e. no scores, and gives it pending status. 
     * status of active/inactive players is unchanged.
     * 
     * @param leagueId The ID of the league to reset.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    void resetLeague(int leagueId) throws IDInvalidException;

    
    /*
     * Clone a league to make a new league. 
     * Owners of the original league are also owners of the new league.
     * Invitations are created for all players in the original league.
     * League status is set to pending.
     * 
     * @param leagueId The ID of the league to clone.
     * @param newName The name of the new league.
     * @return The ID of the new league.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws IllegalNameException If the new name already exists in the platform.
     */
    int cloneLeague(int leagueId, String newName) throws IDInvalidException;


    /* 
     * Checks if player is active in the league.
     * 
     * @param leagueId The ID of the league.
     * @param playerId The ID of the player.
     * 
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws IllegalOperationException If the ID does not match to any player in the league.
     */
    bool isLeaguePlayerActive(int leagueId, int playerId) 
        throws IDInvalidException, IllegalArgumentException;

    /* 
     * Sets a player to be registered as inactive in the league.
     * 
     * @param leagueId The ID of the league.
     * @param playerId The ID of the player.
     * 
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws IllegalOperationException If the ID does not match to any player in the league.
     */
    void setLeaguePlayerInactive(int leagueId, int playerId) 
        throws IDInvalidException, IllegalArgumentException;

    /* 
     * Sets a player to be registered as active in the league.
     * 
     * @param leagueId The ID of the league.
     * @param playerId The ID of the player.
     * 
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws IllegalOperationException If the ID does not match to any player in the league.
     */
    void setLeaguePlayerActive(int leagueId, int playerId) 
        throws IDInvalidException, IllegalArgumentException;


    /* 
     * Add owner   
     * 
     * @param leagueId The ID of the league to add the owner to.
     * @param playerId The ID of the player to add as an owner.
     * 
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws IllegalOperationException If the player is not a member of the league.
     * 
     */

    void addOwner(int leagueId, int playerId) throws IDInvalidException, IllegalOperationException;

    /* 
     * Remove owner
     * 
     * @param leagueId The ID of the league to remove the owner from.
     * @param playerId The ID of the player to remove as an owner.
     * 
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws IllegalOperationException If the process would leave the league without an owner.
     * 
     */

    void removeOwner(int leagueId, int playerId) throws IDInvalidException, IllegalOperationException;



    // Results

    /*
     * Register a gameplay by a player in a league.
     * 
     * @param leagueId The ID of the league being queried.
     * @param playerId The ID of the player being queried.
     * @param day The epoch day the game was played.
     * @param gameReport A report detailing the gameplay, may be empty if no report made,
     *                   this may be updated e.g. after other players take actions that affect result
     *
     * @IllegalArgumentException If the day is not a valid day for the league.
     * @throws IDInvalidException If ID do not match to any league & player in the system.
     * 
     */
    void registerGameReport(int day, int leagueId,  int playerId, String gameReport) 
        throws IDInvalidException, IllegalArgumentException;


    /* 
     * Get the game report for a player in a league.
     * 
     * @param leagueId The ID of the league being queried.
     * @param playerId The ID of the player being queried.
     * @param day The epoch day the game was played.
     * 
     * @return The game report for the player in the league on the day, or empty string if no report entry.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not a valid day for the league.
     */

    String getGameReport(int day, int leagueId,  int playerId) throws IDInvalidException, InvalidDateException;

    /*
     * Register day results. Will be called when all play in a round is complete.
     * 
     * @param leagueId The ID of the league being queried.
     * @param playerId The ID of the player being queried.
     * @param day The epoch day the game was played.
     * @param scores The score of the game for the players (in order of player ID).
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws IllegalArgumentException If the day specified has already been closed,
     *                                 or if current date is 2 days or more after the day being voided.
     */
    void registerDayResults(int day, int leagueId, int[] scores) 
        throws IDInvalidException, InvalidDateException;

    /*
    * Register a void day for a league - all scores set to zero
    *
    * @param leagueId The ID of the league being queried.
    * @param day The epoch day being queried.
    * @throws IDInvalidException If the ID does not match to any league in the system.
    * @throws InvalidDateException If the day is not a valid day for the league,
    *                              or the current day is 2 days or more after the day being voided.
    */

    void voidDayResults(int day, int leagueId) 
        throws IDInvalidException, InvalidDateException;



    /*
     * Get the status of a league for a given day.
     * 
     * 
     * @param leagueId The ID of the league being queried.
     * @param day The epoch day being queried.
     * 
     * @return The status of the day as enum
     *          PENDING       not yet played
     *          IN_PROGRESS   active (some active players still to play)
     *          CLOSED        ended (all active players played or day ended)     
     *  
     * @throws IDInvalidException If the ID does not match to any league in the system.
     *         InvalidDateException If the day is not a valid day for the league.
     */
     Status getDayStatus(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException;


    /*
     * Get the scores of a league for a given day.
     * 
     * @param leagueId The ID of the league being queried.
     * @param day The epoch day being queried.
     * 
     * @return An array of the scores for each player from the day 
     *         or empty array if no gameplay from any players yet.
     *          (-2 indicates player has no gameplay recorded for round)
     *          (-1 indicates player has gameplay in round, but score is pending )
     *          (0 or greater indicates the score of the player)
     *          
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     *         InvalidDateException If the day is not a valid day for the league.
     */
    int[] getDayScores(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException;


    /*
     * Get the ranking of a league for a given day.
     * 
     * @param leagueId The ID of the league being queried.
     * @param day The epoch day being queried.
     * 
     * @return An array of the scores for each player from the day 
     *         or empty array if rankings not yet available.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     *         InvalidDateException If the day is not a valid day for the league.
     */
    int[] getDayRanking(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException;



    /*
     * Get the status of a league for a given week.
     * 
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the week being queried.
     * 
     * @return The status of the day as enum
     *          PENDING       no gameplay yet
     *          IN_PROGRESS   active and still games to play
     *          CLOSED        ended all games played or week ended    
     *  
     * @throws IDInvalidException If the ID does not match to any league in the system.
     *         InvalidDateException If the day is not within a valid week for the league.
     */
    Status getWeekStatus(int leagueId, int day ) 
    throws IDInvalidException, InvalidDateException;


   /*
    * Get the scores of a league for a given week.
    * 
    * @param leagueId  The ID of the league being queried.
    * @param day       Epoch day that is within the week being queried.
    * 
    * @return An array of the scores of each players total for the week (or part week played) 
    *         or empty array if no scores yet.
    * 
    * @throws IDInvalidException If the ID does not match to any league in the system.
    *         InvalidDateException If the day is not within a valid week for the league.
    */
   int[] getWeekScores(int leagueId, int day ) 
       throws IDInvalidException, InvalidDateException;


   /*
    * Get the ranking of a league for a given week.
    * 
    * @param leagueId  The ID of the league being queried.
    * @param day       Epoch day that is within the week being queried.
    * 
    * @return An array of the rankings of each players for the week (or part week played) 
    *         or empty array if no rankings yet.

    * 
    * @throws IDInvalidException If the ID does not match to any league in the system.
    *         InvalidDateException     If the day is not within a valid week for the league.
    */
   int[] getWeekRanking(int leagueId, int day ) 
       throws IDInvalidException, InvalidDateException;



    /*
     * Get the status of a league for a given month.
     * 
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the month being queried.
     * 
     * @return The status of the day as enum
     *          PENDING       no gameplay yet
     *          IN_PROGRESS   active and still games to play
     *          CLOSED        ended all games played or month ended    
     *  
     * @throws IDInvalidException If the ID does not match to any league in the system.
     *         InvalidDateException If the day is not within a valid month for the league.
     */
     Status getMonthStatus(int leagueId, int day ) 
     throws IDInvalidException, InvalidDateException;


    /*
     * Get the scores of a league for a given month.
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the month being queried.
     * 
     * @return An array of the scores of each players total for the month (or part month played) 
     *         or empty array if no scores yet.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     *         InvalidDateException If the day is not within a valid month for the league.
     */
    int[] getMonthScores(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException;


    /*
     * Get the ranking of a league for a given month.
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the month being queried.
     * 
     * @return An array of the rankings of each players for the month (or part month played) 
     *         or empty array if no rankings yet.

     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     *         InvalidDateException     If the day is not within a valid month for the league.
     */
    int[] getMonthRanking(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException;


    /*
     * Get the status of a league for a given year.
     * 
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the year being queried.
     * 
     * @return The status of the day as enum
     *          PENDING       no gameplay yet
     *          IN_PROGRESS   active and still games to play
     *          CLOSED        ended all games played or year has ended    
     *  
     * @throws IDInvalidException If the ID does not match to any league in the system.
     *         InvalidDateException If the day is not within a valid month for the league.
     */
    Status getYearStatus(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException;


   /*
    * Get the scores of a league for a given month.
    * 
    * @param leagueId  The ID of the league being queried.
    * @param day       Epoch day that is within the month being queried.
    * 
    * @return An array of the scores of each players total for the month (or part month played) 
    *         or empty array if no scores yet.
    * 
    * @throws IDInvalidException If the ID does not match to any league in the system.
    *         InvalidDateException If the day is not within a valid month for the league.
    */
   int[] getYearScores(int leagueId, int day ) 
       throws IDInvalidException, InvalidDateException;


   /*
    * Get the ranking of a league for a given month.
    * 
    * @param leagueId  The ID of the league being queried.
    * @param day       Epoch day that is within the month being queried.
    * 
    * @return An array of the rankings of each players for the month (or part month played) 
    *         or empty array if no rankings yet.

    * 
    * @throws IDInvalidException If the ID does not match to any league in the system.
    *         InvalidDateException     If the day is not within a valid month for the league.
    */
   int[] getYearRanking(int leagueId, int day ) 
       throws IDInvalidException, InvalidDateException;


    /**
     * Method empties this GamesLeaguePortal of its contents and resets all
     * internal counters.
     */
    void eraseGamesLeaguePortal();


    /**
    * Method saves this GamesLeaguePortal contents into a serialised file,
    * with the filename given in the argument.
    * <p>
    * The state of this GamesLeaguePortal must be unchanged if any
    * exceptions are thrown.
    *
    * @param filename Location of the file to be saved.
    * @throws IOException If there is a problem experienced when trying to save the * store contents to the file.
    */
    void saveGamesLeaguePortal(String filename) throws IOException;
    
    /**
    * Method should load and replace this GamesLeaguePortal contents with the
    * serialised contents stored in the file given in the argument.
    * <p>
    * The state of this GamesLeaguePortal must be unchanged if any
    * exceptions are thrown.
    *
    * @param filename Location of the file to be loaded.
    * @throws IOException If there is a problem experienced when trying
    *                     to load the store contents from the file.
    * @throws ClassNotFoundException If required class files cannot be found when * loading.
    */
    void loadGamesLeaguePortal(String filename) throws IOException, ClassNotFoundException;

}