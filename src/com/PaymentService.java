package com;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


import model.Payment;
@Path("/Payments")


public class PaymentService {
	
	
	Payment paymentObj = new Payment();

	 
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
		return paymentObj.readPayment();
	 }
	
@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertPayment(
		
  @FormParam("cardNumber") String cardNumber,
 @FormParam("CVV") String CVV,
 @FormParam("expDate") String expDate,
 @FormParam("amount") String amount)
{
 String output = paymentObj.insertPayment( cardNumber,  CVV,  expDate, amount);
return output;
}
	

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updatePayment(String sData)
{
//Convert the input string to a JSON object
 JsonObject paymentObject = new JsonParser().parse(sData).getAsJsonObject();
//Read the values from the JSON object
 String paymentId = paymentObject.get("paymentId").getAsString();
 String cardNumber = paymentObject.get("cardNumber").getAsString();
 String CVV = paymentObject.get("CVV").getAsString();
 String expDate = paymentObject.get("expDate").getAsString();
 String amount = paymentObject.get("amount").getAsString();
 String output = paymentObj.updatePayment( paymentId, cardNumber,  CVV,  expDate,  amount);
return output;
}



@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteItem(String sData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(sData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String paymentId = doc.select("paymentId").text();
 String output = paymentObj.deletePayment(paymentId);
return output;
}
}
