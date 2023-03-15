package swaglabspages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
 
	static Properties prop;
	public static Properties readproperty()
	{
		try {
			 prop=new Properties();
			FileInputStream fis=new FileInputStream("C:\\Users\\Subha\\LiveProjects\\SwagLabs\\src\\test\\resources\\PropertyFiles\\config.properties");
			prop.load(fis);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
		
	}
}
