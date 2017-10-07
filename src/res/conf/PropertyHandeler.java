package res.conf;

import java.io.*;
import java.util.Properties;

/**
 * Created by jobstoit on 27-06-17.
 */

public class PropertyHandeler {
    private File target;

    /**
     *
     * @param name String (optional) name of .properties file
     * @param systemprops bool (optional) save properies to system.properties
     */

    public PropertyHandeler(String name, boolean systemprops){
        this.target = new File(getClass().getResource(name+".properties").getPath());
        if(systemprops){
            systemInit();
        }
    }

    public PropertyHandeler(String name){
        this.target = new File(getClass().getResource(name+".properties").getPath())  ;
        systemInit();
    }

    public PropertyHandeler(boolean systemprops){
        this.target = new File(getClass().getResource("main.properties").getPath())  ;
        if (systemprops){
            systemInit();
        }
    }

    public PropertyHandeler(){
        this.target = new File(getClass().getResource("main.properties").getPath())  ;
        systemInit();
    }

    /**
     * function get()
     *
     * @param key String
     * @return value|null
     */

    public String get(String key){
        Properties prop = new Properties();
        try {
            InputStream in = new FileInputStream(this.target);
            prop.load(in);

            return prop.getProperty(key);

        } catch (IOException e){
            System.out.println("error - PropertyHandeler.setAllToSystem:");
            System.out.println(e);
            return null;
        }
    }

    /**
     * function addProperty()
     *
     * @param key String
     * @param value String
     */

    public void add(String key, String value) {
        Properties prop = new Properties();
        try{
            InputStream in = new FileInputStream(this.target);
            prop.load(in);
            in.close();

            FileOutputStream output = new FileOutputStream(this.target);

            prop.setProperty(key, value);
            prop.store(output, null);

            output.close();

            systemInit();

        } catch (FileNotFoundException e){
            System.out.println(e);
        } catch (IOException e){
            System.out.println("error - ProperyHandeler.addPropery :");
            System.out.println(e);
        }
    }

    /**
     * function remove()
     *
     * @param key String
     */

    public void remove(String key) {
        Properties prop = new Properties();
        try{
            InputStream in = new FileInputStream(this.target);
            prop.load(in);
            in.close();

            OutputStream out = new FileOutputStream(this.target);

            System.out.println("test:");
            System.out.println(prop.getProperty("test"));
            prop.remove(key);

            prop.store(out , "test");

            out.close();

            systemInit();

        } catch (IOException e){
            System.out.println("error - ProperyHandeler.addPropery :");
            System.out.println(e);
        }
    }



    /**
     * systemInit()
     *
     * updates the System.properies on runtime
     */

   private void systemInit(){
//       Properties prop = new Properties();
       Properties oldProps = System.getProperties();
       try {
           InputStream in = new FileInputStream(this.target);
//           prop.load(in);
           oldProps.load(in);
           System.setProperties(oldProps);

       } catch (IOException e){
           System.out.println("error - PropertyHandeler.setAllToSystem:");
           System.out.println(e);
       }
   }

}
