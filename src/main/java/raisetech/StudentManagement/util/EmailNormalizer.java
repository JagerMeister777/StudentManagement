package raisetech.StudentManagement.util;

import java.net.IDN;
import java.text.Normalizer;

public class EmailNormalizer {
  public static String normalizeEmail(String email) {
    if (email == null || email.trim().isEmpty()) {
      return null; // 無効な入力
    }

    // 1. 前後の空白を削除 & 小文字変換
    email = email.trim().toLowerCase();

    // 2. メールアドレスの形式チェック
    if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
      return null;
    }

    // 3. Unicode正規化（NFKC: 全角→半角など）
    email = Normalizer.normalize(email, Normalizer.Form.NFKC);

    // 4. ローカル部分とドメイン部分を分離
    String[] parts = email.split("@", 2);
    String local = parts[0];
    String domain = parts[1];

    // 5. 国際ドメイン名（IDN）をPunycodeに変換し、小文字化
    domain = IDN.toASCII(domain).toLowerCase();

    // 6. Gmail のエイリアス処理
    if (domain.equals("gmail.com") || domain.equals("googlemail.com")) {
      local = local.split("\\+")[0];  // `+` 以降を削除
      local = local.replace(".", ""); // `.` を削除
    }

    // 7. RFC違反の修正（連続 `..` を `.` に置換、末尾の `.` を削除）
    local = local.replaceAll("\\.{2,}", "."); // 連続する `..` を `.` に置換
    local = local.replaceAll("\\.+$", "");    // 末尾の `.` を削除

    // 8. 正規化されたメールアドレスを返す
    return local + "@" + domain;
  }
}
