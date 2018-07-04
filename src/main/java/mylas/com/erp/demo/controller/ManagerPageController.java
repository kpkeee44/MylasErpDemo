package mylas.com.erp.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import mylas.com.erp.demo.EmpDetails;
import mylas.com.erp.demo.TblDesignation;
import mylas.com.erp.demo.TblEmpAttendanceNew;
import mylas.com.erp.demo.TblEmpLeavereq;
import mylas.com.erp.demo.TblManRoleTransfer;
import mylas.com.erp.demo.appservices.EmailSender;
import mylas.com.erp.demo.appservices.UserServiceImpl;
import mylas.com.erp.demo.dao.EmpLeaveRequestDao;
import mylas.com.erp.demo.dao.ManagerServicesDao;
import mylas.com.erp.demo.dao.RoleTrasforDao;
import mylas.com.erp.demo.daoimpl.EmpAttendanceDaoImpl;
import mylas.com.erp.demo.service.Client;
import mylas.com.erp.demo.service.DesignationService;

@Controller
public class ManagerPageController {

	@Autowired
	ManagerServicesDao mandao;

	@Autowired
	EmpLeaveRequestDao empleavereq;

	@Autowired
	EmpAttendanceDaoImpl empattreq;

	@Autowired
	RoleTrasforDao roleTransfer;

	DesignationService depdetails = new DesignationService();
	Client client = new Client();
	EmailSender emailsender = new EmailSender();

	static String emailToRecipient, emailSubject, emailMessage;


	@RequestMapping(value= "/manager/leave/register")
	public ModelAndView empLeavePage(HttpSession session) {
		ModelAndView mav = new ModelAndView("empleaverequests");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		mav.addObject("Role",role);
		Client cl = new Client();
		List<TblEmpLeavereq> allempleave = empleavereq.view();
		List<TblEmpLeavereq> leavereq =  empleavereq.viewbyid(user.getEid());
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<EmpDetails> emp1 = client.getDetails();
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("User", user);
		mav.addObject("empleave", leavereq);
		mav.addObject("manservices", mandao.list());
		mav.addObject("employees", emp1);
		mav.addObject("count",count);
		return mav;
	}

	@RequestMapping(value="/manager/allemp/register")
	public ModelAndView allEmpPage() {
		ModelAndView mav = new ModelAndView("employees");
		mav.addObject("manservices", mandao.list());	
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		List<TblDesignation> depts = depdetails.getDetails();
		mav.addObject("designations", depts);
		String role = user.getRole();
		mav.addObject("Role",role);
		mav.addObject("User",user);
		Client cl = new Client();
		String mesg = "hi";
		List<EmpDetails> emp1 = cl.getDetails();
		List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("employees", emp1);
		mav.addObject("dupmsg", mesg);
		mav.addObject("User",user);
		mav.addObject("employees", emp1);
		return mav;		
	}
	@RequestMapping(value="/manager/allemp/register", method=RequestMethod.POST)
	public ModelAndView saveEmpPage(HttpServletRequest request, HttpServletResponse response) throws ConstraintViolationException{

		EmpDetails emp = new EmpDetails(null, request.getParameter("cpswd"), null, request.getParameter("empid"), request.getParameter("email"), request.getParameter("firstname"), null, request.getParameter("lastname"), false, null, request.getParameter("pswd"), null, request.getParameter("uname"), null,null,null,null);

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
		List<TblDesignation> depts = depdetails.getDetails();
		mesg = client.getConnection(emp);
		List<EmpDetails> emp1 = client.getDetails();
		List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
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
	}

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
		Client cl = new Client();
		Edetails = cl.getById(id);
		List<EmpDetails> emp1 = client.getDetails();
		List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
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

