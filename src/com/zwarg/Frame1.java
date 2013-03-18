package com.zwarg;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import javax.media.jai.*;
import java.io.*;
import javax.swing.border.*;

/**
 * <p>Title: ThumbNailing App</p>
 * <p>Description: Make Thumbnails from Dirs</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Zwarg
 * @version 1.0
 */

public class Frame1 extends JFrame {
  private JPanel contentPane;
  private JMenuBar jMenuBar1 = new JMenuBar();
  private JMenu jMenuFile = new JMenu();
  private JMenuItem jMenuFileExit = new JMenuItem();
  private JMenu jMenuHelp = new JMenu();
  private JMenuItem jMenuHelpAbout = new JMenuItem();
  private JToolBar jToolBar = new JToolBar();
  private JButton jButtonOpen = new JButton();
  private JButton jButtonClose = new JButton();
  private JButton jButtonHelp = new JButton();
  private ImageIcon image1;
  private ImageIcon image2;
  private ImageIcon image3;
  private BorderLayout borderLayout1 = new BorderLayout();
  private JSplitPane jSplitPane1 = new JSplitPane();
  private JScrollPane jScrollPane2 = new JScrollPane();
  private JPanel jPanel1 = new JPanel();
  private JLabel statusBar = new JLabel();
  private BorderLayout borderLayout2 = new BorderLayout();
  private JProgressBar jProgressBar1 = new JProgressBar();
  private TitledBorder titledBorder1;
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JPanel jPanelThumbs = new JPanel();
  private GridBagLayout gridBagLayout1 = new GridBagLayout();
  private ImagePanel imagePanel1 = new ImagePanel();
  private JToolBar jToolBar1 = new JToolBar();
  private JTextField jTextField1 = new JTextField();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JTextField jTextField2 = new JTextField();


