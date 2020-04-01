package com.example.navigation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import  java.util.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


public class MainActivity extends AppCompatActivity {
    Database mDatabaseHelper;
    Button act2;
    Button submit;
    Button classi;
    EditText src;
    EditText des;
    TextView path;
    WebView webview;
    public static HashMap<Integer, String> dic;
    public  static String s2,d2;
    public int source=1;
    public int dest=1;
    public  String p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView.enableSlowWholeDocumentDraw();
        setContentView(R.layout.activity_main);// Java program to print BFS traversal from a given source vertex.
        //com.makor.hotornot.MainActivity.setContext(this);
        submit = findViewById(R.id.getData);
        path = findViewById(R.id.output);
        classi = findViewById(R.id.claasi);
        act2 = findViewById(R.id.act2);

        mDatabaseHelper = new Database(this);






        final HashMap<Integer, String> dic = new HashMap<>();
        dic.put(1, "Marcus Exit from main atrium (Marcus exit A)*");
        dic.put(2, "Guinness Student Center");
        dic.put(3, "CEI HUB");
        dic.put(4, "Marcus Entrance from Main Atrium (Marcus Entrance A)*");
        dic.put(5, "Marcus exit from past the pillars (Marcus exit B) *");
        dic.put(6, "Marcus 131");
        dic.put(7, "Hallway Entrance");
        dic.put(8, "Marcus Entrance from past the pillars (Marcus Entrance B)*");
        dic.put(9, "Men’s Restroom");
        dic.put(10, "Wall opposite Mens Restroom");
        dic.put(11, "Women’s Restroom");
        dic.put(12, "Wall opposite Womens RestroomF");
        dic.put(13, "Water fountain (Entrance to stairs)");
        dic.put(14, "Bulletin Board opposite water fountain");
        dic.put(15, "Entrance to Graduate Hub");
        dic.put(16, "Doorway to Staircase");
        dic.put(17, "Bulletin Board Wall (Hallway to Marston)");
        dic.put(18, "Engineering Graduate Hub Back entrance");
        dic.put(19, "Entrance to Marston");
        dic.put(20, "Wall with the photo (On the way back from entrance to Marston)");

        setMap(dic);


        final String piAddr = "http://192.168.43.120:8081/";
        webview = (WebView) findViewById(R.id.streamview);
        //webview = new WebView(getApplicationContext());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setAllowContentAccess(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.zoomOut();
        webview.clearSslPreferences();
        // webview.setWebChromeClient(new MyWebChromeClient());
        webview.loadUrl(piAddr);

