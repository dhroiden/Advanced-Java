package trng.imcs.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import trng.imcs.model.Department;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
	
	@Autowired
	DataSource dataSource;
	
	@Override
	public Department getDepById(int id) {
		String selectQuery = "select * from department where depId=?";
		Department department = null;
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = createPreparedStatement(connection, selectQuery, id);
				ResultSet rs = preparedStatement.executeQuery()) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				department = new Department();
				department.setDepId(resultSet.getInt(1));
				department.setDepName(resultSet.getString(2));
			}
		} catch (SQLException e) {
			System.out.println("An unexpected sql exception occured.");
		}
		
		return department;
	}
	
	private PreparedStatement createPreparedStatement(Connection connection, String query,int id) throws SQLException {
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setInt(1, id);
	    return preparedStatement;
	}

}
