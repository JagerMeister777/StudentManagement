package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.StudentManagement.data.Course;

/**
 * コース情報のRepository
 */
@Mapper
public interface CoursesRepository {

  /**
   * コース情報の全件検索
   * @return 全件コース情報
   */
  @Select("SELECT * FROM courses")
  List<Course> getCoursesList();

  /**
   * コース情報をIDで検索
   * @param id コースID
   * @return コース情報
   */
  @Select("SELECT * FROM courses WHERE id = #{id}")
  Course findByCourseId(int id);
}
