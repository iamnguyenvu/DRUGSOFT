package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThongKeNhanVien extends JFrame {

    public ThongKeNhanVien() {
        setTitle("Dashboard Form");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Top Panel for Search Options
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(2, 6, 5, 5));

        // Search options
        JLabel timeTypeLabel = new JLabel("Loại thời gian");
        JComboBox<String> timeTypeCombo = new JComboBox<>(new String[]{"Báo cáo theo ngày", "Báo cáo theo tháng"});
        
        JLabel companyLabel = new JLabel("Công ty");
        JTextField companyField = new JTextField();

        JLabel startDateLabel = new JLabel("Ngày bắt đầu");
        JTextField startDateField = new JTextField("01/01/2020");

        JLabel endDateLabel = new JLabel("Ngày kết thúc");
        JTextField endDateField = new JTextField("09/01/2020");

        JLabel keywordLabel = new JLabel("Từ khóa");
        JTextField keywordField = new JTextField();

        JLabel filterLabel = new JLabel("Lọc tất cả");
        JComboBox<String> filterCombo = new JComboBox<>(new String[]{"Tất cả", "Option 1", "Option 2"});

        JButton searchButton = new JButton("Tìm kiếm");

        // Adding search options to the search panel
        searchPanel.add(timeTypeLabel);
        searchPanel.add(timeTypeCombo);
        searchPanel.add(companyLabel);
        searchPanel.add(companyField);
        searchPanel.add(startDateLabel);
        searchPanel.add(startDateField);
        searchPanel.add(endDateLabel);
        searchPanel.add(endDateField);
        searchPanel.add(keywordLabel);
        searchPanel.add(keywordField);
        searchPanel.add(filterLabel);
        searchPanel.add(filterCombo);
        
        // Summary Panel for displaying summary values
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new GridLayout(1, 3, 10, 10));
        
        JPanel revenuePanel = createSummaryPanel("Doanh thu", "531.109.486");
        JPanel capitalPanel = createSummaryPanel("Tổng vốn", "0");
        JPanel returnsPanel = createSummaryPanel("Trả hàng", "0");

        summaryPanel.add(revenuePanel);
        summaryPanel.add(capitalPanel);
        summaryPanel.add(returnsPanel);

        // Chart Panel
        JPanel chartPanel = new JPanel();
        chartPanel.setLayout(new BorderLayout());
        
        JLabel chartTitle = new JLabel("LỢI NHUẬN CAO NHẤT", JLabel.CENTER);
        chartTitle.setFont(new Font("Arial", Font.BOLD, 16));
        
        String[] employees = {"Minh Đăng", "Thảo Nhiên"};
        int[] profits = {350_000_000, 200_000_000};
        
        JPanel barChartPanel = createBarChart(employees, profits);
        
        chartPanel.add(chartTitle, BorderLayout.NORTH);
        chartPanel.add(barChartPanel, BorderLayout.CENTER);
        
        // Main layout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(searchPanel, BorderLayout.NORTH);
        getContentPane().add(summaryPanel, BorderLayout.CENTER);
        getContentPane().add(chartPanel, BorderLayout.SOUTH);

        // Search button action
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Search clicked!");
            }
        });
    }

    private JPanel createSummaryPanel(String title, String value) {
        JPanel panel = new JPanel();
        
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setBounds(0, 0, 886, 17);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel valueLabel = new JLabel(value, JLabel.CENTER);
        valueLabel.setBounds(0, 17, 886, 377);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 18));
        valueLabel.setForeground(Color.BLUE);
        panel.setLayout(null);
        
        panel.add(titleLabel);
        panel.add(valueLabel);
        
        return panel;
    }

    private JPanel createBarChart(String[] names, int[] values) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(names.length, 1, 5, 5));

        for (int i = 0; i < names.length; i++) {
            JPanel barPanel = new JPanel(new BorderLayout());
            JLabel nameLabel = new JLabel(names[i], JLabel.LEFT);
            JLabel valueLabel = new JLabel(String.format("%,d", values[i]), JLabel.RIGHT);

            JPanel bar = new JPanel();
            bar.setBackground(Color.BLUE);
            bar.setPreferredSize(new Dimension(values[i] / 1_000_000, 20));

            barPanel.add(nameLabel, BorderLayout.WEST);
            barPanel.add(bar, BorderLayout.CENTER);
            barPanel.add(valueLabel, BorderLayout.EAST);
            
            panel.add(barPanel);
        }

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ThongKeNhanVien().setVisible(true));
    }
}
