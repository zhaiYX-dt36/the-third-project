package com.zhu.printscreen;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageSelection implements Transferable{
	private static DataFlavor[] flavors={DataFlavor.imageFlavor};
	private Image image;
	public ImageSelection(Image image) {
		// TODO Auto-generated constructor stub
		this.image=image;
	}
	@Override
	public DataFlavor[] getTransferDataFlavors() {
		// TODO Auto-generated method stub
		return (DataFlavor[])flavors.clone();
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		// TODO Auto-generated method stub
		if(DataFlavor.imageFlavor.equals(flavor)){
			return true;
		}
		return false;
	}

	@Override
	public Image getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		// TODO Auto-generated method stub
		if(flavor.equals(DataFlavor.imageFlavor)){
			return image;
		}else{
			throw new UnsupportedFlavorException(flavor);
		}
		
	}
}
