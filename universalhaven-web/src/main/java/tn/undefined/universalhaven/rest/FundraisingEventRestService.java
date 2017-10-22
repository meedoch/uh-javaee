package tn.undefined.universalhaven.rest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import tn.undefined.universalhaven.service.FundraisingEventServiceLocal;
import tn.undefined.universalhaven.dto.FundraisingEventDto;
import tn.undefined.universalhaven.entity.*;
import tn.undefined.universalhaven.enumerations.UserRole;
import tn.undefined.universalhaven.jwt.JWTTokenNeeded;
/*http://localhost:18080/universalhaven-web/rest/fundraisingEvent/*/
@Path("fundraisingEvent")
public class FundraisingEventRestService {
	
	@EJB
	FundraisingEventServiceLocal fundraisingEventService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded(role=UserRole.SUPER_ADMIN)
	public Response getAll(){
		if(fundraisingEventService.listActiveEventsDto()==null)
			return null;
		return Response.status(Status.OK).entity(fundraisingEventService.listActiveEventsDto()).build();
	}	
	//http://localhost:18080/universalhaven-web/rest/fundraisingEvent/stat	
	
	@Path("stat")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCountEventPerCamp(){
		if(fundraisingEventService.getEventCountByCountry()==null)
			return null;
		return Response.status(Status.OK).entity(fundraisingEventService.getEventCountByCountry()).build();
		
	}
	/*@Path("user/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<FundraisingEventDto> getEventByUser(@PathParam("id")long idUser){
		return fundraisingEventService.listEventsByUserDto(idUser);
	}*/
	/*{"id":1}*/
	@Path("user")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventByUser(User user){
		if(fundraisingEventService.listEventsByUserDto(user)==null)
			return null;
		return Response.status(Status.ACCEPTED).entity(fundraisingEventService.listEventsByUserDto(user)).build();
		
	}
	/*{"title":"ddd","description":"eeee","goal":500,"imagePath":"aa.png","state":"In progress",
	  "urgency":"HIGH","publisher":1,"camp":2}*/
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEvent(FundraisingEvent event) {
		fundraisingEventService.startEvent(event);
		return Response.status(Status.CREATED).encoding("ok").build();
		
	}
	//http://localhost:18080/universalhaven-web/rest/fundraisingEvent/avgCompletionEvent
	@Path("avgCompletionEvent")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAvgCompletionEvent(){
		if(fundraisingEventService.getAverageCompletionDate()==null)
			return null;
		return Response.status(Status.OK).entity(fundraisingEventService.getAverageCompletionDate()).build();
		
	}
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded(role=UserRole.CAMP_MANAGER)
	public Response updateEvent(FundraisingEvent event) {
		fundraisingEventService.updateEvent(event);
		return Response.status(Status.ACCEPTED).encoding("ok").build();
		
	}
	public void genererExcel() throws IOException,WriteException{
		List<FundraisingEventDto> liste=fundraisingEventService.listActiveEventsDto();
		try{
			String filename="C:\\Users\\Dell\\Desktop\\4TWIN\\excel.xls";
			/*WritableWorkbook workbook=Workbook.createWorkbook(new File(filename));
			WritableSheet sheet=workbook.createSheet("Sheet1",0 );*/
			WritableWorkbook book= Workbook.createWorkbook(new File(filename)); 
			String title[]={"title","description","publish date","camp","user","??","??","??"};
		       WritableSheet sheet=book.createSheet("???",0); 
		       sheet.setColumnView(0, 25);
		       sheet.setColumnView(1, 20);
		       sheet.setColumnView(2, 20);
		       sheet.setColumnView(3, 30);
		       sheet.setColumnView(4, 15);
		       sheet.setColumnView(5, 15);
		       sheet.setColumnView(6, 15);
		       sheet.setColumnView(7, 100);
		       for(int i=0;i<8;i++) {
		           sheet.addCell(new Label(i,0,title[i])); 
		       }
			for(int j=0; j<liste.size(); j++) {
		   		sheet.addCell(new Label(0, j+1, liste.get(j).getTitle()));
		   		sheet.addCell(new Label(1, j+1, liste.get(j).getDescription()));
		   		sheet.addCell(new Label(2, j+1, liste.get(j).getPublishDate().toString()));
		   		sheet.addCell(new Label(3, j+1, liste.get(j).getCamp().getAddress()));
		   		sheet.addCell(new Label(4, j+1, liste.get(j).getUser().getLogin()));
		   	}
			
			
			book.write();
			book.close();
			
		}catch(WriteException e){
			
		}
	}

    @GET  
    @Path("excel")  
    @Produces("application/vnd.ms-excel")
    public Response getFile() throws WriteException, IOException {
    	genererExcel();
        File file = new File("C:\\Users\\Dell\\Desktop\\4TWIN\\excel.xls");  
        ResponseBuilder response = Response.ok((Object) file);  
        response.header("Content-Disposition","attachment; filename=listFundraisingEvents.xls");  
        return response.build();  
    }  

}
