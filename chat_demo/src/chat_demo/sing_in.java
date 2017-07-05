package chat_demo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;










import net.sf.json.JSONObject;
import chat_deal.ClientDeal;
import chat_deal.ClientThread;
import chat_deal.Config;



//import com.kaigetongxun.view.LoginDialog;

public class sing_in extends JDialog {

	private JPasswordField password2;
	private JPasswordField password1;
	private JTextField code;
	private JTextField res_username;
	/**
	 * 
	 */
	private static final long serialVersionUID = -9165043788029080199L;
	private JPasswordField password;
	private JTextField username;
	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String args[]) 
	{
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
//		

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sing_in frame = new sing_in();
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
	public sing_in() {
		super();
		setResizable(false);
		setAlwaysOnTop(true);
		setTitle("自制聊天软件PP");
		getContentPane().setLayout(null);
		setBounds(100, 100, 349, 319);//736,330
		PointXYZ p=new PointXYZ();
		setLocation(p.getXY(getSize().width,getSize().height));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		final JLabel label = new JLabel();
		label.setBackground(Color.DARK_GRAY);
		label.setFont(new Font("@宋体", Font.PLAIN, 14));
		label.setText("手  机：");
		label.setBounds(10, 60, 65, 18);
		getContentPane().add(label);

		final JLabel label_1 = new JLabel();
		label_1.setFont(new Font("@宋体", Font.PLAIN, 14));
		label_1.setText("Email：");
		label_1.setBounds(10, 84, 65, 18);
		getContentPane().add(label_1);

		username = new JTextField();
		username.setBackground(Color.WHITE);
		username.setBounds(66, 60, 222, 43);
		getContentPane().add(username);

		final JLabel label_2 = new JLabel();
		label_2.setFont(new Font("@宋体", Font.PLAIN, 14));
		label_2.setText("密  码：");
		label_2.setBounds(10, 151, 65, 18);
		getContentPane().add(label_2);

		password = new JPasswordField();
		password.setBounds(66, 138, 222, 37);
		getContentPane().add(password);

		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) 
			{
				if(getHeight()==330)
				{
					setSize(349,736);
				}else{setSize(349,330);}
				PointXYZ p=new PointXYZ();
				setLocation(p.getXY(getSize().width,getSize().height));
			}
		});
		button.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		button.setBackground(Color.GRAY);
		button.setText("注   册");
		button.setBounds(41, 219, 97, 30);
		getContentPane().add(button);
		//1027435905@qq.com
		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) 
			{
				String username_str = username.getText().trim();
				String password_str = password.getText().trim();
				if (username_str.trim().equals("") || password_str.trim().equals("")) {
					javax.swing.JOptionPane.showMessageDialog(sing_in.this, "用户名和密码必须填写!");
					return;
				}
				Config.username = username_str;
				Config.password = password_str;
				try {
					JSONObject s=ClientDeal.getClientDeal().clientLogin();
					if(s.getInt("state")==0)
					{
						javax.swing.JOptionPane.showMessageDialog(sing_in.this, "登录成功!");
					    //Socket	socket=new Socket(Config.IP,Config.LOGIN_PORT);
						//new Thread(new ClientThread(socket)).start();												
						ClientDeal.getClientDeal().startNewThread();						
						sing_in.this.setVisible(false);
						sing_in.this.dispose();
						while(true)
						{
							//System.out.println("等待数据中：");
							//System.out.println(Config.my_feiend_online);
							if(Config.my_feiend_online!=null)               /////等待第一次的线程完成后，才处理，不然可能会没有数据
							{
								Config.fl=new FriendList2();
								Config.fl.setVisible(true);
								break;
							}																				
						}						
					}else
					{
						javax.swing.JOptionPane.showMessageDialog(sing_in.this, s.getString("msg"));
					}														
				} catch (UnknownHostException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}								
			}
		}
				);
		button_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		button_1.setBackground(Color.GRAY);
		button_1.setText("登   录");
		button_1.setBounds(191, 219, 97, 30);
		getContentPane().add(button_1);

		final JPanel panel = new JPanel();
		panel.setFont(new Font("宋体", Font.BOLD, 12));
		panel.setBorder(new TitledBorder(null, "用户注册", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("@宋体", Font.BOLD, 14), null));
		panel.setLayout(null);
		panel.setBounds(32, 301, 287, 347);
		getContentPane().add(panel);

		final JLabel label_3 = new JLabel();
		label_3.setText("手 机");
		label_3.setBounds(10, 52, 80, 18);
		panel.add(label_3);

		final JLabel emailLabel = new JLabel();
		emailLabel.setText("email");
		emailLabel.setBounds(10, 76, 80, 18);
		panel.add(emailLabel);

		res_username = new JTextField();
		res_username.setBounds(73, 49, 187, 45);
		panel.add(res_username);

		final JLabel label_4 = new JLabel();
		label_4.setText("验 证 码：");
		label_4.setBounds(10, 165, 80, 18);
		panel.add(label_4);

		code = new JTextField();
		code.setBounds(86, 161, 90, 27);
		panel.add(code);

		final JLabel label_5 = new JLabel();
		label_5.setText("密    码：");
		label_5.setBounds(10, 214, 80, 18);
		panel.add(label_5);

		final JLabel label_6 = new JLabel();
		label_6.setText("确认密码：");
		label_6.setBounds(10, 255, 80, 18);
		panel.add(label_6);

		password1 = new JPasswordField();
		password1.setBounds(87, 210, 173, 27);
		panel.add(password1);

		password2 = new JPasswordField();
		password2.setBounds(87, 251, 173, 27);
		panel.add(password2);

		final JButton button_3 = new JButton();
		button_3.setFont(new Font("", Font.BOLD, 14));
		button_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		button_3.setText("放弃");
		button_3.setBounds(10, 304, 121, 27);
		panel.add(button_3);

		final JButton button_4 = new JButton();
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) 
			{
				String username_str = res_username.getText().trim();
				String code_z = code.getText().trim();
				String  password1_z= password1.getText().trim();
				String password2_z = password2.getText().trim();
				if (username_str.trim().equals("") || code_z.trim().equals("")||password1_z.trim().equals("") || password2_z.trim().equals("")) {
					javax.swing.JOptionPane.showMessageDialog(sing_in.this, "有资料未填写，请完善资料再试！");
					return;
				}				
				if(!password1_z.equals(password2_z))
				{
					javax.swing.JOptionPane.showMessageDialog(sing_in.this, "两次密码不一致，请核对再试！");
					return;
				}
				
				Socket socket3;
				InputStream in;
				OutputStream out;
				
				try {
					socket3 = new Socket(Config.IP,Config.EEG_PORT);
					in=socket3.getInputStream();
				    out=socket3.getOutputStream();
				    /////////////发送注册请求了
				    out.write(("{\"type\":\"reg\",\"username\":\"" + username_str + "\",\"password\":\""
							+ password1_z + "\",\"code\":\"" + code_z + "\"}").getBytes());
				    out.flush();
				    
				    byte[] bt=new byte[1024];
				    int len=in.read(bt);
				    String getinfo=new String(bt,0,len);
				    javax.swing.JOptionPane.showMessageDialog(sing_in.this, getinfo);	
				} catch (UnknownHostException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			    			
												
			}
		});
		button_4.setFont(new Font("@宋体", Font.BOLD, 14));
		button_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		button_4.setText("注册");
		button_4.setBounds(156, 304, 121, 27);
		panel.add(button_4);

		final JButton button_3_1 = new JButton();
		button_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) 
			{
				if (res_username.getText().trim().equals("")) {
					javax.swing.JOptionPane.showMessageDialog(sing_in.this, "用户名不能为空!");
					return;
				}else
				{
					String username=res_username.getText().trim();
					try{
					    Socket socket2=new Socket(Config.IP,Config.EEG_PORT);
					    InputStream in=socket2.getInputStream();
					    OutputStream out=socket2.getOutputStream();
					    
					    out.write(("{\"type\":\"code\",\"username\":\"" + username + "\"}").getBytes());
					    out.flush();
					    
					    byte[] bt=new byte[1024];
					    int len=in.read(bt);
					    String getinfo=new String(bt,0,len);
					    javax.swing.JOptionPane.showMessageDialog(sing_in.this, getinfo);					
					}catch(IOException e)
					{
						e.printStackTrace();
					}					
				}				
			}
		});
		button_3_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		button_3_1.setFont(new Font("Dialog", Font.BOLD, 14));
		button_3_1.setText("发送验证");
		button_3_1.setBounds(140, 115, 121, 27);
		panel.add(button_3_1);
		//
	}
}