	/*
	 * Post Method Handelling method
	 */
	@RequestMapping(value= "/manager/leave/register", method=RequestMethod.POST)
	public ModelAndView manLeaveRequestPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {


		ModelAndView mav = new ModelAndView("empleaverequests");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		mav.addObject("Role",role);

		/**
		 * Handling request from empleaverequests.jsp form with post action.
		 */

		String fromdate = request.getParameter("fromdate");
		String[] date = fromdate.split("-");
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

		long daysNegative = ChronoUnit.DAYS.between(Day1, Day2);
		List<EmpDetails> emp1 = client.getDetails();

		mav.addObject("User", user);
		mav.addObject("manservices", mandao.list());	
		mav.addObject("employees", emp1);

		TblEmpLeavereq empleave = new TblEmpLeavereq((int)daysNegative,null, request.getParameter("fromdate"),request.getParameter("leavereason"), request.getParameter("leavetype"), null, null,  request.getParameter("todate"),null,null);


		empleave.setManagerid(user.getManagerid());
		empleave.setEmployeeid(user.getEid());
		empleave.setStatus(null);
		try {
			empleavereq.save(empleave);	
			List<TblEmpLeavereq> leavereq =  empleavereq.viewbyid(user.getEid());
			mav.addObject("empleave", leavereq);
			System.out.println("Req Sent to Save");
			mav.addObject("Submitmsg", "Your Leave Request Has Been Submitted Sucessfully! Please Wait for your Manager Approval");
			emailSubject = "New Leave Request has sent by"+empleave.getEmployeeid()+ "From: "+empleave.getFromdate()+"To: "+empleave.getTodate()+"";
			emailMessage = "A new Time Sheet For Approval has Been Sent to :"+empleave.getManagerid()+"On: "+new Date();
			emailToRecipient = "kaparapu.praveen@gmail.com";
			System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
			emailsender.javaMailService("bojagangadhar@gmail.com", "14131f0008", "krishnavarma.java@gmail.com", emailMessage, emailSubject);
			List<TblEmpLeavereq> allempleave = empleavereq.view();
			int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
			List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
			mav.addObject("empattendances",empattendances);
			mav.addObject("allempleave", allempleave);
			mav.addObject("count",count);
			return mav;
		}catch(DateTimeParseException e){
			mav.addObject("errmsg", "Please enter the required fields");
			return mav;
		}

	}

