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
import ua.com.hav.pb.dao.NotConfiguredException;

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
		Properties property = new Properties();
		int configType = -1;
		String conf = "";
		if (args.length > 0) {
			String s = args[0];
			if (s.contains(KEY)) {
				conf = s.replace(KEY, "");
			}
		}
		try {
			property.load(new FileInputStream(conf));
			String type = property.getProperty("config.type");
			configType = Integer.parseInt(type);
			switch (configType) {
				case 1: {
					BeanConfig.configure(
							property.getProperty("file.user"),
							property.getProperty("file.contact"));
					break;
				}
				case 0: {
					BeanConfig.configure(
							property.getProperty("mysql.user"),
							property.getProperty("mysql.pass"),
							property.getProperty("mysql.url"));
					break;
				}
			}
			if (configType == -1) {
				throw new NotConfiguredException();
			}
		} catch (IOException e) {
			System.err.println("ОШИБКА: Файл свойств отсуствует!");
		}
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}


}
