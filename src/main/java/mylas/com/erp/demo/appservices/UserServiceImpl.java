package mylas.com.erp.demo.appservices;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mylas.com.erp.demo.EmpDetails;
import mylas.com.erp.demo.dao.EmployeeDao;
import mylas.com.erp.demo.exceptions.UserBlockedException;
import mylas.com.erp.demo.operations.LoginOperations;
import mylas.com.erp.demo.service.Client;


public class UserServiceImpl implements User {

	

	Client cl = new Client();

	@Override
	public void Register(EmpDetails emp) {
		cl.getConnection(emp);

	}

	@Override
	public List<EmpDetails> Login(String loginName, String Password) throws UserBlockedException {
		String sqlquery = "SELECT uname, fname, lname, phone, email, role, login_status FROM emp_details WHERE uname='"+loginName+"' and pswd='"+Password+"'";
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
	
		session.beginTransaction();
		Map map = null;
		try {
			SQLQuery query = session.createSQLQuery(sqlquery);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<EmpDetails> user = query.list();
			session.getTransaction().commit();
			for(Object usr : user) {
				map = (Map) usr;
			}
			if(map.get("login_status").equals(User.Login_Status_Blocked)) {

				throw new UserBlockedException("Your Account has been Blocked. Contact the Admin");					
			}else {
				return user;
			}



		} catch (NullPointerException e) {
			session.getTransaction().rollback();
			return null;
		}


	}

	@Override
	public List<EmpDetails> getEmpList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAllAttendanceList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void applyLeave() {
		// TODO Auto-generated method stub

	}

	@Override
	public void approveLeave(int empid, boolean leavestatus) {
		// TODO Auto-generated method stub

	}

}
