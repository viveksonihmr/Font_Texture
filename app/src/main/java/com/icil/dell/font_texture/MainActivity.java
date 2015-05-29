package com.icil.dell.font_texture;


        import android.app.Activity;
        import android.content.Context;
        import android.opengl.GLSurfaceView;
        import android.os.Bundle;

public class MainActivity extends Activity
{
    private GLSurfaceView glView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        glView = new T_SurfaceView( this );
        setContentView( glView );
    }

    @Override
    protected void onPause() {
        glView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        glView.onResume();
    }
}

class T_SurfaceView extends GLSurfaceView {

    public T_SurfaceView(Context context){
        super( context );


        setRenderer( new T_Renderer( context ) );
    }
}
