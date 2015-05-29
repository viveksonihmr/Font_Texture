package com.icil.dell.font_texture;

/**
 * Created by Dell on 26-05-2015.
 */

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class T_Renderer implements GLSurfaceView.Renderer  {

    private GLText glText;                             // A GLText Instance
    private Context context;                           // Context (from Activity)

    private int width = 100;                           // Updated to the Current Width + Height in onSurfaceChanged()
    private int height = 100;

    public T_Renderer(Context context)  {
        super();
        this.context = context;                         // Save Specified Context
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Set the background frame color
        gl.glClearColor( 0.0f, 0.5f, 0.0f, 1.0f );

        // Create the GLText
        glText = new GLText( gl, context.getAssets() );

        // Load the font from file (set size + padding), creates the texture
        // NOTE: after a successful call to this the font is ready for rendering!
        glText.load( "Lato-Black.ttf", 20, 2, 2 );  // Create Font (Height: 14 Pixels / X+Y Padding 2 Pixels)
    }

    public void onDrawFrame(GL10 gl) {
        // Redraw background color
        gl.glClear( GL10.GL_COLOR_BUFFER_BIT );

        // Set to ModelView mode
        gl.glMatrixMode( GL10.GL_MODELVIEW );           // Activate Model View Matrix
        gl.glLoadIdentity();                            // Load Identity Matrix

        // enable texture + alpha blending
        gl.glEnable( GL10.GL_TEXTURE_2D );              // Enable Texture Mapping
        gl.glEnable( GL10.GL_BLEND );                   // Enable Alpha Blend
        gl.glBlendFunc( GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA );  // Set Alpha Blend Function


        // render some strings with the font
        glText.begin( 1f, 1.0f, 1.0f, 1.0f );         // Begin Text Rendering (Set Color WHITE)
        glText.draw( "Test String :)", 1500, 10 );          // Draw Test String
        glText.draw( "We Can write any text string", 1000, 1000 );                // Draw Test String
        glText.draw( "Line 2", 100, 100 );              // Draw Test String
        glText.end();                                   // End Text Rendering

        glText.begin( 0.0f, 0.0f, 1.0f, 1.0f );         // Begin Text Rendering (Set Color BLUE)
        glText.draw( "More Lines...", 50, 150 );        // Draw Test String
        glText.draw( "The End.", 50, 150 + glText.getCharHeight() );  // Draw Test String
        glText.end();                                   // End Text Rendering

        // disable texture + alpha
        gl.glDisable( GL10.GL_BLEND );                  // Disable Alpha Blend
        gl.glDisable( GL10.GL_TEXTURE_2D );             // Disable Texture Mapping
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport( 0, 0, width, height );

        // Setup orthographic projection
        gl.glMatrixMode( GL10.GL_PROJECTION );          // Activate Projection Matrix
        gl.glLoadIdentity();                            // Load Identity Matrix
        gl.glOrthof(                                    // Set Ortho Projection (Left,Right,Bottom,Top,Front,Back)
                0, width,
                0, height,
                1.0f, -1.0f
        );

        // Save width and height
        this.width = width;                             // Save Current Width
        this.height = height;                           // Save Current Height
    }
}
