package ua.com.hav.pb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ua.com.hav.pb.config.BeanConfig;
import ua.com.hav.pb.config.WebConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "ua.com.hav")
@Import({BeanConfig.class, WebConfig.class})
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {

	private static Class applicationClass = Application.class;
	public static String KEY = "-Dlardi.conf=";
	public static void main(String[] args) {
//		BeanConfig.configure();
		FileInputStream fis;
		Properties property = new Properties();
		String file1 = null;
		String file2 = null;
		String userName = null;
		String userPass = null;
		String dbUrl = null;
		int configType = -1;
		String conf = "";
		if (args.length > 0) {
			String s = args[0];
			if (s.contains(KEY)) {
				conf = s.replace(KEY, "");
			}
		}
		try {
//			fis = new FileInputStream("c:/pb/conf/mysqlconf.properties");
			fis = new FileInputStream(conf);

			property.load(fis);

			String type = property.getProperty("config.type");
			configType = Integer.parseInt(type);
			if (configType == 1) {
				file1 = property.getProperty("file.user");
				file2 = property.getProperty("file.contact");
				BeanConfig.configure(file1, file2);
			} else if (configType == 0) {
				userName = property.getProperty("mysql.user");
				userPass = property.getProperty("mysql.pass");
				dbUrl = property.getProperty("mysql.url");
				BeanConfig.configure(userName, userPass, dbUrl);
			}

			System.out.println("configType = " + configType);
			System.out.println("file1 = " + file1);
			System.out.println("file2 = " + file2);
			System.out.println("userName = " + userName);
			System.out.println("userPass = " + userPass);
			System.out.println("dbUrl = " + dbUrl);

		} catch (IOException e) {
			System.err.println("ОШИБКА: Файл свойств отсуствует!");
		}
//		BeanConfig.configure(file1, file2);
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}


}
