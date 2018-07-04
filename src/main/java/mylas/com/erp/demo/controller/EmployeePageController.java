
package mylas.com.erp.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mylas.com.erp.demo.EmpDetails;
import mylas.com.erp.demo.TblEmpAttendanceNew;
import mylas.com.erp.demo.TblEmpLeavereq;
import mylas.com.erp.demo.appservices.EmailSender;
import mylas.com.erp.demo.dao.EmpLeaveRequestDao;
import mylas.com.erp.demo.dao.EmpServicesDao;
import mylas.com.erp.demo.daoimpl.EmpAttendanceDaoImpl;
import mylas.com.erp.demo.service.Client;

/*
 * This Controller will Handle all the Employee Related Pages.
 */

@Controller
public class EmployeePageController {

	@Autowired
	EmpServicesDao empservicesdao;

	@Autowired
	EmpLeaveRequestDao empleavereq;

	@Autowired
	EmpAttendanceDaoImpl empattreq;

	EmailSender emailsender = new EmailSender();

	static String emailToRecipient, emailSubject, emailMessage;


	/*
	 * Employee Regestration Page Get Method
	 */
	@RequestMapping(value= "/employee/leave/register")
	public ModelAndView empLeavePage(HttpSession session) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		String role = user.getRole();
		ModelAndView mav = new ModelAndView("empleaverequests");
		List<TblEmpLeavereq> leavereq =  empleavereq.viewbyid(user.getEid());
		mav.addObject("Role",role);
		mav.addObject("User", user);
		mav.addObject("empleave", leavereq);
		mav.addObject("empservices", empservicesdao.list());
		return mav;
	}

	@RequestMapping(value= "/employee/profile/register")
	public ModelAndView empProfilePage() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		String role = user.getRole();
		ModelAndView mav = new ModelAndView("useremployee");
		mav.addObject("Role", role);
		mav.addObject("User", user);
		mav.addObject("empservices", empservicesdao.list());	
		return mav;
	}

	/*
	 * Test Comment
	 */
	@RequestMapping(value= "/employee/timesheet/register")
	public ModelAndView indvidtimesheet() {
		ModelAndView mav = new ModelAndView("emptimesheet");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		List<TblEmpAttendanceNew> attendances =  empattreq.viewbyid(user.getEid());
		mav.addObject("attendancelist",attendances);
		mav.addObject("empservices", empservicesdao.list());
		mav.addObject("Role",role);
		mav.addObject("User", user);
		return mav;
	}

	@RequestMapping(value= "/employee/timesheet/search")
	public ModelAndView indvidtimesheetSearch(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("emptimesheet");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		List<TblEmpAttendanceNew> attendances =  empattreq.Search(request.getParameter("month"), request.getParameter("status"), user.getEid());
		
		mav.addObject("attendancelist",attendances);
		mav.addObject("empservices", empservicesdao.list());
		mav.addObject("Role",role);
		mav.addObject("User", user);
		return mav;
	}
	
	@RequestMapping(value= "/employee/timesheet/register/{id}")
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
		mav.addObject("attendancelist",attendances);
		mav.addObject("empservices", empservicesdao.list());
		mav.addObject("Role",role);
		mav.addObject("User", user);

		return mav;
	}

	@RequestMapping(value= "/employee/timesheet/register", method=RequestMethod.POST)
	public ModelAndView indvidtimesheetsubmit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView mav = new ModelAndView("emptimesheet");



		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();

		TblEmpAttendanceNew attedance = new TblEmpAttendanceNew(null, null, null, null, Integer.parseInt(request.getParameter("day1")), Integer.parseInt(request.getParameter("day2")), Integer.parseInt(request.getParameter("day3")), Integer.parseInt(request.getParameter("day4")), Integer.parseInt(request.getParameter("day5")), Integer.parseInt(request.getParameter("day6")), Integer.parseInt(request.getParameter("day7")), Integer.parseInt(request.getParameter("day8")), Integer.parseInt(request.getParameter("day9")), Integer.parseInt(request.getParameter("day10")), Integer.parseInt(request.getParameter("day11")), Integer.parseInt(request.getParameter("day12")), Integer.parseInt(request.getParameter("day13")), Integer.parseInt(request.getParameter("day14")), Integer.parseInt(request.getParameter("day15")), Integer.parseInt(request.getParameter("day16")), Integer.parseInt(request.getParameter("day17")), Integer.parseInt(request.getParameter("day18")), Integer.parseInt(request.getParameter("day19")), Integer.parseInt(request.getParameter("day20")), Integer.parseInt(request.getParameter("day21")), Integer.parseInt(request.getParameter("day22")), Integer.parseInt(request.getParameter("day23")), Integer.parseInt(request.getParameter("day24")), Integer.parseInt(request.getParameter("day25")), Integer.parseInt(request.getParameter("day26")), Integer.parseInt(request.getParameter("day27")), Integer.parseInt(request.getParameter("day28")), null, null, null, null, null,null);

		attedance.setEmpid(user.getEid());
		attedance.setStatas(null);
		attedance.setManagerid(user.getManagerid());
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
		empattreq.save(attedance);
		System.out.println("save");
		List<TblEmpAttendanceNew> attendances =  empattreq.viewbyid(user.getEid());
		mav.addObject("attendancelist",attendances);
		mav.addObject("empservices", empservicesdao.list());	
		mav.addObject("Role",role);
		mav.addObject("User", user);
		return mav;
	}

	/*
	 * Handling EMployee Requests
	 * 
	 */

	@RequestMapping(value= "/employee/leave/register", method=RequestMethod.POST)
	public ModelAndView empLeaveRequestPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();

		ModelAndView mav = new ModelAndView("empleaverequests");
		Client cl = new Client();


		mav.addObject("Role",role);
		mav.addObject("User",user);
		/*
		 * Message Handling
		 */


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





		TblEmpLeavereq empleave = new TblEmpLeavereq((int)daysNegative,null, request.getParameter("fromdate"),request.getParameter("leavereason"), request.getParameter("leavetype"), null, null,  request.getParameter("todate"),null,null,null);
		empleave.setManagerid(user.getManagerid());
		empleave.setEmployeeid(user.getEid());
		empleave.setStatus(null);
		empleavereq.save(empleave);		
		System.out.println("Req Sent to Save");

	
		List<EmpDetails> emp1 = cl.getDetails();
		List<TblEmpLeavereq> leavereq =  empleavereq.viewbyid(user.getEid());
		mav.addObject("employees", emp1);
		mav.addObject("empleave", leavereq);
		mav.addObject("empservices", empservicesdao.list());	
		mav.addObject("employees", emp1);
		mav.addObject("empleave", leavereq);
		mav.addObject("Submitmsg", "Your Leave Request Has Been Submitted Sucessfully! Please Wait for your Manager Approval");
		mav.addObject("Role",role);
		emailSubject = "New Time Sheet For:";
		emailMessage = "A new Time Sheet For Approval has Been Sent to :"+"On: "+new Date();
		emailToRecipient = "kpraveen@mylastech.com";
		//System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
		emailsender.javaMailService("bgrao@mylastech.com", "Bganga@07", emailToRecipient, emailMessage, emailSubject);
		System.out.println("send mail");
		return mav;
	}
	
	@RequestMapping(value= "/employee/leave/search",method=RequestMethod.POST)
	 public ModelAndView empLeavePageSearch(HttpSession session,HttpServletRequest request) {
	  Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  EmpDetails user=null;
	  if (principal instanceof EmpDetails) {
	   user = ((EmpDetails)principal);
	  }
	  String role = user.getRole();
	  ModelAndView mav = new ModelAndView("empleaverequests");
	  System.out.println(request.getParameter("month"));
	  List<TblEmpLeavereq> leavereq =  empleavereq.empLeaveSearch(user.getEid(),request.getParameter("month"), request.getParameter("status"));
	   
	  mav.addObject("Role",role);
	  mav.addObject("User", user);
	  mav.addObject("empleave", leavereq);
	  mav.addObject("empservices", empservicesdao.list());
	  return mav;
	 }
	@RequestMapping(value= "/employee/leave/search")
	public ModelAndView empLeavePageSearch() {
		return new ModelAndView("redirect:/employee/leave/register");
		
	}


	/*
	 * Employee Leave Request Edit and Delete Operations
	 */


	@RequestMapping(value= "/employee/leave/delete/{id}")
	public ModelAndView empLeavedeletePage(HttpSession session,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/employee/leave/register");
		String DelMsg = empleavereq.delete(id);
		return mav;
	}
	@RequestMapping(value= "/employee/timesheet/delete/{id}")
	public ModelAndView empTimeSheetdeletePage(HttpSession session,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/employee/timesheet/register");
		String DelMsg = empattreq.delete(id);
		return mav;
	}

}
