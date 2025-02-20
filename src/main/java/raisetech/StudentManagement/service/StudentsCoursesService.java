package raisetech.StudentManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCoursesDTO;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.exceptions.ExistedStudentsCoursesException;
import raisetech.StudentManagement.form.RegisterStudentForm;
import raisetech.StudentManagement.repository.StudentsCoursesRepository;

/** 受講生コース情報のService */
@Service
public class StudentsCoursesService {

  /** 受講生情報のService */
  private StudentsService studentsService;

  /** コース情報のService */
  private CoursesService coursesService;

  /** 受講生コース情報のRepository */
  private StudentsCoursesRepository studentsCoursesRepository;

  @Autowired
  public StudentsCoursesService(StudentsService studentsService,
      StudentsCoursesRepository studentsCoursesRepository, CoursesService coursesService) {
    this.studentsService = studentsService;
    this.studentsCoursesRepository = studentsCoursesRepository;
    this.coursesService = coursesService;
  }

  /**
   * 特定の受講生コース情報を取得する
   * @param studentId 受講生ID
   * @return リスト型の受講生コース情報（複数件の可能性を考慮）
   */
  public List<StudentsCourses> getStudentsCoursesList(int studentId) {
    return studentsCoursesRepository.getStudentsCoursesList(studentId);
  }

  /**
   * 受講生コース情報の全件を取得し、DTOへ変換してリストで返す
   * @return 全件StudentCoursesDTO
   */
  public List<StudentsCoursesDTO> getAllStudentsCoursesList() {
    List<StudentsCourses> allStudentsCoursesList = studentsCoursesRepository.getAllStudentsCoursesList();

    List<StudentsCoursesDTO> studentsCoursesDTOList = new ArrayList<>();

    for(StudentsCourses studentsCourses: allStudentsCoursesList) {
      StudentsCoursesDTO studentsCoursesDTO = new StudentsCoursesDTO();

      studentsCoursesDTO.setStudentName(studentsService.findByStudentId(studentsCourses.getStudentId()));
      studentsCoursesDTO.setCourseName(coursesService.findByCourseId(studentsCourses.getCourseId()));
      studentsCoursesDTO.setCourseStartDate(studentsCourses.getCourseStartDate());
      studentsCoursesDTO.setCourseEndDate(studentsCourses.getCourseEndDate());

      studentsCoursesDTOList.add(studentsCoursesDTO);
    }

    return studentsCoursesDTOList;
  }

  /**
   * 受講生の条件検索（Javaフルコースの学生を検索）
   * @return Javaフルコースの受講生リスト
   */
  public List<StudentsCoursesDTO> getJavaStudentsCoursesList() {
    // 「Javaフルコース」のIDを取得して、「Javaフルコース」のコース情報のみを抽出する。
    //  受講生IDから参照して絞り込みした学生リストをコントローラーに渡す。

    // ID:1はJavaフルコース
    // TODO 動的にコース名で検索できるようにする
    final int JAVA_COURSE_ID = 1;

    // TODO 例外処理の実装
    // 対象のコースを受講している受講生情報の全件取得
    List<StudentsCourses> javaStudentsList = studentsCoursesRepository.javaStudentsList(JAVA_COURSE_ID);

    List<StudentsCoursesDTO> studentsCoursesDTOList = new ArrayList<>();

    // TODO 例外処理の実装
    // DTOクラスで出力
    for(StudentsCourses studentsCourses: javaStudentsList) {
      StudentsCoursesDTO studentsCoursesDTO = new StudentsCoursesDTO();

      studentsCoursesDTO.setStudentName(studentsService.findByStudentId(studentsCourses.getStudentId()));
      studentsCoursesDTO.setCourseName(coursesService.findByCourseId(JAVA_COURSE_ID));
      studentsCoursesDTO.setCourseStartDate(studentsCourses.getCourseStartDate());
      studentsCoursesDTO.setCourseEndDate(studentsCourses.getCourseEndDate());

      studentsCoursesDTOList.add(studentsCoursesDTO);
    }

    return studentsCoursesDTOList;
  }

  /**
   * 受講生コース情報で受講生IDとコースIDの組み合わせが存在するか確認する
   * @param studentId 受講生ID
   * @param courseName コースID
   * @return true or false
   */
  public boolean isExistingCombination(int studentId, String courseName) {
    Optional<StudentsCourses> isExistingCombination = studentsCoursesRepository.isExistingCombination(studentId,coursesService.findByCourseName(courseName));
    return isExistingCombination.isPresent();
  }

  /**
   * 受講生コース情報の登録
   * @param form 登録フォームの情報
   */
  @Transactional
  public void registerStudentsCourses(RegisterStudentForm form) {
    Optional<Student> student = studentsService.findByEmail(form.getEmail());

    int studentId = student.get().getId();
    int courseId = coursesService.findByCourseName(form.getCourseName());

    StudentsCourses registerStudentsCourses = new StudentsCourses(
        studentId,
        courseId,
        form.getCourseStartDate(),
        form.getCourseEndDate()
    );

    studentsCoursesRepository.save(registerStudentsCourses);
  }

  /**
   * 受講生情報の登録処理
   * @param form 受講生登録フォームに入力された情報
   * @return メッセージ
   */
  public String registerHandling(RegisterStudentForm form) {
    Optional<Student> existedStudent = studentsService.findByEmail(form.getEmail());
    if (existedStudent.isPresent()) {
      // 既に登録されている場合
      if (isExistingCombination(existedStudent.get().getId(), form.getCourseName())) {
        throw new ExistedStudentsCoursesException("登録するコースを既に受講しています。");
      } else {
        // コース情報のみ登録
        registerStudentsCourses(form);
        return "コース情報が登録されました。  " + form.getFullName() + "：" + form.getCourseName();
      }
    } else {
      // 新規登録の場合
      studentsService.registerStudent(form);
      registerStudentsCourses(form);
      return "受講生情報が登録されました。  " + form.getFullName();
    }
  }
}
