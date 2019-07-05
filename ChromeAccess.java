import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
public class ChromeAccess {

	private static final String email_id = "alipour_armin@student.smc.edu";
	private static final String password = "276666Co";
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","/home/lolwtf/Desktop/Armin/Computer Science/chromedriver");
		
		  String fromEmail = "";
		  boolean runner = true;

		  Properties properties = new Properties(); //set properties 
		  properties.put("mail.store.protocol", "imaps"); //You can use imap or imaps , *s -Secured
		  properties.put("mail.imaps.host", "imap.gmail.com"); //Host Address of Your Mail
		  properties.put("mail.imaps.port", "993"); //Port number of your Mail Host
		 
		  //properties.put("mail.imaps.timeout", "10000");
		 
		  try {
		 
		   Session session = Session.getDefaultInstance(properties, null); //create a session 
		   Store store = session.getStore("imaps"); //SET the store for IMAPS
		   System.out.println("Connection initiated......");
		   store.connect(email_id, password);  //Trying to connect IMAP server
		   System.out.println("Connection is ready ");
		 
		    
		   Folder inbox = store.getFolder("inbox"); //Get inbox folder
		   inbox.open(Folder.READ_ONLY); //SET readonly format (*You can set read and write)
		 
		 
		   while (runner == true)
		   {

		        fromEmail =  inbox.getMessage(inbox.getMessageCount()).getSubject();
		      
		        if(fromEmail != null)
		       {
		    	   
		       
		         if(findCourse(fromEmail).equals("4382"))
		        {
		        	addFast("4382");
		        	runner = false;
		        }
		        else
		        {
		        	System.out.println("Currently no email");
		        }
		        
		       }
		        Thread.sleep(1000);
		        
		   }
		   inbox.close(true);
		   store.close();
		 
		  } 
		  catch (Exception e) 
		  {
		   e.printStackTrace();
		  }
		 
		 
		 

		 }
		 
	
	
public static void addFast(String classNumber)
{
	WebDriver driver = new ChromeDriver(); 
    driver.get("https://smccis.smc.edu/smcweb/f?p=420181023:1:5552447647573:1516262:NO::P1_SEMCODE:20190"); 
    
    WebElement id = driver.findElement(By.name("P102_LOGIN_ID"));
    WebElement pass = driver.findElement(By.name("P102_PASSWORD"));
 
    id.sendKeys("1516262");
    pass.sendKeys("276666Co");
    pass.submit();
    
    Select dropdown = new Select(driver.findElement(By.id("P1_SEMCODE")));
    dropdown.selectByIndex(2);
    driver.findElement(By.linkText("Add a Class")).click();
    
    WebElement textAdd = driver.findElement(By.name("P301_ADDSCTNUM"));
    textAdd.sendKeys(classNumber);
   
    
    WebElement buttonAdd = driver.findElement(By.id("B18390178385123965523"));
    buttonAdd.click();
   
}

public static String findCourse(String fck)
{
  String theString = "";
  int exiter = 0;
  if(fck.charAt(0) == 'S' && fck.charAt(1) == 'e' && fck.charAt(2) == 'c')
  {
  for(int i = 0 ; i < fck.length(); i++)
  {
    if(fck.charAt(i) >= 48 && fck.charAt(i) <= 57)
    {
      theString+=fck.charAt(i);
      if(exiter++ == 3)
      {
        break;
      }
    }
  }
}
  return theString;
}

}