  //Construct the frame
  public Frame1() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Component initialization
  private void jbInit() throws Exception  {
    image1 = new ImageIcon(com.zwarg.Frame1.class.getResource("openFile.gif"));
    image2 = new ImageIcon(com.zwarg.Frame1.class.getResource("closeFile.gif"));
    image3 = new ImageIcon(com.zwarg.Frame1.class.getResource("help.gif"));
    //setIconImage(Toolkit.getDefaultToolkit().createImage(Frame1.class.getResource("[Your Icon]")));
    contentPane = (JPanel) this.getContentPane();
    titledBorder1 = new TitledBorder("");
    contentPane.setLayout(borderLayout1);
    this.setSize(new Dimension(412, 317));
    this.setTitle("www.zwarg.com Thumb Nailer");
    jMenuFile.setText("File");
    jMenuFileExit.setText("Exit");
    jMenuFileExit.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        jMenuFileExit_actionPerformed(e);
      }
    });
    jMenuHelp.setText("Help");
    jMenuHelpAbout.setText("About");
    jMenuHelpAbout.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        jMenuHelpAbout_actionPerformed(e);
      }
    });
    jButtonOpen.setIcon(image1);
    jButtonOpen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonOpen_actionPerformed(e);
      }
    });
    jButtonOpen.setToolTipText("Open File");
    jButtonClose.setIcon(image2);
    jButtonClose.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonClose_actionPerformed(e);
      }
    });
    jButtonClose.setToolTipText("Close File");
    jButtonHelp.setIcon(image3);
    jButtonHelp.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonHelp_actionPerformed(e);
      }
    });
    jButtonHelp.setToolTipText("Help");
    jSplitPane1.setMinimumSize(new Dimension(140, 24));
    statusBar.setText(" ");
    jPanel1.setLayout(borderLayout2);
    contentPane.setPreferredSize(new Dimension(150, 260));
    jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    jScrollPane1.setMaximumSize(new Dimension(124, 32767));
    jScrollPane1.setMinimumSize(new Dimension(124, 1));
    jScrollPane1.setPreferredSize(new Dimension(124, 200));
    jPanelThumbs.setLayout(gridBagLayout1);
    jTextField1.setText("100");
    jLabel1.setText("Max Size");
    jLabel2.setText("Prefix");
    jTextField2.setText(prefix);
    jToolBar.add(jButtonOpen);
    jToolBar.add(jButtonClose);
    jToolBar.add(jButtonHelp);
    jToolBar.add(jToolBar1, null);
    jToolBar1.add(jLabel1, null);
    jToolBar1.add(jTextField1, null);
    jToolBar1.add(jLabel2, null);
    jToolBar1.add(jTextField2, null);
    contentPane.add(jToolBar, BorderLayout.NORTH);
    contentPane.add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(statusBar,  BorderLayout.WEST);
    jPanel1.add(jProgressBar1, BorderLayout.EAST);
    contentPane.add(jSplitPane1, BorderLayout.CENTER);
    jSplitPane1.add(jScrollPane2, JSplitPane.RIGHT);
    jScrollPane2.getViewport().add(imagePanel1, null);
    jSplitPane1.add(jScrollPane1, JSplitPane.LEFT);
    jScrollPane1.getViewport().add(jPanelThumbs, null);
    jMenuFile.add(jMenuFileExit);
    jMenuHelp.add(jMenuHelpAbout);
    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jMenuHelp);
    this.setJMenuBar(jMenuBar1);
  }
  //File | Exit action performed
  public void jMenuFileExit_actionPerformed(ActionEvent e) {
    System.exit(0);
  }
  //Help | About action performed
  public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
    Frame1_AboutBox dlg = new Frame1_AboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      jMenuFileExit_actionPerformed(null);
    }
  }

  void jButtonOpen_actionPerformed(ActionEvent e) {
    JFileChooser chooser = new JFileChooser();

    chooser.setFileSelectionMode( chooser.DIRECTORIES_ONLY );

    int returnVal = chooser.showOpenDialog(this);

    if(returnVal == JFileChooser.APPROVE_OPTION) {
      java.io.File[] imgs = chooser.getSelectedFile().listFiles();

      clearImages();
      loadImages( imgs, new String[]{ "jpg", "gif", "tif", "bmp" } );
    }
  }

  private void loadImages( File[] list, String[] accept ) {

    Vector vecImgs = new Vector();
    Vector vecFiles = new Vector();
    boolean goodFile = false;
    prefix = jTextField2.getText();

    for ( int i = 0; i < list.length; i++ ) {
      for ( int j = 0; j < accept.length; j++ ) {
        if ( list[i].getName().toLowerCase().endsWith( accept[j] ) &&
             !list[i].getName().startsWith( prefix ) )
          goodFile = true;
      }

      if ( goodFile ) {
        vecFiles.add( list[i] );
        goodFile = false;
      }
    }

    jProgressBar1.setMaximum( vecFiles.size() - 1 );

    loader = new ImageLoader();

    loader.files = vecFiles;
    loader.progress = jProgressBar1;
    loader.panel = jPanelThumbs;
    loader.scroll = jScrollPane1;
    loader.display = imagePanel1;
    loader.status = statusBar;

    loader.start();
  }

  private void clearImages() {
    Component[] old = jPanelThumbs.getComponents();

    for ( int i = 0; i < old.length; i++ )
      jPanelThumbs.remove(old[i]);
  }

  void jButtonClose_actionPerformed(ActionEvent e) {
    JFileChooser chooser = new JFileChooser();

    chooser.setFileSelectionMode( chooser.DIRECTORIES_ONLY );

    int returnVal = chooser.showSaveDialog(this);

    if(returnVal == JFileChooser.APPROVE_OPTION) {
      saveImages( chooser.getCurrentDirectory() );
    }
  }

  private void saveImages( File location ) {
    saver = new ImageSaver();

    try {
      saver.location = location;
      saver.panel = jPanelThumbs;
      saver.progress = jProgressBar1;
      saver.prefix = jTextField2.getText();
      saver.size = new Float( jTextField1.getText() ).floatValue();
    }
    catch (NumberFormatException ex) {
      saver = null;
      return;
    }

    saver.start();
  }

  void jButtonHelp_actionPerformed(ActionEvent e) {
    PlanarImage pi = imagePanel1.getImage();
    ParameterBlockJAI pb = new ParameterBlockJAI( "convolve" );

    pb.setSource( pi, 0 );
    float[] kernData = new float[] { -0.5f, -0.5f, -0.5f,
                                     -0.5f,  4.f,  -0.5f,
                                     -0.5f, -0.5f, -0.5f };

    KernelJAI kern = new KernelJAI( 3, 3, 1, 1, kernData );
    pb.setParameter( "kernel", kern );

    imagePanel1.setImage( JAI.create( "convolve", pb ) );
  }

  /**
   * Load images in background.
   */
  ImageLoader loader;

  /**
   * Save images in background.
   */
  ImageSaver saver;

  /**
   * Default prefix of thumbnails
   */
  String prefix = "thumb";
}