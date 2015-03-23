package com.greenbug.carlos.Noticias;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class NoticiasUv {

    private String title = "title";
    private String link = "link";
    private String description = "descripcion";
    private String pubDay = "pubDate";

    private String urlString = null;
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;

    public NoticiasUv(String url){
        this.urlString = url;
    }
    public String getTitle(){
        return title;
    }
    public String getLink(){
        return link;
    }
    public String getDescription(){
        return description;
    }

    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        String text=null;
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name=myParser.getName();
                switch (event){
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(name.equals("title")){
                            title = text;
                        }
                        else if(name.equals("link")){
                            link = text;
                        }
                        else if(name.equals("descripcion")){
                            description = text;
                        }
                        else if(name.equals("pubDate")){
                            description = text;
                        }
                        Log.e("Noticia", getTitle()+ getLink()+getDescription());
                        break;
                }
                event = myParser.next();
            }
            parsingComplete = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void fetchXML(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream stream = conn.getInputStream();
                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myparser = xmlFactoryObject.newPullParser();
                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    myparser.setInput(stream, null);
                    parseXMLAndStoreIt(myparser);
                    stream.close();
                } catch (Exception e) {
                }
            }
        });
        thread.start();
    }
}