package chat_demo;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class SelectFriend extends JFrame {

	private JTable table;
	private JTextField textField;
	/**
	 * Launch the application
	 * @param args
	 */
	
	public Vector cols=new Vector();
	public Vector rows=new Vector();
	
	
	
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
					SelectFriend frame = new SelectFriend();
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
	public SelectFriend() {
		super();
		setTitle("好友查找");
		getContentPane().setLayout(null);
		setBounds(100, 100, 488, 456);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		
		
		PointXYZ p=new PointXYZ();
		setLocation(p.getXY(getSize().width,getSize().height));
		
		
		
		final JLabel label = new JLabel();
		label.setFont(new Font("", Font.PLAIN, 14));
		label.setText("好友昵称：");
		label.setBounds(10, 22, 81, 18);
		getContentPane().add(label);

		textField = new JTextField();
		textField.setBounds(84, 15, 311, 34);
		getContentPane().add(textField);

		final JButton button = new JButton();
		button.setText("查找");
		button.setBounds(401, 15, 60, 29);
		getContentPane().add(button);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 451, 337);
		getContentPane().add(scrollPane);

		cols.add("昵称");
		cols.add("在线");
		table = new JTable(rows,cols);
		scrollPane.setViewportView(table);
		//
	}

}
