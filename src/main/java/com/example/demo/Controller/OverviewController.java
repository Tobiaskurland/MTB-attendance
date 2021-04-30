package com.example.demo.Controller;

import com.example.demo.Model.Course;
import com.example.demo.Model.Lecture;
import com.example.demo.Model.User;
import com.example.demo.Service.ICourseService;
import com.example.demo.Service.ILectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.*;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class OverviewController
{
    @Autowired
    private ILectureService lectureService;

    @Autowired
    private ICourseService courseService;

    @GetMapping("/course/{courseID}")
    public String getCourseOverviewPage(@PathVariable int courseID, @RequestParam("weeknumber") int weekNumber, @RequestParam("year") int year, HttpSession session, Model model)
    {
        User user = getLoggedInUser(session);

        if(user != null)
        {
            List<Lecture> lectures = lectureService.findLecturesByCourseIdForWeek(courseID, weekNumber, year);
            Course course = courseService.findById(courseID);

            model.addAttribute("week", weekNumber);
            model.addAttribute("year", year);
            model.addAttribute("lectures", lectures);
            model.addAttribute("course", course);
            model.addAttribute("role", user.getRole_id());

            System.out.println(user.getRole_id());

            LocalDate monday = LocalDate.now()
                    .withYear(year)
                    .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, weekNumber)
                    .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

            List<Lecture> mondayList = lecturesForDate(lectures, monday);
            List<Lecture> tuesdayList = lecturesForDate(lectures, monday.plusDays(1));
            List<Lecture> wednesdayList = lecturesForDate(lectures, monday.plusDays(2));
            List<Lecture> thursdayList = lecturesForDate(lectures, monday.plusDays(3));
            List<Lecture> fridayList = lecturesForDate(lectures, monday.plusDays(4));
            List<Lecture> saturdayList = lecturesForDate(lectures, monday.plusDays(5));
            List<Lecture> sundayList = lecturesForDate(lectures, monday.plusDays(6));

            model.addAttribute("monday", mondayList);
            model.addAttribute("tuesday", tuesdayList);
            model.addAttribute("wednesday", wednesdayList);
            model.addAttribute("thursday", thursdayList);
            model.addAttribute("friday", fridayList);
            model.addAttribute("saturday", saturdayList);
            model.addAttribute("sunday", sundayList);

            model.addAttribute("startdate", monday.toString());
            model.addAttribute("enddate", monday.plusDays(6).toString());

            return "CourseOverview";
        }
        else
        {
            return "redirect:/";
        }
    }

    @GetMapping("/overview")
    public String getOverviewPage(HttpSession session, Model model)
    {
        User user = getLoggedInUser(session);
        if(user != null)
        {
            List<Course> courses = courseService.findCourseByClassId(1);
            List<Lecture> todaysLectures = lectureService.findLecturesByDate(LocalDate.now());

            model.addAttribute("lectures", lecturesForDate(todaysLectures, LocalDate.now()));

            int weekNumber = LocalDate.now().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
            int year = LocalDate.now().getYear();

            String statsUrl = "/student/statistics";

            if(user.getRole_id() != 1)
            {
                statsUrl = "/teacher/statistics";
            }

            model.addAttribute("role", user.getRole_id());
            model.addAttribute("courses", courses);
            model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
            model.addAttribute("week", weekNumber);
            model.addAttribute("year", year);
            model.addAttribute("statsUrl", statsUrl);

            return "Overview";
        }
        else
        {
            return "redirect:/";
        }
    }

    private User getLoggedInUser(HttpSession session)
    {
        try
        {
            User user = (User) session.getAttribute("login");
            return user;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    private List<Lecture> lecturesForDate(List<Lecture> lectures, LocalDate date)
    {
        List<Lecture> l = Arrays.asList(new Lecture[6]);

        for (int i = 0; i < lectures.size(); i++)
        {
            if(lectures.get(i).getDate().toLocalDate().equals(date))
            {
                l.set(lectures.get(i).getTimeInterval() - 1, lectures.get(i));
            }
        }

        for (int i = 0; i < 6; i++)
        {
            if(i > l.size())
            {
                l.add(i, null);
            }
        }
        return l;
    }
}
