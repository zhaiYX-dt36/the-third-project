package com.zhu.printscreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

import com.zhu.chatframe.ChatFrame;


public class Operation extends JFrame implements MouseMotionListener,MouseListener{
	private BufferedImage bi;
	private BufferedImage drawBoard;       //画板，将需要画的东西全部画在此画板，最后一次性画在屏幕上，防止闪烁
	private Dimension di;
	private Image toolsImage;               //工具栏图标
	private Point clickPos,pos1,pos8;               //记录鼠标点击的位置,和1,8点的位置
	private Rectangle shotArea;           //截图区域
	private Rectangle sizeRect;            //显示截图区域的大小
	private Rectangle rect[];             //截图位置控制点，小矩形
	private Rectangle saveRec;             //保存按钮
	private Rectangle exitRec;				//退出按钮
	private Rectangle cutRec;			//完成按钮
	private Point pos[];       			   //控制点的位置
	private int mouseIn;       			 //标记鼠标当前所在的矩形
	private int state;      			//判断当前的状态   0：还未开始截图  1：正在截图    2：截图完毕
	private ChatFrame chatFrame;
	public Operation(BufferedImage bi,ChatFrame chatFrame){
		this.bi=bi;
		this.chatFrame=chatFrame;
		shotArea=new Rectangle();
		sizeRect=new Rectangle();
		drawBoard=new BufferedImage(bi.getWidth(),bi.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
		pos=new Point[9];
		rect=new Rectangle[9];
		for(int i=0;i<=8;i++){              //实例化
			pos[i]=new Point();
			rect[i]=new Rectangle();
		}
		saveRec=new Rectangle();
		exitRec=new Rectangle();
		cutRec=new Rectangle();
		this.setUndecorated(true);   //禁用此 frame 的装饰
		this.setAlwaysOnTop(true);   //使该窗口始终保持在最上方
		state=0;             //初始化为还未截图
		mouseIn=0;
		Toolkit toolKit=Toolkit.getDefaultToolkit();  //获取工具类
		di=toolKit.getScreenSize();                 //获取屏幕的大小
		this.setBounds(0, 0, di.width, di.height);  
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addMouseListener(this);       //添加鼠标事件
		this.addMouseMotionListener(this);
		toolsImage=new ImageIcon("images//tool.png").getImage();
	}
	public void paint(Graphics g){ 
		Graphics2D g2d=drawBoard.createGraphics();
		g2d.drawImage(bi, 0, 0, di.width, di.height,this);
		Color color=new Color(2,105,208);
		g2d.setColor(color);       //设置颜色
		if(state!=0){
			drawPos(g2d);
		}
		if(state==2){
			this.drawTools(g2d);
		}
		g.drawImage(drawBoard, 0, 0, di.width,di.height,this);
	}
	public void drawTools(Graphics g){
		sizeRect.setBounds(shotArea.x, shotArea.y-20,100,20);
		g.setColor(new Color(65,65,65));
		g.fillRect(sizeRect.x,sizeRect.y,sizeRect.width, sizeRect.height);
		g.setColor(Color.WHITE);
		String size=shotArea.width+" * "+shotArea.height;
		g.drawString(size,sizeRect.x+15,sizeRect.y+15);
		g.drawImage(toolsImage, shotArea.x+shotArea.width-toolsImage.getWidth(this), shotArea.y+shotArea.height+5,this);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(state==1){
			pos[8]=e.getPoint();
			this.repaint();
		}else if(state==2&&mouseIn!=0){
			Point currentPos=e.getPoint();    //当前鼠标的位置
			this.reSetPos(mouseIn, currentPos);
			this.repaint();
		}
	}
	private void reSetPos(int i,Point p) {      //通过用户的调整在次调整截图区域的范围
		// TODO Auto-generated method stub
		switch(i){                          //只需要改变pos[1]和pos[8]即可，其他的点会通过drawPos方法确定
		case 1:  pos[1]=p;     break;
		case 2:  pos[1].y=p.y; break;
		case 3:  pos[1].y=p.y; pos[8].x=p.x;break;
		case 4:  pos[8].x=p.x; break;
		case 5:  pos[1].x=p.x; break;
		case 6:  pos[1].x=p.x; pos[8].y=p.y;	break;
		case 7:  pos[8].y=p.y; break;
		case 8:  pos[8]=p;	   break;
		case 9:  
			pos[1].x=pos1.x+p.x-clickPos.x;
			pos[1].y=pos1.y+p.y-clickPos.y;
			pos[8].x=pos8.x+p.x-clickPos.x;
			pos[8].y=pos8.y+p.y-clickPos.y;
			break;
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(state==0){  //如果还未开始截图就将当前点设为初始点
			state=1;
			pos[1]=e.getPoint();
			pos[8]=e.getPoint();
		}else if(state==2){
			Point p=e.getPoint();     //获取鼠标当前的位置
			for(int i=1;i<=8;i++){    //判断鼠标是否在小矩形的范围内
				if(rect[i].contains(p)){
						mouseIn=i;    //标记当前鼠标在哪个小矩形内
						break;
				}
			}
			if(shotArea.contains(p)){       //判断鼠标是否在截图区域内点击
				mouseIn=9;                  //标记鼠标在截图区域
				clickPos=new Point(p.x, p.y);
				pos1=new Point(pos[1].x, pos[1].y);
				pos8=new Point(pos[8].x, pos[8].y);
				
			}else if(saveRec.contains(p)){
				BufferedImage saveImage=bi.getSubimage(shotArea.x,shotArea.y,shotArea.width,shotArea.height);//将截图区域转化为一个BufferedImage对象
				this.setVisible(false);	  //退出截图界面
				this.savePic(saveImage);       //保存图片
						//显示聊天界面
				chatFrame.setVisible(true);
			}else if(cutRec.contains(p)){
				BufferedImage image=bi.getSubimage(shotArea.x,shotArea.y,shotArea.width,shotArea.height);
				this.cutPic(image);             //将图片复制到系统剪切板 使Ctrl v即可粘贴出来
				this.setVisible(false);
				chatFrame.setVisible(true);
			}else if(exitRec.contains(p)){
				this.setVisible(false);
				chatFrame.setVisible(true);
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(state==1){
			state=2;
			this.repaint();
		}else if(state==2){
			mouseIn=0;       //鼠标离开时不再标记当前鼠标在哪个矩形内
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	} 
	public void drawPos(Graphics g){     //通过pos[1],和pos[8]两个点的位置来确定其余剩下点的位置并画出来。
		int x1=pos[1].x;
		int y1=pos[1].y;
		int x2=pos[8].x; 			//    位置演示
		int y2=pos[8].y;            //   pos[1]			pos[2]			pos[3]
		pos[2].x=(x2+x1)/2;		    //
		pos[2].y=y1;				//	 pos[5]							pos[4]
		pos[3].x=x2;				//
		pos[3].y=y1;				//	 pos[6]			pos[7]			pos[8]
		pos[4].x=x2;
		pos[4].y=(y1+y2)/2;
		pos[5].x=x1;
		pos[5].y=(y1+y2)/2;
		pos[6].x=x1;
		pos[6].y=y2;
		pos[7].x=(x1+x2)/2;
		pos[7].y=y2;
		for(int i=1;i<=8;i++){
			rect[i].setBounds(pos[i].x-2, pos[i].y-2,5,5);
		}
		
		cutRec.setBounds(pos[8].x-80,pos[8].y,80,30);
		saveRec.setBounds(pos[8].x-160,pos[8].y,80,30);
		exitRec.setBounds(pos[8].x-240, pos[8].y,80,30);
		
		shotArea.x=pos[1].x<pos[8].x?pos[1].x:pos[8].x;
		shotArea.y=pos[1].y<pos[8].y?pos[1].y:pos[8].y;
		shotArea.width=Math.abs(pos[1].x-pos[8].x);
		shotArea.height=Math.abs(pos[1].y-pos[8].y);
		g.drawRect(shotArea.x, shotArea.y, shotArea.width, shotArea.height);  //画出截图区域
		for(int i=0;i<=8;i++){   //画小矩形
			g.fillRect(rect[i].x,rect[i].y,rect[i].width, rect[i].height);
		}
	}
	public void cutPic(BufferedImage image){   //将图片复制到系统剪切板  使用 ctrl v即可粘贴出来
		Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
		ImageSelection selection=new ImageSelection(image);
		clipboard.setContents(selection, null);
	}
	public void savePic(BufferedImage image){     //保存图片操作
		String style="jpg";
		JFileChooser jFileChooser=new JFileChooser();
		jFileChooser.addChoosableFileFilter(new JPGFilter());
		jFileChooser.addChoosableFileFilter(new PNGFilter());
		jFileChooser.addChoosableFileFilter(new GIFFilter());
		jFileChooser.showSaveDialog(this);
		File file=jFileChooser.getSelectedFile();
		if(file==null)
			return;
		String fileName=file.toString().toLowerCase();
		FileFilter fileFilter=jFileChooser.getFileFilter();
		if(fileFilter instanceof JPGFilter){
			if(!fileName.endsWith(".jpg")){
				fileName+=".jpg";
			}
			style="jpg";
		}else if(fileFilter instanceof PNGFilter){
			if(!fileName.endsWith(".png")){
				fileName+=".png";
			}
			style="png";
		}else if(fileFilter instanceof GIFFilter){
			if(!fileName.endsWith(".gif")){
				fileName+=".gif";
			}
			style="gif";
		}
		File saveFile=new File(fileName);
		try {
			ImageIO.write(image, style, saveFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private class JPGFilter extends FileFilter{

		@Override
		public boolean accept(File f) {
			// TODO Auto-generated method stub
			if(f.toString().toLowerCase().endsWith(".jpg"))
				return true;
			return false;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return "(.jpg)";
		}
		
	}
	private class PNGFilter extends FileFilter{

		@Override
		public boolean accept(File f) {
			// TODO Auto-generated method stub
			if(f.toString().toLowerCase().endsWith(".png"))
				return true;
			return false;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return "(.png)";
		}
		
	} 
	private class GIFFilter extends FileFilter{

		@Override
		public boolean accept(File f) {
			// TODO Auto-generated method stub
			if(f.toString().toLowerCase().endsWith(".gif"))
				return true;
			return false;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return "(.gif)";
		}
		
	}
}
