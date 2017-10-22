package tn.undefined.universalhaven.resources;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import tn.undefined.universalhaven.entity.User;
import tn.undefined.universalhaven.enumerations.UserRole;

import tn.undefined.universalhaven.buisness.UserServiceLocal;

@Stateless
@Path("user")
public class UserServices {

	@EJB
	UserServiceLocal userService;

	@Produces(MediaType.APPLICATION_JSON)
	@Path("ban")
	@PUT
	public void banUser(@QueryParam(value = "id") long id) {
		System.out.println(userService.banUser(id));

	}

	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public void AddUser(User user) {

		System.out.println(userService.addUser(user));

	}

	@Path("hamdi")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public User AddUser() {

		User user = new User();

		return user;

	}

	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
	public void UpdateUser(User user) {

		System.out.println(userService.updateUser(user));

	}

	
	//getAgeAverage
	@Path("getAgeAverage")
	@Produces(MediaType.APPLICATION_JSON)
	@GET

	public Response getAgeAverage() {

		return Response.status(Status.ACCEPTED)
				.entity(userService.getAgeAverage()).build();

	}
	
	
	
	@Path("getAvailableUser")
	@Produces(MediaType.APPLICATION_JSON)
	@GET

	public Collection<User> getAvailableVolenteers() {

		return userService.getAvailableVolenteers();

	}

	//getGenderStats
	@Path("getGenderStats")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Map<String,Long> getGenderStats() {

		return userService.getGenderStats();
		 

	}
	
	//getUserCountPerRole
	@Path("getUserCountPerRole")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Map<String,Integer> getUserCountPerRole() {

		return userService.getUserCountPerRole();
		 

	}
	
	
	@Path("getUserPerRole")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<User> getUserPerRole(@QueryParam(value = "role") UserRole role) {

		return userService.getUserPerRole(role);

	}

	@Path("searchForUser")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<User> searchForUser(@QueryParam(value = "variable") String variable) {

		return userService.searchForUser(variable);

	}

	@Path("find")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public User findUser() {

		return userService.findUser();

	}

	@Path("upload")
	@POST
	@Consumes("multipart/form-data")
	public Response uploadFile(MultipartFormDataInput input) {

		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("file");

		for (InputPart inputPart : inputParts) {
			MultivaluedMap<String, String> headers = inputPart.getHeaders();
			try {
				InputStream inputStream = inputPart.getBody(InputStream.class, null);
				byte[] bytes = IOUtils.toByteArray(inputStream);
				String filename = getFileName(headers);

				// test for file format //

				String extension = "";
				int i = filename.lastIndexOf('.');
				if (i >= 0) {
					extension = filename.substring(i + 1);
				}

				if (!extension.equalsIgnoreCase("xlsx")) {
					return Response.status(Status.NOT_ACCEPTABLE)
							.entity("Format not supported  please use .xlsx  format").build();
				}
				// test for file format //

				
				String fileLocation = "C:\\Users\\HD-EXECUTION\\workspaceJBosss\\universalhaven-javaee\\universalhaven-ejb\\assets\\"
				+UUID.randomUUID().toString()		+ filename;

				
				
				FileOutputStream fileOuputStream = new FileOutputStream(fileLocation);
				fileOuputStream.write(bytes);
				int iu = userService.importUserList(fileLocation);
				// saveFile(inputStream, fileLocation);
				// TODO: HERE you do whatever you want to do with the file
				// ...

			} catch (IOException e) {
				return Response.status(Status.NOT_ACCEPTABLE).entity("please check your file ").build();

			}
		}
		return Response.status(Status.OK).entity("Users Imported ").build();
	}

	private String getFileName(MultivaluedMap<String, String> headers) {
		String[] contentDisposition = headers.getFirst("Content-Disposition").split(";");

		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");

				String finalFileName = sanitizeFilename(name[1]);
				return finalFileName;
			}
		}
		return "unknown";
	}

	private String sanitizeFilename(String s) {
		return s.trim().replaceAll("\"", "");
	}

	@Path("changePassword")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST

	public Response dellaa(@QueryParam(value = "old") String old, @QueryParam(value = "new") String neww,
			@QueryParam(value = "username") String username) {

		System.out.println(old);
		System.out.println(neww);
		System.out.println(username);

		if (userService.changePassword(old, neww, username) == 1) {
			return Response.status(Status.OK).entity("Password changed ").build();
		}

		return Response.status(Status.FORBIDDEN).entity("old password is note correct").build();

	}

}
