
package mylas.com.erp.demo.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mylas.com.erp.demo.EmpDetails;
import mylas.com.erp.demo.LeaveAddition;
/*import mylas.com.erp.demo.TblEmpAttendanceNew;*/
import mylas.com.erp.demo.TblEmpLeavereq;
import mylas.com.erp.demo.Tblleavestype;
import mylas.com.erp.demo.appservices.EmailSender;
import mylas.com.erp.demo.dao.EmpLeaveRequestDao;
import mylas.com.erp.demo.dao.EmpServicesDao;
import mylas.com.erp.demo.dao.LeaveManiplication;
import mylas.com.erp.demo.daoimpl.EmpAttendanceDaoImpl;
import mylas.com.erp.demo.daoimpl.EmpLeaveRequestService;
import mylas.com.erp.demo.daoimpl.LeavesTypeDaoImpl;
import mylas.com.erp.demo.procedures.EmpLeaveRequestJoin;
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

/*	@Autowired
	EmpAttendanceDaoImpl empattreq;*/
	
	@Autowired
	LeaveManiplication leave;


	EmailSender emailsender = new EmailSender();

	static String emailToRecipient, emailSubject, emailMessage;
	EmpLeaveRequestService elrs = new EmpLeaveRequestService();
	LeavesTypeDaoImpl ltdi = new LeavesTypeDaoImpl();


	/*
	 * Employee Regestration Page Get Method
	 */
	

/*	@RequestMapping(value= "/employee/profile/register")
	public ModelAndView empProfilePage() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		String role = user.getRole();
	//	int a[]=empleavereq.countSum(user.getEid());
		ModelAndView mav = new ModelAndView("useremployee");
		List<LeaveAddition> numofleaves=leave.getDetailsofleavetye();
		//Map<String,Integer> using=empleavereq.count(user.getEid());
		Map<String,Integer> pending=new HashMap<>();
		//Set<String> keys = using.keySet();
		//Iterator itr = keys.iterator();
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
		mav.addObject("Role", role);
		mav.addObject("User", user);
		mav.addObject("medical",a[0]);
		mav.addObject("casual",a[1]);
		mav.addObject("sick",a[2]);
		mav.addObject("pmedical",10-a[0]);
		mav.addObject("pcasual",10-a[1]);
		mav.addObject("psick",10-a[2]);
		mav.addObject("empservices", empservicesdao.list());	
		return mav;
	}
*/
	/*
	 * Test Comment
	 */
	/*@RequestMapping(value= "/employee/timesheet/register")
	public ModelAndView indvidtimesheet() {
		ModelAndView mav = new ModelAndView("emptimesheet");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		//List<TblEmpAttendanceNew> attendances =  empattreq.viewbyid(user.getEid());
		//mav.addObject("attendancelist",attendances);
		mav.addObject("empservices", empservicesdao.list());
		mav.addObject("Role",role);
		mav.addObject("User", user);
		return mav;
	}*/

	/*@RequestMapping(value= "/employee/timesheet/search")
	public ModelAndView indvidtimesheetSearch(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("emptimesheet");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
	//	List<TblEmpAttendanceNew> attendances =  empattreq.Search(request.getParameter("month"), request.getParameter("status"), user.getEid());
		
		//mav.addObject("attendancelist",attendances);
		mav.addObject("empservices", empservicesdao.list());
		mav.addObject("Role",role);
		mav.addObject("User", user);
		return mav;
	}
	*/
	/*@RequestMapping(value= "/employee/timesheet/register/{id}")
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
		//List<TblEmpAttendanceNew> attendances =  empattreq.viewbyid(user.getEid());
		//mav.addObject("attendancelist",attendances);
		mav.addObject("empservices", empservicesdao.list());
		mav.addObject("Role",role);
		mav.addObject("User", user);

		return mav;
	}*/

	/*@RequestMapping(value= "/employee/timesheet/register", method=RequestMethod.POST)
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
		//attedance.setManagerid(user.getManagerid());
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
	//	empattreq.save(attedance);
		System.out.println("save");
		//List<TblEmpAttendanceNew> attendances =  empattreq.viewbyid(user.getEid());
		//mav.addObject("attendancelist",attendances);
		mav.addObject("empservices", empservicesdao.list());	
		mav.addObject("Role",role);
		mav.addObject("User", user);
		emailSubject = "New Time Sheet For:";
		emailMessage = "A new Time Sheet For Approval has Been Sent to :"+"On: "+new Date();
		emailToRecipient = "kpraveen@mylastech.com";
		//System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
		emailsender.javaMailService("bgrao@mylastech.com", "Bganga@07", emailToRecipient, emailMessage, emailSubject);
		System.out.println("send mail");
		return mav;
		
		
		
		
		
	}*/

	/*
	 * Handling EMployee Requests
	 * 
	 */

	
	
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
	//  List<TblEmpLeavereq> leavereq =  empleavereq.empLeaveSearch(user.getEid(),request.getParameter("month"), request.getParameter("status"));
	   
	  mav.addObject("Role",role);
	  mav.addObject("User", user);
	 // mav.addObject("empleave", leavereq);
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
		//String DelMsg = empleavereq.delete(id);
		return mav;
	}
