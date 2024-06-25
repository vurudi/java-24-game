package Game;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.event.*;
import java.awt.print.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import javax.swing.*;

import static javax.imageio.ImageIO.read;

public class Main extends JFrame implements ActionListener {
	public static void main(String[] args) {
		JFrame frame = new Main();
		frame.setTitle("Math Puzzler 24 Solver");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	PrinterJob pj;
	PrintPanel painter;
	public static Boolean is24;
	public static int totalRespondidas = 0;
	public static int totalCertas = 0;
	public static int randNum = 0;
	public static int[] nums = IntStream.range(1, 38).toArray();
	public static int rotationAngle = 0;
	public static boolean AnimationState = false;
	public static int squareNextPadding = 20;
	public static Integer arr[] = {};
	public static boolean hasDone = false;
	public static boolean lastPerform = false;

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if ("Print".equals(cmd) && pj.printDialog()) {
			try {
				// pj.print();
				pj.print();
			} catch (PrinterException ex) {
				ex.printStackTrace();
			}
		} else if ("NO24".equals(cmd)) {
			System.out.println("LEFT BUTTON PRESSED");
			System.out.println("is24: " + is24);
			randNum = getRandomElement(nums);
			if (is24 == false) {
				totalCertas++;
				totalRespondidas++;
				System.out.println("++++++++++++++++++++++");
				System.out.println("is24 " + is24);
				System.out.println("TotalRespondidas " + totalRespondidas);
				System.out.println("totalCertas " + totalCertas);
				System.out.println("++++++++++++++++++++++");
				if (totalRespondidas == 10) {
					PrintPanel.infoBox(
							"Right Answers: " + totalCertas + " Wrong Answers: " + (totalRespondidas - totalCertas),
							"RESULTADO");
					totalRespondidas = 0;
					totalCertas = 0;
				}
			} else {
				totalRespondidas++;
				System.out.println("++++++++++++++++++++++");
				System.out.println("is24 " + is24);
				System.out.println("TotalRespondidas " + totalRespondidas);
				System.out.println("totalCertas " + totalCertas);
				System.out.println("++++++++++++++++++++++");
				if (totalRespondidas == 10) {
					PrintPanel.infoBox(
							"Right Answers: " + totalCertas + " Wrong Answers: " + (totalRespondidas - totalCertas),
							"RESULTADO");
					totalRespondidas = 0;
					totalCertas = 0;
				}
			}
			repaint();
		} else if ("24".equals(cmd)) {
			System.out.println("RIGHT BUTTON PRESSED");
			System.out.println("is24: " + is24);
			randNum = getRandomElement(nums);
			if (is24 == true) {
				totalCertas++;
				totalRespondidas++;
				System.out.println("++++++++++++++++++++++");
				System.out.println("is24 " + is24);
				System.out.println("TotalRespondidas " + totalRespondidas);
				System.out.println("totalCertas " + totalCertas);
				System.out.println("++++++++++++++++++++++");
				if (totalRespondidas == 10) {
					PrintPanel.infoBox(
							"Right Answers: " + totalCertas + " Wrong Answers: " + (totalRespondidas - totalCertas),
							"RESULTADO");
					totalRespondidas = 0;
					totalCertas = 0;
				}
			} else {
				totalRespondidas++;
				System.out.println("++++++++++++++++++++++");
				System.out.println("is24 " + is24);
				System.out.println("TotalRespondidas " + totalRespondidas);
				System.out.println("totalCertas " + totalCertas);
				System.out.println("++++++++++++++++++++++");
				if (totalRespondidas == 10) {
					PrintPanel.infoBox(
							"Right Answers: " + totalCertas + " Wrong Answers: " + (totalRespondidas - totalCertas),
							"RESULTADO");
					totalRespondidas = 0;
					totalCertas = 0;
				}
			}
			repaint();
		} else if ("Start Animation".equals(cmd)) {
			AnimationState = true;
		} else if ("Stop Animation".equals(cmd)) {
			AnimationState = false;
		} else if ("Reset Score".equals(cmd)) {
			totalRespondidas = 0;
			totalCertas = 0;
			repaint();
		} else if ("Grayscale".equals(cmd)) {
			System.out.println("Grayscale applied");
			PrintPanel.process();
		} else if ("Exit".equals(cmd)) {
			System.exit(0);
		} else if ("How to play".equals(cmd)) {
			PrintPanel.infoBox("Objective of the game:" + "\n" + "Make the number 24 from the four numbers shown." + "\n"
					+ "You can add, subtract, multiply and divide. Use all four numbers on" + "\n"
					+ "the card, but use each number only once. You do not have to use all" + "\n"
					+ "four operations. Can you solve the card below?", "How to play");
		}
	}

