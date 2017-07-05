package chat_demo;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class OneJpanel extends JPanel implements Comparable<OneJpanel>,MouseListener{

	/**
	 * ��������һ�����ѵ�������ԣ��������ѵ�������������Ϣ��ͷ�����ʾͼƬ��
	 *  
	 *  * Create the panel
	 */
	private String image;
	private String netName;
	private String info;
	private String uid;
	private JLabel label_image;
	private JLabel label_netName;
	private JLabel label_info;
	private boolean online=false;

	public OneJpanel(String uid,String image,String netName,String info) 
	{
		super();
		this.uid=uid;
		this.netName=netName;
		this.info=info;
		this.image=image;
		
		//
		
		
		
		this.setLayout(null);

		label_image = new JLabel();
		label_image.setBounds(4, 4, 48, 48);
		add(label_image);
		if(image=="def"){
			label_image.setIcon(new ImageIcon("face1/" +888+ ".png"));
		}else{
			
		label_image.setIcon(new ImageIcon("face1/" + image + ".png"));}	
				
		label_netName = new JLabel();
		label_netName.setBounds(58, 7, 478, 18);
		add(label_netName);
		label_netName.setFont(new Font("΢���ź�", Font.BOLD, 14));
		label_netName.setText(netName);

		label_info = new JLabel();
		label_info.setBounds(58, 34, 478, 18);
		add(label_info);
		label_info.setText(info);
		this.addMouseListener(this);
	}
	
	
	public void setImage(String image)
	{	
		this.image = image;
	}

	public void setNetname(String netName) {
		
		this.netName = netName;
	}

	public void setInfo(String info) {	
		this.info = info;
	}
	
	public void setOnline(boolean o) {	
		this.online =o;
	}


	public int compareTo(OneJpanel y) {
		// TODO �Զ����ɵķ������
		//System.out.println("�Ƚ�������");
		if(online==true&&y.online==false) return -1;
		else if(online==false&&y.online==true) return 1;
		else return 0; 
	}
	
	public void setLableImage(boolean o)
	{
		if(image=="def"){
			label_image.setIcon(new ImageIcon("face0/" +007+ ".png"));
		}else if(o){
			
		label_image.setIcon(new ImageIcon("face0/" + image + ".png"));}
		else
		{
			label_image.setIcon(new ImageIcon("face1/" + image + ".png"));
			
		}
	
	}


	public void mouseClicked(MouseEvent e) {
		// TODO �Զ����ɵķ������
		if (e.getClickCount() == 2) {
			System.out.println("һ������������"+netName+" ��ע�ǣ�"+info+" ͷ���ǣ�"+image );
			
			new ChatWindows(uid, netName, info, image);
			
		}
	}


	public void mouseEntered(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		xx = this.getX();
		yy = this.getY();
		this.setLocation(xx - 3, yy - 3);
	}


	public void mouseExited(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		this.setLocation(xx, yy);
	}


	public void mousePressed(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}


	public void mouseReleased(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
	
	int xx = 0;
	int yy = 0;
	

}
