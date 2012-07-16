package BaseCamp;

/**
 * Control - Central Control of BaseCamp. Interacts with all packages and ensure smooth processing.
 * @author Sandeep
 */

public class Control {
	
	public static void main(String args[]){
		boolean retValue = false;
		String userLoginId = "Admin";
		String userFirstName = "Admin";
		String userSecondName = "Admin";
		String userEmailId = "admin@auto.com";
		String userPassword = "ad";
		String userCategory = "Administrator";
		String userDefaultOffice = "Office";
		String userAccessLevel = "0";
		
		UserManager.UsersInfo newUser = new UserManager.UsersInfo(userLoginId,userFirstName,userSecondName,userEmailId,userPassword,userCategory,userDefaultOffice,userAccessLevel);
		//DataManager.StoreObject db = new DataManager.StoreObject();
		//retValue = db.StoreUserDb(newUser);
		if(retValue == true)
			System.out.println("User Created");
		else
			System.out.println("Error");
		
	}
}