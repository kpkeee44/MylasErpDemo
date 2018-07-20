package mylas.com.erp.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import mylas.com.erp.demo.EmpDetails;
import mylas.com.erp.demo.LeaveAddition;
import mylas.com.erp.demo.TblDepartment;
import mylas.com.erp.demo.TblDesignation;
//import mylas.com.erp.demo.TblEmpAttendanceNew;
import mylas.com.erp.demo.TblEmpLeavereq;
import mylas.com.erp.demo.TblManRoleTransfer;
import mylas.com.erp.demo.Tblleaves;
import mylas.com.erp.demo.appservices.EmailSender;
import mylas.com.erp.demo.appservices.UserServiceImpl;
import mylas.com.erp.demo.dao.DepartmentDao;
import mylas.com.erp.demo.dao.DesignationDao;
import mylas.com.erp.demo.dao.EmpAttendenceDao;
import mylas.com.erp.demo.dao.EmpLeaveRequestDao;
import mylas.com.erp.demo.dao.EmployeeDao;
import mylas.com.erp.demo.dao.LeaveManiplication;
import mylas.com.erp.demo.dao.ManagerServicesDao;
import mylas.com.erp.demo.dao.RoleTrasforDao;
import mylas.com.erp.demo.service.Client;

@Controller
public class ManagerPageController {

	@Autowired
	ManagerServicesDao mandao;
	
	@Autowired
	RoleTrasforDao roleTransfer;

	@Autowired
	EmployeeDao userDetails;

	@Autowired
	DesignationDao designationImpl;

	@Autowired
	DepartmentDao deptdao;

	@Autowired
	EmpAttendenceDao attimpl;

	@Autowired
	EmpLeaveRequestDao ers;
	
	@Autowired
	LeaveManiplication leave;



	EmailSender emailsender = new EmailSender();

	static String emailToRecipient, emailSubject, emailMessage;
   
