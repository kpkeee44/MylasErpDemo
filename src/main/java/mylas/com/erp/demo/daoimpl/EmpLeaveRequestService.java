package mylas.com.erp.demo.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.springframework.stereotype.Repository;

import mylas.com.erp.demo.appservices.EmailSender;
import mylas.com.erp.demo.appservices.HibernateUtil;
import mylas.com.erp.demo.dao.EmpLeaveRequestDao;
import mylas.com.erp.demo.procedures.EmpLeaveRequestJoin;


@Repository("ers")
public class EmpLeaveRequestService implements EmpLeaveRequestDao {

	EmailSender emailsender = new EmailSender();

	static String emailToRecipient, emailSubject, emailMessage;

	
	
	@Override
	public String saveLeaveRequest(int id,int leavecount,String eid,Date fdate,String lreason,int ltypeid,int mgrid,int lstatusid,Date todate,boolean isactive,int cby,Date cdate,int uby,Date udate) {
	
		System.out.println("fdate");
			try(Session  s=HibernateUtil.getSessionFactory().openSession()){
			System.out.println("inside try");
			
			StoredProcedureQuery query=s.createStoredProcedureQuery("sp_insup_empleavereq");
			query.registerStoredProcedureParameter(1,Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(2,Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(3,String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(4,Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(5,String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(6,Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(7,Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(8,Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(9,Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(10,Boolean.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(11,Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(12,Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(13,Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(14,Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(15,Integer.class, ParameterMode.OUT);
		
			System.out.println("exev proc");
		query.setParameter(1,id);
		query.setParameter(2,leavecount);
		query.setParameter(3,eid);
		query.setParameter(4,fdate);
		query.setParameter(5,lreason);
		query.setParameter(6,ltypeid);
		query.setParameter(7,mgrid);
		query.setParameter(8,lstatusid);
		query.setParameter(9,todate);
		query.setParameter(10,isactive);
		query.setParameter(11,cby);
		query.setParameter(12,cdate);
		query.setParameter(13,uby);
		query.setParameter(14,udate);
		
		query.execute();
		
	  int a=(int) query.getOutputParameterValue(15);
		System.out.println(a);
		System.out.println("after exec");
			}
			catch(Exception e)
			{
				
				return "Leave Request Failed to Send";
			}
		return "Your Leave Request has been sent. Wait for manager Approval";
		}



	@Override
	public List<EmpLeaveRequestJoin> viewAll(int id) {
		System.out.println("entrance");
		List<EmpLeaveRequestJoin> empleavejoin=new ArrayList<>();
		System.out.println("join entry");
			try(Session  session=HibernateUtil.getSessionFactory().openSession()){
				session.beginTransaction();
				CallableStatement cst = null;
		        ResultSet rst = null;
		        SessionImpl sessionImpl = (SessionImpl) session; Connection con = sessionImpl.connection();
		        System.out.println("session open");
		        cst = con.prepareCall("{call sp_viewempleaves(?)}");
		        cst.setInt(1,id);
		        cst.execute();
		        rst = cst.getResultSet();
		        System.out.println(rst);
		        System.out.println("proc call");
		        
		        while(rst.next()) {
		        	System.out.println("entering while");
		        	EmpLeaveRequestJoin empleaves=new EmpLeaveRequestJoin();
		        	empleaves.setId(rst.getInt(1));
		        	empleaves.setLeavecount(rst.getInt(2));
		        	empleaves.setEmployeeid(rst.getString(3));
		        	empleaves.setFromdate(rst.getDate(4));
		        	empleaves.setLeavereason(rst.getString(5));
		        	empleaves.setLeavetypeid(rst.getInt(6));
		        	empleaves.setManagerid(rst.getInt(7));
		        	empleaves.setLeavestatusid(rst.getInt(8));
		        	empleaves.setTodate(rst.getDate(9));
		        	empleaves.setIsactive(rst.getBoolean(10));
		        	empleaves.setCreatedby(rst.getInt(11));
		        	empleaves.setCreateddate(rst.getDate(12));
		        	empleaves.setUpdatedby(rst.getInt(13));
		        	empleaves.setUpdateddate(rst.getDate(14));
		        	empleaves.setLeavetype(rst.getString(15));
		        	empleaves.setLeavestatus(rst.getString(16));
		        	empleaves.setEid(rst.getString(17));
		        	empleavejoin.add(empleaves);
		        }
		        if(!empleavejoin.isEmpty())
					for(EmpLeaveRequestJoin elrj:empleavejoin)
					{
						System.out.println(elrj.getEmployeeid());
					}
					else {
						System.out.println("nodata");
					}
			}catch(Exception e) {
				System.out.println(e);
			}
				return empleavejoin;
				
	}



	/*@Override
	public void save(TblEmpLeavereq empleave) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(empleave);
		System.out.println("Request Saved");
		session.getTransaction().commit();
	}

	@Override
	public List<TblEmpLeavereq> view() {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from TblEmpLeavereq");
		List<TblEmpLeavereq> empleave = q.list();
		System.out.println("view Called");
		session.getTransaction().commit();
		return (empleave);
	}

	@Override
	public List<TblEmpLeavereq> viewbyid(String empid) {
		String sqlquery = "FROM TblEmpLeavereq WHERE employeeid ='"+empid+"'";
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<TblEmpLeavereq> map = null;
		Query query = session.createQuery(sqlquery);
		List<TblEmpLeavereq> leaves = query.list();
		session.getTransaction().commit();
		return leaves;

	}
	public List<TblEmpLeavereq> viewbyManagerid(String mgrid){
		String sqlquery = "SELECT * FROM tbl_emp_leavereq WHERE managerid ='"+mgrid+"'";
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<TblEmpLeavereq> map = null;

		SQLQuery query = session.createSQLQuery(sqlquery);
		query.addEntity(TblEmpLeavereq.class);
		List<TblEmpLeavereq> leaves = query.list();
		session.getTransaction().commit();
		return leaves;

	}
	public List<TblEmpLeavereq> viewbyStatusid(Boolean statusid){
		String sqlquery = "SELECT * FROM tbl_emp_leavereq WHERE status ='"+statusid+"'";
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<TblEmpLeavereq> map = null;

		SQLQuery query = session.createSQLQuery(sqlquery);
		query.addEntity(TblEmpLeavereq.class);
		List<TblEmpLeavereq> leaves = query.list();
		System.out.println("list according to status");
		session.getTransaction().commit();
		return leaves;

	}

	@Override
	public void edit() {
		// TODO Auto-generated method stub

	}

	@Override
	public TblEmpLeavereq view(int id) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TblEmpLeavereq empleave = session.load(TblEmpLeavereq.class, id);
		session.getTransaction().commit();
		return empleave;
	}

	@Override
	public String delete(int id) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TblEmpLeavereq empleave = session.load(TblEmpLeavereq.class, id);
		session.delete(empleave);
		System.out.println("Object Deleted successfully.....!!");
		session.getTransaction().commit();
		return "Deleted_Entry!";
	}

	@Override
	public String update(int id, String reason, boolean status) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TblEmpLeavereq empleave = session.load(TblEmpLeavereq.class, id);
		empleave.setReason(reason);
		empleave.setStatus(status);
		session.update(empleave);
		session.getTransaction().commit();
		System.out.println("updated table");
		return "Request";
	}

	@Override
	public String updatetransManager(int id,String transmanid) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TblEmpLeavereq employe = session.load(TblEmpLeavereq.class, id);
		employe.setMantrans(transmanid);
		try {
			session.update(employe);session.getTransaction().commit();return "Updated";
		}catch(Exception e){			session.getTransaction().rollback();
return "error occured while updating";}

	}

	@Override
	public String ChangeManager(int id) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TblEmpLeavereq employe = session.load(TblEmpLeavereq.class, id);
		employe.setMantrans(null);
		try {
			session.update(employe);session.getTransaction().commit();return "Updated";
		}catch(Exception e){			session.getTransaction().rollback();
return "error occured while updating";}

	}

	@Override
	public int countEmployee(String manid) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		  session.beginTransaction();
		  Criteria crit = session.createCriteria(TblEmpLeavereq.class);
		  crit.add( Restrictions.isNull("status"));
		  crit.add(Restrictions.ilike("managerid", manid));
		  List<TblEmpLeavereq> emps = crit.list();
		  session.getTransaction().commit();
		return emps.size();
	}

	@Override
	public List<TblEmpLeavereq> viewSearch(String firstname, String lastname, String month, String status) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = null;
		if(firstname!="" && lastname!="" && month!="" && status!="") {

			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpLeavereq where employeeid IN (select eid from EmpDetails where fname='"+firstname+"' AND lname='"+lastname+"') AND fromdate LIKE '%-"+month+"-%' AND status="+status);
			}else {
				q=session.createQuery("from TblEmpLeavereq where  employeeid IN (select eid from EmpDetails where fname='"+firstname+"' AND lname='"+lastname+"') AND fromdate LIKE '%-"+month+"-%' AND status is null");
			}
		}
		else if(firstname!="" && lastname!="" && month!="") {

				q=session.createQuery("from TblEmpLeavereq where employeeid IN (select eid from EmpDetails where fname='"+firstname+"' AND lname='"+lastname+"') AND fromdate LIKE '%-"+month+"-%'");
			}
		
		else if(firstname!="" && lastname!="" && status!="") {

			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpLeavereq where employeeid IN (select eid from EmpDetails where fname='"+firstname+"' AND lname='"+lastname+"') AND status="+status);
			}else {
				q=session.createQuery("from TblEmpLeavereq where employeeid IN (select eid from EmpDetails where fname='"+firstname+"' AND lname='"+lastname+"') AND status is null");
			}
		}
		else if(firstname!="" && month!="" && status!="") {

			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpLeavereq where  employeeid IN (select eid from EmpDetails where fname='"+firstname+"') AND fromdate LIKE '%-"+month+"-%' AND status="+status);
			}else {
				q=session.createQuery("from TblEmpLeavereq where employeeid IN (select eid from EmpDetails where fname='"+firstname+"') AND fromdate LIKE '%-"+month+"-%' AND status is null");
			}
		}
		else if(lastname!="" && month!="" && status!="") {

			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpLeavereq where  employeeid IN (select eid from EmpDetails where lname='"+lastname+"') AND fromdate LIKE '%-"+month+"-%' AND status="+status);
			}else {
				q=session.createQuery("from TblEmpLeavereq where employeeid IN (select eid from EmpDetails where lname='"+lastname+"') AND fromdate LIKE '%-"+month+"-%' AND status is null");
			}
		}
		else if(firstname!="" && month!="") {
			q=session.createQuery("from TblEmpLeavereq where employeeid IN (select eid from EmpDetails where fname='"+firstname+"') AND fromdate LIKE '%-"+month+"-%'");
		}
		else if(firstname!="" && lastname!="") {
			q=session.createQuery("from TblEmpLeavereq where employeeid IN (select eid from EmpDetails where fname='"+firstname+"' AND lname='"+lastname+"')");
		}
		else if(firstname!="" && status!="") {
			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpLeavereq where employeeid IN (select eid from EmpDetails where fname='"+firstname+"') AND status="+status);
			}else {
				q=session.createQuery("from TblEmpLeavereq where employeeid IN (select eid from EmpDetails where fname='"+firstname+"') AND status is null");
			}
		}
		else if(lastname!="" && month!="") {
			q=session.createQuery("from TblEmpLeavereq where employeeid IN (select eid from EmpDetails where lname='"+lastname+"') AND fromdate LIKE '%-"+month+"-%'");
		}
		else if(lastname!="" && status!="") {
			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpLeavereq where employeeid IN (select eid from EmpDetails where lname='"+lastname+"') AND status="+status);
			}else {
				q=session.createQuery("from TblEmpLeavereq where employeeid IN (select eid from EmpDetails where lname='"+lastname+"') AND status is null");
			}
		}
		else if(month!="" && status!="") {
			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpLeavereq where fromdate LIKE '%-"+month+"-%' AND status="+status);
			}else {
				q=session.createQuery("from TblEmpLeavereq where fromdate LIKE '%-"+month+"-%' AND status is null");
			}

		}
		else if(firstname!="")
		{

			q = session.createQuery("from TblEmpLeavereq where employeeid IN (select eid from EmpDetails where fname='"+firstname+"')");	
		}
		else if(lastname!="")
		{

			q = session.createQuery("from TblEmpLeavereq where employeeid IN (select eid from EmpDetails where lname='"+lastname+"')");	
		}
		else if(month!="")
		{

			q = session.createQuery("from TblEmpLeavereq where fromdate LIKE '%-"+month+"-%'");	
		}
		else if(status!="")
		{
			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpLeavereq where status="+status);
			}else {
				q=session.createQuery("from TblEmpLeavereq where status is null");
			}
		}
		List<TblEmpLeavereq> empleave = q.list();
		
		session.getTransaction().commit();
		return (empleave);
	}

	@Override
	public List<TblEmpLeavereq> empLeaveSearch(String uname, String month, String status) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = null;
		if(uname!="" && month!="" && status!="") {

			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpLeavereq where employeeid='"+uname+"'AND fromdate LIKE '%-"+month+"-%' AND status="+status);
			}else {
				q=session.createQuery("from TblEmpLeavereq where employeeid='"+uname+"'AND fromdate LIKE '%-"+month+"-%' AND status is null");
			}
		}
		else if(uname!="" && month!="") {
			q=session.createQuery("from TblEmpLeavereq where employeeid='"+uname+"'AND fromdate LIKE '%-"+month+"-%'");
		}
		else if(uname!="" && status!="") {
			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpLeavereq where employeeid='"+uname+"'AND status="+status);
			}else {
				q=session.createQuery("from TblEmpLeavereq where employeeid='"+uname+"'AND status is null");
			}
		}
		else if(month!="" && status!="") {
			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpLeavereq where fromdate LIKE '%-"+month+"-%' AND status="+status);
			}else {
				q=session.createQuery("from TblEmpLeavereq where fromdate LIKE '%-"+month+"-%' AND status is null");
			}

		}
		else if(uname!="")
		{

			q = session.createQuery("from TblEmpLeavereq where employeeid='"+uname+"'");	
		}
		else if(month!="")
		{

			q = session.createQuery("from TblEmpLeavereq where fromdate LIKE '%-"+month+"-%'");	
		}
		else if(status!="")
		{
			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpLeavereq where status="+status);
			}else {
				q=session.createQuery("from TblEmpLeavereq where status is null");
			}
		}
		List<TblEmpLeavereq> empleave = q.list();

		session.getTransaction().commit();
		return (empleave);	
	}
	@Override
	public TblEmpLeavereq getById(int id) {
		TblEmpLeavereq user;
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		user =  session.get(TblEmpLeavereq.class, id);
		session.getTransaction().commit();
		return user;

}


	@Override
	public String updateLeave(int id, String leavetype, String fdate, String tdate, String reasion,int count,
			String status) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TblEmpLeavereq update= session.load(TblEmpLeavereq.class, id);
		update.setReferenceid(0);
		TblEmpLeavereq newLeaveEntryByManager = new TblEmpLeavereq();
		newLeaveEntryByManager.setEmployeeid(update.getEmployeeid());
		newLeaveEntryByManager.setLeavetype(leavetype);newLeaveEntryByManager.setFromdate(fdate);newLeaveEntryByManager.setTodate(tdate);newLeaveEntryByManager.setLeavereason(reasion);
		newLeaveEntryByManager.setCount(count);newLeaveEntryByManager.setReferenceid(id);newLeaveEntryByManager.setStatus(true);
		newLeaveEntryByManager.setManagerid(update.getManagerid());newLeaveEntryByManager.setReason(status);
		try {
			session.update(update);
			session.save(newLeaveEntryByManager);session.getTransaction().commit();return "Updated";
		}catch(Exception e){
			
			
			session.getTransaction().rollback();
return "error occured while updating";}
		
		
	}

	@Override
	public void EmailSend(int id, int count) {
			// TODO Auto-generated method stub
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TblEmpLeavereq employe = session.load(TblEmpLeavereq.class, id);
		count=count-1;
		employe.setDaycount(count);
		try {
			session.update(employe);session.getTransaction().commit();
		}catch(Exception e){session.getTransaction().rollback();}
			emailSubject = "New Time Sheet For:";
			emailMessage = "A Remaindere Request For Approval has Been Sent to :"+employe.getFromdate()+"to"+employe.getTodate()+"On: "+new Date();
			emailToRecipient ="kpraveen@mylastech.com";
			//System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
			emailsender.javaMailService("bgrao@mylastech.com", "Bganga@07", emailToRecipient, emailMessage, emailSubject);
			System.out.println("send mail");	
	}
	@Override
	public int[] countSum(String eid) {
		
		int countcasual=0,countmedical=0,countsick=0;
		List<TblEmpLeavereq> empleave=this.view();
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q =session.createQuery("from TblEmpLeavereq");
		List<TblEmpLeavereq> empleave = q.list();
		
		for(TblEmpLeavereq  tbl:empleave)
		{
					if(tbl.getLeavetype().equals("Medical Leave") &&tbl.getEmployeeid().equals(eid) ) {		
						countmedical=countmedical+tbl.getCount();}
					else if(tbl.getLeavetype().equals("Casual Leave") &&tbl.getEmployeeid().equals(eid))	
				countcasual=countcasual+tbl.getCount();
					else if(tbl.getLeavetype().equals("Loss of Pay") &&tbl.getEmployeeid().equals(eid))	
						countsick=countsick+tbl.getCount();
		}
		 int[] sum = new int[]{countmedical,countcasual,countsick}; 
		//countleave[0]=countmedical;countleave[1]=countcasual;
		System.out.println(countmedical);System.out.println(countcasual);
		session.getTransaction().commit();
	return sum;	
	}
	
	
	public Map count() {
		Map leavecount = new HashMap();
		List<TblEmpLeavereq> empleave=this.view();
		LeaveManiplicatiionImpl leave = new LeaveManiplicatiionImpl();
		List<Tblleaves> leavesCount = leave.getDetailsofleavetye();
		for(Tblleaves leavename:  leavesCount) {
			int count= 0;
		for(TblEmpLeavereq leaves: empleave) {
			
			if(leaves.getLeavetype().equals(leavename.getLeavetype())) {
				count= count + leaves.getCount();
			}
			leavecount.put(leavename.getLeavetype(), count);	
			}
		}
		return leavecount;
	}

	@Override
	public Map count(String eid) {
		Map leavecount = new HashMap();
		List<TblEmpLeavereq> empleave=this.view();
		LeaveManiplicatiionImpl leave = new LeaveManiplicatiionImpl();
		List<Tblleaves> leavesCount = leave.getDetailsofleavetye();
		for(Tblleaves leavename:  leavesCount) {
			int count= 0;
		for(TblEmpLeavereq leaves: empleave) {
			if(leaves.getStatus()!=null)
				if(leaves.getStatus())
			if(leaves.getLeavetype().equals(leavename.getLeavetype())) {
				if(leaves.getEmployeeid().equals(eid))
					if(leaves.getStatus())
				count= count + leaves.getCount();
			}
			leavecount.put(leavename.getLeavetype(), count);	
			}
		}
		
		System.out.println(leavecount);
		System.out.println("hi");
		return leavecount;
	}*/
	
}
