package raisetech.StudentManagement.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.StudentManagement.data.StudentsCourses;

@Getter
@Setter
public class UpdateStudentForm {

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

  List<StudentsCourses> studentsCoursesList;
}
