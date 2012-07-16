package DataManager;

import com.db4o.*;
import com.db4o.query.*;

import java.util.*;
/**
 * StoreObject interfaces with all back end storage operations
 * @author Sandeep
 */

public class GetObject {

	static String dbDirectoryPath = "/home/sandeep/Dev/CodeBase/Automator/Db/";
	static ObjectContainer db = null;
	boolean userExist = false;

	/*
	public static void main(String args[])
	{
		DataManager.GetObject Users = new DataManager.GetObject();
		Users.GetAllUsers();
	}
	*/
	
	/**
	 * Method to get details of all users from the system 
	 */
	public void GetAllUsers()
	{
		try
		{
			db = Db4o.openFile(dbDirectoryPath+"Auto_Users.txt");
			UserManager.UsersInfo newUser = new UserManager.UsersInfo(null,null,null,null,null,null,null,null);
			ObjectSet <UserManager.UsersInfo> result=db.queryByExample(newUser);
			listResult(result);
		}
		catch(Exception e)
		{
		}
		finally
		{
			db.close();
		}
	}
	/**
	 * 
	 * Method to get object pointer to a user object, given a particular user id as input 
	 */
	public UserManager.UsersInfo GetUserById(String userLoginId){
		UserManager.UsersInfo validUser = new UserManager.UsersInfo("", "", "", "", "", "", "", "");
		try{
			
		}
		catch(Exception e){
			System.out.println("Exception in DataManager.GetObject.GetUserById()");
			ErrorLogger.ErrorLog.logIt(""+e);
		}
		return validUser;
	}
	public static void UserExists(String userLoginId)
	{
		try
		{
			db = Db4o.openFile(dbDirectoryPath+"Auto_Users.txt");
			List <UserManager.UsersInfo> users = (List<UserManager.UsersInfo>) db.query(new Predicate() 
			{
				public boolean match(UserManager.UsersInfo usr) 
				{
					String names = usr.getLoginId(usr);
					System.out.println("Name: "+names);
					return true;
				}

				@Override
				public boolean match(Object arg0) {
					// TODO Auto-generated method stub
					return false;
				}
			});
		}
		catch(Exception e)
		{
			
		}
	}

	public static void listResult(ObjectSet result) 
	{
	    System.out.println(result.size());
	    while(result.hasNext()) {
	        System.out.println(result.next());
	    }
	}
}
