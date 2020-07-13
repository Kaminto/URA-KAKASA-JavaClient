/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.efris.GeneralUtilities;
import com.efris.innerclasses.Item;
import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author bajuna
 */
public class UpdateStock {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UpdateStock updateStock = new UpdateStock();
        updateStock.updatestock();
    }

    public void updatestock() {
        try {
            /**
             * Goods inquiry
             */
            String json = "{\n"
                    + "	\"goodsCode\": \"147\",\n"
                    + "	\"goodsName \": \"\",\n"
                    + "	\"commodityCategoryName\": \"\",\n"
                    + "	\"pageNo\": \"1\",\n"
                    + "	\"pageSize\": \"10\"\n"
                    + "}";

            com.sun.jersey.api.client.Client client = com.sun.jersey.api.client.Client.create();
            WebResource webResource = client.resource("http://127.0.0.1:9880/efristcs/ws/tcsapp/getInformation");
            String PostData = GeneralUtilities.PostData_Offline(Base64.encodeBase64String(json.getBytes("UTF-8")), "", "AP04", "", "9230489223014123", "123", "TCS443cd31997972232", "T127", "1001068009");

            ClientResponse response = webResource.type("application/json").post(ClientResponse.class, PostData);
            String output = response.getEntity(String.class);
            //System.out.println(output);

            JSONObject parentjsonObject = new JSONObject(output);
            JSONObject dataobject = parentjsonObject.getJSONObject("returnStateInfo");

            JSONObject dataobjectcontent = parentjsonObject.getJSONObject("data");
            String content = dataobjectcontent.getString("content");

            String DecryptedContent = new String(Base64.decodeBase64(content));

            JSONObject parentbasicInformationjsonObject = new JSONObject(DecryptedContent);
            JSONArray jSONArray = parentbasicInformationjsonObject.getJSONArray("records");
            List<Item> itemslist = new ArrayList<>();
            for (int i = 0, size = jSONArray.length(); i < size; i++) {
                JSONObject objectInArray = jSONArray.getJSONObject(i);
                Gson g = new Gson();
                Item item = g.fromJson(objectInArray.toString(), Item.class);
                itemslist.add(item);
            }

            json = "[\n"
                    + "	{\n"
                    //+ "\"commodityGoodsId\": \"" + "350638171648238454" + "\",\n"
                    + "\"commodityGoodsId\": \"" + itemslist.get(0).getId() + "\",\n"
                    + "\"quantity\": \"100\",\n"
                    + "\"unitPrice\": \"6999\"\n"
                    + "},\n"
                    + "]";

            /**
             * Add Stock
             */
            PostData = GeneralUtilities.PostData_Offline(Base64.encodeBase64String(json.getBytes("UTF-8")), "", "AP04", "", "9230489223014123", "123", "TCS443cd31997972232", "T131", "1001068009");

            response = webResource.type("application/json").post(ClientResponse.class, PostData);
            output = response.getEntity(String.class);

            parentjsonObject = new JSONObject(output);
            dataobject = parentjsonObject.getJSONObject("returnStateInfo");

            dataobjectcontent = parentjsonObject.getJSONObject("data");
            content = dataobjectcontent.getString("content");

            DecryptedContent = new String(Base64.decodeBase64(content));
            System.out.println(DecryptedContent);

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TestOfflineInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
