	import java.applet.Applet;
	import java.awt.*;
	import java.awt.event.*;
	import java.util.ArrayList;

	public class Project1 extends Applet {

	  int numFlag;
	  Color colorFlag;
	  boolean solidFlag;
	  ArrayList < Shapes > array = new ArrayList < Shapes > ();
	  Shapes shape;
	  int x1, y1, x2, y2,xx,yy;

	  public void init() {

	    Button rectangle = new Button("Rectangle");
	    Button green = new Button("Green");
	    Button oval = new Button("Oval");
	    Checkbox solid = new Checkbox("Solid");
	    Button line = new Button("Line");
	    Button red = new Button("Red");
	    Button pencil = new Button("Pencil");
	    Button eraser = new Button("Eraser");
	    Button black = new Button("Black");
	    Button clear = new Button("Clear");
	    Button undo = new Button("Undo");

	    add(line);
	    add(rectangle);
	    add(oval);
	    add(pencil);
	    add(eraser);
	    add(solid);
	    add(black);
	    add(red);
	    add(green);
	    add(undo);
	    add(clear);

	    solidFlag = true;
	    colorFlag = Color.BLACK;

	    Listener p = new Listener();
	    this.addMouseListener(p);
	    this.addMouseMotionListener(p);

	    rectangle.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ev) {
	        numFlag = 2;

	      }
	    });

	    clear.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ev) {
	        numFlag = 5;
	        shape = new Rectangle(0, 0, 1000, 1000, false, Color.WHITE);
	        array.add(shape);
	        repaint();
	      }
	    });

	    undo.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ev) {
			if(array.size()>0)
			{array.remove(array.size() - 1);
	        repaint();}
	      }
	    });

	    oval.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ev) {
	        numFlag = 3;
	      }
	    });

	    pencil.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ev) {
	        numFlag = 6;

	      }
	    });

	    solid.addItemListener(new ItemListener() {
	      public void itemStateChanged(ItemEvent ev) {
	        if (solidFlag == true) {
	          solidFlag = false;
	        } else {
	          solidFlag = true;
	        }
	      }
	    });

	    line.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ev) {
	        numFlag = 1;
	      }
	    });

	    red.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ev) {

	        colorFlag = Color.RED;
	      }
	    });

	    black.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ev) {

	        colorFlag = Color.BLACK;
	      }
	    });

	    green.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ev) {

	        colorFlag = Color.GREEN;
	      }
	    });

	    eraser.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ev) {

	        numFlag = 4;
	      }
	    });

	  }

	  public void paint(Graphics g) {
	    for (int i = 0; i < array.size(); i++) {
	      array.get(i).draw(g);
	    }
	  }

	  class Listener extends MouseAdapter {

	    public void mousePressed(MouseEvent e) {

	      switch (numFlag) {

	      case 1:
	        shape = new Line(e.getX(), e.getY(), e.getX(), e.getY(), colorFlag);
	        break;
	      case 2:

	        shape = new Rectangle(e.getX(), e.getY(), e.getX(), e.getY(), solidFlag, colorFlag);
	        break;

	      case 3:
	        shape = new Oval(e.getX(), e.getY(), e.getX(), e.getY(), solidFlag, colorFlag);
	        break;
	     
	      case 4:
	        shape = new Eraser(e.getX(), e.getY(), e.getX(), e.getY(), colorFlag);
	        break; 
	      default:
	        numFlag = 6;
	        shape = new Pencil(e.getX(), e.getY(), e.getX(), e.getY(), colorFlag);
	        break;
	
	      }
	      array.add(shape);
	    }

	    public void mouseDragged(MouseEvent e) {

	      if (numFlag == 6) {
	        shape = new Pencil(e.getX(), e.getY(), e.getX(), e.getY(), colorFlag);
	        shape.x1 = e.getX();
	        shape.y1 = e.getY();
	        array.add(shape);
	      } else if (numFlag == 4) {
	        shape = new Eraser(e.getX(), e.getY(), e.getX(), e.getY(), Color.WHITE);
	        shape.x1 = e.getX();
	        shape.y1 = e.getY();
	        array.add(shape);
	      } else {
	        shape.x2 = e.getX();
	        shape.y2 = e.getY();
	      }
	      repaint();

	    }

	  }

	  abstract class Shapes {
	    int x1, y1, x2, y2;

	    Color color;
	    boolean filled;
	    abstract void draw(Graphics g);
	    public Shapes(int x11, int y11, int x22, int y22, boolean fill, Color y) {
	      x1 = x11;
	      y1 = y11;
	      x2 = x22;
	      y2 = y22;
	      filled = fill;
	      color = y;

	    }
	  }

	  class Pencil extends Shapes {

	    void draw(Graphics g) {
	      g.setColor(color);
	      g.fillRect(x1, y1, 15, 15);
	    }
	    public Pencil(int x1, int y1, int x2, int y2, Color color) {
	      super(x1, y1, x2, y2, solidFlag, color);
	    }
	  }

	  class Eraser extends Shapes {

	    void draw(Graphics g) {
	      g.setColor(Color.WHITE);
	      g.fillRect(x1, y1, 15, 15);
	    }
	    public Eraser(int x1, int y1, int x2, int y2, Color color) {
	      super(x1, y1, x2, y2, solidFlag, color);
	    }
	  }

	  class Line extends Shapes {

	    void draw(Graphics g) {

	      g.setColor(color);
	      g.drawLine(x1, y1, x2, y2);
	    }

	    public Line(int x1, int y1, int x2, int y2, Color color) {
	      super(x1, y1, x2, y2, solidFlag, color);
	    }
	  }
	  class Rectangle extends Shapes {

	    void draw(Graphics g) {

	      if (numFlag == 5) {
	        g.setColor(Color.WHITE);
	        g.fillRect(0, 0, 1000, 1000);
	      } else {
	        g.setColor(color);

	        if (filled) {
				if(x2<x1){xx=x2;}else xx=x1;
				if (y2<y1){yy=y2;}else yy=y1;
				g.drawRect(xx,yy,Math.abs(x2 - x1),Math.abs(y2 - y1));
				}

				
	         else {
				if(x2<x1){xx=x2;}else xx=x1;
				if (y2<y1){yy=y2;}else yy=y1;
				g.fillRect(xx,yy,Math.abs(x2 - x1),Math.abs(y2 - y1));
				}
	        }
	      }

	    

	    public Rectangle(int x1, int y1, int x2, int y2, boolean fill, Color color) {
	      super(x1, y1, x2, y2, fill, color);
	    }
	  }
	  class Oval extends Shapes {
	    void draw(Graphics g) {
	      g.setColor(color);

	      if (filled) {	
			  if(x2<x1){xx=x2;}else xx=x1;
				if (y2<y1){yy=y2;}else yy=y1;
				g.drawOval(xx,yy,Math.abs(x2 - x1),Math.abs(y2 - y1));
				}

				
	       else {
			  if(x2<x1){xx=x2;}else xx=x1;
				if (y2<y1){yy=y2;}else yy=y1;
				g.fillOval(xx,yy,Math.abs(x2 - x1),Math.abs(y2 - y1));
			   
				}		
	      }

	    

	    public Oval(int x1, int y1, int x2, int y2, boolean fill, Color color) {
	      super(x1, y1, x2, y2, fill, color);
	    }
	  }
	}