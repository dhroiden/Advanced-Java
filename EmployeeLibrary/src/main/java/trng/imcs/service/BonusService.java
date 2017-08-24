package trng.imcs.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import trng.imcs.model.*;

public interface BonusService {
	public List<Bonus> loadFromFile() throws FileNotFoundException, IOException, ParseException;
	public void allocateBonus() throws SQLException;
	public void allocatingBonus(Bonus b, Employee e) throws SQLException;
}
