package form;

import koneksi.Koneksi;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FrmKasus extends javax.swing.JFrame {

    Connection conn;
    DefaultTableModel model;

    public FrmKasus() {
        initComponents();
        conn = Koneksi.getKoneksi();
        loadTable();
    }

    private void loadTable() {
        String[] kolom = {"ID", "Nama Kasus", "Jenis Kasus", "Tanggal Mulai", "Status"};
        model = new DefaultTableModel(null, kolom);
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM kasus");
            while (rs.next()) {
                Object[] data = {
                    rs.getInt("id_kasus"),
                    rs.getString("nama_kasus"),
                    rs.getString("jenis_kasus"),
                    rs.getDate("tanggal_mulai"),
                    rs.getString("status")
                };
                model.addRow(data);
            }
            tblKasus.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal Memuat Data: " + e.getMessage());
        }
    }

    private void clearForm() {
        txtNamaKasus.setText("");
        txtJenisKasus.setText("");
        txtTanggalMulai.setText("");
        txtStatus.setText("");
    }

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String sql = "INSERT INTO kasus (nama_kasus, jenis_kasus, tanggal_mulai, status) VALUES (?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txtNamaKasus.getText());
            pst.setString(2, txtJenisKasus.getText());
            pst.setString(3, txtTanggalMulai.getText());
            pst.setString(4, txtStatus.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan");
            loadTable();
            clearForm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal Simpan: " + e.getMessage());
        }
    }

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tblKasus.getSelectedRow();
        if (row != -1) {
            int id = Integer.parseInt(tblKasus.getValueAt(row, 0).toString());
            try {
                String sql = "DELETE FROM kasus WHERE id_kasus=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, id);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Dihapus");
                loadTable();
                clearForm();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal Hapus: " + e.getMessage());
            }
        }
    }

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tblKasus.getSelectedRow();
        if (row != -1) {
            int id = Integer.parseInt(tblKasus.getValueAt(row, 0).toString());
            try {
                String sql = "UPDATE kasus SET nama_kasus=?, jenis_kasus=?, tanggal_mulai=?, status=? WHERE id_kasus=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, txtNamaKasus.getText());
                pst.setString(2, txtJenisKasus.getText());
                pst.setString(3, txtTanggalMulai.getText());
                pst.setString(4, txtStatus.getText());
                pst.setInt(5, id);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Diperbarui");
                loadTable();
                clearForm();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal Update: " + e.getMessage());
            }
        }
    }

    private void tblKasusMouseClicked(java.awt.event.MouseEvent evt) {
        int row = tblKasus.getSelectedRow();
        txtNamaKasus.setText(tblKasus.getValueAt(row, 1).toString());
        txtJenisKasus.setText(tblKasus.getValueAt(row, 2).toString());
        txtTanggalMulai.setText(tblKasus.getValueAt(row, 3).toString());
        txtStatus.setText(tblKasus.getValueAt(row, 4).toString());
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setTitle("Data Kasus Hukum");
        setSize(700, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lbl1 = new JLabel("Nama Kasus");
        lbl1.setBounds(20, 20, 100, 25);
        add(lbl1);

        txtNamaKasus = new JTextField();
        txtNamaKasus.setBounds(130, 20, 300, 25);
        add(txtNamaKasus);

        JLabel lbl2 = new JLabel("Jenis Kasus");
        lbl2.setBounds(20, 55, 100, 25);
        add(lbl2);

        txtJenisKasus = new JTextField();
        txtJenisKasus.setBounds(130, 55, 300, 25);
        add(txtJenisKasus);

        JLabel lbl3 = new JLabel("Tanggal Mulai (YYYY-MM-DD)");
        lbl3.setBounds(20, 90, 200, 25);
        add(lbl3);

        txtTanggalMulai = new JTextField();
        txtTanggalMulai.setBounds(230, 90, 120, 25);
        add(txtTanggalMulai);

        JLabel lbl4 = new JLabel("Status");
        lbl4.setBounds(20, 125, 100, 25);
        add(lbl4);

        txtStatus = new JTextField();
        txtStatus.setBounds(130, 125, 300, 25);
        add(txtStatus);

        btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(20, 165, 90, 28);
        btnSimpan.addActionListener(evt -> btnSimpanActionPerformed(evt));
        add(btnSimpan);

        btnEdit = new JButton("Edit");
        btnEdit.setBounds(120, 165, 90, 28);
        btnEdit.addActionListener(evt -> btnEditActionPerformed(evt));
        add(btnEdit);

        btnHapus = new JButton("Hapus");
        btnHapus.setBounds(220, 165, 90, 28);
        btnHapus.addActionListener(evt -> btnHapusActionPerformed(evt));
        add(btnHapus);

        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(20, 210, 640, 200);
        add(scroll);

        tblKasus = new JTable();
        tblKasus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKasusMouseClicked(evt);
            }
        });
        scroll.setViewportView(tblKasus);
    }

    private javax.swing.JTextField txtNamaKasus, txtJenisKasus, txtTanggalMulai, txtStatus;
    private javax.swing.JButton btnSimpan, btnEdit, btnHapus;
    private javax.swing.JTable tblKasus;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrmKasus().setVisible(true));
    }
}
