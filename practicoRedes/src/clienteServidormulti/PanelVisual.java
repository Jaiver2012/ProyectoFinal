package clienteServidormulti;

import java.awt.*;

import javax.swing.*;

public class PanelVisual extends JPanel {

	private int[] arr;

	String caballoA = "0";

	public PanelVisual() {
		// TODO Auto-generated constructor stub
		setSize(400, 400);

		arr = new int[6];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = 0;
		}

	}

	public String getCaballoA() {
		return caballoA;
	}

	public void setCaballoA(String caballoA) {
		this.caballoA = caballoA;
	}

	public void setARR(String[] a) {
		// arr=a;
		// arr=new int[a.length];
		for (int i = 0; i < a.length; i++) {
			arr[i] = Integer.parseInt(a[i]);
			// System.out.print(""+arr[i]);

		}
	}

	public int demeCA() {
		//String[] a = caballoA.split(",");
		// System.out.println(a[0]);
		int b = Integer.parseInt(caballoA);

		return b;
	}

	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D gra = (Graphics2D) g;

		// linea meta
	
		gra.drawLine(400, 10, 400, 125);

		// poner los 6 caballos

		for (int i = 0; i < arr.length; i++) {

			if(i==(demeCA()-1)) {
				gra.setColor(Color.RED);
				gra.drawString("" + (i + 1), 2, (i * 15) + 30);
				
			}else {
			
				gra.setColor(Color.BLACK);
				
			gra.drawString("" + (i + 1), 2, (i * 15) + 30);
			}
			
		}

		for (int i = 0; i < arr.length; i++) {

			// gra.setColor(Color.RED);
			gra.drawLine(10, (i * 15) + 30, arr[i], (i * 15) + 30);

			//gra.drawLine(10, (i * 15) + 30, arr[i], (i * 15) + 30);

		}

		repaint();
		validate();

	}

}
