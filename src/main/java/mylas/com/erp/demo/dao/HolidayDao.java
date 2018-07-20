package mylas.com.erp.demo.dao;

import java.util.Date;
import java.util.List;

import mylas.com.erp.demo.TblHolidays;

public interface HolidayDao {
	
	public String saveHoliday(int id,String name,String dt,boolean active,int eid,Date cdt,int upby,Date update);
	/*public void deleteHoliday(int id);

	public String updateHOliday(int id,Holidays holiday);*/
	public TblHolidays getHolidayById(int id);
	
	public List<TblHolidays> viewAll();
}
