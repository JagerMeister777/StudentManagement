package raisetech.StudentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCoursesDTO;
import raisetech.StudentManagement.service.StudentsCoursesService;
import raisetech.StudentManagement.service.StudentsService;

@Controller
public class StudentsController {

  /** 受講生情報のService */
  private StudentsService studentsService;

  /** 受講生コース情報のService */
  private StudentsCoursesService studentsCoursesService;

  private StudentConverter studentConverter;

  @Autowired
  public StudentsController(StudentsService studentsService,
      StudentsCoursesService studentsCoursesService, StudentConverter studentConverter) {
    this.studentsService = studentsService;
    this.studentsCoursesService = studentsCoursesService;
    this.studentConverter = studentConverter;
  }


  /**
   * 受講生情報の全件検索
   * @return 受講生情報の全件リスト
   */
  @GetMapping("/students")
  public List<Student> getStudentsList() {
    return studentsService.getStudentsList();
  }

  /**
   * 受講生情報を出力
   * @return 受講生情報(受講生の名前、受講しているコース、受講開始日、受講終了日)
   */
  @GetMapping("/studentsCourses")
  public List<StudentsCoursesDTO> getStudentsCoursesList() {
    return studentsCoursesService.getJavaStudentsCoursesList();
  }

  @GetMapping("/studentsList")
  public String getStudentDetail(Model model) {
    model.addAttribute("studentList",studentConverter.studentConverter(studentsService.getStudentsList()));
    return "studentList";
  }

  @GetMapping("/studentsCoursesList")
  public String getStudentCoursesList(Model model) {
    model.addAttribute("studentsCoursesDTO",studentsCoursesService.getAllStudentsCoursesList());
    return "studentsCoursesList";
  }
}
