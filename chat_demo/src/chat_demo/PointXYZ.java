package chat_demo;

import java.awt.Point;
import java.awt.Toolkit;



public class PointXYZ 
{
	public Point getXY(int x,int y)
	{
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		int width=toolkit.getScreenSize().width;
		int height=toolkit.getScreenSize().height;
		return new Point((width-x)/2,(height-y)/2);
	}

}
