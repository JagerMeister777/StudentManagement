package raisetech.StudentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.Course;
import raisetech.StudentManagement.repository.CoursesRepository;

/**
 * コース情報のService
 */
@Service
public class CoursesService {

  /** コース情報のRepository */
  private CoursesRepository repository;

  @Autowired
  public CoursesService(CoursesRepository repository) {
    this.repository = repository;
  }


  /**
   * コース情報の全件取得
   * @return コース情報の全件リスト
   */
  public List<Course> getCoursesList(){
    return repository.getCoursesList();
  }

  /**
   * コース名をIDで検索
   * @param id コースID
   * @return コース名
   */
  public String findByCourseId(int id) {
    Course course = repository.findByCourseId(id);
    return course.getName();
  }
}
