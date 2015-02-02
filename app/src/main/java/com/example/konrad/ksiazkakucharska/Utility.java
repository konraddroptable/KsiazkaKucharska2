package com.example.konrad.ksiazkakucharska;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.Random;

/**
 * Created by Konrad on 2015-01-21.
 */
public class Utility {
    //get listview height
    public static void getListViewSize(ListView myListView) {
        ListAdapter myListAdapter = myListView.getAdapter();
        if (myListAdapter == null) {
            //do nothing return null
            return;
        }
        //set listAdapter in loop for getting final size
        int totalHeight = 0;
        for (int size = 0; size < myListAdapter.getCount(); size++) {
            View listItem = myListAdapter.getView(size, null, myListView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        //setting listview item in adapter
        ViewGroup.LayoutParams params = myListView.getLayoutParams();
        params.height = totalHeight + (myListView.getDividerHeight() * (myListAdapter.getCount() - 1)) + 200;
        myListView.setLayoutParams(params);
        // print height of adapter on log
        Log.i("height of listItem:", String.valueOf(totalHeight));
    }

    public static void decodeAndSetImage(String base64bytes, ImageView image){
        //zamień ciąg tekstowy Base-64 na tablice bajtów
        byte[] decodedString = Base64.decode(base64bytes, Base64.DEFAULT);
        // utwórz bitmapę na podstawie ciągu bajtów z obrazem JPEG
        Bitmap decodedBytes = BitmapFactory.decodeByteArray(decodedString, 0,
                decodedString.length);
        // wstaw bitmapę do komponentu ImageView awatara
        image.setImageBitmap(decodedBytes);
    }

    public static void colorFilter(ImageView imageView){
        Random random = new Random();
        int randomNumber = random.nextInt(5);

        if(randomNumber == 0)
            imageView.setColorFilter(0xff33b5e5, PorterDuff.Mode.MULTIPLY);
        if(randomNumber == 1)
            imageView.setColorFilter(0xffaa66cc, PorterDuff.Mode.MULTIPLY);
        if(randomNumber == 2)
            imageView.setColorFilter(0xff99cc00, PorterDuff.Mode.MULTIPLY);
        if(randomNumber == 3)
            imageView.setColorFilter(0xffffbb33, PorterDuff.Mode.MULTIPLY);
        if(randomNumber == 4)
            imageView.setColorFilter(0xffff4444, PorterDuff.Mode.MULTIPLY);
    }
}
