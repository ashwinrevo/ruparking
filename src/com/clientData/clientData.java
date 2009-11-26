package com.clientData;

import android.app.Activity;
import android.os.Bundle;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;



import android.widget.TextView;

    public class clientData extends Activity {
    	public String[] sensordata;
    	public static ArrayList<String> read=new ArrayList<String>();
    	public static ArrayList<String> latitude_coor=new ArrayList<String>();
    	public static ArrayList<String> longitude_coor=new ArrayList<String>();
    	
    	/** Called when the activity is first created. */
        @Override
         
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
//            setContentView(R.layout.main);
            
            String parkingInfo =
                DownloadText("http://www.winlab.rutgers.edu/~vrajeshv/database.txt");
            String[] lines;
            String delimiter = "\\s+";
            lines = parkingInfo.split(delimiter);//temp is an array of all lines
          //  String[] sensordata;
            String delimiter1 = ",";
            
      for (int i=0; i<lines.length;i++){
            sensordata = lines[i].split(delimiter1);//sensordata is an array of all words
   
            read.add(new String(sensordata[0]));
            latitude_coor.add(new String(sensordata[1]));
            longitude_coor.add(new String(sensordata[3]));
           
           }
            
 //           TextView txt = (TextView) findViewById(R.id.text);
 //           txt.setText(longitude_coor.get(24));        

        }   
            
        private InputStream OpenHttpConnection(String urlString) 
        throws IOException
        {
            InputStream in = null;
            int response = -1;
                   
            URL url = new URL(urlString); 
            URLConnection conn = url.openConnection();
                     
            if (!(conn instanceof HttpURLConnection))                     
                throw new IOException("Not an HTTP connection");
            
            try{
                HttpURLConnection httpConn = (HttpURLConnection) conn;
                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setRequestMethod("GET");
                httpConn.connect(); 

                response = httpConn.getResponseCode();                 
                if (response == HttpURLConnection.HTTP_OK) {
                    in = httpConn.getInputStream();                                 
                }                     
            }
            catch (Exception ex)
            {
                throw new IOException("Error connecting");            
            }
            return in;     
        }
        private String DownloadText(String URL)
        {
            int BUFFER_SIZE = 2000;
            InputStream in = null;
            try {
                in = OpenHttpConnection(URL);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "";
            }
            
            InputStreamReader isr = new InputStreamReader(in);
            int charRead;
              String str = "";
              char[] inputBuffer = new char[BUFFER_SIZE];          
            try {
                while ((charRead = isr.read(inputBuffer))>0)
                {                    
                    //---convert the chars to a String---
                    String readString = 
                        String.copyValueOf(inputBuffer, 0, charRead);                    
                    str += readString;
                    inputBuffer = new char[BUFFER_SIZE];
                }
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "";
            }    
            return str;        
        }
        
    }