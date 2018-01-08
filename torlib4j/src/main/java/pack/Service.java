package pack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/ZonnepaneelService")
public class Service {

	@Path("/create/{code}/{data}")
	@POST
	@Produces("application/json")	
	public Response createData(@PathParam("code") int code,
			@PathParam("data") String data) throws JSONException {

		List<String> list = null;
		String check = null;
		JSONObject jsonObject = new JSONObject();

		try {
			File file = new File("t0rlib4j.dat");

			
				if (!file.exists()) {
					String s = code + "-" + data;

					list = new ArrayList<String>();
					list.add(s);
					saveList(list);
					jsonObject.put("C Value", code);
					jsonObject.put("D Value", data);

					check = "@Produces(\"application/json\") Output: \n\nOutput: list created \n\n"
							+ jsonObject;

				} else {
					String s = code + "-" + data;
					FileInputStream fis = new FileInputStream(file);
					ObjectInputStream ois = new ObjectInputStream(fis);
					list = (List<String>) ois.readObject();

					if (!list.contains(s)) {
						list.add(s);
						FileOutputStream fos;
						fos = new FileOutputStream(file);
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						oos.writeObject(list);
						oos.close();
						jsonObject.put("C Value", code);
						jsonObject.put("D Value", data);

						check = "@Produces(\"application/json\") Output: \n\nOutput: value added \n\n"
								+ jsonObject;
						return Response.status(200).entity(check).build();
					} else {
						check = "@Produces(\"application/json\") Output: \n\nOutput: value already exists\n\n";
					}

					ois.close();

				}
		} catch (IOException e) {
			check = "@Produces(\"application/json\") Output: \n\nfailed \n\n";
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			check = "@Produces(\"application/json\") Output: \n\nfailed \n\n";
			e.printStackTrace();
		}

		return Response.status(200).entity(check).build();
	}

	@Path("/getlist")
	@GET
	@Produces("application/json")
	public Response getList() throws JSONException, IOException,
			ClassNotFoundException {

		JSONObject jsonObject = new JSONObject();
		List<String> list = new ArrayList<String>();
		List<String> printlist = new ArrayList<String>();
		FileInputStream fis = new FileInputStream("t0rlib4j.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		list = (List<String>) ois.readObject();

		String check = "@Produces(\"application/json\") Output:  \n\n";

		for (int i = 0; i < list.size(); i++) {
			jsonObject.put("Value", list.get(i));
			check += "\n" + jsonObject;
		}

		return Response.status(200).entity(check).build();

	}

	private void saveList(List<String> zpList) {
		try {
			File file = new File("t0rlib4j.dat");
			FileOutputStream fos;
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(zpList);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}