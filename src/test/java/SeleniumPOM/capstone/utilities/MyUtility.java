package SeleniumPOM.capstone.utilities;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

//Class having methods for getting data from properties file and path of folders
public class MyUtility {
	
	//get data from properties file
	public String getDataFromProperties(String key) throws Exception {
		FileInputStream input=new FileInputStream(getPathOfProperties());
		Properties prob=new Properties();
		prob.load(input);
		return prob.getProperty(key);
	}
	//path of excel sheet
	public String getPathOfXlsx() {
		Path path = Paths.get("ExcellData/data.xlsx");
		return path.toAbsolutePath().toString();
	} 
	
	//path of properties file
	public String getPathOfProperties() {
		Path path = Paths.get("Properties/MyProperties.properties");
		return path.toAbsolutePath().toString();
	}
	//path of screenshot folder
	public String getPathOfScreenShots() {
		Path path = Paths.get("screenshots");
		return path.toAbsolutePath().toString();
	}
	//path of Report folder
	public String getPathOfReport() {
		Path path = Paths.get("Reports");
		return path.toAbsolutePath().toString();
	}
}
