package trng.imcs.dao;

import java.sql.SQLException;
import java.util.List;

import trng.imcs.model.*;

public interface BonusDAO {
	public void add(List<Bonus> bonusList) throws SQLException;
	public List<Bonus> getBonus() throws SQLException;
}