	Client c = new Client();
    
/*	@RequestMapping(value= "/manager/leave/register")
	public ModelAndView empLeavePage(HttpSession session) {
		ModelAndView mav = new ModelAndView("empleaverequests");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		mav.addObject("Role",role);
		List<TblEmpLeavereq> allempleave = ers.view();
		List<TblEmpLeavereq> leavereq =  ers.viewbyid(user.getEid());
		int count = ers.countEmployee(user.getEid()) + attimpl.countEmployee(user.getEid());
		List<EmpDetails> emp1 = userDetails.getDetails();
		List<TblEmpAttendanceNew> empattendances =  attimpl.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("User", user);
		mav.addObject("empleave", leavereq);
		mav.addObject("manservices", mandao.list());
		mav.addObject("employees", emp1);
		mav.addObject("count",count);
		List<LeaveAddition> numofleaves=leave.getDetailsofleavetye();
		mav.addObject("nleave",numofleaves);
		return mav;
	}
*/
/*	@RequestMapping(value="/manager/allemp/register")
	public ModelAndView allEmpPage() {
		ModelAndView mav = new ModelAndView("employees");
		mav.addObject("manservices", mandao.list());	
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		List<TblDesignation> depts = designationImpl.getDetails();
		List<TblDepartment> deptList = deptdao.getDetails();
		mav.addObject("designations", depts);
		mav.addObject("departments", deptList);
		String role = user.getRole();
		mav.addObject("Role",role);
		mav.addObject("User",user);
		String mesg = "hi";
		List<EmpDetails> emp1 = userDetails.getDetails();
		List<TblEmpLeavereq> allempleave = ers.view();
		int count = ers.countEmployee(user.getEid()) + attimpl.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  attimpl.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("employees", emp1);
		mav.addObject("dupmsg", mesg);
		mav.addObject("User",user);
		mav.addObject("employees", emp1);
		return mav;		
	}*/
	/*@RequestMapping(value="/manager/allemp/register", method=RequestMethod.POST)
	public ModelAndView saveEmpPage(HttpServletRequest request, HttpServletResponse response) throws ConstraintViolationException{

		EmpDetails emp = new EmpDetails(null, request.getParameter("cpswd"), null, request.getParameter("empid"), request.getParameter("email"), request.getParameter("firstname1"), null, request.getParameter("lastname1"), false, null, request.getParameter("pswd"), null, request.getParameter("uname"), null,null,null,null);

		emp.setLoginStatus(UserServiceImpl.Login_Status_Active);
		emp.setRole("EMPLOYEE_ROLE");


		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		emp.setManagerid(user.getEid());
		emp.setJdate(request.getParameter("joindate"));
		emp.setPhone((request.getParameter("phone")));
		emp.setCompName(user.getCompName());
		emp.setDepartment(user.getDepartment());
		emp.setDesignation(request.getParameter("designation"));
		ModelAndView mav = new ModelAndView("employees");
		mav.addObject("manservices", mandao.list());
		System.out.println("before getconn");
		String mesg = "hi";


		mav.addObject("dupmsg", mesg);
		List<TblDesignation> depts = designationImpl.getDetails();
		mesg = userDetails.getConnection(emp);
		List<EmpDetails> emp1 = userDetails.getDetails();
		List<TblEmpLeavereq> allempleave = ers.view();
		int count = ers.countEmployee(user.getEid()) + attimpl.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  attimpl.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("designations", depts);
		String role = user.getRole();
		mav.addObject("Role",role);
		mav.addObject("employees", emp1);
		mav.addObject("User",user);
		mav.addObject("employee", emp);

		return mav;		
	}*/
/*
	@RequestMapping(value="/manager/allemp/register/{id}/employeedetails")
	public ModelAndView eachEmpDetailsPage(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("employeedetails");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		String role = user.getRole();
		EmpDetails Edetails = null;
		Edetails = userDetails.getById(id);
		List<EmpDetails> emp1 = userDetails.getDetails();
		List<TblEmpLeavereq> allempleave = ers.view();
		int count = ers.countEmployee(user.getEid()) + attimpl.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  attimpl.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("empID", id);
		mav.addObject("Role",role);
		mav.addObject("employee",Edetails);
		mav.addObject("manservices", mandao.list());
		mav.addObject("employees", emp1);
		return mav;		
	}
*/
	/*
	 * Post Method Handelling method
	 */
/*	@RequestMapping(value= "/manager/leave/register", method=RequestMethod.POST)
	public ModelAndView manLeaveRequestPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {


		ModelAndView mav = new ModelAndView("empleaverequests");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		mav.addObject("Role",role);

		*//**
		 * Handling request from empleaverequests.jsp form with post action.
		 *//*

		String fromdate = request.getParameter("fromdate");
		//String[] date = fromdate.split("-");
		String todate = request.getParameter("todate");
		SimpleDateFormat formatfromdate = new SimpleDateFormat("yyyy-mm-dd");
		Date reqfromDate = null;
		Date reqtoDate = null;
		try {
			reqfromDate = formatfromdate.parse(fromdate);
			reqtoDate = formatfromdate.parse(todate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LocalDate Day1 = LocalDate.parse(fromdate);
		LocalDate Day2 = LocalDate.parse(todate);
		LocalDate Day3=new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		long daysNegative = ChronoUnit.DAYS.between(Day1, Day2);
		long daysNegative1 = ChronoUnit.DAYS.between(Day3, Day1);
		System.out.println(daysNegative1);
		List<EmpDetails> emp1 = userDetails.getDetails();

		mav.addObject("User", user);
		mav.addObject("manservices", mandao.list());	
		mav.addObject("employees", emp1);
		int dayCount;
		if(daysNegative1>3)
			dayCount=3;
		else
			dayCount=(int) (daysNegative1-1);
		TblEmpLeavereq empleave = new TblEmpLeavereq((int)daysNegative,null, request.getParameter("fromdate"),request.getParameter("leavereason"), request.getParameter("leavetype"), null, null,  request.getParameter("todate"),null,null,null,dayCount);


		//empleave.setManagerid(user.getManagerid());
		empleave.setEmployeeid(user.getEid());
		empleave.setStatus(null);
		try {
			ers.save(empleave);	
			List<TblEmpLeavereq> leavereq =  ers.viewbyid(user.getEid());
			mav.addObject("empleave", leavereq);
			System.out.println("Req Sent to Save");
			mav.addObject("Submitmsg", "Your Leave Request Has Been Submitted Sucessfully! Please Wait for your Manager Approval");
			
			emailSubject = "New Leave Request For:";
			emailMessage = "A new Leave Request For Approval has Been Sent to :"+"On: "+new Date()+"\n"+"\n Employee Name:  "+user.getEmplfirstname()+" "+user.getEmpllastname()+"\n Time Sheet request for the Month:  "+request.getParameter("month")+"("+request.getParameter("year")+")";
			emailToRecipient = c.getMail(user.getCreatedby());
			//System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
			emailsender.javaMailService("bgrao@mylastech.com", "Bganga@07", emailToRecipient, emailMessage, emailSubject);
			System.out.println("send mail");
		
			
					
			List<TblEmpLeavereq> allempleave = ers.view();
			int count = ers.countEmployee(user.getEid()) + attimpl.countEmployee(user.getEid());
			List<TblEmpAttendanceNew> empattendances =  attimpl.getDetails();
			mav.addObject("empattendances",empattendances);
			mav.addObject("allempleave", allempleave);
			mav.addObject("count",count);
			List<LeaveAddition> numofleaves=leave.getDetailsofleavetye();
			mav.addObject("nleave",numofleaves);
			return mav;
		}catch(DateTimeParseException e){
			mav.addObject("errmsg", "Please enter the required fields");
			return mav;
		}

	}*/

/*	@RequestMapping(value= "/manager/profile/register")
	public ModelAndView empProfilePage() {
		ModelAndView mav = new ModelAndView("useremployee");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		mav.addObject("Role",role);
		mav.addObject("User", user);
		int a[]=ers.countSum(user.getEid());
		mav.addObject("manservices", mandao.list());	
		List<TblEmpLeavereq> allempleave = ers.view();
		int count = ers.countEmployee(user.getEid()) + attimpl.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  attimpl.getDetails();
		List<LeaveAddition> numofleaves=leave.getDetailsofleavetye();
		Map<String,Integer> using=ers.count(user.getEid());
		Map<String,Integer> pending=new HashMap<>();
		Set<String> keys = using.keySet();
		Iterator itr = keys.iterator();
		int pleave=0;
		for(Tblleaves li:numofleaves) {
			System.out.println(li.getLeavetype());
			System.out.println(using);
			while(itr.hasNext())
	        {System.out.println("comes");
	        System.out.println(li.getLeavetype());
	       
	        String key=(String) itr.next();
	        System.out.println(key);
		if(li.getLeavetype().equals(key) ) {
			System.out.println(li.getNumleavedays());
			pleave=li.getNumleavedays()-using.get(key);
			pending.put(li.getLeavetype(), pleave);
			
			System.out.println(pending);}
	        }
			itr = keys.iterator();
			System.out.println("hai");
			}
		System.out.println(pending);
		mav.addObject("pleave",pending);
		mav.addObject("using",using);
		mav.addObject("nleave",numofleaves);
		mav.addObject("medical",a[0]);
		mav.addObject("casual",a[1]);
		mav.addObject("sick",a[2]);
		mav.addObject("pmedical",10-a[0]);
		mav.addObject("pcasual",10-a[1]);
		mav.addObject("psick",10-a[2]);
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		return mav;
	}*/

/*	@RequestMapping(value= "/manager/timesheet/register")
	public ModelAndView indvidtimesheet(HttpSession session) {
		ModelAndView mav = new ModelAndView("emptimesheet");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		mav.addObject("Role",role);

		List<TblEmpAttendanceNew> attendances =  attimpl.viewbyid(user.getEid());
		List<EmpDetails> emp1 = userDetails.getDetails();
		List<TblEmpLeavereq> allempleave = ers.view();
		int count = ers.countEmployee(user.getEid()) + attimpl.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  attimpl.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("attendancelist",attendances);
		mav.addObject("manservices", mandao.list());	
		mav.addObject("User", user);
		mav.addObject("employees", emp1);
		return mav;
	}*/
/*	@RequestMapping(value= "/manager/timesheet/register/{id}")
	public ModelAndView indvidtimesheets(HttpSession session,@PathVariable("id") String id) {

		ModelAndView mav = new ModelAndView("emptimesheet");

		int l=id.length();
		int root=Integer.parseInt(id.substring(0,2));
		String month=id.substring(2,(l-4));
		String year=id.substring((l-4),l);

		mav.addObject("root",root);
		mav.addObject("month",month);
		mav.addObject("year",year);

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		List<TblEmpAttendanceNew> attendances =  attimpl.viewbyid(user.getEid());
		List<TblEmpLeavereq> allempleave = ers.view();
		int count = ers.countEmployee(user.getEid()) + attimpl.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  attimpl.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("attendancelist",attendances);
		mav.addObject("manservices", mandao.list());
		mav.addObject("Role",role);
		mav.addObject("User", user);

		return mav;
	}
	*/
	@RequestMapping(value= "/manager/timesheet/register", method=RequestMethod.POST)
	public ModelAndView timesheetSave(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("emptimesheet");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		mav.addObject("Role",role);

		/*TblEmpAttendanceNew attedance = new TblEmpAttendanceNew(null, null, null, null, Integer.parseInt(request.getParameter("day1")), Integer.parseInt(request.getParameter("day2")), Integer.parseInt(request.getParameter("day3")), Integer.parseInt(request.getParameter("day4")), Integer.parseInt(request.getParameter("day5")), Integer.parseInt(request.getParameter("day6")), Integer.parseInt(request.getParameter("day7")), Integer.parseInt(request.getParameter("day8")), Integer.parseInt(request.getParameter("day9")), Integer.parseInt(request.getParameter("day10")), Integer.parseInt(request.getParameter("day11")), Integer.parseInt(request.getParameter("day12")), Integer.parseInt(request.getParameter("day13")), Integer.parseInt(request.getParameter("day14")), Integer.parseInt(request.getParameter("day15")), Integer.parseInt(request.getParameter("day16")), Integer.parseInt(request.getParameter("day17")), Integer.parseInt(request.getParameter("day18")), Integer.parseInt(request.getParameter("day19")), Integer.parseInt(request.getParameter("day20")), Integer.parseInt(request.getParameter("day21")), Integer.parseInt(request.getParameter("day22")), Integer.parseInt(request.getParameter("day23")), Integer.parseInt(request.getParameter("day24")), Integer.parseInt(request.getParameter("day25")), Integer.parseInt(request.getParameter("day26")), Integer.parseInt(request.getParameter("day27")), Integer.parseInt(request.getParameter("day28")), null, null, null, null, null,null);
		attedance.setEmpid(user.getEid());
		//attedance.setManagerid(user.getManagerid());
		attedance.setStatas(null);
		attedance.setMonth(request.getParameter("month"));
		attedance.setYear(Integer.parseInt(request.getParameter("year")));
		if(attedance.getMonth().equals("January")||attedance.getMonth().equals("March")||attedance.getMonth().equals("May")||attedance.getMonth().equals("July")||attedance.getMonth().equals("August")||attedance.getMonth().equals("October")||attedance.getMonth().equals("December")) {
			attedance.setDay29(Integer.parseInt(request.getParameter("day29")));
			attedance.setDay30(Integer.parseInt(request.getParameter("day30")));
			attedance.setDay31(Integer.parseInt(request.getParameter("day31")));
		}else if(attedance.getMonth().equals("April")||attedance.getMonth().equals("June")||attedance.getMonth().equals("September")||attedance.getMonth().equals("November")) {
			attedance.setDay29(Integer.parseInt(request.getParameter("day29")));
			attedance.setDay30(Integer.parseInt(request.getParameter("day30")));
		}else if(attedance.getMonth().equals("Febraury") && (attedance.getYear()%4==0)) {
			attedance.setDay29(Integer.parseInt(request.getParameter("day29")));
		}*/
		/*emailSubject = "New Time Sheet For:"+attedance.getMonth()+" "+attedance.getYear()+"";
		emailMessage = "A new Time Sheet For Approval has Been Sent to :"+attedance.getManagerid()+"On: "+new Date();
		emailToRecipient = "krishnavarma.java@gmail.com";
		System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
		//emailsender.javaMailService("bojagangadhar@gmail.com", "14131f0008", emailToRecipient, emailMessage, emailSubject);
*/		//attimpl.save(attedance);
	/*	List<TblEmpAttendanceNew> attendances =  attimpl.viewbyid(user.getEid());
		System.out.println(attendances);*/
		//List<TblEmpLeavereq> allempleave = ers.view();
		//int count = ers.countEmployee(user.getEid()) + attimpl.countEmployee(user.getEid());
	//	List<TblEmpAttendanceNew> empattendances =  attimpl.getDetails();
		
		
			
		//mav.addObject("empattendances",empattendances);
	//	mav.addObject("allempleave", allempleave);
	//	mav.addObject("count",count);
		//mav.addObject("attendancelist",attendances);
		mav.addObject("manservices", mandao.list());
		mav.addObject("User", user);
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("employees", emp1);
		
		emailSubject = "New Time Sheet For:";
		emailMessage = "A new Time Sheet For Approval has Been Sent to :"+"On: "+new Date()+"\n"+"\n Employee Name:  "+user.getEmplfirstname()+" "+user.getEmpllastname()+"\n Time Sheet request for the Month:  "+request.getParameter("month")+"("+request.getParameter("year")+")";
	//	emailToRecipient = c.getMail(user.getCreatedby());
		//System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
		emailsender.javaMailService("bgrao@mylastech.com", "Bganga@07", emailToRecipient, emailMessage, emailSubject);
		System.out.println("send mail");
		
		
		return mav;
	}


/*
	@RequestMapping(value= "manager/leaverequests/register")
	public ModelAndView allleaverequests(HttpSession session) {
		ModelAndView mav = new ModelAndView("allempleaverequests");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		mav.addObject("Role",role);
		List<TblEmpLeavereq> leavereq =  ers.view();
		List<EmpDetails> emp1 = userDetails.getDetails();
		int count = ers.countEmployee(user.getEid()) + attimpl.countEmployee(user.getEid());
		mav.addObject("employees", emp1);
		mav.addObject("allempleave", leavereq);
		mav.addObject("empleave", leavereq);
		mav.addObject("User",user);
		mav.addObject("manservices", mandao.list());	
		mav.addObject("count",count);
		return mav;
	}
*/

/*	@RequestMapping(value= "manager/employeetimesheets/register")
	public ModelAndView allAttRequests(HttpSession session) {
		ModelAndView mav = new ModelAndView("allemptimesheetrequests");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		mav.addObject("Role",role);
		List<TblEmpAttendanceNew> attendances =  attimpl.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		List<TblEmpLeavereq> allempleave = ers.view();
		int count = ers.countEmployee(user.getEid()) + attimpl.countEmployee(user.getEid());
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("employees", emp1);
		mav.addObject("attendancelist",attendances);
		mav.addObject("empattendances",attendances);	
		mav.addObject("User",user);
		mav.addObject("manservices", mandao.list());	
		return mav;
	}*/

