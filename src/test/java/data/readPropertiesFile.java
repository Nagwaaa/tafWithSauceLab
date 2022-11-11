package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class readPropertiesFile {
	
	
	public static Properties sauseData=loadproperties(System.getProperty("user.dir")+"\\src\\main\\java\\properities\\saucelabData.properities");
	
	private static Properties loadproperties(String path) {
		
		Properties pro=new Properties();
		FileInputStream stream;
		try {
			stream = new FileInputStream(path);
			pro.load(stream);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	return pro;
		
	}

}