	private int getRandomElement(int[] arr) {
		return arr[ThreadLocalRandom.current().nextInt(arr.length)];
	}

	public Main() {
		Container containerpanel = this.getContentPane();
		JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		containerpanel.setLayout(new BorderLayout());
		JButton btnLeft = new JButton("NO24");
		JButton btnRight = new JButton("24");
		btnPnl.add(btnLeft);
		btnPnl.add(btnRight);
		btnLeft.addActionListener(this);
		btnRight.addActionListener(this);
		containerpanel.add(btnPnl, BorderLayout.SOUTH);
		painter = new PrintPanel();
		containerpanel.add(painter, BorderLayout.CENTER);
		pj = PrinterJob.getPrinterJob();
		pj.setPrintable(painter);

		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);

		JMenu menu = new JMenu("File");
		JMenuItem mi = new JMenuItem("Print");
		mi.addActionListener(this);
		menu.add(mi);
		menu.addSeparator();
		mi = new JMenuItem("Exit");
		mi.addActionListener(this);
		menu.add(mi);

		mb.add(menu);
		JMenu menuAnimation = new JMenu("Animation");
		mb.add(menuAnimation);
		mi = new JMenuItem("Start Animation");
		mi.addActionListener(this);
		menuAnimation.add(mi);
		mi = new JMenuItem("Stop Animation");
		mi.addActionListener(this);
		menuAnimation.add(mi);

		JMenu menuGame = new JMenu("Game");
		mb.add(menuGame);
		mi = new JMenuItem("Reset Score");
		mi.addActionListener(this);
		menuGame.add(mi);

		JMenu menuProcessing = new JMenu("Processing");
		mb.add(menuProcessing);
		mi = new JMenuItem("Grayscale");
		mi.addActionListener(this);
		menuProcessing.add(mi);

		JMenu menuHelp = new JMenu("Help");
		mb.add(menuHelp);
		mi = new JMenuItem("How to play");
		mi.addActionListener(this);
		menuHelp.add(mi);
	}
}

class PrintPanel extends JPanel implements Printable, ActionListener {
	private BufferedImage image;
	public static BufferedImage image2;
	private float degrees = 0;

