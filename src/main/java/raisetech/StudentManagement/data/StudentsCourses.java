package raisetech.StudentManagement.data;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 受講生コースの情報を格納するクラス
 * 受講生ID、コースID、受講開始日、受講終了日
 */

@Getter
@Setter
public class StudentsCourses {

  private int id;
  private int studentId;
  private int courseId;
  private LocalDateTime courseStartDate;
  private LocalDateTime courseEndDate;

}
