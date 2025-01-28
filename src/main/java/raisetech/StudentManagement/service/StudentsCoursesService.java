package raisetech.StudentManagement.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentInfo;
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
  public List<StudentInfo> getStudentsCoursesList() {
    // 「Javaフルコース」のIDを取得して、「Javaフルコース」のコース情報のみを抽出する。
    //  受講生IDから参照して絞り込みした学生リストをコントローラーに渡す。

    // ID:1はJavaフルコース
    // TODO 動的にコース名で検索できるようにする
    int courseId = 1;

    // TODO 例外処理の実装
    // 対象のコースを受講している受講生情報の全件取得
    List<StudentsCourses> studentsCoursesList = studentsCoursesRepository.getStudentsCourses()
        .stream()
        .filter(studentsCourses -> studentsCourses.getCourseId() == courseId)
        .toList();

    List<StudentInfo> studentInfoList = new ArrayList<>();

    // TODO 例外処理の実装
    // StudentInfoクラスで出力
    for(int i = 1; i < studentsCoursesList.size(); i++) {
      StudentInfo studentInfo = new StudentInfo();
      studentInfo.setStudentName(studentsService.findByStudentId(studentsCoursesList.get(i).getStudentId()));
      studentInfo.setCourseName(coursesService.findByCourseId(courseId));
      studentInfo.setCourseStartDate(studentsCoursesList.get(i).getCourseStartDate());
      studentInfo.setCourseEndDate(studentsCoursesList.get(i).getCourseEndDate());
      studentInfoList.add(studentInfo);
    }

    return studentInfoList;
  }
}
