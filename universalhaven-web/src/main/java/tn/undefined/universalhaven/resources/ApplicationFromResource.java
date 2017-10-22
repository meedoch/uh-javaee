package tn.undefined.universalhaven.resources;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import tn.undefined.universalhaven.entity.ApplicationForm;
import tn.undefined.universalhaven.buisness.ApplicationFormServiceLocal;

@Stateless
@Path("application")
public class ApplicationFromResource {

	@EJB
	ApplicationFormServiceLocal applicationForm;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response apply(ApplicationForm app) {
		if (applicationForm.apply(app) == 1)
			return Response.status(Status.ACCEPTED).entity(app).build();

		return Response.status(Status.CONFLICT).entity("please check ur applicationForm Json").build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAPPlcationForm() {

		ApplicationForm f = new ApplicationForm();
		return Response.status(Status.OK).entity(f).build();

	}

	@GET
	@Path("listApplication")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ApplicationForm> listApplication() {

		return applicationForm.listApplication();

	}

	@GET
	@Path("listApplicationCountry")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listApplicationPerCountry(@QueryParam(value = "country") String country) {
		return Response.status(Status.OK).entity(applicationForm.listApplicationPerCountry(country)).build();

	}

	@GET
	@Path("listApplicationPerGender")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listApplicationPerGender(@QueryParam(value = "gender") String gender) {
		
		return Response.status(Status.OK).entity(applicationForm.listApplicationPerGender(gender)).build();

	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@Path("reviewApplication")
	@PUT
	  public Response reviewApplication(ApplicationForm application,@QueryParam(value = "review")boolean review , @QueryParam(value = "revieww") long revieww ){
		 
		return Response.status(Status.OK).entity(applicationForm.reviewApplication(application,review,revieww)).build();

	  }
	
//ApplicationForm application, String name
	@Path("upload/{applicationform}")
	@POST
	@Consumes("multipart/form-data")
	public Response addAttachment(MultipartFormDataInput input,@PathParam("applicationform") int application)
	{
		List<String> formatFile = new ArrayList<String>();
		formatFile.add("jpeg");
		formatFile.add("jpg");
		formatFile.add("png");
		
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

			if (!formatFile.contains(extension))   {
				return Response.status(Status.NOT_ACCEPTABLE)
						.entity("Format not supported  please use .jpeg .jpg .png  format").build();
			}
			// test for file format //

			
			String fileLocation = "C:\\Users\\HD-EXECUTION\\workspaceJBosss\\universalhaven-javaee\\universalhaven-web\\src\\main\\webapp\\assets\\"
			+UUID.randomUUID().toString()		+ filename;

			
			
			FileOutputStream fileOuputStream = new FileOutputStream(fileLocation);
			fileOuputStream.write(bytes);
			int iu = applicationForm.addAttachment(application, fileLocation);
			// saveFile(inputStream, fileLocation);
			// TODO: HERE you do whatever you want to do with the file
			// ...

		} catch (IOException e) {
			return Response.status(Status.NOT_ACCEPTABLE).entity("please check the Id ").build();

		}
	}
	return Response.status(Status.OK).entity("Attachemnt added  ").build();
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
	
	
}