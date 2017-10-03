package res.api;

import res.conf.PropertyHandeler;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Project: hw-planner
 *
 * @autor Job Stoit
 * @copy 2017
 * @since 01-10-17
 */
public class APIConnect {

    private URL apiUrl;
    private String APIKey;
    private String sendMethod;

    private String dataResp;

    /**
     * construct
     *
     * Please note the default properties are defined in the "res/conf/api.properies" file
     *
     * To add another ApiConnect create a new {name}.properties file with
     * the {name}.APIUrl and {name}.sentMethod and {name}.APIKey
     *
     *
     * @param name (optional) specifies the name to the apiconfig properties file
     */

    public APIConnect(String name){
        PropertyHandeler PH = new PropertyHandeler(name);

        try {
            this.apiUrl = new URL(System.getProperty(name+".APIUrl"));
            this.sendMethod = System.getProperty(name+".sentMethod");
            this.APIKey = System.getProperty(name+".APIKey");

        } catch (MalformedURLException e){
            System.out.println("error - DataConnect.construct:");
            System.out.println(e);
        }

        requestData();
    }

    public APIConnect(){
        PropertyHandeler PH = new PropertyHandeler("api");

        try {
            this.apiUrl = new URL(System.getProperty("main.APIUrl"));
            this.sendMethod = System.getProperty("main.sentMethod");
            this.APIKey = System.getProperty("main.APIKey");

        } catch (MalformedURLException e){
            System.out.println("error - DataConnect.construct:");
            System.out.println(e);
        }

        requestData();
    }


    /**
     * function requestData()
     *
     * @param parameters String|HashMap< String, String>
     *
     */

    public void requestData(String parameters){

        parameters = parameters +"&"+ this.APIKey;

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) this.apiUrl.openConnection();

            connection.setRequestMethod(this.sendMethod);

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", Integer.toString(parameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //send data
            DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
            wr.writeBytes(parameters);
            wr.close();

            //receive data
            InputStream in = connection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder resp = new StringBuilder();
            String ln;

            while((ln = br.readLine()) != null){
                resp.append(ln);
            }

            this.dataResp = resp.toString();


            connection.disconnect();
        } catch (IOException e){
            System.out.println("error - DataConnect.requestData:");
            System.out.println(e);
        }
    }

    public void requestData(HashMap<String, String> parameters){

        HttpURLConnection connection = null;

        StringBuilder sentString = new StringBuilder();

        parameters.forEach((key, value) ->{
            sentString.append(key+"="+value+"&");
        });

        if(this.APIKey != null){
            sentString.append(APIKey);
        }

        String sentstring = sentString.toString();

        try {
            connection = (HttpURLConnection) this.apiUrl.openConnection();

            connection.setRequestMethod(this.sendMethod);

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", Integer.toString(sentstring.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //send data
            DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
            wr.writeBytes(sentstring);
            wr.close();

            //receive data
            InputStream in = connection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder resp = new StringBuilder();
            String ln;

            while((ln = br.readLine()) != null){
                resp.append(ln);
            }

            this.dataResp = resp.toString();



            connection.disconnect();
        } catch (IOException e){
            System.out.println("error - DataConnect.requestData:");
            System.out.println(e);
        }
    }



    public void requestData(){

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) this.apiUrl.openConnection();

            connection.setRequestMethod(this.sendMethod);

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //receive data
            InputStream in = connection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder resp = new StringBuilder();
            String ln;

            while((ln = br.readLine()) != null){
                resp.append(ln);
            }

            this.dataResp = resp.toString();


            connection.disconnect();
        } catch (IOException e){
            System.out.println("error - DataConnect.requestData:");
            System.out.println(e);
        }
    }

    /**
     * getResp()
     *
     * @return resp String
     */

    public String getResp(){
        return this.dataResp;
    }

}
