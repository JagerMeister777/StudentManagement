package raisetech.StudentManagement.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCoursesDTO;
import raisetech.StudentManagement.form.RegisterStudentForm;
import raisetech.StudentManagement.service.CoursesService;
import raisetech.StudentManagement.service.StudentsCoursesService;
import raisetech.StudentManagement.service.StudentsService;

@Controller
public class StudentsController {

  /** 受講生情報のService */
  private StudentsService studentsService;

  private CoursesService coursesService;

  /** 受講生コース情報のService */
  private StudentsCoursesService studentsCoursesService;

  private StudentConverter studentConverter;

  @Autowired
  public StudentsController(StudentsService studentsService, CoursesService coursesService,
      StudentsCoursesService studentsCoursesService, StudentConverter studentConverter) {
    this.studentsService = studentsService;
    this.coursesService = coursesService;
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

  /**
   * 受講生リストをテンプレートにレンダリング
   * @param model 受講生リスト
   * @return studentList.html
   */
  @GetMapping("/studentsList")
  public String getStudentDetail(Model model) {
    model.addAttribute("studentList",studentConverter.studentConverter(studentsService.getStudentsList()));
    return "studentList";
  }

  /**
   * 受講生コース情報リストをテンプレートにレンダリング
   * @param model 受講生コース情報リスト
   * @return studentsCoursesList.html
   */
  @GetMapping("/studentsCoursesList")
  public String getStudentCoursesList(Model model) {
    model.addAttribute("studentsCoursesDTO",studentsCoursesService.getAllStudentsCoursesList());
    return "studentsCoursesList";
  }

  /**
   * 受講生登録画面の表示
   * @param model 受講生登録フォームとコースリスト
   * @return 受講生登録画面
   */
  @GetMapping("/register/student")
  public String registerStudent(Model model) {
    RegisterStudentForm registerStudentForm = new RegisterStudentForm();
    model.addAttribute("registerStudentForm", registerStudentForm);
    model.addAttribute("coursesList",coursesService.getCoursesList());
    return "registerStudent";
  }

  /**
   * 受講生登録のPOST
   * @param model エラーメッセージ、コースリスト
   * @param form 受講生登録フォーム
   * @param redirectAttributes Successメッセージ
   * @return 受講生の新規登録 → 受講生一覧、受講生が登録済み → 受講生コース情報一覧
   */
  @PostMapping("/register/student")
  public String registerStudent(Model model,@ModelAttribute RegisterStudentForm form,RedirectAttributes redirectAttributes) {
    Optional<Student> existedStudent = studentsService.findByEmail(form.getEmail());

    if(existedStudent.isPresent()) {
      if(studentsCoursesService.isExistingCombination(existedStudent.get().getId(),form.getCourseName())) {
        model.addAttribute("message","登録するコースを既に受講しています。");
        model.addAttribute("coursesList",coursesService.getCoursesList());
        model.addAttribute("registerStudentForm", form);
        return "registerStudent";
      }else{
        studentsCoursesService.registerStudentsCourses(form);
        redirectAttributes.addFlashAttribute("message",form.getFullName() + "：" + form.getCourseName() + "が登録されました。");
        return "redirect:/studentsCoursesList";
      }
    }else{
      studentsService.registerStudent(form);
      studentsCoursesService.registerStudentsCourses(form);

      redirectAttributes.addFlashAttribute("message",form.getFullName() + "が登録されました。");
      return "redirect:/studentsList";
    }
  }
}
