package raisetech.StudentManagement.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.data.StudentsCoursesDTO;

/**
 * 受講生更新画面のフォームに入力された情報をバインドします
 */

@Getter
@Setter
public class UpdateStudentForm {

  private int id;

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

  List<StudentsCoursesDTO> studentsCoursesList;

  public UpdateStudentForm(int id, String fullName, String furigana, String nickName, String email,
      String livingArea, int age, String gender, String remark, boolean isDeleted,
      List<StudentsCoursesDTO> studentsCoursesList) {
    this.id = id;
    this.fullName = fullName;
    this.furigana = furigana;
    this.nickName = nickName;
    this.email = email;
    this.livingArea = livingArea;
    this.age = age;
    this.gender = gender;
    this.remark = remark;
    this.isDeleted = isDeleted;
    this.studentsCoursesList = studentsCoursesList;
  }

  public boolean getIsDeleted() {  // 明示的に getter を定義
    return isDeleted;
  }
}
