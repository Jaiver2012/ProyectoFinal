package clienteServidormulti;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class caballo extends Thread {

	private JPanel box;
	int x = 40;
	int y = 40;
	Color color;
	int numero;
	double monto;
	int position;
	boolean estado;

	public caballo(int m, double n) {
		numero = m;
		monto = n;
		estado = false;

	}

	public caballo(JPanel b, Color c, int p) {
		// TODO Auto-generated constructor stub
		// numero=m;
		// monto=n;

		box = b;
		color = c;
		position = p;

	}

	// public void draw()
	// {
	// Graphics g = box.getGraphics();
	// g.setColor(color);
	// g.drawString("" + position, 10, position * 40);
	// g.drawLine(x, position * y, x + 5, position * y);
	// g.dispose();
	// }
	//
	// public void move()
	// {
	// if(!box.isVisible())
	// {
	// return;
	// }
	//
	// Graphics g = box.getGraphics();
	// g.setXORMode(box.getBackground());
	//
	// g.setColor(color);
	//
	// while(x <= (box.getSize().width - 40))
	// {
	// g.drawLine(x, position * y, x + 5, position * y);
	// x += 5;
	// try
	// {
	// Thread.sleep(50);
	// }catch(InterruptedException e)
	// {
	// System.out.println("Thread error");
	// }
	// }
	// g.drawString("T", box.getSize().width - 40 , position * y);
	// g.dispose();
	// }
	//
	// public void run()
	// {
	// draw();
	// move();
	// }
	//

	 
	public void run() {

		while (Carrera.empezo == true && x<Carrera.META) {

			
			Random aleatorio = new Random(System.currentTimeMillis());
			
			int intAletorio = aleatorio.nextInt(15);
			x += intAletorio;
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public JPanel getBox() {
		return box;
	}

	public void setBox(JPanel box) {
		this.box = box;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

}
