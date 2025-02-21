package raisetech.StudentManagement.exceptions;

/**
 * 更新処理時にフィールドに不備があった場合に例外をthrowする
 */
public class UpdateFieldBindingException extends RuntimeException{
  public UpdateFieldBindingException(String message) {
    super(message);
  }
}