	/*@RequestMapping(value= "/manager/leave/delete/{id}")
	public ModelAndView empLeavedeletePage(HttpSession session,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/manager/leave/register");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
	//	List<TblEmpLeavereq> leavereq =  ers.viewbyid(user.getEid());
		//String DelMsg = ers.delete(id);
		//List<TblEmpLeavereq> allempleave = ers.view();
		//int count = ers.countEmployee(user.getEid()) + attimpl.countEmployee(user.getEid());
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("empleave", leavereq);
		mav.addObject("DelMsg", DelMsg);
		mav.addObject("manservices", mandao.list());	
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("employees", emp1);
		return mav;*/
	

	/*
	 * Leave Approve and Decline Handlers
	 */

	@RequestMapping(value= "/manager/leave/approve/{id}")
	public ModelAndView empLeaveApprovePage(HttpSession session,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/manager/leaverequests/register");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		//List<TblEmpLeavereq> leavereq =  ers.view();
		String reason = "Approved";
		boolean status = true;
	//	String UMsg = ers.update(id,reason,status);
		
		//mav.addObject("empleave", leavereq);
	//	mav.addObject("UMsg", UMsg+" "+reason);
		mav.addObject("manservices", mandao.list());
		emailSubject = "Leave Request Status For:";
		emailMessage = "A new Leave Request was approved :"+"On: "+new Date()+" By:  "+user.getEmplfirstname()+" "+user.getEmpllastname();
		//emailToRecipient = c.getMail(user.getCreatedby());
		//System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
		emailsender.javaMailService("bgrao@mylastech.com", "14131f0008", emailToRecipient, emailMessage, emailSubject);
		return mav;
	}
	@RequestMapping(value= "/manager/leave/decline/{id}")
	public ModelAndView empLeavedeclinePage(HttpSession session,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/manager/leaverequests/register");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		//List<TblEmpLeavereq> leavereq =  ers.view();
		String reason = "Decline";
		boolean status = false;
		//String UMsg = ers.update(id,reason,status);
		//mav.addObject("empleave", leavereq);
		//mav.addObject("UMsg", UMsg+" "+reason);
		mav.addObject("manservices", mandao.list());
		
		emailSubject = "Leave Request Status For:";
		emailMessage = "A new Leave Request was declined :"+"On: "+new Date()+" By:  "+user.getEmplfirstname()+" "+user.getEmpllastname();
	//	emailToRecipient = c.getMail(user.getCreatedby());
		//System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
		emailsender.javaMailService("bgrao@mylastech.com", "14131f0008", emailToRecipient, emailMessage, emailSubject);

		return mav;
	}

