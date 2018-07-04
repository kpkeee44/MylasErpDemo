package mylas.com.erp.demo.dao;

import java.util.List;

import mylas.com.erp.demo.Holidays;

public interface HolidayDao {
	
	public void saveHoliday(Holidays hsl);
	public void deleteHoliday(int id);
	public List<Holidays> viewAll();
	public void updateHOliday(int id,Holidays holiday);
	public Holidays getHolidayById(int id);
	

}
