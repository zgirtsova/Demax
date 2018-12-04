import java.io.*;
import java.sql.*;

import singletons.ConnectionPoolSingleton;

public class PictureTest {
    private byte[] readFile(String file) throws IOException {

        ByteArrayOutputStream bos = null;

        try {
            File f = new File(file);
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        return bos != null ? bos.toByteArray() : null;

    }

    public void updatePicture(int pictureId, String filename) throws IOException {

        String updateSQL =
            "UPDATE pictures " + "SET picture = ? " + "WHERE id=?";

        try (Connection conn = ConnectionPoolSingleton.getInstance().getPooledConnection().getConnection();

                PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            pstmt.setBytes(1, readFile(filename));
            pstmt.setInt(2, pictureId);

            pstmt.executeUpdate();
            System.out.println("Stored the file in the pictures table.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
