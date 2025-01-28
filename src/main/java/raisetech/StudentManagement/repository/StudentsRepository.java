package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.StudentManagement.data.Student;

/**　受講生情報のRepository　*/
@Mapper
public interface StudentsRepository {

  /**
   * 受講生の全件検索
   * @return 全件受講生情報
   */
  @Select("SELECT * FROM students")
  List<Student> getStudentsList();

  /**
   * 受講生情報をIDで検索
   * @param id 受講生ID
   * @return 受講生情報
   */
  @Select("SELECT * FROM students WHERE id = #{id}")
  Student findByStudentId(int id);
}
