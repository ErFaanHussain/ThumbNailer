package com.zwarg;

import java.io.File;
import java.util.Vector;
import java.awt.*;
import javax.swing.*;
import javax.media.jai.*;
import javax.media.jai.JAI;

/**
 * <p>Title: ThumbNailing App</p>
 * <p>Description: Make Thumbnails from Dirs</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Zwarg
 * @version 1.0
 */

public class ImageLoader extends Thread {

  public ImageLoader() {
    super();

    //images = new Vector();
  }

  public void run() {
    File aFile;
    PlanarImage tmp;
    ParameterBlockJAI pb = new ParameterBlockJAI( "scale" );
    int w, h;
    float scl;

    ImageButton[] thumbs = new ImageButton[ files.size() ];
    GridBagLayout gbl = (GridBagLayout)panel.getLayout();
    GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridwidth = gbc.REMAINDER;
      gbc.fill = gbc.HORIZONTAL;

    for ( int i = 0; i < files.size(); i++ ) {
      aFile = (File)files.get(i);

      progress.setValue(i);

      tmp = JAI.create( "fileload", aFile.getAbsolutePath() );

      w = tmp.getWidth();
      h = tmp.getHeight();

      if ( w > h ) {
        scl = 100.f/(float)w;
      }
      else {
        scl = 100.f/(float)h;
      }

      RenderingHints rh = new RenderingHints( JAI.KEY_BORDER_EXTENDER,
          BorderExtender.createInstance(BorderExtender.BORDER_COPY) );

      pb = new ParameterBlockJAI( "scale" );
      pb.setSource( tmp, 0 );
      pb.setParameter( "xScale", scl );
      pb.setParameter( "yScale", scl );
      pb.setParameter( "xTrans", 0.f );
      pb.setParameter( "yTrans", 0.f );
      //pb.setParameter( "interpolation", new InterpolationBicubic( 4 ) );

      tmp = JAI.create( "scale", pb, rh );

      thumbs[ i ] = new ImageButton( new ImageIcon( tmp.getAsBufferedImage() ) );
      thumbs[ i ].target = aFile;
      thumbs[ i ].display = this.display;
      thumbs[ i ].status = this.status;
      gbl.setConstraints( thumbs[i], gbc );
      panel.add( thumbs[i] );

      panel.revalidate();
      panel.repaint();
    }
  }

  public Vector files;
  public JProgressBar progress;
  public JScrollPane scroll;
  public JPanel panel;
  public ImagePanel display;
  public JLabel status;
  public String prefix;
}