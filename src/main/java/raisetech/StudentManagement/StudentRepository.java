package raisetech.StudentManagement;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM student_info WHERE name = #{name}")
  Student searchStudentInfo(String name);

  @Select("SELECT * FROM student_info")
  Student getAllStudent();

  @Insert("INSERT student_info VALUES(#{name}, #{age})")
  void resisterStudent(String name, int age);

  @Update("UPDATE student_info SET age = #{age} WHERE name = #{name}")
  void updateStudentInfo(String name, int age);

  @Delete("DELETE FROM student_info WHERE name = #{name}")
  void deleteStudent(String name);

}