	public PrintPanel() {
		setPreferredSize(new Dimension(520, 700));
		setBackground(Color.white);
		Timer timer = new Timer(40, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				degrees += 0.5f;
				repaint();
			}
		});
		timer.start();

		File file1 = new File("src/Game/resources/texture.jpg");
		try {

            image = read(file1);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		File file2 = new File("src/Game/resources/logo.jpeg");
		try {

            image2 = read(file2);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "Info " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	public int print(Graphics g, PageFormat pf, int pageIndex) {
		switch (pageIndex) {
		case 0:
			draw(g);
			break;
		case 1:
			g.translate(-(int) pf.getImageableWidth(), 0);
			draw(g);
			break;
		default:
			return NO_SUCH_PAGE;
		}
		return PAGE_EXISTS;
	}

	protected Point2D pointAt(double radians, double radius) {
		double x = radius * Math.cos(radians);
		double y = radius * Math.sin(radians);
		return new Point2D.Double(x, y);
	}

	protected Point2D translate(Point2D point, Point2D to) {
		Point2D newPoint = new Point2D.Double(point.getX(), point.getY());
		newPoint.setLocation(point.getX() + to.getX(), point.getY() + to.getY());
		return newPoint;
	}

	public static int getRandomElement(int[] arr) {
		return arr[ThreadLocalRandom.current().nextInt(arr.length)];
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public static Puzzle arrayObjectos(int randNum) {
		// puzzle id, n1,n2,n3,n4 solution, difficulty
		Puzzle[] obj = new Puzzle[50];
		obj[0] = new Puzzle(0, 6, 9, 1, 2, true, 1);
		obj[1] = new Puzzle(1, 4, 2, 8, 8, true, 1);
		obj[2] = new Puzzle(2, 1, 8, 4, 1, true, 1);
		obj[3] = new Puzzle(3, 1, 9, 1, 5, true, 1);
		obj[4] = new Puzzle(4, 3, 4, 5, 5, true, 1);
		obj[5] = new Puzzle(5, 4, 8, 8, 4, true, 1);
		obj[6] = new Puzzle(6, 6, 8, 4, 6, true, 1);
		obj[7] = new Puzzle(7, 2, 4, 6, 7, true, 2);
		obj[8] = new Puzzle(8, 8, 5, 6, 2, true, 2);
		obj[9] = new Puzzle(9, 1, 3, 4, 7, true, 2);
		obj[10] = new Puzzle(10, 6, 8, 6, 5, true, 2);
		obj[11] = new Puzzle(11, 2, 7, 2, 8, true, 2);
		obj[12] = new Puzzle(12, 2, 4, 7, 3, true, 2);
		obj[13] = new Puzzle(13, 3, 3, 1, 7, true, 2);
		obj[14] = new Puzzle(14, 4, 8, 8, 7, true, 2);
		obj[15] = new Puzzle(15, 7, 5, 4, 1, true, 2);
		obj[16] = new Puzzle(16, 6, 5, 8, 7, true, 2);
		obj[17] = new Puzzle(17, 6, 9, 3, 1, true, 2);
		obj[18] = new Puzzle(18, 7, 4, 8, 4, true, 3);
		obj[19] = new Puzzle(19, 5, 2, 8, 2, true, 3);
		obj[20] = new Puzzle(20, 4, 2, 8, 8, true, 3);
		obj[21] = new Puzzle(21, 2, 2, 3, 5, true, 3);
		obj[22] = new Puzzle(22, 3, 8, 8, 1, true, 3);
		obj[23] = new Puzzle(23, 3, 2, 5, 7, true, 3);
		obj[24] = new Puzzle(24, 8, 5, 5, 2, true, 3);
		obj[25] = new Puzzle(25, 3, 3, 6, 8, true, 3);
		obj[26] = new Puzzle(26, 7, 5, 3, 3, true, 3);
		
		obj[27] = new Puzzle(15, 1, 1, 1, 1, false, 1);
		obj[28] = new Puzzle(16, 1, 2, 3, 4, false, 2);
		obj[29] = new Puzzle(17, 2, 1, 1, 1, false, 2);
		obj[30] = new Puzzle(18, 1, 1, 2, 3, false, 2);
		obj[31] = new Puzzle(19, 5, 2, 1, 2, false, 2);
		obj[32] = new Puzzle(20, 8, 1, 1, 1, false, 2);
		obj[33] = new Puzzle(21, 3, 1, 2, 3, false, 2);
		obj[34] = new Puzzle(22, 1, 7, 8, 1, false, 3);
		obj[35] = new Puzzle(23, 1, 4, 9, 4, false, 3);
		obj[36] = new Puzzle(24, 2, 7, 1, 2, false, 3);
		obj[37] = new Puzzle(25, 1, 8, 4, 1, false, 3);
		obj[38] = new Puzzle(26, 7, 4, 3, 1, false, 3);
		
		//obj[2] = new Puzzle(1, 5, 1, 9, 8, true, 3);
		return obj[randNum];
	}
	static BufferedImage a;
	public static void process() {
		BufferedImageOp op = null;
		op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		BufferedImage bi = op.filter(image2, null);
		a = bi;
	}

	private void draw(Graphics g) {
		int mleft = 10;
		int mtop = 50;
		
		// Cores
		Color azulBg = new Color(03, 19, 108);
		Color amareloBg = new Color(247, 171, 36);
		Color amareloBgDarker = new Color(207, 149, 43);
		Color crossBg = new Color(226, 41, 61);

		// Rectangle
		var r = new RoundRectangle2D.Double(mleft, mtop, 500, 500, 50, 50);
		g.setColor(azulBg);
		((Graphics2D) g).fill(r);

		// Composition
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
		((Graphics2D) g).setComposite(ac);

		TexturePaint tp = new TexturePaint(image,
				new Rectangle2D.Double(100, 100, image.getWidth(), image.getHeight()));
		((Graphics2D) g).setPaint(tp);
		var rtransparency = new RoundRectangle2D.Double(mleft, mtop, 500, 500, 50,50);
		((Graphics2D) g).fill(rtransparency);

		AlphaComposite ac2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
		((Graphics2D) g).setComposite(ac2);

		// Circle
		var circle = new Ellipse2D.Double(mleft + 20, mtop + 20, 460, 460);
		GradientPaint gp = new GradientPaint(0,250,amareloBg, 150, 50, amareloBgDarker, true);
		((Graphics2D) g).setPaint(gp);
		((Graphics2D) g).fill(circle);

		// Custom Shape
		if (Main.AnimationState) {
			AffineTransform backup = ((Graphics2D) g).getTransform();
			AffineTransform a = AffineTransform.getRotateInstance(degrees, (mleft + 225) + 25,
					mtop + 225 + 25);
			((Graphics2D) g).setTransform(a);
			CustomShape vv = new CustomShape(0, 0, 500, 500);
			g.setColor(crossBg);
			g.setClip(vv);
			((Graphics2D) g).fill(vv);
			((Graphics2D) g).setTransform(backup);

		} else {
			CustomShape h = new CustomShape(0, 0, 500, 500);
			g.setColor(crossBg);
			((Graphics2D) g).fill(h);
			g.setClip(h);
		}

		//
		g.setColor(Color.GRAY);
		double startAngle = 0;
		double divisions = 100;
		double delta = 360.0 / divisions;

		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;
		int radius = Math.min(centerX, centerY);

		Point2D centerPoint = new Point2D.Double(centerX, centerY);
		double angle = startAngle;
		for (int index = 0; index < divisions; index++) {
			Point2D point = pointAt(Math.toRadians(angle), radius);
			point = translate(point, centerPoint);
			((Graphics2D) g).draw(new Line2D.Double(centerPoint, point));
			angle += delta;
		}

		g.setClip(null);
		g.setColor(crossBg);
		g.fillRect(mleft + 190, mtop + 190, 120, 120);

		g.setColor(Color.WHITE);
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		path.moveTo(mleft + 200, mtop + 200);
		path.lineTo(mleft + 300, mtop + 200);
		path.lineTo(mleft + 300, mtop + 300);
		path.lineTo(mleft + 200, mtop + 300);
		path.lineTo(mleft + 200, mtop + 200);

		Stroke stroke = new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);
		((Graphics2D) g).setStroke(stroke);
		((Graphics2D) g).draw(path);

		if (arrayObjectos(Main.randNum).difficulty == 1) {
			g.setColor(Color.WHITE);
			var difficulty = new Ellipse2D.Double(mleft + 20, mtop + 20, 15, 15);
			((Graphics2D) g).fill(difficulty);
			var difficulty2 = new Ellipse2D.Double(mleft + 460, mtop + 20, 15, 15);
			((Graphics2D) g).fill(difficulty2);
			var difficulty3 = new Ellipse2D.Double(mleft + 460, mtop + 460, 15, 15);
			((Graphics2D) g).fill(difficulty3);
			var difficulty4 = new Ellipse2D.Double(mleft + 20, mtop + 460, 15, 15);
			((Graphics2D) g).fill(difficulty4);

			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			Font font = new Font("Calibri", Font.PLAIN, 14);
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.drawOval(485, 15, 15, 15);
			g.drawString("Difficulty", 425, 27);
			
		} else if (arrayObjectos(Main.randNum).difficulty == 2) {
			g.setColor(Color.WHITE);
			var difficulty = new Ellipse2D.Double(mleft + 20, mtop + 20, 15, 15);
			((Graphics2D) g).fill(difficulty);
			var difficulty5 = new Ellipse2D.Double(mleft + 20, mtop + 40, 15, 15);
			((Graphics2D) g).fill(difficulty5);
			var difficulty2 = new Ellipse2D.Double(mleft + 460, mtop + 20, 15, 15);
			((Graphics2D) g).fill(difficulty2);
			var difficulty6 = new Ellipse2D.Double(mleft + 440, mtop + 20, 15, 15);
			((Graphics2D) g).fill(difficulty6);
			var difficulty3 = new Ellipse2D.Double(mleft + 460, mtop + 460, 15, 15);
			((Graphics2D) g).fill(difficulty3);
			var difficulty7 = new Ellipse2D.Double(mleft + 460, mtop + 440, 15, 15);
			((Graphics2D) g).fill(difficulty7);
			var difficulty4 = new Ellipse2D.Double(mleft + 20, mtop + 460, 15, 15);
			((Graphics2D) g).fill(difficulty4);
			var difficulty8 = new Ellipse2D.Double(mleft + 40, mtop + 460, 15, 15);
			((Graphics2D) g).fill(difficulty8);
			
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			Font font = new Font("Calibri", Font.PLAIN, 14);
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.drawOval(465, 15, 15, 15);
			g.drawOval(485, 15, 15, 15);
			g.drawString("Difficulty", 405, 27);
			// set2
			
		} else if (arrayObjectos(Main.randNum).difficulty == 3) {
			g.setColor(Color.WHITE);
			var difficulty = new Ellipse2D.Double(mleft + 20, mtop + 20, 15, 15);
			((Graphics2D) g).fill(difficulty);
			var difficulty5 = new Ellipse2D.Double(mleft + 20, mtop + 40, 15, 15);
			((Graphics2D) g).fill(difficulty5);
			var difficulty9 = new Ellipse2D.Double(mleft + 20, mtop + 60, 15, 15);
			((Graphics2D) g).fill(difficulty9);
			var difficulty2 = new Ellipse2D.Double(mleft + 460, mtop + 20, 15, 15);
			((Graphics2D) g).fill(difficulty2);
			var difficulty6 = new Ellipse2D.Double(mleft + 440, mtop + 20, 15, 15);
			((Graphics2D) g).fill(difficulty6);
			var difficulty10 = new Ellipse2D.Double(mleft + 420, mtop + 20, 15, 15);
			((Graphics2D) g).fill(difficulty10);
			var difficulty3 = new Ellipse2D.Double(mleft + 460, mtop + 460, 15, 15);
			((Graphics2D) g).fill(difficulty3);
			var difficulty7 = new Ellipse2D.Double(mleft + 460, mtop + 440, 15, 15);
			((Graphics2D) g).fill(difficulty7);
			var difficulty11 = new Ellipse2D.Double(mleft + 460, mtop + 420, 15, 15);
			((Graphics2D) g).fill(difficulty11);
			var difficulty4 = new Ellipse2D.Double(mleft + 20, mtop + 460, 15, 15);
			((Graphics2D) g).fill(difficulty4);
			var difficulty8 = new Ellipse2D.Double(mleft + 40, mtop + 460, 15, 15);
			((Graphics2D) g).fill(difficulty8);
			var difficulty12 = new Ellipse2D.Double(mleft + 60, mtop + 460, 15, 15);
			((Graphics2D) g).fill(difficulty12);

			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			Font font = new Font("Calibri", Font.PLAIN, 14);
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.drawOval(445, 15, 15, 15);
			g.drawOval(465, 15, 15, 15);
			g.drawOval(485, 15, 15, 15);
			g.drawString("Difficulty", 385, 27);
		}
		Main.is24 = arrayObjectos(Main.randNum).pro_name;
		
		// FONT
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Calibri", Font.PLAIN, 156);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(String.valueOf(arrayObjectos(Main.randNum).n1), mleft + 215,mtop + 150);

		// 5
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.rotate(Math.toRadians(180), mleft + 250, mtop + 250);
		Font rotatedFont = font.deriveFont(affineTransform);
		g.setFont(rotatedFont);
		g.drawString(String.valueOf(arrayObjectos(Main.randNum).n2), mleft + 215,mtop + 150);

		// 4
		AffineTransform affineTransform2 = new AffineTransform();
		affineTransform2.rotate(Math.toRadians(90), mleft + 250, mtop + 250);
		Font rotatedFont2 = font.deriveFont(affineTransform2);
		g.setFont(rotatedFont2);
		g.drawString(String.valueOf(arrayObjectos(Main.randNum).n3), mleft + 215,mtop + 150);

		// 1
		AffineTransform affineTransform3 = new AffineTransform();
		affineTransform3.rotate(Math.toRadians(-90), mleft + 250, mtop + 250);
		Font rotatedFont3 = font.deriveFont(affineTransform3);
		g.setFont(rotatedFont3);
		g.drawString(String.valueOf(arrayObjectos(Main.randNum).n4), mleft + 215,
				mtop + 150);

		if (a == null) {
			g.drawImage(image2, 10, 560, null);
		} else {
			g.drawImage(PrintPanel.a, 10, 560, null);
		}
		
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		Font font2 = new Font("Calibri", Font.PLAIN, 14);

		AffineTransform affineTransformResultado = new AffineTransform();
		affineTransformResultado.rotate(Math.toRadians(0), mleft + 250, mtop + 250);
		Font rotatedFontResultado = font2.deriveFont(affineTransformResultado);
		g.setFont(rotatedFontResultado);
		g.drawString("Puzzle " + arrayObjectos(Main.randNum).game_id, 15, 27);
		g.drawString("Score :" + Main.totalCertas + "/" + Main.totalRespondidas, 100, 27);
		g.drawString("RIGHT: ", 265, 590);

		switch (Main.totalCertas) {
		case 1:
			g.setColor(Color.GREEN);
			int tes1 = 0;
			for (int z = 0; z <= 0; z++) {
				var z2 = new Rectangle2D.Double(310 + tes1, 578, 15, 15);
				((Graphics2D) g).fill(z2);
				tes1 = tes1 + 20;
			}
			break;
		case 2:
			g.setColor(Color.GREEN);
			int tes2 = 0;
			for (int z = 0; z <= 1; z++) {
				var z2 = new Rectangle2D.Double(310 + tes2, 578, 15, 15);
				((Graphics2D) g).fill(z2);
				tes2 = tes2 + 20;
			}
			break;
		case 3:
			g.setColor(Color.GREEN);
			int tes3 = 0;
			for (int z = 0; z <= 2; z++) {
				var z2 = new Rectangle2D.Double(310 + tes3, 578, 15, 15);
				((Graphics2D) g).fill(z2);
				tes3 = tes3 + 20;
			}
			break;
		case 4:
			g.setColor(Color.GREEN);
			int tes4 = 0;
			for (int z = 0; z <= 3; z++) {
				var z2 = new Rectangle2D.Double(310 + tes4, 578, 15, 15);
				((Graphics2D) g).fill(z2);
				tes4 = tes4 + 20;
			}
			break;

		case 5:
			g.setColor(Color.GREEN);
			int tes5 = 0;
			for (int z = 0; z <= 4; z++) {
				var z2 = new Rectangle2D.Double(310 + tes5, 578, 15, 15);
				((Graphics2D) g).fill(z2);
				tes5 = tes5 + 20;
			}
			break;
		case 6:
			g.setColor(Color.GREEN);
			int tes6 = 0;
			for (int z = 0; z <= 5; z++) {
				var z2 = new Rectangle2D.Double(310 + tes6, 578, 15, 15);
				((Graphics2D) g).fill(z2);
				tes6 = tes6 + 20;
			}
			break;
		case 7:
			g.setColor(Color.GREEN);
			int tes7 = 0;
			for (int z = 0; z <= 6; z++) {
				var z2 = new Rectangle2D.Double(310 + tes7, 578, 15, 15);
				((Graphics2D) g).fill(z2);
				tes7 = tes7 + 20;
			}
			break;
		case 8:
			g.setColor(Color.GREEN);
			int tes8 = 0;
			for (int z = 0; z <= 7; z++) {
				var z2 = new Rectangle2D.Double(310 + tes8, 578, 15, 15);
				((Graphics2D) g).fill(z2);
				tes8 = tes8 + 20;
			}
			break;
		case 9:
			g.setColor(Color.GREEN);
			int tes9 = 0;
			for (int z = 0; z <= 8; z++) {
				var z2 = new Rectangle2D.Double(310 + tes9, 578, 15, 15);
				((Graphics2D) g).fill(z2);
				tes9 = tes9 + 20;
			}
			break;
		case 10:
			g.setColor(Color.GREEN);
			int tes10 = 0;
			for (int z = 0; z <= 9; z++) {
				var z2 = new Rectangle2D.Double(310 + tes10, 578, 15, 15);
				((Graphics2D) g).fill(z2);
				tes10 = tes10 + 20;
			}
			break;
		default:
			// code block
		}
		g.setColor(Color.black);
		g.drawString("WRONG: ", 255, 611);

		switch ((Main.totalRespondidas - Main.totalCertas)) {
		case 0:
			// code block

			break;
		case 1:
			g.setColor(Color.RED);
			int tes1 = 0;
			for (int z = 0; z <= 0; z++) {
				var z2 = new Rectangle2D.Double(310 + tes1, 600, 15, 15);
				((Graphics2D) g).fill(z2);
				tes1 = tes1 + 20;
			}
			break;
		case 2:
			g.setColor(Color.RED);
			int tes2 = 0;
			for (int z = 0; z <= 1; z++) {
				var z2 = new Rectangle2D.Double(310 + tes2, 600, 15, 15);
				((Graphics2D) g).fill(z2);
				tes2 = tes2 + 20;
			}
			break;
		case 3:
			g.setColor(Color.RED);
			int tes3 = 0;
			for (int z = 0; z <= 2; z++) {
				var z2 = new Rectangle2D.Double(310 + tes3, 600, 15, 15);
				((Graphics2D) g).fill(z2);
				tes3 = tes3 + 20;
			}
			break;
		case 4:
			g.setColor(Color.RED);
			int tes4 = 0;
			for (int z = 0; z <= 3; z++) {
				var z2 = new Rectangle2D.Double(310 + tes4, 600, 15, 15);
				((Graphics2D) g).fill(z2);
				tes4 = tes4 + 20;
			}
			break;

		case 5:
			g.setColor(Color.RED);
			int tes5 = 0;
			for (int z = 0; z <= 4; z++) {
				var z2 = new Rectangle2D.Double(310 + tes5, 600, 15, 15);
				((Graphics2D) g).fill(z2);
				tes5 = tes5 + 20;
			}
			break;
		case 6:
			g.setColor(Color.RED);
			int tes6 = 0;
			for (int z = 0; z <= 5; z++) {
				var z2 = new Rectangle2D.Double(310 + tes6, 600, 15, 15);
				((Graphics2D) g).fill(z2);
				tes6 = tes6 + 20;
			}
			break;
		case 7:
			g.setColor(Color.RED);
			int tes7 = 0;
			for (int z = 0; z <= 6; z++) {
				var z2 = new Rectangle2D.Double(310 + tes7, 600, 15, 15);
				((Graphics2D) g).fill(z2);
				tes7 = tes7 + 20;
			}
			break;
		case 8:
			g.setColor(Color.RED);
			int tes8 = 0;
			for (int z = 0; z <= 7; z++) {
				var z2 = new Rectangle2D.Double(310 + tes8, 600, 15, 15);
				((Graphics2D) g).fill(z2);
				tes8 = tes8 + 20;
			}
			break;
		case 9:
			g.setColor(Color.RED);
			int tes9 = 0;
			for (int z = 0; z <= 8; z++) {
				var z2 = new Rectangle2D.Double(310 + tes9, 600, 15, 15);
				((Graphics2D) g).fill(z2);
				tes9 = tes9 + 20;
			}
			break;
		case 10:
			g.setColor(Color.RED);
			int tes10 = 0;
			for (int z = 0; z <= 9; z++) {
				var z2 = new Rectangle2D.Double(310 + tes10, 600, 15, 15);
				((Graphics2D) g).fill(z2);
				tes10 = tes10 + 20;
			}
			break;
		default:
			// code block
		}

		// System.out.println(obj[randNum].n1);
		// Animation Object

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//  Auto-generated method stub

	}
}
