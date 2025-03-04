package googleaggregator;

import java.util.List;

import org.openqa.selenium.WebElement;

public class GenericHelper {

	public String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36";
    public String url = "https://shopping.google.com/?nord=1";
    public int implicitWait = 30;
    public int pageLoadOutTime = 30;
    public String inputForSearch = "Mobile";
    public List<WebElement> productList;    
    
}
