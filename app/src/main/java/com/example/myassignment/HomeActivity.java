package com.example.myassignment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class HomeActivity extends AppCompatActivity {
    private TextView mTVname;
    private TextView mTVphonenumber;
    private TextView mTVaddress;
    private TextView mTVnationality;
    private SharedPreferences prefManager;
    private SharedPreferences.Editor editorimage;

    private ImageView mimgview;
    private Bitmap Imagepath;

    private static String BUNDLE_NAME="NAME";
    private static String BUNDLE_PHNNO="PHONENUMBER";
    private static String BUNDLE_ADDRESS="ADDRESS";
    private static String BUNDLE_NATIONALITY="NATIONALITY";
    public static final int RESULT_OK=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        mTVname=findViewById(R.id.tv_name);
        mTVphonenumber=findViewById(R.id.tv_phonenumber);
        mTVaddress=findViewById(R.id.tv_address);
        mTVnationality=findViewById(R.id.tv_nationality);

        prefManager=getApplicationContext().getSharedPreferences("camera_credentials",MODE_PRIVATE);
        editorimage=prefManager.edit();

        mimgview=findViewById(R.id.imageView2);
        mimgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,904);
            }
        });


        String retrieveddvalue=prefManager.getString("IMAGEBYTES","");
        byte[] ImageToBytes=retrieveddvalue.getBytes();
        Bitmap ImagetoBitmap= BitmapFactory.decodeByteArray(ImageToBytes,0,ImageToBytes.length);
        mimgview.setImageBitmap(ImagetoBitmap);

        Bundle returndata=getIntent().getExtras();
        if (returndata!=null){
            String edittedname= returndata.getString("NAME");
            String edittedphnno= returndata.getString("PHONENUMBER");
            String edittedaddress= returndata.getString("ADDRESS");
            String edittednationality=returndata.getString("NATIONALITY");
            mTVname.setText(edittedname);
            mTVphonenumber.setText(edittedphnno);
            mTVaddress.setText(edittedaddress);
            mTVnationality.setText(edittednationality);

            if (RESULT_OK==-1){
                Toast.makeText(HomeActivity.this,"Edited Successfully",Toast.LENGTH_LONG).show();}
        }




    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==904){
            if (data.getExtras()!=null){
                Imagepath= (Bitmap) data.getExtras().get("data");
            }
            else{
                try{
                Imagepath=MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            mimgview.setImageBitmap(Imagepath);
            ByteArrayOutputStream stream=new ByteArrayOutputStream();
            Imagepath.compress(Bitmap.CompressFormat.PNG,100,stream);
            byte[] ImageByte=stream.toByteArray();
            String ImageString=ImageByte.toString();
            editorimage.putString("IMAGEBYTES",ImageString);
            editorimage.commit();

        }




    }


    public void movetoeditscreen(View view){
        Intent object=new Intent(HomeActivity.this,EditActivity.class);
        startActivity(object);



    }









}