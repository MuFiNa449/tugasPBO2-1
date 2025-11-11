package form;
import javax.swing.*;
import java.sql.*;
import koneksi.Koneksi;

public class FrmLogin {
    public static void main(String[] args) {
        String username = JOptionPane.showInputDialog("Masukkan Username:");
        String password = JOptionPane.showInputDialog("Masukkan Password:");

        try (Connection conn = Koneksi.getKoneksi()) {
            String sqlAdmin = "SELECT * FROM admin_183 WHERE username=? AND password=?";
            PreparedStatement pstA = conn.prepareStatement(sqlAdmin);
            pstA.setString(1, username);
            pstA.setString(2, password);
            ResultSet rsA = pstA.executeQuery();

            if (rsA.next()) {
                JOptionPane.showMessageDialog(null, "Login sebagai Admin");
                FrmKasus.main(null);
                return;
            }

            String sqlUser = "SELECT * FROM user_183 WHERE username=? AND password=?";
            PreparedStatement pstU = conn.prepareStatement(sqlUser);
            pstU.setString(1, username);
            pstU.setString(2, password);
            ResultSet rsU = pstU.executeQuery();

            if (rsU.next()) {
                JOptionPane.showMessageDialog(null, "Login sebagai User");
                FrmPelapor.main(null);
                return;
            }

            JOptionPane.showMessageDialog(null, "Username atau password salah!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kesalahan koneksi: " + e.getMessage());
        }
    }
}
