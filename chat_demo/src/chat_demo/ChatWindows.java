package chat_demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;




import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import net.sf.json.JSONObject;
import chat_deal.Config;
import chat_deal.Msg;





public class ChatWindows extends JFrame implements WindowListener {

	
	private JTextArea textArea_1;
	/**
	 * Launch the application
	 * @param args
	 */
	
	
	final JLabel infoLabel = new JLabel();
	final JLabel netnameLabel = new JLabel();
	final JLabel img = new JLabel();
	private JTextArea textArea;

	private String uidStr;
	private String netnameStr;
	private String imgStr;
	private String infoStr;

	/**
	 * Create the frame
	 */
	
	
	public void addMessage(String msg) {

		String str = "\n" + netnameStr + "\t" + new Date().toLocaleString() + "\n" + msg + "\n";

		textArea_1.setText(textArea_1.getText() + str);

		textArea_1.setSelectionStart(textArea_1.getText().toString().length());
		textArea_1.setSelectionEnd(textArea_1.getText().toString().length());
		textArea.requestFocus();
	}
	
	
	
	public void addMyMessage(Msg msg) {

		String str = "\n" + JSONObject.fromObject(Config.my_json_data).getString("netname") + "\t"
				+ new Date().toLocaleString() + "\n" + msg.getMsg() + "\n";

		textArea_1.setText(textArea_1.getText() + str);
		textArea_1.setSelectionStart(textArea_1.getText().toString().length());
		textArea_1.setSelectionEnd(textArea_1.getText().toString().length());
		
		textArea.requestFocus();
	}
	
	
	
	
	

	
	
	public ChatWindows(final String uidStr, String netnameStr, String infoStr, String imgStr) {
		super();
		this.uidStr = uidStr;
		this.netnameStr = netnameStr;
		this.imgStr = imgStr;
		this.infoStr = infoStr;

		this.setTitle(netnameStr);
		ImageIcon imageIcon = new ImageIcon("face0/" + imgStr + ".png");

		this.setIconImage(imageIcon.getImage());
        System.out.println("备注为："+infoStr);
		infoLabel.setText(infoStr);
		System.out.println("网名为："+netnameStr);
		netnameLabel.setText(netnameStr);
		netnameLabel.setPreferredSize(new Dimension(2, 2));
		img.setIcon(imageIcon);

		setBounds(100, 100, 559, 655);

		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.NORTH);

		img.setPreferredSize(new Dimension(48, 48));
		panel.add(img, BorderLayout.WEST);

		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout());
		panel.add(panel_1, BorderLayout.CENTER);

		netnameLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		panel_1.add(netnameLabel, BorderLayout.CENTER);

		panel_1.add(infoLabel, BorderLayout.SOUTH);

		final JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(400);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		final JPanel panel_2 = new JPanel();
		panel_2.setLayout(new BorderLayout());
		splitPane.setRightComponent(panel_2);

		final JPanel panel_3 = new JPanel();
		final FlowLayout flowLayout_1 = new FlowLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_3.setLayout(flowLayout_1);
		panel_2.add(panel_3, BorderLayout.NORTH);

		final JButton button_2 = new JButton();
		button_2.setText("字体");
		panel_3.add(button_2);

		final JButton button_3 = new JButton();
		button_3.setText("震屏");
		panel_3.add(button_3);

		final JPanel panel_4 = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_4.setLayout(flowLayout);
		panel_2.add(panel_4, BorderLayout.SOUTH);

		final JButton button = new JButton();
		button.setText("关闭");
		panel_4.add(button);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {

				// {“type”:”msg”,”toUID”:””,”MyUID”:””,”msg”:””,”code”:””}
				try {
					Msg msg = new Msg();
					msg.setCode(System.currentTimeMillis() + "");
					msg.setMyUID(JSONObject.fromObject(Config.my_json_data).getString("uid"));
					msg.setToUID(uidStr);
					String mss=textArea.getText();
					if(mss.equals(""))
					{
						javax.swing.JOptionPane.showMessageDialog(ChatWindows.this, "不能发送空信息！");
						return;
					}else{
					msg.setMsg(mss);
					}
					msg.setType("msg");

					String json = JSONObject.fromObject(msg).toString();
					byte[] bytes = json.getBytes();
					System.out.println("即将从客户端发到服务器的数据"+json);
                    //////////////////////发送给服务器的聊天数据包
					DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length,
							InetAddress.getByName(Config.IP), 30002); 
					Config.socket.send(datagramPacket);
					textArea.setText("");
					
					
					addMyMessage(msg);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button_1.setText("　发　送　");
		panel_4.add(button_1);

		final JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		final JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setLeftComponent(scrollPane_1);

		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);

		//this.addWindowListener(this);
		Config.oneframe=this;
		this.setVisible(true);
		//
	}

	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public void windowClosing(WindowEvent e) {

		//Config.closeLiaotianFrame(uidStr);

		this.dispose();
	}

	
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}


}
