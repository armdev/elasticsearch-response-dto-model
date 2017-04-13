package com.elastic.request.app;

import com.elastic.response.model.ElasticModel;
import com.elastic.response.model.Source;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import java.util.logging.Level;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

public class ElasticClientBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String SERVICE_PATH = "http://localhost:9200/auth/user/";

    public ElasticClientBean() {

    }

    public static void main(String args[]) {
        ElasticClientBean bean = new ElasticClientBean();
        Source model = new Source();
        model.setAge(33);
        model.setEmail("mail@gmail.com" + System.currentTimeMillis());
        model.setFirstname("FirstName");
        model.setLastname("LastName");
        bean.saveUser(model);
        bean.findAll();

    }

    public Source saveUser(Source model) {
        CloseableHttpClient CLIENT = HttpClients.createDefault();
        try {
            HttpPost request = new HttpPost(SERVICE_PATH);
            JSONObject json = new JSONObject();
            UUID userId = UUID.randomUUID();
            json.put("userId", userId.toString());
            json.put("firstname", model.getFirstname());
            json.put("lastname", model.getLastname());
            json.put("email", model.getEmail());
            json.put("age", model.getAge());
            StringEntity params = new StringEntity(json.toString(), "UTF-8");
            request.addHeader("content-type", "application/json;charset=UTF-8");
            request.addHeader("charset", "UTF-8");
            request.setEntity(params);
            HttpResponse response = (HttpResponse) CLIENT.execute(request);
            HttpEntity entity = response.getEntity();
            ObjectMapper mapper = new ObjectMapper();
            model = mapper.readValue((EntityUtils.toString(entity)), Source.class);
        } catch (IOException | ParseException ex) {
            try {
                CLIENT.close();
            } catch (IOException ex1) {
                java.util.logging.Logger.getLogger(ElasticClientBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                CLIENT.close();
            } catch (IOException ex1) {
                java.util.logging.Logger.getLogger(ElasticClientBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        return model;
    }

    public ElasticModel findAll() {
        ElasticModel userModel = new ElasticModel();
        CloseableHttpClient CLIENT = HttpClients.createDefault();
        try {
            //HttpGet request = new HttpGet(SERVICE_PATH + "_search?pretty=true&q=userId:" + userId); //one
            HttpGet request = new HttpGet(SERVICE_PATH + "_search?pretty=true"); //return the list
            request.addHeader("charset", "UTF-8");
            HttpResponse response = CLIENT.execute(request);
            response.addHeader("content-type", "application/json;charset=UTF-8");
            HttpEntity entity = response.getEntity();
            ObjectMapper mapper = new ObjectMapper();
            userModel = mapper.readValue((EntityUtils.toString(entity)), ElasticModel.class);
            System.out.println("Response is  " + userModel.toString());

            //parse and get one source if used _search?pretty=true&q=userId:" + userId
            //String jsonObject = EntityUtils.toString(entity);
            //  System.out.println("jsonObject " + jsonObject);
            /**
             * String jsonObject = EntityUtils.toString(entity);
             *
             * System.out.println("jsonObject " + jsonObject); JsonFactory
             * factory = mapper.getJsonFactory(); JsonParser jp =
             * factory.createJsonParser(jsonObject); JsonNode input =
             * mapper.readTree(jp);
             *
             * JsonNode hits = input.get("hits"); // System.out.println("hits "
             * + hits.toString()); JsonNode histsObj = hits.get("hits");
             *
             * // System.out.println("histsObj " + histsObj.toString());
             * JsonNode first = histsObj.get(0);
             *
             * // System.out.println("first " + first.toString()); JsonNode
             * source = first.get("_source");
             *
             * // System.out.println("source " +
             * source.get("firstname").asText()); for (final JsonNode element :
             * source) { Iterator<Map.Entry<String, JsonNode>> nodeIterator =
             * element.getFields(); while (nodeIterator.hasNext()) {
             * Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>)
             * nodeIterator.next(); System.out.println("entry.getKey() " +
             * entry.getKey() + " / " + entry.getValue());
             *
             * }
             * }
             * *
             */
        } catch (IOException | ParseException ex) {
            try {
                CLIENT.close();

            } catch (IOException ex1) {
            }
        } finally {
            try {
                CLIENT.close();
            } catch (IOException ex1) {
            }
        }
        return userModel;

    }

}
