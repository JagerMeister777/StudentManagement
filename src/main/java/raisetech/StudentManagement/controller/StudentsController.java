package raisetech.StudentManagement.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCoursesDTO;
import raisetech.StudentManagement.exceptions.InvalidDateRangeException;
import raisetech.StudentManagement.exceptions.ExistStudentsCoursesException;
import raisetech.StudentManagement.exceptions.ExistStudentEmailException;
import raisetech.StudentManagement.form.RegisterStudentForm;
import raisetech.StudentManagement.form.UpdateStudentForm;
import raisetech.StudentManagement.service.CoursesService;
import raisetech.StudentManagement.service.StudentsCoursesService;
import raisetech.StudentManagement.service.StudentsService;

@Controller
public class StudentsController {

  /** 受講生情報のService */
  private final StudentsService studentsService;

  /** コース情報のService */
  private final CoursesService coursesService;

  /** 受講生コース情報のService */
  private final StudentsCoursesService studentsCoursesService;

  /** 受講生情報のコンバーター */
  private final StudentConverter studentConverter;

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
   *
   * @return 受講生情報の全件リスト
   */
  @GetMapping("/students")
  public List<Student> getStudentsList() {
    return studentsService.getStudentsList();
  }

  /**
   * 受講生情報を出力
   *
   * @return 受講生情報(受講生の名前 、 受講しているコース 、 受講開始日 、 受講終了日)
   */
  @GetMapping("/studentsCourses")
  public List<StudentsCoursesDTO> getStudentsCoursesList() {
    return studentsCoursesService.getJavaStudentsCoursesList();
  }

  /**
   * 受講生リストをテンプレートにレンダリング
   *
   * @param model 受講生リスト
   * @return studentsList.html
   */
  @GetMapping("/studentsList")
  public String getStudentDetail(Model model) {
    model.addAttribute("studentList",
        studentConverter.studentConverter(studentsService.getStudentsList()));
    return "studentsList";
  }

  /**
   * 受講生コース情報リストをテンプレートにレンダリング
   *
   * @param model 受講生コース情報リスト
   * @return studentsCoursesList.html
   */
  @GetMapping("/studentsCoursesList")
  public String getStudentCoursesList(Model model) {
    model.addAttribute("studentsCoursesDTO", studentsCoursesService.getAllStudentsCoursesList());
    return "studentsCoursesList";
  }

  /**
   * 受講生登録画面の表示
   *
   * @param model 受講生登録フォームとコースリスト
   * @return 受講生登録画面
   */
  @GetMapping("/register/student")
  public String registerStudent(Model model) {
    RegisterStudentForm registerStudentForm = new RegisterStudentForm();
    model.addAttribute("registerStudentForm", registerStudentForm);
    model.addAttribute("coursesList", coursesService.getCoursesList());
    return "registerStudent";
  }

  /**
   * 受講生登録のPOST
   *
   * @param model              エラーメッセージ、コースリスト
   * @param form               受講生登録フォーム
   * @param redirectAttributes Successメッセージ
   * @return 受講生の新規登録 → 受講生一覧、受講生が登録済み → 受講生コース情報一覧
   */
  @PostMapping("/register/student")
  public String registerStudent(Model model,
      @Valid @ModelAttribute RegisterStudentForm form,
      BindingResult result,
      RedirectAttributes redirectAttributes) {
    try {
      if (result.hasErrors()) {
        model.addAttribute("registerStudentForm", form);
        model.addAttribute("coursesList", coursesService.getCoursesList());
        return "registerStudent";
      } else if (form.getCourseEndDate().isBefore(form.getCourseStartDate())) {
        throw new InvalidDateRangeException("終了日は開始日より後でなければなりません。");
      }
      String message = studentsCoursesService.registerHandling(form);
      if (message.contains("コース情報が登録されました。")) {
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/studentsCoursesList";
      } else {
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/studentsList";
      }
    } catch (ExistStudentsCoursesException | InvalidDateRangeException e) {
      model.addAttribute("message", e.getMessage());
      model.addAttribute("coursesList", coursesService.getCoursesList());
      model.addAttribute("registerStudentForm", form);
      return "registerStudent";
    }
  }

  /**
   * 受講生情報の詳細画面の表示
   *
   * @param id    受講生ID
   * @param model 受講生情報、受講生コース情報DTO
   * @return 受講生情報の詳細画面
   */
  @GetMapping("/student/{id}")
  public String studentDetailView(@PathVariable("id") int id, Model model) {
    Student student = studentsService.findByStudentId(id);
    List<StudentsCoursesDTO> studentsCoursesDTO = studentsCoursesService.getStudentsCoursesDTO(id);
    model.addAttribute("student", student);
    model.addAttribute("studentsCoursesDTO", studentsCoursesDTO);
    return "studentDetail";
  }

  /**
   * 受講生情報の更新画面の表示
   *
   * @param id    受講生ID
   * @param model 受講生更新フォーム
   * @return 受講生更新画面
   */
  @GetMapping("/update/student/{id}")
  public String updateStudentView(@PathVariable("id") int id, Model model) {
    Student student = studentsService.findByStudentId(id);
    List<StudentsCoursesDTO> studentsCoursesList = studentsCoursesService.getStudentsCoursesDTO(id);
    UpdateStudentForm updateStudentForm = new UpdateStudentForm(
        id,
        student.getFullName(),
        student.getFurigana(),
        student.getNickName(),
        student.getEmail(),
        student.getLivingArea(),
        student.getAge(),
        student.getGender(),
        student.getRemark(),
        false,
        studentsCoursesList);
    model.addAttribute("updateStudentForm", updateStudentForm);
    return "updateStudent";
  }

  /**
   * 受講生更新処理
   *
   * @param id                 受講性ID
   * @param form               受講生更新フォーム
   * @param model              更新処理が成功したら、受講生詳細画面。エラーが発生したら、更新画面。
   * @param redirectAttributes 受講生詳細画面
   * @return 画面遷移先
   */
  @PostMapping("/update/student/{id}")
  public String updateStudent(
      @PathVariable("id") int id,
      @Valid @ModelAttribute("updateStudentForm") UpdateStudentForm form,
      BindingResult result,
      Model model,
      RedirectAttributes redirectAttributes) {
    try {
      if (result.hasErrors()) {
        model.addAttribute("updateStudentForm", form);
        return "updateStudent";
      }
      form.getStudentsCoursesList().forEach(studentsCoursesDTO -> {
        if (studentsCoursesDTO.getCourseEndDate()
            .isBefore(studentsCoursesDTO.getCourseStartDate())) {
          throw new InvalidDateRangeException("終了日は開始日より後でなければなりません。");
        }
      });
      String message = studentsCoursesService.updateHandling(form);
      redirectAttributes.addFlashAttribute("message", message);
      return "redirect:/student/" + id;
    } catch (ExistStudentEmailException | InvalidDateRangeException e) {
      model.addAttribute("updateStudentForm", form);
      model.addAttribute("message", e.getMessage());
      return "updateStudent";
    }
  }

  @PatchMapping("/delete/student/{id}")
  public String deleteStudent(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
    studentsService.deleteStudent(id);
    redirectAttributes.addFlashAttribute("message",
        studentsService.findByStudentId(id).getFullName() + " を削除しました。");
    return "redirect:/studentsList";
  }
}
