package chat_demo;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Information extends JFrame {

	private JTextArea textArea;
	private JComboBox comboBox_3;
	private JComboBox comboBox_2;
	private JComboBox comboBox;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField;
	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String args[]) {
		
		
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
//		

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Information frame = new Information();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame
	 */
	public Information() {
		super();
		setTitle("个人资料");
		getContentPane().setLayout(null);
		setBounds(100, 100, 553, 568);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
		PointXYZ p=new PointXYZ();
		setLocation(p.getXY(getSize().width,getSize().height));

		final JLabel label = new JLabel();
		label.setPreferredSize(new Dimension(70, 70));
		label.setText("New JLabel");
		label.setBounds(10, 11, 70, 70);
		getContentPane().add(label);

		textField = new JTextField();
		textField.setBounds(101, 28, 256, 30);
		getContentPane().add(textField);

		textField_2 = new JTextField();
		textField_2.setBounds(99, 60, 372, 30);
		getContentPane().add(textField_2);

		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "详细信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel.setLayout(null);
		panel.setBounds(15, 107, 496, 392);
		getContentPane().add(panel);

		final JLabel label_1 = new JLabel();
		label_1.setFont(new Font("@宋体", Font.PLAIN, 14));
		label_1.setText("真实姓名：");
		label_1.setBounds(10, 44, 70, 18);
		panel.add(label_1);

		final JLabel label_2 = new JLabel();
		label_2.setFont(new Font("@宋体", Font.PLAIN, 14));
		label_2.setText("性别：");
		label_2.setBounds(245, 44, 66, 18);
		panel.add(label_2);

		textField_1 = new JTextField();
		textField_1.setBounds(86, 39, 111, 31);
		panel.add(textField_1);

		final JRadioButton radioButton = new JRadioButton();
		radioButton.setText("男");
		radioButton.setBounds(301, 41, 47, 26);
		panel.add(radioButton);

		final JRadioButton radioButton_1 = new JRadioButton();
		radioButton_1.setText("女");
		radioButton_1.setBounds(351, 41, 38, 26);
		panel.add(radioButton_1);

		final JLabel label_3 = new JLabel();
		label_3.setFont(new Font("@宋体", Font.PLAIN, 14));
		label_3.setText("出身年月：");
		label_3.setBounds(10, 107, 70, 18);
		panel.add(label_3);

		comboBox = new JComboBox();
		comboBox.setBounds(86, 104, 62, 27);
		panel.add(comboBox);

		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(193, 104, 61, 27);
		panel.add(comboBox_2);

		comboBox_3 = new JComboBox();
		comboBox_3.setBounds(301, 104, 62, 27);
		panel.add(comboBox_3);

		final JLabel label_4 = new JLabel();
		label_4.setText("年");
		label_4.setBounds(154, 108, 21, 18);
		panel.add(label_4);

		final JLabel label_4_1 = new JLabel();
		label_4_1.setText("月");
		label_4_1.setBounds(260, 108, 21, 18);
		panel.add(label_4_1);

		final JLabel label_4_2 = new JLabel();
		label_4_2.setText("日");
		label_4_2.setBounds(369, 108, 21, 18);
		panel.add(label_4_2);

		final JLabel label_5 = new JLabel();
		label_5.setFont(new Font("@宋体", Font.PLAIN, 14));
		label_5.setText("备      注：");
		label_5.setBounds(10, 177, 70, 18);
		panel.add(label_5);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(86, 177, 358, 188);
		panel.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		//
	}

}
