import java.util.Random;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests extends Settings{
	Settings settings;
	
	public String keyToken = "key=d63ab9a58a5b6353fd6af51e9dc73e81&token=23f6e4aabfb03518cc5a70a44e7af88a098f7a291feee10c253d8ba10e0093be";
	public static String idBoard;
	public static String idList;
	public static String[] idCard = {"",""};
	
	
	
	@Test
	public void stage1_createBoard() {
		
		settings = new Settings();
		RequestSpecification httpRequest = settings.setupBaseURI();
		
		Response response = httpRequest.post("/1/boards/?name=firstMavenBoard&" + keyToken);
		settings.checkStatusCodeSuccessfull(response);
		
		idBoard = settings.getValueJson(response.asPrettyString(), "id");
		
	}
	
	@Test
	public void stage2_createList() {
	
		settings = new Settings();
		RequestSpecification httpRequest = settings.setupBaseURI();
		
		Response response = httpRequest.post("/1/lists/?name=firstList&idBoard=" + idBoard + "&" + keyToken);
		settings.checkStatusCodeSuccessfull(response);
		
		idList = settings.getValueJson(response.asPrettyString(), "id");
		
	}
	
	
	@Test
	public void stage3_createTwoCards() {
		
		settings = new Settings();
		RequestSpecification httpRequest = settings.setupBaseURI();
		RequestSpecification httpRequest2 = settings.setupBaseURI();
		
		Response response = httpRequest.post("/1/cards/?name=firstCard&idList=" + idList + "&" + keyToken);
		settings.checkStatusCodeSuccessfull(response);
		
		Response response2 = httpRequest2.post("/1/cards/?name=secondCard&idList=" + idList + "&" + keyToken);
		settings.checkStatusCodeSuccessfull(response2);
		
		idCard[0] = settings.getValueJson(response.asPrettyString(), "id");
		idCard[1] = settings.getValueJson(response2.asPrettyString(), "id");
		
	}
	
	
	@Test
	public void stage4_updateRandomCard() {
		
		settings = new Settings();
		RequestSpecification httpRequest = settings.setupBaseURI();
		
		Random random = new Random();
		int rand = random.nextInt(2);
		
		Response response = httpRequest.put("/1/cards/"+ idCard[rand] + "?name=updatedCard&" + keyToken);
		
		settings.checkStatusCodeSuccessfull(response);
		
	}
	
	@Test
	public void stage5_deleteCards() {
		
		settings = new Settings();
		RequestSpecification httpRequest = settings.setupBaseURI();
		
		Response response = httpRequest.delete("/1/cards/" + idCard[0] + "?" + keyToken);
		settings.checkStatusCodeSuccessfull(response);
		
		Response response2 = httpRequest.delete("/1/cards/" + idCard[1] + "?" + keyToken);
		settings.checkStatusCodeSuccessfull(response2);
		
	}
	
	
	@Test
	public void stage6_deleteBoard() {
		
		settings = new Settings();
		RequestSpecification httpRequest = settings.setupBaseURI();
		
		Response response = httpRequest.delete("/1/boards/" + idBoard + "?" + keyToken);
		settings.checkStatusCodeSuccessfull(response);
		
	}
}
