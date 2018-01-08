package pack;

import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

public class User implements UserInfo{

	
	@Override
	public String getPassphrase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return "B123273*tom";
	}

	@Override
	public boolean promptPassword(String message) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean promptPassphrase(String message) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean promptYesNo(String message) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showMessage(String message) {
		// TODO Auto-generated method stub
		
	}

}
