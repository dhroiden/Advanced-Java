package trng.imcs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import trng.imcs.jdbc.*;
import trng.imcs.model.Bonus;
import trng.imcs.dao.BonusDAO;;

public class BonusDAOImplementation implements BonusDAO {
	final Logger logger = Logger.getLogger(BonusDAOImplementation.class);

	public void add(List<Bonus> bonusList) {

		logger.info("adding bonus");

		final String sql = "insert into bonus (depNo, amount, remainingAmount) values (?, ?, ?)";
		try (Connection con = JdbcConnectionFactory.getConnection();
				Statement st = con.createStatement();
				PreparedStatement preparedStatement = con.prepareStatement(sql)) {

			con.setAutoCommit(false);
			st.execute("truncate employeebonus");
			st.execute("truncate bonus");
			int count = 1;
			boolean hasLeftOverBatchRecords = true;
			for (Bonus b : bonusList) {

				if (b == null)
					break;

				preparedStatement.setInt(1, b.getDepNo());
				preparedStatement.setFloat(2, b.getAmount());
				preparedStatement.setFloat(3, b.getRemainingAmount());

				preparedStatement.addBatch();

				if (count++ % 3 == 0) {
					int[] updateCount = preparedStatement.executeBatch();
					hasLeftOverBatchRecords = false;
				} else {
					hasLeftOverBatchRecords = true;
				}
			}

			if (hasLeftOverBatchRecords) {
				int[] updateCount = preparedStatement.executeBatch();
			}

			con.commit();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

	}

	public List<Bonus> getBonus() throws SQLException {
		logger.info("getting Bonus from DB");
		List<Bonus> bonusList = new ArrayList<Bonus>();
		ResultSet rs = null;
		String sql = "select depNo, amount, remainingAmount from bonus";
		try (Connection con = JdbcConnectionFactory.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			rs = st.executeQuery();
			while (rs.next()) {
				Bonus e = new Bonus();
				e.setDepNo(rs.getInt(1));
				e.setAmount(rs.getFloat(2));
				e.setRemainingAmount(rs.getFloat(3));
				bonusList.add(e);
			}
		} finally {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
		return bonusList;
	}
}
