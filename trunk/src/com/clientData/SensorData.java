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
/**
 * This class fetches the parking spot information from the server.
 * It contains three ArrayLists which store values of latitude, longitude and sensor readings.
 * 
 * @version     1.0
 *
 */
   public class SensorData extends Activity {
       public String[] sensordata;
       public ArrayList<String> read=new ArrayList<String>();
       public ArrayList<String> latitude_coor=new ArrayList<String>();
       public ArrayList<String> longitude_coor=new ArrayList<String>();
       /**
   	 * Called when the activity is first created. 
   	 * Returns data to the MapDisplay class. 
   	 * 
   	 * @param  url String contains the address of the server from where the file is fetched.
   	 * @throws FileNotFoundException if the data file is not present in the mobile phone.
   	 * @return parkingInfo String containing the information corresponding to location of the parking spot.
   	 * 
   	 */   
       
       @Override

       public void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
//            setContentView(R.layout.main);
       }

       public void initialize(){
           String parkingInfo =

DownloadText("http://www.winlab.rutgers.edu/~vrajeshv/new.txt");
           String[] lines;
           String delimiter = "\n";
           lines = parkingInfo.split(delimiter);//temp is an array of all lines
           String delimiter1 = ",";

     for (int i=0; i<(lines.length)-1;i++){
           sensordata = lines[i].split(delimiter1);//sensordata is an array of all words

           read.add(new String(sensordata[0]));
           latitude_coor.add(new String(sensordata[1]));
           longitude_coor.add(new String(sensordata[3]));

          }

        }

       /**
        * Checks if http connection is open     
        * @param url String contains the address of the server from where the file is fetched.
        * @return in InputStream 
        * @throws IOException
        */
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
   /**
     * Downloads the file from the specified location
     * @param  URL String contains the address of the server from where the file is fetched.
   	 * @throws FileNotFoundException if the data file is not present at the specified location.
   	 * @return str String containing the information corresponding to location of the parking spot.
   	 */
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