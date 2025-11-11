package form;

import koneksi.Koneksi;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FrmPengacara extends javax.swing.JFrame {
    Connection conn;
    DefaultTableModel model;

    public FrmPengacara() {
        initComponents();
        conn = Koneksi.getKoneksi();
        loadTable();
    }

    private void loadTable() {
        String[] kolom = {"ID", "Nama", "Spesialisasi", "No Telp"};
        model = new DefaultTableModel(null, kolom);
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM pengacara");
            while (rs.next()) {
                Object[] data = {
                    rs.getInt("id_pengacara"),
                    rs.getString("nama_pengacara"),
                    rs.getString("spesialisasi"),
                    rs.getString("no_telp")
                };
                model.addRow(data);
            }
            tblPengacara.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal Memuat Data: " + e.getMessage());
        }
    }

    private void clearForm() {
        txtNama.setText("");
        txtSpesialis.setText("");
        txtTelp.setText("");
    }

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String sql = "INSERT INTO pengacara (nama_pengacara, spesialisasi, no_telp) VALUES (?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txtNama.getText());
            pst.setString(2, txtSpesialis.getText());
            pst.setString(3, txtTelp.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan");
            loadTable();
            clearForm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal Simpan: " + e.getMessage());
        }
    }

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tblPengacara.getSelectedRow();
        if (row != -1) {
            int id = Integer.parseInt(tblPengacara.getValueAt(row, 0).toString());
            try {
                String sql = "DELETE FROM pengacara WHERE id_pengacara=?";
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
        int row = tblPengacara.getSelectedRow();
        if (row != -1) {
            int id = Integer.parseInt(tblPengacara.getValueAt(row, 0).toString());
            try {
                String sql = "UPDATE pengacara SET nama_pengacara=?, spesialisasi=?, no_telp=? WHERE id_pengacara=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, txtNama.getText());
                pst.setString(2, txtSpesialis.getText());
                pst.setString(3, txtTelp.getText());
                pst.setInt(4, id);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Diperbarui");
                loadTable();
                clearForm();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal Update: " + e.getMessage());
            }
        }
    }

    private void tblPengacaraMouseClicked(java.awt.event.MouseEvent evt) {
        int row = tblPengacara.getSelectedRow();
        txtNama.setText(tblPengacara.getValueAt(row, 1).toString());
        txtSpesialis.setText(tblPengacara.getValueAt(row, 2).toString());
        txtTelp.setText(tblPengacara.getValueAt(row, 3).toString());
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setTitle("Data Pengacara");
        setSize(600, 420);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel l1 = new JLabel("Nama");
        l1.setBounds(20, 20, 80, 25);
        add(l1);
        txtNama = new JTextField();
        txtNama.setBounds(110, 20, 300, 25);
        add(txtNama);

        JLabel l2 = new JLabel("Spesialisasi");
        l2.setBounds(20, 55, 80, 25);
        add(l2);
        txtSpesialis = new JTextField();
        txtSpesialis.setBounds(110, 55, 300, 25);
        add(txtSpesialis);

        JLabel l3 = new JLabel("No Telp");
        l3.setBounds(20, 90, 80, 25);
        add(l3);
        txtTelp = new JTextField();
        txtTelp.setBounds(110, 90, 140, 25);
        add(txtTelp);

        btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(20, 130, 90, 28);
        btnSimpan.addActionListener(evt -> btnSimpanActionPerformed(evt));
        add(btnSimpan);

        btnEdit = new JButton("Edit");
        btnEdit.setBounds(120, 130, 90, 28);
        btnEdit.addActionListener(evt -> btnEditActionPerformed(evt));
        add(btnEdit);

        btnHapus = new JButton("Hapus");
        btnHapus.setBounds(220, 130, 90, 28);
        btnHapus.addActionListener(evt -> btnHapusActionPerformed(evt));
        add(btnHapus);

        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(20, 170, 540, 200);
        add(scroll);

        tblPengacara = new JTable();
        tblPengacara.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPengacaraMouseClicked(evt);
            }
        });
        scroll.setViewportView(tblPengacara);
    }

    private javax.swing.JTextField txtNama, txtSpesialis, txtTelp;
    private javax.swing.JButton btnSimpan, btnEdit, btnHapus;
    private javax.swing.JTable tblPengacara;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrmPengacara().setVisible(true));
    }
}
