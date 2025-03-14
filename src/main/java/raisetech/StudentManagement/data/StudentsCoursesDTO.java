package raisetech.StudentManagement.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 最終的な受講生情報を出力するためのクラス
 * 受講生の名前、受講しているコース名、受講開始日、受講終了日
 */

@Getter
@Setter
public class StudentsCoursesDTO {

  private String studentName;
  private String courseName;
  private LocalDateTime courseStartDate;
  private LocalDateTime courseEndDate;

  public StudentsCoursesDTO(String studentName, String courseName, LocalDateTime courseStartDate,
      LocalDateTime courseEndDate) {
    this.studentName = studentName;
    this.courseName = courseName;
    this.courseStartDate = courseStartDate;
    this.courseEndDate = courseEndDate;
  }
}