	/*
	 * Attendance TimeSheet Approve ans Decline
	 */

	@RequestMapping(value= "/manager/attendance/approve/{id}")
	public ModelAndView empTimesheetApprovePage(HttpSession session,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/manager/employeetimesheets/register");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		String reason = "Approved";
		boolean status = true;
		//attimpl.update(status, id);	
		
		
		emailSubject = "Time Sheet Request Status For:";
		emailMessage = "A new Time Sheet Request was approved :"+"On: "+new Date()+" By:  "+user.getEmplfirstname()+" "+user.getEmpllastname();
	//	emailToRecipient = c.getMail(user.getCreatedby());
		//System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
		emailsender.javaMailService("bgrao@mylastech.com", "14131f0008", emailToRecipient, emailMessage, emailSubject);
		return mav;
	}
	@RequestMapping(value= "/manager/attendance/decline/{id}")
	public ModelAndView empTimesheetdeclinePage(HttpSession session,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/manager/employeetimesheets/register");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		String reason = "Decline";
		boolean status = false;
	//	attimpl.update(status, id);
		
		emailSubject = "Time Sheet Request Status For:";
		emailMessage = "A new Time Sheet Request was declined :"+"On: "+new Date()+" By:  "+user.getEmplfirstname()+" "+user.getEmpllastname();
	//	emailToRecipient = c.getMail(user.getCreatedby());
		//System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
		emailsender.javaMailService("bgrao@mylastech.com", "14131f0008", emailToRecipient, emailMessage, emailSubject);
     
		
		return mav;
	}

