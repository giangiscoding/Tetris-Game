package tetris.framework.display;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.Timer;
import javax.swing.SwingConstants;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

public class Window {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 500;
    private static List<JPanel> activeMessageBoxes = new ArrayList<>(); // Danh sách các thông báo đang hiển thị
    private static JFrame window;

    /**Creates window<br>
     * Called on game startup
     */
    public static void create() {
        // Tạo đối tượng JFrame
        window = new JFrame("Tetris");

        // Tính toán tọa độ để cửa sổ nằm ở giữa màn hình
        int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

        int windowWidth = WIDTH;  // Chiều rộng của cửa sổ
        int windowHeight = HEIGHT;  // Chiều cao của cửa sổ

        int xPosition = (screenWidth - windowWidth) / 2;  // Tính toán vị trí x
        int yPosition = (screenHeight - windowHeight) / 2;  // Tính toán vị trí y

        // Đặt vị trí của cửa sổ
        window.setBounds(xPosition, yPosition, windowWidth, windowHeight);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(new GameScreen());
        window.setVisible(true);

        // In thông báo
        System.out.println("[Framework][Display]: Created window");
    }

    /** Tạo hộp thông báo */
    public static void createMessagePanel(String message) {
        // Xóa tất cả thông báo cũ trước khi thêm thông báo mới
        for (JPanel panel : activeMessageBoxes) {
            window.getLayeredPane().remove(panel);
        }
        activeMessageBoxes.clear();

        // Tạo JPanel cho thông báo lỗi với hình dáng bo góc
        JPanel messageBox = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Gọi phương thức của lớp cha để vẽ nền
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Chế độ làm mịn
                g2d.setColor(getBackground()); // Đặt màu nền
                int arcSize = 20; // Kích thước bo góc
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arcSize, arcSize); // Vẽ hình chữ nhật bo góc
            }
        };

        messageBox.setBackground(new Color(128, 0, 128)); // Màu tím đậm
        messageBox.setBounds(40, 100, 200, 30); // Vị trí và kích thước của hộp thông báo
        messageBox.setOpaque(false); // Đảm bảo không có nền vẽ thêm
        messageBox.setVisible(true); // Hiển thị hộp thông báo

        // Tạo JLabel chứa thông báo
        JLabel errorLabel = new JLabel(message, SwingConstants.CENTER);
        errorLabel.setForeground(Color.WHITE); // Màu chữ trắng
        messageBox.add(errorLabel); // Thêm JLabel vào JPanel

        // Thêm thông báo mới vào danh sách và LayeredPane
        activeMessageBoxes.add(messageBox);
        window.getLayeredPane().add(messageBox, Integer.valueOf(1)); // Thêm vào layered pane và đặt z-index cao hơn

        // Tự động xóa hộp thông báo sau 4 giây
        new Timer(4000, e -> {
            window.getLayeredPane().remove(messageBox); // Xóa khỏi LayeredPane
            window.revalidate(); // Cập nhật giao diện
            window.repaint(); // Vẽ lại giao diện
            activeMessageBoxes.remove(messageBox); // Loại bỏ khỏi danh sách
        }).start();
    }
}
