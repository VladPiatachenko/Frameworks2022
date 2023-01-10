/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sumdu.edu.ua.studentweb.controller;


import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sumdu.edu.ua.studentweb.CustomExceptions.EmailException;
import sumdu.edu.ua.studentweb.DAO.StudentDAO;
import sumdu.edu.ua.studentweb.Support.StatsCalculator;
import sumdu.edu.ua.studentweb.model.Student;
import sumdu.edu.ua.studentweb.Support.Utils;

/**
 *
 * @author Erlkonig
 */

@Controller
public class StudentController {
    List<Student> students;
    ApplicationContext factory;
    @Autowired
    private StudentDAO dao;
	    
    
    @ModelAttribute
        public void modelData(Model m){
            if(students==null){ students = new LinkedList<Student>();}
            factory = new ClassPathXmlApplicationContext("/SpringXMLConfig.xml");
        }
    
        
        @RequestMapping(value = "/")
	public String home(Model m) {
            students=dao.getStudents();
                m.addAttribute("students", students);
		return "home";
	}
        
        @RequestMapping("StudentAdd")
        public String addStudent(HttpServletRequest request,Model m){
      
        if (request.getParameter("name") != "" && request.getParameter("surname") != "") {
        Student student = (Student)factory.getBean("Student");
        
        student.setName(request.getParameter("name"));
        student.setSurname(request.getParameter("surname"));
        student.setAge(request.getParameter("age"));
        student.setEmail(request.getParameter("email"));
        student.setGroup(request.getParameter("group"));
        student.setFaculty(request.getParameter("faculty"));
        dao.addStudent(student);}
        students=dao.getStudents();
    
        m.addAttribute("students", students);
        
    return "home";

    }
        @PostMapping("calculateStats")
        public ModelAndView  calculateSt() throws EmailException{ 
            ModelAndView modelNview = new ModelAndView();
		modelNview.setViewName("statistics");
            StatsCalculator stat=StatsCalculator.getInstance();
            stat.setMeanAge(Utils.calculateMeanAge((LinkedList<Student>) students));
            stat.setPopularMail( Utils.calculatePopularDomain((LinkedList<Student>) students));
            modelNview.addObject("stat",stat);
            return modelNview;
        }
        
        @GetMapping("filterFaculty")
        public String calculateSt(@RequestParam("filter") String faculty,Model m){ 
            List<Student> filtered = new LinkedList<Student>();;
            for(Student st:students){
                if(st.getFaculty().toLowerCase().equals(faculty.toLowerCase())){
                filtered.add(st);
            }
           
            }
            m.addAttribute("filter", faculty);
            m.addAttribute("filtered", filtered);
            return "listed";
        }
}
