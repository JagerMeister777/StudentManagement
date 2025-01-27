package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.StudentManagement.data.Course;

@Mapper
public interface CoursesRepository {

  @Select("SELECT * FROM courses")
  List<Course> getCoursesList();
}
