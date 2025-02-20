package raisetech.StudentManagement.form;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 受講生登録画面のフォームに入力された情報をバインドします。
 */

@Getter
@Setter
public class RegisterStudentForm {
  
  private String fullName;
  private String furigana;
  private String nickName;
  private String email;
  private String livingArea;
  private int age;
  private String gender;
  private String remark;
  private boolean isDeleted;
  private String courseName;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private LocalDateTime courseStartDate;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private LocalDateTime courseEndDate;

}
