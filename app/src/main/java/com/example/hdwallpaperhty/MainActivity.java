package com.example.hdwallpaperhty;


import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    GridView myGridView;
    Integer [] myImageArray={
            R.drawable.wp1,R.drawable.wp2,R.drawable.wp3
    };
    ImageView myCurrentWallpaper;
    WallpaperManager myWallpaperManager;
    Drawable myDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UpdateMyWallpaper();
        myGridView=findViewById(R.id.myGridView);
        myCurrentWallpaper=findViewById(R.id.myImageView);


        myGridView.setAdapter(new ImageAdapter(getApplicationContext()));
    }


    public class ImageAdapter extends BaseAdapter {
        Context myContext;

        public ImageAdapter(Context applicationContext) {
            myContext=applicationContext;

        }

        @Override
        public int getCount() {
            return myImageArray.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ImageView GridImageView;
            if (view==null){
                GridImageView=new ImageView(myContext);
                GridImageView.setLayoutParams(new GridView.LayoutParams(512,512));
                GridImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


            }else {
                GridImageView=(ImageView)view;
            }
            GridImageView.setImageResource(myImageArray[i]);
            GridImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        myWallpaperManager.setResource(myImageArray [i]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    UpdateMyWallpaper();
                }
            });

            return GridImageView;
        }
    }

    private void UpdateMyWallpaper(){
        myWallpaperManager=WallpaperManager.getInstance(getApplicationContext());
        myDrawable=myWallpaperManager.getDrawable();
        myCurrentWallpaper.setImageDrawable(myDrawable);


    }


}
