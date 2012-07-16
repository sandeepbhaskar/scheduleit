package DataManager;


import com.db4o.*;

/**
 * StoreObject interfaces with all back end storage operations
 * @author Sandeep
 */

public class StoreObject {
	
	public String dbDirectoryPath = "/home/sandeep/Dev/CodeBase/Automator/Db/";
	/*
	public static void main(String args[]){
		UserManager.UsersInfo newUser = new UserManager.UsersInfo("trial","","","","","","","");
		DataManager.StoreObject db = new DataManager.StoreObject();
		boolean retValue = db.StoreUserDb(newUser);
	}
	*/

	/**
	 * StoreUserDb is called from UserManager create new user method to store user details into database.
	 */
	public boolean StoreUserDb(UserManager.UsersInfo newUser){
		boolean userAlready = false;
		ObjectContainer db = Db4o.openFile(dbDirectoryPath+"Auto_Users.txt"); 
		try{
		
			GetObject getUser = new GetObject();
			getUser.UserExists(newUser.userLoginId);
			System.out.println("here: "+userAlready);

			if(userAlready)
				System.out.println("user already");
			//db.store(newUser);
			return true;
		}
		catch(Exception e){
			return false;
		}		
		finally{
			db.close();
		}
	}
}