	/*
	 * 
	 * Manager Role Transfer
	 */
	@RequestMapping(value= "/manager/roletransfer",method=RequestMethod.POST)
	public ModelAndView testmyPagePost(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("redirect:/");

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		/*
		 * Object Creation and saving the object
		 */
		TblManRoleTransfer roletransfor = new TblManRoleTransfer();
		roletransfor.setFromdate(request.getParameter("fromdate"));
		roletransfor.setTodate(request.getParameter("todate"));
		roletransfor.setFrommanid(user.getEid());
		roletransfor.setTomanid(request.getParameter("managerId"));

		String msg = roleTransfer.save(roletransfor);
		String fromdate = request.getParameter("fromdate");

		String role = user.getRole();
		mav.addObject("Role",role);
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("employees", emp1);

		mav.addObject("manservices", mandao.list());	
		return mav;
	}
/*    @RequestMapping(value= "/manager/timesheet/search")
 public ModelAndView indvidtimesheetSearch(HttpSession session,HttpServletRequest request) {
  ModelAndView mav = new ModelAndView("emptimesheet");
  Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  EmpDetails user=null;
  if (principal instanceof EmpDetails) {
   user = ((EmpDetails)principal);
  }

  String role = user.getRole();
  mav.addObject("Role",role);

  List<TblEmpAttendanceNew> attendances =  attimpl.Search(request.getParameter("month"), request.getParameter("status"), user.getEid());
    //viewSearch(request.getParameter("month"), request.getParameter("status"),attimpl.countEmployee(user.getEid()));
  List<EmpDetails> emp1 = userDetails.getDetails();
  List<TblEmpLeavereq> allempleave = ers.view();
  int count = ers.countEmployee(user.getEid()) + attimpl.countEmployee(user.getEid());
  List<TblEmpAttendanceNew> empattendances =  attimpl.getDetails();
  mav.addObject("empattendances",empattendances);
 mav.addObject("allempleave", allempleave);
 mav.addObject("count",count);
  mav.addObject("attendancelist",attendances);
  mav.addObject("manservices", mandao.list()); 
  mav.addObject("User", user);
  mav.addObject("employees", emp1);
  return mav;
 }*/
/*    @RequestMapping(value="/manager/search/register")
	public ModelAndView empLeaveSearch(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("allempleaverequests");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		mav.addObject("Role",role);
		
		List<TblEmpLeavereq> empleave =  ers.viewSearch(request.getParameter("firstname"),request.getParameter("lastname"),request.getParameter("month"), request.getParameter("status"));
		
		List<EmpDetails> emp1 = userDetails.getDetails();
		int count = ers.countEmployee(user.getEid()) + attimpl.countEmployee(user.getEid());
		mav.addObject("employees", emp1);
		 
		mav.addObject("empleave", empleave);
		mav.addObject("User",user);
		mav.addObject("manservices", mandao.list());	
		mav.addObject("count",count);
		return mav;
	}*/
 /*   @RequestMapping(value= "/manager/leave/search")
    public ModelAndView managerLeaveSearch(HttpServletRequest request) {
     ModelAndView mav = new ModelAndView("empleaverequests");
     Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     EmpDetails user=null;
     if (principal instanceof EmpDetails) {
      user = ((EmpDetails)principal);
     }

     String role = user.getRole();
     mav.addObject("Role",role);
     List<TblEmpLeavereq> leavereq =  ers.empLeaveSearch(user.getEid(),request.getParameter("month"), request.getParameter("status"));
     int count = ers.countEmployee(user.getEid()) + attimpl.countEmployee(user.getEid());
     List<EmpDetails> emp1 = userDetails.getDetails();
     List<TblEmpAttendanceNew> empattendances =  attimpl.getDetails();
     mav.addObject("empattendances",empattendances);
     
     mav.addObject("count",count);
     mav.addObject("User", user);
     mav.addObject("empleave", leavereq);
     mav.addObject("manservices", mandao.list());
     mav.addObject("employees", emp1);
     mav.addObject("count",count);
     return mav;
   }*/
    @RequestMapping(value= "/manager/timesheet/delete/{id}")
	public ModelAndView empTimeSheetdeletePage(HttpSession session,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/manager/timesheet/register");
		//String DelMsg = attimpl.delete(id);
		return mav;
	}
/*    @RequestMapping(value="/manager/employee/search", method=RequestMethod.POST)
	public ModelAndView searchBars(HttpServletRequest req) {

		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String department = req.getParameter("department1");
		String designation = req.getParameter("designation1");
		List<TblDepartment> deptList = deptdao.getDetails();
		List<TblDesignation> designList = designationImpl.getDetails();

		List<EmpDetails> details = userDetails.viewSearch(firstname, lastname, department, designation);

		ModelAndView mav = new ModelAndView("employees");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;

		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		List<TblEmpLeavereq> allempleave = ers.view();
		int count = ers.countEmployee(user.getEid()) + attimpl.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  attimpl.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		mav.addObject("User",user);
		String mesg = "hi";

		mav.addObject("employees", details);
		mav.addObject("manservices", mandao.list());

		mav.addObject("dupmsg", mesg);
		mav.addObject("departments", deptList);
		mav.addObject("designations", designList);

		List<TblDepartment> dests = deptdao .getDetails();
		mav.addObject("departments", dests);

		mav.addObject(user);
		return mav;		
	}*/
    
