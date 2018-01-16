package pack;

import java.util.ArrayList;
import java.util.List;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

import pack.PortForwardingL.MyUserInfo;

public class Main {

	public static void main(String[] args) {

		int lport = 1234;
		String rhost = "localhost";
		int rport = 3306;

		try {
			JSch jsch = new JSch();
			
			

			String user = "t0mB";
			String host = "136.144.170.66";

			Session session = jsch.getSession(user, host, 22);

			//NOT SECURE FOR RELEASE OUTSIDE OF TESTING
			java.util.Properties config = new java.util.Properties(); 
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			
//			UserInfo ui = new MyUserInfo();
//			session.setUserInfo(ui);
			
			session.setPassword("");
			
			int assinged_port=session.setPortForwardingL(lport, rhost, rport);
		      System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport);
		      
			session.connect();
			
			
			
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DAO dao = new DAO();

		List<Integer> l = new ArrayList<Integer>();
		l = dao.getInts();

		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i));
		}

	}

}
