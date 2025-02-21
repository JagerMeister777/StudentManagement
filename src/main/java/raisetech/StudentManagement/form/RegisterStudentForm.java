package raisetech.StudentManagement.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

  @NotBlank(message = "名前を入力してください。")
  private String fullName;

  @NotBlank(message = "フリガナを入力してください。")
  private String furigana;

  @NotBlank(message = "ニックネームを入力してください。")
  private String nickName;

  @NotBlank(message = "メールアドレスを入力してください。")
  @Email(message = "メールアドレスの形式が不正です。")
  private String email;

  @NotBlank(message = "住んでいる地域を入力してください。")
  private String livingArea;

  private int age;

  @NotBlank(message = "性別を選んでください。")
  private String gender;

  private String remark;
  private boolean isDeleted;

  @NotBlank
  private String courseName;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  @NotBlank
  private LocalDateTime courseStartDate;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  @NotBlank
  private LocalDateTime courseEndDate;

}