/*	@RequestMapping(value= "/employee/timesheet/delete/{id}")
	public ModelAndView empTimeSheetdeletePage(HttpSession session,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/employee/timesheet/register");
		//String DelMsg = empattreq.delete(id);
		return mav;
	}
*/
/*-----------------------------------------	employee leave requests---------------------------------------*/
	@RequestMapping(value= "/employee/leave/register")	
	public ModelAndView empLeavePage(HttpSession session) {
		ModelAndView mav = new ModelAndView("empleaverequests");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}	
		mav.addObject("User", user);
		String role = user.getRole();
		mav.addObject("Role",role);

		List<Tblleavestype> leavetypes = ltdi.viewAll();
		 mav.addObject("leavetypes",leavetypes);
         List<EmpLeaveRequestJoin> empleaves = elrs.viewAll(0);
         mav.addObject("empleaves",empleaves);
    	 mav.addObject("empservices", empservicesdao.list());
   
    	 return mav;
	}
	
	
	@RequestMapping(value= "/employee/leave/register", method=RequestMethod.POST) 
	public ModelAndView empLeaveRequestPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView mav = new ModelAndView("empleaverequests");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		 EmpLeaveRequestService elrs = new EmpLeaveRequestService();
        String role = user.getRole();
		System.out.println(role);
		mav.addObject("Role",role);
		List<Tblleavestype> leavetypes = ltdi.viewAll();	
		mav.addObject("leavetypes",leavetypes);
		String leavetype = request.getParameter("leavetype");
		String fromdate = request.getParameter("fromdate");
		 System.out.println(fromdate);
		String todate = request.getParameter("todate");
		String leavereason = request.getParameter("leavereason");
		 System.out.println(todate);
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date fromDate=null;
	    Date toDate=null;
		try
		{
	    fromDate = dateFormat.parse(fromdate);
	    System.out.println(fromDate);
	    toDate = dateFormat.parse(todate);
	    System.out.println(toDate);
			LocalDate Day1 = LocalDate.parse(fromdate);
			LocalDate Day2 = LocalDate.parse(todate);
			long count = ChronoUnit.DAYS.between(Day1, Day2);
     
	    String s = elrs.saveLeaveRequest(0,(int)count,user.getEid(),fromDate,leavereason,Integer.parseInt(request.getParameter("leavetype")),(int)user.getCreatedby(),4,toDate,true,user.getId(),new Date(),(int)user.getCreatedby(),new Date());		
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
		mav.addObject("User",user);
	     List<EmpLeaveRequestJoin> empleaves = elrs.viewAll(0);
	        mav.addObject("empleaves",empleaves);
		mav.addObject("empservices", empservicesdao.list());	

		return mav;
	}

}