        webview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });


        classi.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {

                                          int clsource;
                                        /*
                                          try {
                                          com.makor.hotornot.Classif.init(getApplicationContext());
                                          Picture picture = webview.capturePicture();
                                          PictureDrawable pd = new PictureDrawable(picture);
                                          Bitmap bt = Bitmap.createBitmap(pd.getIntrinsicWidth(), pd.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                                          Canvas canvas = new Canvas(bt);
                                          canvas.drawPicture(pd.getPicture());
                                          String classres = String.valueOf(com.makor.hotornot.Classif.classifyPhoto(getApplicationContext(),bt));
                                          classres = classres.substring(classres.indexOf("="), classres.indexOf(","));
                                          clsource = getKeyByValue(dic, classres);
                                          }
                                          catch (NullPointerException e) {
                                              clsource = 1;
                                          }


                                         */
                                          src= (EditText) findViewById(R.id.src);
                                          src.setText("1");
                                      }
                                  }
        );


        act2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openact2();
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // NODES AND EDGES ---- GRAPH STUFF ----- NODES AND EDGES
                GraphWeighted graphWeighted = new GraphWeighted(true);
                NodeWeighted one = new NodeWeighted(1, "1");
                NodeWeighted two = new NodeWeighted(2, "2");
                NodeWeighted three = new NodeWeighted(3, "3");
                NodeWeighted four = new NodeWeighted(4, "4");
                NodeWeighted five = new NodeWeighted(5, "5");
                NodeWeighted six = new NodeWeighted(6, "6");
                NodeWeighted seven = new NodeWeighted(7, "7");
                NodeWeighted eight = new NodeWeighted(8, "8");
                NodeWeighted nine = new NodeWeighted(9, "9");
                NodeWeighted ten = new NodeWeighted(10, "10");

                NodeWeighted eleven = new NodeWeighted(11, "11");
                NodeWeighted twelve = new NodeWeighted(12, "12");
                NodeWeighted thirteen = new NodeWeighted(13, "13");
                NodeWeighted fourteen = new NodeWeighted(14, "14");
                NodeWeighted fifteen = new NodeWeighted(15, "15");
                NodeWeighted sixteen = new NodeWeighted(16, "16");
                NodeWeighted seventeen = new NodeWeighted(17, "17");
                NodeWeighted eighteen = new NodeWeighted(18, "18");
                NodeWeighted nineteen = new NodeWeighted(19, "19");
                NodeWeighted twenty = new NodeWeighted(20, "20");

                // Our addEdge method automatically adds Nodes as well.
                // The addNode method is only there for unconnected Nodes,
                // if we wish to add any
                graphWeighted.addEdge(one, two, 2,"S");
                graphWeighted.addEdge(two, three, 2,"L");
                graphWeighted.addEdge(three, four, 2,"S");
                graphWeighted.addEdge(four, five, 2,"S");
                graphWeighted.addEdge(five, six, 3,"R");
                graphWeighted.addEdge(five, seven, 2,"S");
                graphWeighted.addEdge(seven, eight, 2,"R");
                graphWeighted.addEdge(seven, nine, 2,"S");
                graphWeighted.addEdge(nine, ten, 2,"R");
                graphWeighted.addEdge(nine, eleven, 2,"L");

                graphWeighted.addEdge(twelve, thirteen, 2,"S");
                graphWeighted.addEdge(thirteen, two, 2,"S");
                graphWeighted.addEdge(thirteen, fourteen, 2,"L");
                graphWeighted.addEdge(fourteen, six, 2,"R");
                graphWeighted.addEdge(fourteen, thirteen, 2,"S");
                graphWeighted.addEdge(fifteen, eight, 2,"R");
                graphWeighted.addEdge(fifteen, sixteen, 2,"S");
                graphWeighted.addEdge(sixteen, ten, 2,"R");
                graphWeighted.addEdge(sixteen, twenty, 2,"L");
                graphWeighted.addEdge(sixteen, seventeen, 2,"S");
                graphWeighted.addEdge(seventeen, eighteen, 2,"S");
                graphWeighted.addEdge(eighteen, nineteen, 2,"L");
                // NODES AND EDGES ---- GRAPH STUFF ----- NODES AND EDGES


                //To parse in source and dest later
                //src= (EditText) findViewById(R.id.src);
                des = (EditText) findViewById(R.id.des);
                int source = Integer.parseInt(src.getText().toString());
                int dest = Integer.parseInt(des.getText().toString());
                HashMap<Integer, NodeWeighted> map = new HashMap<>();
                map.put(1, one);
                map.put(2, two);
                map.put(3, three);
                map.put(4, four);
                map.put(5, five);
                map.put(6, six);
                map.put(7, seven);
                map.put(8, eight);
                map.put(9, nine);
                map.put(10, ten);
                map.put(11, eleven);
                map.put(12, twelve);
                map.put(13, thirteen);
                map.put(14, fourteen);
                map.put(15, fifteen);
                map.put(16, sixteen);
                map.put(17, seventeen);
                map.put(18, eighteen);
                map.put(19, nineteen);
                map.put(20, twenty);


                final NodeWeighted s;
                final NodeWeighted d ;

                // USER INPUT for destination
                s = map.get(source);
                d = map.get(dest);

                //DATABASE STUFF
                mDatabaseHelper.addData("1", "2", 2.0,"S");
                mDatabaseHelper.addData("2", "3", 2.0,"L");
                mDatabaseHelper.addData("3", "4", 2.0,"S");
                mDatabaseHelper.addData("4", "5", 2.0,"S");
                mDatabaseHelper.addData("5", "6", 3.0,"R");
                mDatabaseHelper.addData("6", "14", 3.0,"R");
                mDatabaseHelper.addData("5", "7", 2.0,"S");
                mDatabaseHelper.addData("7", "8", 2.0,"R");
                mDatabaseHelper.addData("8", "15", 3.0,"R");
                mDatabaseHelper.addData("7", "9", 2.0,"S");
                mDatabaseHelper.addData("9", "10", 2.0,"R");
                mDatabaseHelper.addData("9", "11", 2.0,"L");

                mDatabaseHelper.addData("12", "13", 2.0,"S");
                mDatabaseHelper.addData("13", "2", 2.0,"S");
                mDatabaseHelper.addData("13", "14", 2.0,"L");
                mDatabaseHelper.addData("14", "6", 2.0,"R");
                mDatabaseHelper.addData("15", "13", 2.0,"S");
                mDatabaseHelper.addData("15", "8", 2.0,"R");
                mDatabaseHelper.addData("15", "16", 2.0,"S");
                mDatabaseHelper.addData("16", "10", 2.0,"R");
                mDatabaseHelper.addData("16", "20", 2.0,"L");
                mDatabaseHelper.addData("16", "17", 2.0,"S");
                mDatabaseHelper.addData("17", "18", 2.0,"S");
                mDatabaseHelper.addData("18", "19", 2.0,"L");
                //DATABASE STUFF


                String p = graphWeighted.DijkstraShortestPath(s, d, getApplicationContext());
                path.setText(p);




            }

        });




    }
    public static HashMap getmap(){
        return dic;
    }
    public void setMap(HashMap dic){
        this.dic = dic;
    }
    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }


    public  void openact2() {
        //Send data to act2
        src=  findViewById(R.id.src);
        des = findViewById(R.id.des);
        int source2 = Integer.parseInt(src.getText().toString());
        int dest2 = Integer.parseInt(des.getText().toString());
        Bundle extras = new Bundle();
        extras.putInt("source", source2);
        extras.putInt("dest", dest2);
        Intent intent = new Intent(this, MainActivity2.class);

        intent.putExtras(extras);

        startActivity(intent);
    }

}
