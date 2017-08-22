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
	private BufferedImage drawBoard;       //���壬����Ҫ���Ķ���ȫ�����ڴ˻��壬���һ���Ի�����Ļ�ϣ���ֹ��˸
	private Dimension di;
	private Image toolsImage;               //������ͼ��
	private Point clickPos,pos1,pos8;               //��¼�������λ��,��1,8���λ��
	private Rectangle shotArea;           //��ͼ����
	private Rectangle sizeRect;            //��ʾ��ͼ����Ĵ�С
	private Rectangle rect[];             //��ͼλ�ÿ��Ƶ㣬С����
	private Rectangle saveRec;             //���水ť
	private Rectangle exitRec;				//�˳���ť
	private Rectangle cutRec;			//��ɰ�ť
	private Point pos[];       			   //���Ƶ��λ��
	private int mouseIn;       			 //�����굱ǰ���ڵľ���
	private int state;      			//�жϵ�ǰ��״̬   0����δ��ʼ��ͼ  1�����ڽ�ͼ    2����ͼ���
	private ChatFrame chatFrame;
	public Operation(BufferedImage bi,ChatFrame chatFrame){
		this.bi=bi;
		this.chatFrame=chatFrame;
		shotArea=new Rectangle();
		sizeRect=new Rectangle();
		drawBoard=new BufferedImage(bi.getWidth(),bi.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
		pos=new Point[9];
		rect=new Rectangle[9];
		for(int i=0;i<=8;i++){              //ʵ����
			pos[i]=new Point();
			rect[i]=new Rectangle();
		}
		saveRec=new Rectangle();
		exitRec=new Rectangle();
		cutRec=new Rectangle();
		this.setUndecorated(true);   //���ô� frame ��װ��
		this.setAlwaysOnTop(true);   //ʹ�ô���ʼ�ձ��������Ϸ�
		state=0;             //��ʼ��Ϊ��δ��ͼ
		mouseIn=0;
		Toolkit toolKit=Toolkit.getDefaultToolkit();  //��ȡ������
		di=toolKit.getScreenSize();                 //��ȡ��Ļ�Ĵ�С
		this.setBounds(0, 0, di.width, di.height);  
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addMouseListener(this);       //�������¼�
		this.addMouseMotionListener(this);
		toolsImage=new ImageIcon("images//tool.png").getImage();
	}
	public void paint(Graphics g){ 
		Graphics2D g2d=drawBoard.createGraphics();
		g2d.drawImage(bi, 0, 0, di.width, di.height,this);
		Color color=new Color(2,105,208);
		g2d.setColor(color);       //������ɫ
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
			Point currentPos=e.getPoint();    //��ǰ����λ��
			this.reSetPos(mouseIn, currentPos);
			this.repaint();
		}
	}
	private void reSetPos(int i,Point p) {      //ͨ���û��ĵ����ڴε�����ͼ����ķ�Χ
		// TODO Auto-generated method stub
		switch(i){                          //ֻ��Ҫ�ı�pos[1]��pos[8]���ɣ������ĵ��ͨ��drawPos����ȷ��
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
		if(state==0){  //�����δ��ʼ��ͼ�ͽ���ǰ����Ϊ��ʼ��
			state=1;
			pos[1]=e.getPoint();
			pos[8]=e.getPoint();
		}else if(state==2){
			Point p=e.getPoint();     //��ȡ��굱ǰ��λ��
			for(int i=1;i<=8;i++){    //�ж�����Ƿ���С���εķ�Χ��
				if(rect[i].contains(p)){
						mouseIn=i;    //��ǵ�ǰ������ĸ�С������
						break;
				}
			}
			if(shotArea.contains(p)){       //�ж�����Ƿ��ڽ�ͼ�����ڵ��
				mouseIn=9;                  //�������ڽ�ͼ����
				clickPos=new Point(p.x, p.y);
				pos1=new Point(pos[1].x, pos[1].y);
				pos8=new Point(pos[8].x, pos[8].y);
				
			}else if(saveRec.contains(p)){
				BufferedImage saveImage=bi.getSubimage(shotArea.x,shotArea.y,shotArea.width,shotArea.height);//����ͼ����ת��Ϊһ��BufferedImage����
				this.setVisible(false);	  //�˳���ͼ����
				this.savePic(saveImage);       //����ͼƬ
						//��ʾ�������
				chatFrame.setVisible(true);
			}else if(cutRec.contains(p)){
				BufferedImage image=bi.getSubimage(shotArea.x,shotArea.y,shotArea.width,shotArea.height);
				this.cutPic(image);             //��ͼƬ���Ƶ�ϵͳ���а� ʹCtrl v����ճ������
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
			mouseIn=0;       //����뿪ʱ���ٱ�ǵ�ǰ������ĸ�������
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
	public void drawPos(Graphics g){     //ͨ��pos[1],��pos[8]�������λ����ȷ������ʣ�µ��λ�ò���������
		int x1=pos[1].x;
		int y1=pos[1].y;
		int x2=pos[8].x; 			//    λ����ʾ
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
		g.drawRect(shotArea.x, shotArea.y, shotArea.width, shotArea.height);  //������ͼ����
		for(int i=0;i<=8;i++){   //��С����
			g.fillRect(rect[i].x,rect[i].y,rect[i].width, rect[i].height);
		}
	}
	public void cutPic(BufferedImage image){   //��ͼƬ���Ƶ�ϵͳ���а�  ʹ�� ctrl v����ճ������
		Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
		ImageSelection selection=new ImageSelection(image);
		clipboard.setContents(selection, null);
	}
	public void savePic(BufferedImage image){     //����ͼƬ����
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
