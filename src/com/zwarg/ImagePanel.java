package com.zwarg;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.media.jai.*;

/**
 * <p>Title: ThumbNailing App</p>
 * <p>Description: Make Thumbnails from Dirs</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Zwarg
 * @version 1.0
 */

public class ImagePanel extends JPanel {

  public ImagePanel() {
    super();
  }

  public void paint( Graphics g ) {
    update(g);
  }

  public void update( Graphics g ) {
    Rectangle clip = g.getClipBounds();

    g.clearRect( 0, 0, this.getWidth(), this.getHeight() );

    if ( image != null )
      g.drawImage( image.getAsBufferedImage(), 0, 0, null );
  }

  public void setImage( PlanarImage image ) {
    this.image = image;

    this.setPreferredSize( new Dimension( this.image.getWidth(), this.image.getHeight() ) );

    repaint();

    ((JScrollPane)(getParent().getParent())).revalidate();
    getParent().getParent().repaint();
  }

  public PlanarImage getImage() {
    return image;
  }

  PlanarImage image;
}