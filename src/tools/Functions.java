package tools;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import starlight.*;
import tools.*;
import java.text.*;
import tools.popup.*;
import java.util.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import java.sql.*;


public class Functions {
   
     public static String saveLoginCookie(Client client) {     
String cookie = RandomString.getRandomString(10); 
client.setLoginCookie(cookie);
return cookie;
 }
       public static String getNickUmrandung(Client client,Channel channel) {
              Font f = new Font("Arial", Font.BOLD, channel.getStyle().getFontSize());
                FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(f);
                int width = fm.stringWidth(client.getName()); // nickbreite in pixel.
              
           String t1 = "null";
           String t2 = "null";
       

           /*  if (!client.getBedOfRoses().isEmpty()) {
          
        
           String dazu2 = "";
int start2 = width*-1;
if (width >= 60) {
   double x = (width-59)/15;
int mehr = (int)Math.ceil(x);
int imgzahl = 1;
for (int begin = 0; begin <= mehr; begin++) { 
dazu2 += "°>features/bedofroses/nickleaves_frontrep"+imgzahl+"...b.w_0.h_26.mx_"+start2+".my_12.png<°";
imgzahl++;
if (imgzahl >= 2) {
imgzahl = 1;
}
start2 = start2+18;
}
} else {
    dazu2 += "°>features/bedofroses/nickleaves_frontrep1...b.w_0.h_26.mx_"+start2+".my_12.png<°";
    start2 = start2+18;
}      
           
t1 = dazu2;
               
    String dazu = "";
int start = 21;
if (width >= 50) {
   double x = (width-49)/40;
int mehr = (int)Math.ceil(x);
int imgzahl = 1;
for (int begin = 0; begin <= mehr; begin++) { 
dazu += "°>features/bedofroses/nickleaves_rep"+imgzahl+"...b.w_0.h_26.mx_"+start+".my_12.png<°";
imgzahl++;
if (imgzahl >= 2) {
imgzahl = 1;
}
start = start+22;
}
} else {
    dazu += "°>features/bedofroses/nickleaves_rep1...b.w_0.h_26.mx_"+start+".my_12.png<°";
    start = start+22;
}
   
    t2 = "°>features/bedofroses/nickleaves_start...b.w_0.h_26.mx_-5.my_12.png<°"+dazu+"°>features/bedofroses/nickleaves_end...b.w_0.h_26.mx_"+start+".my_12.png<°°W>{+textborder}<°";

                  
                
               
               
           } else 
           */
        if (client.getKonfetti()) {
          
        
    
 
    String dazu = "";
int start = (width*-1)-12;
if (width >= 18) {
   double x = ((double)width-(double)18)/(double)26;
int mehr = (int)Math.ceil(x);
for (int begin = 1; begin <= mehr; begin++) { 
dazu += "°>features/confetti/ft_confetti_m...b.mx_"+start+".w_0.my_3.png<°";
start = start+26;
}
} else {
 dazu += "°>features/confetti/ft_confetti_m...b.mx_"+start+".w_0.my_3.png<°";
    start = start+26;
}
   t2 = "null";
    t1 = "°>features/confetti/ft_confetti_l...b.mx_-"+width+".w_0.my_3.png<°"+dazu+"°>features/confetti/ft_confetti_r...b.mx_"+start+".w_0.my_3.png<°";

                  
         
               
               
           } else  if(client.getElement() != 0 && client.getElement() <= 4) {
           String elementcode = "";    
           // wasser und  erde
if (client.getElement() == 4 || client.getElement() == 3) {
    String iconletter = "e";
    if (client.getElement() == 3) {
        iconletter = "w";
    }
String dazu = "";
int start = 13;
width = width-10;
if (width >= 50) {
    
    double x = (width-49)/20;
int mehr = (int)Math.ceil(x);
int imgzahl = 1;

for (int begin = 0; begin <= mehr; begin++) { 
dazu += "°>features/elements/nick_"+iconletter+"-c_0"+imgzahl+"...mx_"+start+".w_0.h_0.my_2.png<°";
imgzahl++;
if (imgzahl >= 4) {
imgzahl = 1;
}
start = start+20;
}
} else {
    dazu += "°>features/elements/nick_"+iconletter+"-c_01...mx_"+start+".w_0.h_0.my_2.png<°";
    start = start+20;
}
elementcode =  "°>features/elements/nick_"+iconletter+"-l...mx_-10.w_0.h_0.my_2.png<°"+dazu+"°>features/elements/nick_"+iconletter+"-r...mx_"+start+".w_0.h_0.my_2.png<°";
elementcode += "°>{+textborder}<°";
} else 
if (client.getElement() == 2) {
String dazu = "";
int start = 13;
width=width-10;
if (width >= 47) {
   double x = (width-49)/20;
int mehr = (int)Math.ceil(x);
int imgzahl = 1;
for (int begin = 0; begin <= mehr; begin++) { 
dazu += "°>features/elements/nick_a-c_0"+imgzahl+"...mx_"+start+".w_0.h_0.my_2.png<°";
imgzahl++;
if (imgzahl >= 3) {
imgzahl = 1;
}
start = start+20;
}
} else {
    dazu += "°>features/elements/nick_a-c_01...mx_"+start+".w_0.h_0.my_2.png<°";
    start = start+20;
}
elementcode =  "°>features/elements/nick_a-l...mx_-10.w_0.h_0.my_2.png<°"+dazu+"°>features/elements/nick_a-r...mx_"+start+".w_0.h_0.my_2.png<°";
elementcode += "°>{+textborder}<°";
} else 
    // feuer ^^
if (client.getElement() == 1) {
String dazu = "";
int start = 15;
width = width-10;
if (width >= 45) {
   double x = (width-44)/10;
int mehr = (int)Math.ceil(x);
int imgzahl = 1;
for (int begin = 0; begin <= mehr; begin++) { 
dazu += "°>features/elements/nick_f-c_0"+imgzahl+"...mx_"+start+".w_0.h_0.my_6.png<°";
imgzahl++;
if (imgzahl >= 4) {
imgzahl = 1;
}
start = start+10;
}
} else {
    dazu += "°>features/elements/nick_f-c_01...mx_"+start+".w_0.h_0.my_6.png<°";
    start = start+10;
}
elementcode =  "°>features/elements/nick_f-l...mx_-10.w_0.h_0.my_6.png<°"+dazu+"°>features/elements/nick_f-r...mx_"+start+".w_0.h_0.my_6.png<°";
elementcode += "°>{+textborder}<°";
}
      
t1 = "null";
if (client.getElement() == 4) {
   
    String dazu = "";
int start = (width*-1)-12;
if (width >= 50) {
   double x = ((double)width-(double)49)/(double)20;
int mehr = (int)Math.ceil(x);
int imgzahl = 1;
for (int begin = 0; begin <= mehr; begin++) { 
dazu += "°>features/elements/nick-over_e-c_00"+imgzahl+"...b.mx_"+start+".w_0.h_0.my_17.png<°";
imgzahl++;
if (imgzahl >= 4) {
imgzahl = 1;
}
start = start+20;
}
} else {
    dazu += "°>features/elements/nick-over_e-c_001...b.mx_"+start+".w_0.h_0.my_17.png<°";
    start = start+20;
}


    t1 = "°>features/elements/nick-over_e-l...b.mx_-106.w_0.h_0.my_17.png<°"+dazu+"°>features/elements/nick-over_e-r...b.mx_"+start+".w_0.h_0.my_17.png<°°>{-textborder}<°";
}
t2 = elementcode;
               }
               
               
           return t1+"~~"+t2;
       }
     
    
}