    @RequestMapping(value="/manager/employee/search")
    public ModelAndView empSearchBars() {
		return new ModelAndView("redirect:/manager/allemp/register");
    	
    }
    
    @RequestMapping(value="/manager/employee/timesheetSearch")	 
	public ModelAndView attendance() {
		return new ModelAndView("redirect:/manager/employeetimesheets/register");}
	@RequestMapping(value="/manager/employee/timesheetSearch", method=RequestMethod.POST)
	public ModelAndView attendanceSearch(HttpServletRequest request) {
		String username = request.getParameter("username");
		System.out.println(request.getParameter("month"));
		ModelAndView mav = new ModelAndView("allemptimesheetrequests");
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		mav.addObject("manservices", mandao.list());
		String role = user.getRole();
		mav.addObject("Role",role);
		//List<TblEmpAttendanceNew> attendances =  attimpl.viewSearch(request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("month"), request.getParameter("status"));
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("User",user);
		mav.addObject("employees", emp1);
	//	mav.addObject("attendancelist",attendances);
		return mav;		
	}
/*
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView getPages() {

		ModelAndView model = new ModelAndView("example");
		return model;

	}
*/
	@RequestMapping(value = "/getTags", method = RequestMethod.GET)
	public @ResponseBody
	List<EmpDetails> getTags(@RequestParam String firstname) {
		

		return userDetails.simulateSearchResult(firstname);

	}
	@RequestMapping(value = "/lastNames", method = RequestMethod.GET)
	public @ResponseBody
	List<EmpDetails> lastName(@RequestParam String lastname) {
		

		return userDetails.simulateSearchResultLastName(lastname);

	}
	@RequestMapping(value= "manager/leavereq/edit/{id}")
	public ModelAndView empLeaveEdit(HttpSession session, @PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("empleaverequestedit");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		mav.addObject("Role",role);
		//List<TblEmpLeavereq> allempleave = ers.view();
		//List<TblEmpLeavereq> leavereq =  ers.viewbyid(id);
		//TblEmpLeavereq leavereq=ers.getById(id);
		//int count = ers.countEmployee(user.getEid()) + attimpl.countEmployee(user.getEid());
		//List<EmpDetails> emp1 = userDetails.getDetails();
		//List<TblEmpAttendanceNew> empattendances =  attimpl.getDetails();
		//mav.addObject("empattendances",empattendances);
	//	mav.addObject("allempleave", allempleave);
	//	mav.addObject("count",count);
		mav.addObject("User", user);
		/*mav.addObject("empleave", leavereq);
		System.out.println(leavereq);*/
		//mav.addObject("manservices", mandao.list());
		//mav.addObject("employees", emp1);
		//mav.addObject("count",count);
	//	List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
	//	mav.addObject("TransferRoleList", transferrole);
		mav.addObject("id",id);
		mav.addObject("manservices", mandao.list());
		mav.addObject("User",user);
		List<LeaveAddition> numofleaves=leave.getDetailsofleavetye();
		mav.addObject("nleave",numofleaves);
		return mav;
			
	}

