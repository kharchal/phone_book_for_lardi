package ua.com.hav.pb.config;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.com.hav.pb.controller.SecurityFilter;
import ua.com.hav.pb.dao.ContactDao;
import ua.com.hav.pb.dao.NotConfiguredException;
import ua.com.hav.pb.dao.UserDao;
import ua.com.hav.pb.dao.file.ContactDaoFileImpl;
import ua.com.hav.pb.dao.file.UserDaoFileImpl;
import ua.com.hav.pb.dao.sql.ContactDaoSQLImpl;
import ua.com.hav.pb.dao.sql.UserDaoSqlImpl;

import javax.sql.DataSource;

/**
 * Created by yulia on 12.07.2017.
 */
@Configuration
public class BeanConfig {


    private static int config;
    private static String userFileName;
    private static String contactFileName;
    private static String dbUser;
    private static String dbPass;
    private static String dbUrl;
    private static boolean isConfigured = false;

    public static synchronized void configure(String userFileName, String contactFileName) {
        if (!isConfigured) {
            BeanConfig.config = 1;
            BeanConfig.userFileName = userFileName;
            BeanConfig.contactFileName = contactFileName;
            isConfigured = true;
        }
    }

    public static synchronized void configure(String user, String pass, String url) {
        if (!isConfigured) {
            BeanConfig.config = 0;
            BeanConfig.dbUser = user;
            BeanConfig.dbPass = pass;
            BeanConfig.dbUrl = url;
            isConfigured = true;
        }
    }

    @Bean
    public FilterRegistrationBean securityFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SecurityFilter());
        registration.addUrlPatterns("/*");
        registration.setName("securityFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(dbUser);
        dataSource.setPassword(dbPass);
        dataSource.setURL(dbUrl);
        return dataSource;
    }

    @Bean
    public UserDao userDao() {
        if (isConfigured) {
            UserDao userDao = null;
            switch (config) {
                case 0: {
                    userDao = new UserDaoSqlImpl();
                    break;
                }
                case 1: {
                    userDao = new UserDaoFileImpl(userFileName);
                    break;
                }
            }
            return userDao;
        } else {
            throw new NotConfiguredException();
        }
    }

    @Bean
    public ContactDao contactDao() {
        if (isConfigured) {
            ContactDao contactDao = null;
            switch (config) {
                case 0: {
                    contactDao = new ContactDaoSQLImpl();
                    break;
                }
                case 1: {
                    contactDao = new ContactDaoFileImpl(contactFileName);
                    break;
                }
            }
            return contactDao;
        } else {
            throw new NotConfiguredException();
        }
    }

}
