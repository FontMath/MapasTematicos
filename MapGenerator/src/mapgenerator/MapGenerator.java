
package mapgenerator;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MapGenerator {


    public static void main(String[] args) {
        
        String texto = "";
        texto = inicio();
            texto += tema("p001","Gerardo Mathus","#t_persona");
            texto += tema("p002","Carmen Cecilia V.","#t_persona");
            texto += tema("p003","Melissa M.","#t_persona");

            texto += tema("a001","Nissan","#t_auto");
            texto += tema("a002","Audi","#t_auto");
            texto += tema("a003","Mercedes","#t_auto");
            
            ArrayList<Integrante> elem1 = new ArrayList<>();
            elem1.add(new Integrante("#p001","#t_persona"));
            elem1.add(new Integrante("#a001","#t_auto"));
            elem1.add(new Integrante("#p003","#t_persona"));
            texto += asociacion("#t_posesion",elem1);
            
            ArrayList<Integrante> elem2 = new ArrayList<>();
            elem2.add(new Integrante("#p002","#t_persona"));
            elem2.add(new Integrante("#a002","#t_auto"));
            elem2.add(new Integrante("#p001","#t_persona"));
            elem2.add(new Integrante("#a001","#t_auto"));
            texto += asociacion("#t_posesion",elem2);
            
            ArrayList<Integrante> elem3 = new ArrayList<>();
            elem3.add(new Integrante("#p003","#t_persona"));
            elem3.add(new Integrante("#a003","#t_auto"));
            texto += asociacion("#t_posesion",elem3);
            
        texto += fin();
        System.out.print(texto);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream("mapaTematicoEnXML.xml"), "utf-8"))) {
            writer.write(texto);
}       catch (IOException ex) {
            Logger.getLogger(MapGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String inicio(){
        String texto = "";
        texto="<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>\n"+
                            "<topicMap xmlns=\"http://www.topicmaps.org/xtm/1.0/\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" id=\"Automatico.hytm\">"
                ;
        texto += tema("t_persona","Persona","");
        texto += tema("t_auto","Automovil","");
        texto += tema("t_relacion","Relacion","");
        texto += tema("t_posesion","Posesion","");
        texto += tema("t_mascota","Mascota","");
        texto += tema("t_gato","Gato","#t_mascota");
        texto += tema("t_perro","Perro","#t_mascota");
        return texto;
    }
    public static String fin(){
        return "</topicMap>";
    }
    public static String tema(String id, String nombre, String instancia){
        String texto = "<topic id=\""+id+"\">\n";
        if(!instancia.isEmpty()){
            texto +=
                "      <instanceOf>\n" +
                "         <topicRef xlink:href=\""+instancia+"\"></topicRef>\n" +
                "      </instanceOf>\n";
        }
        texto +=
                "      <baseName>\n" +
                "         <baseNameString>"+nombre+"</baseNameString>\n" +
                "      </baseName>\n" +
                "   </topic>\n";
        return texto;
    }
    public static String asociacion(String instancia, ArrayList<Integrante> integrantes){
        String texto = "<association>\n" +
                "      <instanceOf>\n" +
                "         <topicRef xlink:href=\""+instancia+"\">\n" +
                "         </topicRef>\n" +
                "      </instanceOf>\n";
        for(int i = 0; i<integrantes.size();i++){
            Integrante m = integrantes.get(i);
            texto +=
                "      <member>\n" +
                "         <roleSpec>\n" +
                "            <topicRef xlink:href=\""+m.Instancia+"\">\n" +
                "            </topicRef>\n" +
                "         </roleSpec>\n" +
                "         <topicRef xlink:href=\""+m.ID+"\">\n" +
                "         </topicRef>\n" +
                "      </member>\n";
        }
        texto += "   </association>\n";
        return texto;
    }
}