	/*@RequestMapping(value="manager/leave/upload/{id}")
	public ModelAndView updateLeaveRequest(HttpServletRequest request,@PathVariable("id") int id) {
		//ModelAndView mav = new ModelAndView("redirect:/admin/search/register");
		String fromdate = request.getParameter("fromdate");
		String[] date = fromdate.split("-");
		String todate = request.getParameter("todate");
		SimpleDateFormat formatfromdate = new SimpleDateFormat("yyyy-mm-dd");
		Date reqfromDate = null;
		Date reqtoDate = null;
		emailSubject = "New Time Sheet For:";
		emailMessage = "A new Time Sheet For Approval has Been Sent to :"+"On: "+new Date();
		emailToRecipient = "kaparapu.praveen@gmail.com";
		//System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
		emailsender.javaMailService("bojagangadhar@gmail.com", "14131f0008", emailToRecipient, emailMessage, emailSubject);
		System.out.println("mail Send");
		try {
			reqfromDate = formatfromdate.parse(fromdate);
			reqtoDate = formatfromdate.parse(todate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LocalDate Day1 = LocalDate.parse(fromdate);
		LocalDate Day2 = LocalDate.parse(todate);

		long daysNegative = ChronoUnit.DAYS.between(Day1, Day2);
		ers.updateLeave(id,request.getParameter("leavetype"),request.getParameter("fromdate"),request.getParameter("todate"),request.getParameter("leavereason"),(int)daysNegative,"true");
		ModelAndView mav = new ModelAndView("redirect:/manager/leaverequests/register");
		return mav;

	}*/

	

}
