package com.example.sujith.space_war;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class flying_spacecraft extends View
{
    private Bitmap spacecraft[]=new Bitmap[2];
    private Bitmap life[]=new Bitmap[2];
    private Bitmap background_sp;

    private int space_craftX=10,space_craftY;
    private int space_craftSpeed;

    private boolean touch=false;

    private int scor_e,life_counter=3;

    private int canvasWidth,canvasHeight;

    private int yellowX,yellowY,yellowSpeed=16;
    private Paint yellowBall=new Paint();

    private int redX,redY,redSpeed=30;
    private Paint redBall=new Paint();


    private Paint score=new Paint();

    public flying_spacecraft(Context context)
    {
        super(context);

        spacecraft[0]=BitmapFactory.decodeResource(getResources(),R.drawable.space_ship1);
        spacecraft[1]=BitmapFactory.decodeResource(getResources(),R.drawable.space_ship2);
        background_sp=BitmapFactory.decodeResource(getResources(),R.drawable.background_space);

        yellowBall.setColor(Color.YELLOW);
        yellowBall.setAntiAlias(false);

        redBall.setColor(Color.RED);
        redBall.setAntiAlias(false);

        life[0]=BitmapFactory.decodeResource(getResources(),R.drawable.heart);
        life[1]=BitmapFactory.decodeResource(getResources(),R.drawable.heartblack);


        score.setColor(Color.WHITE);
        score.setTextSize(50);
        score.setTypeface(Typeface.DEFAULT_BOLD);
        score.setAntiAlias(true);

        space_craftY=550;
        scor_e=0;

    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvasWidth=canvas.getWidth();
        canvasHeight=canvas.getHeight();

        canvas.drawBitmap(background_sp,0,0,null);

        int minspace_craftY=spacecraft[0].getHeight();
        int maxspace_craftY=canvasHeight-spacecraft[0].getHeight()*2;
        space_craftY=space_craftY+space_craftSpeed;

        if (space_craftY<minspace_craftY)
        {
            space_craftY=minspace_craftY;
        }
        if (space_craftY>maxspace_craftY)
        {
            space_craftY=maxspace_craftY;
        }

        space_craftSpeed=space_craftSpeed+2;

        if(touch)
        {
            canvas.drawBitmap(spacecraft[1],space_craftX,space_craftY,null);
            touch=false;

        }
        else
        {
            canvas.drawBitmap(spacecraft[0],space_craftX,space_craftY,null);
            touch=true;

        }

        yellowX=yellowX-yellowSpeed;

        if (hitBallchecker(yellowX,yellowY))
        {
            scor_e=scor_e+10;
            yellowX=-100;
        }

        if (yellowX<0)
        {
            yellowX=canvasWidth+21;
            yellowY=(int)Math.floor(Math.random()*(maxspace_craftY-minspace_craftY))+minspace_craftY;
        }
        canvas.drawCircle(yellowX,yellowY,25,yellowBall);

        redX=redX-redSpeed;

        if (hitBallchecker(redX,redY))
        {
            //scor_e=scor_e+10;
            redX=-100;
            life_counter--;

            if (life_counter==0)
            {
                Toast.makeText(getContext(), "Score : "+scor_e, Toast.LENGTH_SHORT).show();

                Intent i=new Intent(getContext(),page2.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getContext().startActivity(i);



            }

        }

        if (redX<0)
        {
            redX=canvasWidth+21;
            redY=(int)Math.floor(Math.random()*(maxspace_craftY-minspace_craftY))+minspace_craftY;
        }
        canvas.drawCircle(redX,redY,30,redBall);

        canvas.drawText("Score : "+scor_e,20,60,score);

        for (int i=0;i<3;i++)
        {
            int x=(int)(400+life[0].getWidth()*i);
            int y=20;

            if (i<life_counter)
            {
                canvas.drawBitmap(life[0],x,y,null);
            }
            else
            {
                canvas.drawBitmap(life[1],x,y,null);
            }

        }






    }

    public boolean hitBallchecker(int x,int y)
    {
        if (space_craftX<x&&x<(space_craftX+spacecraft[0].getWidth())
                &&space_craftY<y&&y<(space_craftY+spacecraft[0].getHeight()))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //return super.onTouchEvent(event);
        if (event.getAction()==MotionEvent.ACTION_DOWN)
        {
            touch=true;
            space_craftSpeed=-22;
        }

        return true;
    }
}
