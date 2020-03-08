package com.example.navigation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


public class MainActivity extends AppCompatActivity {
    Database mDatabaseHelper;
    Button submit;
    Button classi;
    EditText src;
    EditText des;
    TextView path;
    WebView webview;
    public static HashMap<Integer, String> dic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView.enableSlowWholeDocumentDraw();
        setContentView(R.layout.activity_main);// Java program to print BFS traversal from a given source vertex.
        //com.makor.hotornot.MainActivity.setContext(this);
        submit = findViewById(R.id.getData);
        path = findViewById(R.id.output);
        classi = findViewById(R.id.claasi);



        mDatabaseHelper = new Database(this);
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
                src= (EditText) findViewById(R.id.src);
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

                HashMap<Integer, String> dic = new HashMap<>();
                dic.put(1, "Marcus back entrance from entrance");
                dic.put(2, "Marcus back entrance from middle of atrium");
                dic.put(3, "Entrance to hallway");
                dic.put(4, "Hallway next to Mens bathroom");
                dic.put(5, "Hallway next to Womens bathroom");
                dic.put(6, "Mens bathroom");
                dic.put(7, "Midpoint in the hallway");
                dic.put(8, "Womens bathroom");
                dic.put(9, "End of hallway");
                dic.put(10, "Staircase");
                dic.put(11, "Marston entrance");
                dic.put(12, "Front entrance of Marcus");
                dic.put(13, "Marcus 131");
                dic.put(14, "Hallway to Marcus atrium");
                dic.put(15, "Hallway to Mens bathroom 2");
                dic.put(16, "Hallway to Womens bathroom 2");
                dic.put(17, "Hallway next to poster wale");
                dic.put(18, "End of hallway");
                dic.put(19, "Marston entrance 2");
                dic.put(20, "Graduate centre entrance");







                final NodeWeighted s;
                final NodeWeighted d ;

                s = map.get(source);
                d = map.get(dest);
                // USER INPUT

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
                setMap(dic);
                String p = graphWeighted.DijkstraShortestPath(s, d, getApplicationContext());
                path.setText(p);


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

            }

        });

        classi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.makor.hotornot.Classif.init(getApplicationContext());
                Picture picture = webview.capturePicture();
                PictureDrawable pd = new PictureDrawable(picture);
                Bitmap bt = Bitmap.createBitmap(pd.getIntrinsicWidth(), pd.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bt);
                canvas.drawPicture(pd.getPicture());
                com.makor.hotornot.Classif.classifyPhoto(getApplicationContext(),bt);
            }
        }
        );


    }
    public static HashMap getmap(){
        return dic;
    }
    public void setMap(HashMap dic){
        this.dic = dic;
    }


}