	@RequestMapping(value= "/manager/profile/register")
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
		mav.addObject("manservices", mandao.list());	
		List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		return mav;
	}

	@RequestMapping(value= "/manager/timesheet/register")
	public ModelAndView indvidtimesheet(HttpSession session) {
		ModelAndView mav = new ModelAndView("emptimesheet");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		mav.addObject("Role",role);

		List<TblEmpAttendanceNew> attendances =  empattreq.viewbyid(user.getEid());
		List<EmpDetails> emp1 = client.getDetails();
		List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("attendancelist",attendances);
		mav.addObject("manservices", mandao.list());	
		mav.addObject("User", user);
		mav.addObject("employees", emp1);
		return mav;
	}
	@RequestMapping(value= "/manager/timesheet/register/{id}")
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
		List<TblEmpAttendanceNew> attendances =  empattreq.viewbyid(user.getEid());
		List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("attendancelist",attendances);
		mav.addObject("manservices", mandao.list());
		mav.addObject("Role",role);
		mav.addObject("User", user);

		return mav;
	}
	
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

		TblEmpAttendanceNew attedance = new TblEmpAttendanceNew(null, null, null, null, Integer.parseInt(request.getParameter("day1")), Integer.parseInt(request.getParameter("day2")), Integer.parseInt(request.getParameter("day3")), Integer.parseInt(request.getParameter("day4")), Integer.parseInt(request.getParameter("day5")), Integer.parseInt(request.getParameter("day6")), Integer.parseInt(request.getParameter("day7")), Integer.parseInt(request.getParameter("day8")), Integer.parseInt(request.getParameter("day9")), Integer.parseInt(request.getParameter("day10")), Integer.parseInt(request.getParameter("day11")), Integer.parseInt(request.getParameter("day12")), Integer.parseInt(request.getParameter("day13")), Integer.parseInt(request.getParameter("day14")), Integer.parseInt(request.getParameter("day15")), Integer.parseInt(request.getParameter("day16")), Integer.parseInt(request.getParameter("day17")), Integer.parseInt(request.getParameter("day18")), Integer.parseInt(request.getParameter("day19")), Integer.parseInt(request.getParameter("day20")), Integer.parseInt(request.getParameter("day21")), Integer.parseInt(request.getParameter("day22")), Integer.parseInt(request.getParameter("day23")), Integer.parseInt(request.getParameter("day24")), Integer.parseInt(request.getParameter("day25")), Integer.parseInt(request.getParameter("day26")), Integer.parseInt(request.getParameter("day27")), Integer.parseInt(request.getParameter("day28")), null, null, null, null, null,null);
		attedance.setEmpid(user.getEid());
		attedance.setManagerid(user.getManagerid());
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
		}
		emailSubject = "New Time Sheet For:"+attedance.getMonth()+" "+attedance.getYear()+"";
		emailMessage = "A new Time Sheet For Approval has Been Sent to :"+attedance.getManagerid()+"On: "+new Date();
		emailToRecipient = "krishnavarma.java@gmail.com";
		System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
		emailsender.javaMailService("bojagangadhar@gmail.com", "14131f0008", emailToRecipient, emailMessage, emailSubject);
		empattreq.save(attedance);
		List<TblEmpAttendanceNew> attendances =  empattreq.viewbyid(user.getEid());
		System.out.println(attendances);
		List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("attendancelist",attendances);
		mav.addObject("manservices", mandao.list());
		mav.addObject("User", user);
		List<EmpDetails> emp1 = client.getDetails();
		mav.addObject("employees", emp1);
		return mav;
	}



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
		List<TblEmpLeavereq> leavereq =  empleavereq.view();
		List<EmpDetails> emp1 = client.getDetails();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		mav.addObject("employees", emp1);
		mav.addObject("allempleave", leavereq);
		mav.addObject("empleave", leavereq);
		mav.addObject("User",user);
		mav.addObject("manservices", mandao.list());	
		mav.addObject("count",count);
		return mav;
	}


	@RequestMapping(value= "manager/employeetimesheets/register")
	public ModelAndView allAttRequests(HttpSession session) {
		ModelAndView mav = new ModelAndView("allemptimesheetrequests");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		mav.addObject("Role",role);
		List<TblEmpAttendanceNew> attendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = client.getDetails();
		List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("employees", emp1);
		mav.addObject("attendancelist",attendances);
		mav.addObject("empattendances",attendances);	
		mav.addObject("User",user);
		mav.addObject("manservices", mandao.list());	
		return mav;
	}

	@RequestMapping(value= "/manager/leave/delete/{id}")
	public ModelAndView empLeavedeletePage(HttpSession session,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/manager/leave/register");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		List<TblEmpLeavereq> leavereq =  empleavereq.viewbyid(user.getEid());
		String DelMsg = empleavereq.delete(id);
		List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("empleave", leavereq);
		mav.addObject("DelMsg", DelMsg);
		mav.addObject("manservices", mandao.list());	
		List<EmpDetails> emp1 = client.getDetails();
		mav.addObject("employees", emp1);
		return mav;
	}

	/*
	 * Leave Approve and Decline Handlers
	 */

	@RequestMapping(value= "/manager/leave/approve/{id}")
	public ModelAndView empLeaveApprovePage(HttpSession session,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/manager/leaverequests/register");

		List<TblEmpLeavereq> leavereq =  empleavereq.view();
		String reason = "Approved";
		boolean status = true;
		String UMsg = empleavereq.update(id,reason,status);
		
		mav.addObject("empleave", leavereq);
		mav.addObject("UMsg", UMsg+" "+reason);
		mav.addObject("manservices", mandao.list());	

		return mav;
	}
	@RequestMapping(value= "/manager/leave/decline/{id}")
	public ModelAndView empLeavedeclinePage(HttpSession session,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/manager/leaverequests/register");
		List<TblEmpLeavereq> leavereq =  empleavereq.view();
		String reason = "Decline";
		boolean status = false;
		String UMsg = empleavereq.update(id,reason,status);
		mav.addObject("empleave", leavereq);
		mav.addObject("UMsg", UMsg+" "+reason);
		mav.addObject("manservices", mandao.list());	

		return mav;
	}

	/*
	 * Attendance TimeSheet Approve ans Decline
	 */

	@RequestMapping(value= "/manager/attendance/approve/{id}")
	public ModelAndView empTimesheetApprovePage(HttpSession session,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/manager/employeetimesheets/register");
		String reason = "Approved";
		boolean status = true;
		empattreq.update(status, id);		
		return mav;
	}
	@RequestMapping(value= "/manager/attendance/decline/{id}")
	public ModelAndView empTimesheetdeclinePage(HttpSession session,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/manager/employeetimesheets/register");
		String reason = "Decline";
		boolean status = false;
		empattreq.update(status, id);
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
		if(msg.equals("Saved")) {
			//update emptable
			List<EmpDetails> employees = client.getByManid(user.getEid());
			for(EmpDetails employee : employees) {
				client.TransferManager(employee.getId(), roletransfor.getTomanid());
			}
			//update leave req table for all the employees under the manager
			List<TblEmpLeavereq> leaverequests = empleavereq.viewbyManagerid(user.getEid());
			for(TblEmpLeavereq employee : leaverequests) {
				empleavereq.updatetransManager(employee.getId(), roletransfor.getTomanid());
			}
			//update timesheet table for all the employees under the manager
			List<TblEmpAttendanceNew> attrequests = empattreq.viewbymanagerid(user.getEid());
			for(TblEmpAttendanceNew employee : attrequests) {
				empattreq.updatetransManager(employee.getId(), roletransfor.getTomanid());
			}
		}

		String role = user.getRole();
		mav.addObject("Role",role);
		List<EmpDetails> emp1 = client.getDetails();
		mav.addObject("employees", emp1);

		mav.addObject("manservices", mandao.list());	
		return mav;
	}
    @RequestMapping(value= "/manager/timesheet/search")
 public ModelAndView indvidtimesheetSearch(HttpSession session,HttpServletRequest request) {
  ModelAndView mav = new ModelAndView("emptimesheet");
  Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  EmpDetails user=null;
  if (principal instanceof EmpDetails) {
   user = ((EmpDetails)principal);
  }

  String role = user.getRole();
  mav.addObject("Role",role);

  List<TblEmpAttendanceNew> attendances =  empattreq.Search(request.getParameter("month"), request.getParameter("status"), user.getEid());
    //viewSearch(request.getParameter("month"), request.getParameter("status"),empattreq.countEmployee(user.getEid()));
  List<EmpDetails> emp1 = client.getDetails();
  List<TblEmpLeavereq> allempleave = empleavereq.view();
  int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
  List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
  mav.addObject("empattendances",empattendances);
 mav.addObject("allempleave", allempleave);
 mav.addObject("count",count);
  mav.addObject("attendancelist",attendances);
  mav.addObject("manservices", mandao.list()); 
  mav.addObject("User", user);
  mav.addObject("employees", emp1);
  return mav;
 }
    @RequestMapping(value="/manager/search/register")
	public ModelAndView empLeaveSearch(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("allempleaverequests");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		mav.addObject("Role",role);
		
		List<TblEmpLeavereq> empleave =  empleavereq.empLeaveSearch(request.getParameter("uname"),request.getParameter("month"), request.getParameter("status"));
		
		List<EmpDetails> emp1 = client.getDetails();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		mav.addObject("employees", emp1);
		 
		mav.addObject("empleave", empleave);
		mav.addObject("User",user);
		mav.addObject("manservices", mandao.list());	
		mav.addObject("count",count);
		return mav;
	}
    @RequestMapping(value= "/manager/leave/search")
    public ModelAndView managerLeaveSearch(HttpServletRequest request) {
     ModelAndView mav = new ModelAndView("empleaverequests");
     Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     EmpDetails user=null;
     if (principal instanceof EmpDetails) {
      user = ((EmpDetails)principal);
     }

     String role = user.getRole();
     mav.addObject("Role",role);
     Client cl = new Client();
     List<TblEmpLeavereq> leavereq =  empleavereq.empLeaveSearch(user.getEid(),request.getParameter("month"), request.getParameter("status"));
     int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
     List<EmpDetails> emp1 = client.getDetails();
     List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
     mav.addObject("empattendances",empattendances);
     
     mav.addObject("count",count);
     mav.addObject("User", user);
     mav.addObject("empleave", leavereq);
     mav.addObject("manservices", mandao.list());
     mav.addObject("employees", emp1);
     mav.addObject("count",count);
     return mav;
   }
    @RequestMapping(value= "/manager/timesheet/delete/{id}")
	public ModelAndView empTimeSheetdeletePage(HttpSession session,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/manager/timesheet/register");
		String DelMsg = empattreq.delete(id);
		return mav;
	}


}
