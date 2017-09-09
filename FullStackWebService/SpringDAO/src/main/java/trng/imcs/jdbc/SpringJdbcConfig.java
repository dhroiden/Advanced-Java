package trng.imcs.jdbc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SpringJdbcConfig {

	@Bean
	public DataSource dataSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        //Read properties file
        Properties credentialsProps = null;
		boolean error = true;
		try {
			credentialsProps = new Properties();
			InputStream stream = getClass().getClassLoader().getResourceAsStream("credentials.properties");

			if (stream == null) {
				System.out.println("Error in loading the credentials for JDBC, the stream is null");
				return null;
			}
			credentialsProps.load(stream);
			error = false;
		} catch (FileNotFoundException e1) {
			System.out.println("Error in loading the credentials for JDBC, the file has not been found");
		} catch (IOException e1) {
			System.out.println("Error in loading the credentials for JDBC,"
					+ " credentials.properties file with jdbc credentials in the following foramt is required \n"
					+ "userName=userName\npassword=password");
		}

		if (error) {
			return null;
		}
		
        //MySQL database we are using
        dataSource.setDriverClassName(credentialsProps.getProperty("driver.name"));
        dataSource.setUrl(credentialsProps.getProperty("connectionUrl"));
        dataSource.setUsername(credentialsProps.getProperty("userName"));
        dataSource.setPassword(credentialsProps.getProperty("password"));
        
        return dataSource;
    }
	
	/*@Bean
	public EmployeeDao employeeDao() {
		EmployeeDaoImpl empDaoImpl = new EmployeeDaoImpl();
		empDaoImpl.springJdbcConfig = new SpringJdbcConfig();
		return empDaoImpl;
	}
	
	@Bean
	public DepartmentDao departmentDao() {
		DepartmentDaoImpl depDaoImpl = new DepartmentDaoImpl();
		depDaoImpl.springJdbcConfig = new SpringJdbcConfig();
		return depDaoImpl;
	}*/
	
}
