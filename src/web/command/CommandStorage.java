package web.command;

import java.util.HashMap;


import web.command.auth.LoginCommand;
import web.command.auth.LogoutCommand;
import web.command.pages.LoginPageCommand;
import web.command.pages.ProfilePageCommand;
import web.command.pages.RegisterPageCommand;
import web.command.add.AddUserCommand;
import web.command.pages.admin.ListUsers;
import web.command.pages.admin.UserProfilePage;
import web.command.pages.admin.ListFacultiesAdmin;
import web.command.admin.BlockCommand;
import web.command.admin.CreateStatement;
import web.command.admin.UnblockCommand;
import web.command.add.AddFacultyCommand;
import web.command.pages.admin.AddFacultyPageCommand;
import web.command.admin.DeleteFacultyJava;
import web.command.pages.admin.EditFacultyPage;
import web.command.pages.admin.FacultyInfoAdmin;
import web.command.admin.EditFacultyCommand;
import web.command.upload.UploadCommand;
import web.command.pages.AddMarksPage;
import web.command.pages.FacultyInfoPage;
import web.command.add.AddMarksCommand;
import web.command.pages.RegisterOnFaculty;
import web.command.pages.JoinFaculty;
import web.command.common.ErrorCommand;
/**
 * @author Golubtsov
 * Used to store commands
 */
public class CommandStorage {
	private static HashMap<String, Command> commands = new HashMap<>();

	static {
		commands.put("LOGIN", new LoginCommand());
		commands.put("LOGINPAGE", new LoginPageCommand());
		commands.put("REGISTER", new RegisterPageCommand());
		commands.put("ADDUSER", new AddUserCommand());
		commands.put("PROFILE", new ProfilePageCommand());
		commands.put("LOGOUT", new LogoutCommand());
		commands.put("USERLIST", new ListUsers());
		commands.put("BLOCKUSER", new BlockCommand());
		commands.put("UNBLOCKUSER", new UnblockCommand());
		commands.put("FACULTYLIST", new ListFacultiesAdmin());
		commands.put("ADDFACULTYPAGE", new AddFacultyPageCommand());
		commands.put("ADDFACULTY", new AddFacultyCommand());
		commands.put("DELETEFACULTY", new DeleteFacultyJava());
		commands.put("EDITFACULTYPAGE", new EditFacultyPage());
		commands.put("EDITFACULTY", new EditFacultyCommand());
		commands.put("UPLOADCOMMAND", new UploadCommand());
		commands.put("MARKSPAGE", new AddMarksPage());
		commands.put("ADDMARKS", new AddMarksCommand());
		commands.put("FACULTYREGISTERPAGE", new RegisterOnFaculty());
		commands.put("JOINFACULTY", new JoinFaculty());
		commands.put("FACULTYINFOADMIN", new FacultyInfoAdmin());
		commands.put("CREATESTATEMENT", new CreateStatement());
		commands.put("ERROR", new ErrorCommand());
		commands.put("USERPROFILE", new UserProfilePage());
		commands.put("FACULTYINFOPAGE", new FacultyInfoPage());
	}

	public static Command getCommand(String name) {
		return commands.get(name);
	}
}
