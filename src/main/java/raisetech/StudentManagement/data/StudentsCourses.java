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

  // 新規登録用コンストラクタ
  public StudentsCourses(int studentId, int courseId, LocalDateTime courseStartDate,
      LocalDateTime courseEndDate) {
    this.studentId = studentId;
    this.courseId = courseId;
    this.courseStartDate = courseStartDate;
    this.courseEndDate = courseEndDate;
  }

  // データ取得用コンストラクタ
  public StudentsCourses(int id, int studentId, int courseId, LocalDateTime courseStartDate,
      LocalDateTime courseEndDate) {
    this.id = id;
    this.studentId = studentId;
    this.courseId = courseId;
    this.courseStartDate = courseStartDate;
    this.courseEndDate = courseEndDate;
  }

}
