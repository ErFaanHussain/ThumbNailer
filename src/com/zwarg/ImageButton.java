package com.zwarg;

import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.io.File;
import javax.swing.*;
import java.awt.event.*;
import javax.media.jai.*;

/**
 * <p>Title: ThumbNailing App</p>
 * <p>Description: Make Thumbnails from Dirs</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Zwarg
 * @version 1.0
 */

public class ImageButton extends JButton implements ActionListener {

  public ImageButton() {
    super();

    setMargin( new Insets( 0, 0, 0, 0 ) );

    addActionListener( this );
  }

  public ImageButton( ImageIcon icon ) {
    super( icon );

    setMargin( new Insets( 0, 0, 0, 0 ) );

    addActionListener( this );
  }

  public void actionPerformed( ActionEvent ae ) {
    image = JAI.create( "fileload", target.getAbsolutePath() );

    display.setImage( image );
    this.status.setText( target.getName() );
  }

  File target;
  JLabel status;
  PlanarImage image;
  ImagePanel display;
}