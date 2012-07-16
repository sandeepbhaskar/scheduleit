package UserManager;
import com.db4o.*;
import com.db4o.query.*;
/***************************************************************************************************
 * FuncPoint:	F.3.2.2
 * Methods: 	createUser, getAllUsers, getUserById, openDb, closeDb 
 * Purpose:		Creation/Editing/Deletion of Users
 * Author:		@Sandeep
*****************************************************************************************************/	 	 	 
public class UsersInfo {
	/**
	 * Attributes of User
	 * LoginId, Password, FirstName, SecondName
	 * EmailId,	Category, DefaultOffice, AccessLevel
	 */
	
	public String userLoginId;
	public String userFirstName;
	public String userSecondName;
	public String userEmailId;
	public String userPassword;
	public String userCategory;
	public String userDefaultOffice;
	public String userAccessLevel;
	
	/**
	 * Database Connectivity Code
	 * Object Container db
	 * Database Location
	 */
	static ObjectContainer db = null;
	String databaseLocation = "/home/sandeep/Dev/CodeBase/Automator/Db/User";
	/**
	 * Constructor for UsersInfo
	 * */
	 public UsersInfo(String uLoginId, String uFirstName, String uSecondName, String uEmailId, String uPassword, String uCategory, String uDefaultOffice, String uAccessLevel){
		 this.userLoginId 		= uLoginId;
		 this.userFirstName		= uFirstName;
		 this.userSecondName	= uSecondName;
		 this.userEmailId		= uEmailId;
		 this.userPassword		= uPassword;
		 this.userCategory		= uCategory;
		 this.userDefaultOffice	= uDefaultOffice;
		 this.userAccessLevel	= uAccessLevel;
	 }
	/***************************************************************************************************
	  * Function: 	createUser()
	  * Arguments: 	UserLoginId, First Name, Second Name, Email Id, Password, Category, Office, Access Level 
	  * Return:		boolean
	  * Purpose:	Function is called whenever a new user creation call happens from server side.
	*****************************************************************************************************/	 	 	 
	 public boolean createUser(String uLoginId, String uFirstName, String uSecondName, String uEmailId, String uPassword, String uCategory, String uDefaultOffice, String uAccessLevel){
		 boolean createdUserYN = false;
		 try{
			 openDb();
			 UserManager.UsersInfo newUser = new UserManager.UsersInfo(uLoginId, uFirstName, uSecondName, uEmailId, uPassword, uCategory, uDefaultOffice, uAccessLevel);
			 db.store(newUser);
			 System.out.println("User created");
			 closeDb();
			 return createdUserYN;
		 }
		 catch(Exception e){
			System.out.println("Exception in UserManager.UsersInfo.createUser()");
			ErrorLogger.ErrorLog.logIt(""+e);
			return createdUserYN;
		 }		 
	 }
	 
	 
	/***************************************************************************************************
	  * Function: 	getAllUsers()
	  * Arguments: 	None
	  * Return:		Array of User Objects[UsersInfo[]]
	  * Purpose:	Get Details of all Users
	*****************************************************************************************************/	 	 	 
	 public UsersInfo[] getAllUsers(){
		 UsersInfo[] allUsers = new UsersInfo[10];
		 int i = 0;
		 try{
			openDb();
			Query qury = db.query();
			qury.constrain(UsersInfo.class);
			ObjectSet result = qury.execute();
			System.out.println(result.size());
			while(result.hasNext()){
				UsersInfo newUsr = (UsersInfo) result.next();
				allUsers[i] = newUsr;
				i++;
			}
			closeDb();
			return allUsers;
		}
		catch(Exception e){
			System.out.println("Exception in viewUsers() "+e);
			return allUsers;
		}		 
	 }
	
	 /***************************************************************************************************
	  * Function: 	countUsers()
	  * Arguments: 	None
	  * Return:		Count of Users
	  * Purpose:	Get Details of all Users
	*****************************************************************************************************/	 	 	 
	 public int countUsers(){
		 int userCount = 0;
		 try{
			openDb();
			Query qury = db.query();
			qury.constrain(UsersInfo.class);
			ObjectSet result = qury.execute();
			userCount = result.size();
			closeDb();
			return userCount;
		}
		catch(Exception e){
			System.out.println("Exception in countUsers() "+e);
			return 0;
		}		 
	 }
	 
