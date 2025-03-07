package raisetech.StudentManagement.exceptions;

/**
 * 更新処理時にフィールドに不備があった場合に例外をthrowする
 */
public class ExistStudentEmailException extends RuntimeException{
  public ExistStudentEmailException(String message) {
    super(message);
  }
}
