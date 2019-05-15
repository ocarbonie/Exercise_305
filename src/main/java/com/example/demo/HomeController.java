package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @RequestMapping("/")
    public String index(Model model) {
        Student student = new Student();
        student.setName("Sandra Bullock");
        student.setClassLevel("Junior");

        Course course = new Course();
        course.setTitle("Calculus");
        course.setInstructor("Dr. Mao");
        course.setDescription("The worse class known to man");
        course.setCredit(4);

        //Add the course to an empty list
        Set<Course> courses = new HashSet<Course>();
        courses.add(course);

        //Add the list of movies to the actor's movie list
        student.setCourses(courses);

        //Save the actor to the database
        studentRepository.save(student);

        //Grade all the students from the database to send them to the template
        model.addAttribute("students", studentRepository.findAll());
        return "index";
    }
}