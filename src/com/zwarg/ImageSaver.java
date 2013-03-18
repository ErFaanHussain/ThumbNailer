package com.zwarg;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

import javax.media.jai.*;

/**
 * <p>Title: ThumbNailing App</p>
 * <p>Description: Make Thumbnails from Dirs</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Zwarg
 * @version 1.0
 */

public class ImageSaver extends Thread {

  public ImageSaver() {
    super();
  }

  public void run() {
    File aFile;
    String type;
    PlanarImage tmp;
    ParameterBlockJAI pb;
    int w, h, kDim;
    float scl;
    String fName;
    Component[] thumbs = panel.getComponents();
    RenderingHints rh;
    float[] kernData;
    KernelJAI kern;

    progress.setValue( thumbs.length - 1 );

    for ( int i = 0; i < thumbs.length; i++ ) {
      aFile = ((ImageButton)thumbs[i]).target;

      tmp = JAI.create( "fileload", aFile.getAbsolutePath() );

      w = tmp.getWidth();
      h = tmp.getHeight();

      if ( w > h ) {
        scl = size/(float)w;
      }
      else {
        scl = size/(float)h;
      }

      rh = new RenderingHints( JAI.KEY_BORDER_EXTENDER,
          BorderExtender.createInstance(BorderExtender.BORDER_COPY) );

      kDim = 3;//(int)(1.d/scl);

      if ( kDim > 1 ) {
        pb = new ParameterBlockJAI( "convolve" );
        pb.setSource( tmp, 0 );

        kernData = new float[ kDim*kDim ];

        for( int j = 0; j < kernData.length; j++ )
          kernData[j] = 1.f/9.f;//(float)kernData.length;

        kern = new KernelJAI( kDim, kDim, kDim/2, kDim/2, kernData );
        pb.setParameter( "kernel", kern );

        tmp = JAI.create( "convolve", pb, rh );
      }

      pb = new ParameterBlockJAI( "scale" );
      pb.setSource( tmp, 0 );
      pb.setParameter( "xScale", scl );
      pb.setParameter( "yScale", scl );
      pb.setParameter( "xTrans", 0.f );
      pb.setParameter( "yTrans", 0.f );
      //pb.setParameter( "interpolation", new InterpolationBicubic( 4 ) );

      tmp = JAI.create( "scale", pb, rh );

      type = aFile.getName();

      if ( type.toLowerCase().endsWith("jpg") ||
           type.toLowerCase().endsWith("jpeg") )
        type = "JPEG";
      else if ( type.toLowerCase().endsWith("bmp") )
        type = "BMP";
      else if ( type.toLowerCase().endsWith("tif") ||
                type.toLowerCase().endsWith("tiff") )
        type = "TIFF";
      else //( fileParts[2].toLowerCase().endsWith("png") )
        type = "PNG";

      fName = location.getAbsolutePath() + File.separator + prefix + aFile.getName();
      //System.out.println( "saving as: " + fName );

      pb = new ParameterBlockJAI( "filestore" );
      pb.setSource( tmp, 0 );
      pb.setParameter( "filename", fName );
      pb.setParameter( "format", type );
      pb.setParameter( "param", null );

      JAI.create( "filestore", pb );

      // free image so it can be GC'd and unlocked.
      tmp = null;

      progress.setValue( thumbs.length - ( i + 1 ) );

      panel.remove( thumbs[i] );
      panel.revalidate();
      panel.repaint();
    }
  }

/*
  private String[] getFileParts( String file ) {
    String[] tmp = new String[3];
    int j,k;

    j = file.lastIndexOf("\\");
    k = file.lastIndexOf(".");

    tmp[0] = file.substring(0,j+1);
    tmp[1] = file.substring(j+1,k);
    tmp[2] = file.substring(k+1,file.length());

    return tmp;
  }
*/

  public File location;
  public JProgressBar progress;
  public JPanel panel;
  public String prefix;
  public float size;
}