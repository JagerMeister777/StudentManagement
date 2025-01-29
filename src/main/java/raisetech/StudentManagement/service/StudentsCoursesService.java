package raisetech.StudentManagement.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.StudentsCoursesDTO;
import raisetech.StudentManagement.data.StudentsCourses;
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
   * 受講生の条件検索（Javaフルコースの学生を検索）
   * @return Javaフルコースの受講生リスト
   */
  public List<StudentsCoursesDTO> getStudentsCoursesList() {
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
}
