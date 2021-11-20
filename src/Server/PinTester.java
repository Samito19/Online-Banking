package Server;

public class PinTester {

	private static final DatabaseConnector databaseConnector;
    
    static {databaseConnector = new DatabaseConnector();}

    public static boolean isValid(int id, int userPIN) {
    	
    	try {

			int validPIN = Integer.parseInt(databaseConnector.fetchInDatabasePIN(String.valueOf(id)));

            if (userPIN == validPIN) {return true;}
            
    	} catch (NumberFormatException n) {return false;}
		return false;
    }
             
    public static boolean createNewUser(int id, int pin, String name){
    	
    	if (databaseConnector.fetchInDatabaseID(String.valueOf(id)) == null) {
    		databaseConnector.insertNewUserIntoDatabase(id, pin, name);
    		return true;
    	}
    	else {return false;}
    	
    	
    }
    
}