	 /***************************************************************************************************
	  * Function: 	getUserById()
	  * Arguments: 	userLoginId
	  * Return:		User Object[UsersInfo]
	  * Purpose:	Get Details of only user whose login id is provided
	*****************************************************************************************************/	 	 	 
	 public UsersInfo getUserById(String uLoginId){
		 UsersInfo[] newUsr = new UsersInfo[10];
		 UsersInfo returnUsr = null;
		 boolean flag = false;
		 int i=0;
		 try{
			 openDb();
			 Query qury = db.query();
			 qury.constrain(UsersInfo.class);
			 ObjectSet result = qury.execute();
			 while(result.hasNext()){
				newUsr[i] = (UsersInfo) result.next();
				if(newUsr[i].userLoginId.equals(uLoginId)){
					returnUsr = newUsr[i];
					flag = true;
				}
				i++;
			 }
			 closeDb();
			 if(!flag)
				 return null;
			 return returnUsr;
		 }
		 catch(Exception e){
			 System.out.println("Exception in getUserById "+e);
			 return returnUsr; 
		 }
	 }
	 /***************************************************************************************************
	  * Function: 	deleteUser()
	  * Arguments: 	userLoginId
	  * Return:		boolean
	  * Purpose:	Delete the User, whose login id is provided
	*****************************************************************************************************/	 	 	 
	 public boolean deleteUser(String uLoginId){
		 try{
			 int i=0;
			 boolean flag = false;
			 UsersInfo newUsr[] = new UsersInfo[10];
			 openDb();
			 Query qury = db.query();
			 qury.constrain(UsersInfo.class);
			 ObjectSet result = qury.execute();
			 while(result.hasNext()){
				newUsr[i] = (UsersInfo) result.next();
				if(newUsr[i].userLoginId.equals(uLoginId)){
					db.delete(newUsr[i]);
					flag = true;
				}
				i++;
			 }
			 closeDb();
			 return flag;
		 }
		 catch(Exception e){
			 System.out.println("Exception "+e);
			 return false;
		 }
	 }
 
	 /***************************************************************************************************
	  * Function: 	openDb()
	  * Arguments: 	None
	  * Return:		None
	  * Purpose:	Open the database
	*****************************************************************************************************/	 	 	 
	 public void openDb(){
		 try{
			 db = Db4o.openFile(databaseLocation);
		 }
		 catch(Exception e){
			 System.out.println("Exception :"+e);
		 }
	 }
	 
	 /***************************************************************************************************
	 * Function: 	closeDb() 
	 * Arguments: 	None
	 * Return:		None
	 * Purpose:		Method to close the db
	 *****************************************************************************************************/	 
	 public void closeDb(){
		 try{
			 db.close();
		 }
		 catch(Exception e){
			 System.out.println("Exception :"+e);
		 }		 
	 }
	 /***************************************************************************************************
	  * Function: 	main() 
	  * Arguments: 	None
	  * Return:		None
	  * Purpose:	For Testing Purpose
	  *****************************************************************************************************/	 
		 
	 public static void main(String args[]){
		try{
			int i = 0;
			UsersInfo newUser = new UsersInfo("admin", "Sandeep", "Bhaskar", "sb@gmail.com", "sb", "Administrator", "Bangalore", "1");
			//UsersInfo newUser = new UsersInfo("admin", "Sandeep", "Bhaskar", "sb@gmail.com", "sb", "Administrator", "Bangalore", "1");
			//newUser.createUser("delete", "Guru", "Mishra", "swap@gmail.com", "sw", "User", "Bangalore", "2");
			UsersInfo[] allUsers = new UsersInfo[10];
			allUsers = newUser.getAllUsers();
			while(i < 3){
				System.out.println(allUsers[i].userLoginId);
				i++;
			}
		}
		catch(Exception e){
			System.out.println("Exception in Main "+e);
		}
	 }
}
