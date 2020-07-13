/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.efris.EFRISInvoice;
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
public class TestOfflineInvoice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            String returnCode = "";
            String returnMessage = "";
            String AntifakeCode = "";
            String InvoiceNo = "";

            //String json = "{\"sellerDetails\":{\"tin\":\"1001068009\",\"ninBrn\":\"\",\"legalName\":\"KweNu Juice \\u0026 Salad Bar\",\"businessName\":\"CLIENT DEMO\",\"address\":\"\",\"mobilePhone\":\"\",\"linePhone\":\"\",\"emailAddress\":\"twenceb@gmail.com\",\"placeOfBusiness\":\"\",\"referenceNo\":\"123\"},\"basicInformation\":{\"invoiceNo\":\"SI20062518\",\"antifakeCode\":\"\",\"deviceNo\":\"TCS443cd31997972232\",\"issuedDate\":\"2020-07-10 20:00:16\",\"operator\":\"Admin\",\"currency\":\"UGX\",\"oriInvoiceId\":\"\",\"invoiceType\":\"1\",\"invoiceKind\":\"1\",\"dataSource\":\"103\"},\"buyerDetails\":{\"buyerTin\":\"1001088134\",\"buyerNinBrn\":\"\",\"buyerPassportNum\":\"\",\"buyerLegalName\":\"Racheal\",\"buyerBusinessName\":\"\",\"buyerAddress\":\"\",\"buyerEmail\":\"\",\"buyerMobilePhone\":\"\",\"buyerLinePhone\":\"\",\"buyerPlaceOfBusi\":\"\",\"buyerType\":\"1\",\"buyerCitizenship\":\"\",\"buyerSector\":\"\",\"buyerReferenceNo\":\"\"},\"goodsDetails\":[{\"item\":\"Pepsi Cola\",\"itemCode\":\"147\",\"qty\":\"1.0\",\"unitPrice\":\"106200.0\",\"total\":\"106200.0\",\"taxRate\":\"0.18\",\"tax\":\"16200.0\",\"discountTotal\":\"\",\"discountTaxRate\":\"\",\"orderNumber\":\"0\",\"discountFlag\":\"2\",\"deemedFlag\":\"2\",\"exciseFlag\":\"2\",\"categoryId\":\"\",\"categoryName\":\"\",\"goodsCategoryId\":\"50202306\",\"goodsCategoryName\":\"\",\"exciseRate\":\"\",\"exciseRule\":\"\",\"exciseTax\":\"\",\"pack\":\"\",\"stick\":\"\",\"exciseUnit\":\"\",\"exciseCurrency\":\"\",\"exciseRateName\":\"\"}],\"taxDetails\":[{\"taxCategory\":\"VAT\",\"netAmount\":\"90000.0\",\"taxRate\":\"0.18\",\"taxAmount\":\"16200.0\",\"grossAmount\":\"106200.0\",\"exciseUnit\":\"\",\"exciseCurrency\":\"\",\"taxRateName\":\"STANDARD\"}],\"summary\":{\"netAmount\":\"90000\",\"taxAmount\":\"16200\",\"grossAmount\":\"106200\",\"itemCount\":\"1\",\"modeCode\":\"\",\"remarks\":\"\",\"qrCode\":\"\"},\"payWay\":[],\"extend\":{\"reason\":\"\",\"reasonCode\":\"\"}}";
            String json = "{\"basicInformation\":{\"antifakeCode\":\"\",\"currency\":\"UGX\",\"dataSource\":\"103\",\"deviceNo\":\"TCS443cd31997972232\",\"invoiceIndustryCode\":\"\",\"invoiceKind\":\"1\",\"invoiceNo\":\"\",\"invoiceType\":\"1\",\"isBatch\":\"\",\"issuedDate\":\"2020-07-10 00:00:00\",\"operator\":\"daiqiaodi 00235083\",\"oriInvoiceId\":\"\"},\n" +
"\"buyerDetails\":{\"buyerAddress\":\"Palm Courts, Plot 7A, Rotary Avenue (Lugogo Bypass) P.O.Box 22151, Kampala, Uganda, , , , UG-102 UG-102 33151, UGANDA,,,\\nTIN:1000645262\",\"buyerBusinessName\":\"National Information Technology Authority Uganda\",\"buyerCitizenship\":\"\",\"buyerEmail\":\"\",\"buyerLegalName\":\"National Information Technology Authority Uganda\",\"buyerLinePhone\":\"\",\"buyerMobilePhone\":\"02017620131031\",\"buyerNinBrn\":\"\",\"buyerPassportNum\":\"\",\"buyerPlaceOfBusi\":\"Palm Courts, Plot 7A, Rotary Avenue (Lugogo Bypass) P.O.Box 22151, Kampala, Uganda, , , , UG-102 UG-102 33151, UGANDA,,,\\nTIN:1000645262\",\"buyerReferenceNo\":\"12\",\"buyerSector\":\"\",\"buyerTin\":\"1000645262\",\"buyerType\":\"0\"},\"extend\":{\"reason\":\"\",\"reasonCode\":\"\"},\n" +
"\"goodsDetails\":[{\"categoryId\":\"\",\"categoryName\":\"\",\"deemedFlag\":\"1\",\"discountFlag\":\"2\",\"discountTaxRate\":\"\",\"discountTotal\":\"\",\"exciseCurrency\":\"\",\"exciseFlag\":\"2\",\"exciseRate\":\"\",\"exciseRateName\":\"\",\"exciseRule\":\"\",\"exciseTax\":\"\",\"exciseUnit\":\"\",\"goodsCategoryId\":\"50202306\",\"goodsCategoryName\":\"Network planning services\",\"item\":\"Network planning services\",\"itemCode\":\"147\",\"orderNumber\":\"0\",\"pack\":\"\",\"qty\":\"1\",\"stick\":\"\",\"tax\":\"49.89\",\"taxRate\":\"0.18\",\"total\":\"327.1\",\"unitOfMeasure\":\"\",\"unitPrice\":\"327.1\"}],\n" +
"\"payWay\":[{\"orderNumber\":\"\",\"paymentAmount\":\"\",\"paymentMode\":\"\"}],\n" +
"\"sellerDetails\":{\"address\":\"8th Floor, CUBE,Plots 5 & 7 Kisementi-Kololo,P.O. Box 25799, Kampala-Uganda\",\"businessName\":\"HUAWEI TECHNOLOGIES(UGANDA) CO.,LIMITED\",\"emailAddress\":\"n129168@huawei.com\",\"legalName\":\"HUAWEI TECHNOLOGIES(UGANDA) CO.,LIMITED\",\"linePhone\":\"\",\"mobilePhone\":\"02017620131031\",\"ninBrn\":\"\",\"placeOfBusiness\":\"\",\"referenceNo\":\"UG2000185\",\"tin\":\"1001068009\"},\n" +
"\"summary\":{\"grossAmount\":\"327.10\",\"itemCount\":\"1\",\"modeCode\":\"1\",\"netAmount\":\"327.10\",\"qrCode\":\"\",\"remarks\":\"\",\"taxAmount\":\"0.00\"},\"taxDetails\":[{\"exciseCurrency\":\"\",\"exciseUnit\":\"\",\"grossAmount\":\"327.10\",\"netAmount\":\"277.20\",\"taxAmount\":\"49.89\",\"taxCategory\":\"Standard\",\"taxRate\":\"0.18\",\"taxRateName\":\"18.0%\"}]}";
            //System.out.println(json);
            AntifakeCode = "";
            InvoiceNo = "";
            returnCode = "";
            returnMessage = "";
            com.sun.jersey.api.client.Client client = com.sun.jersey.api.client.Client.create();
            //WebResource webResource = client.resource("https://efristest.ura.go.ug/efrisws/ws/taapp/getInformation");
            WebResource webResource = client.resource("http://127.0.0.1:9880/efristcs/ws/tcsapp/getInformation");
            //String key = new ExtractKeys().PrivateKey(sellerDetails.getTin(), basicInformation.getDeviceNo(), returnCode, returnMessage);
//            String sub = key.substring(0, 5);
//            if (sub.equals("ERROR")) {
//                String[] split = key.split(",");
//                returnCode = split[1];
//                returnMessage = split[2];
//            }
//            PrivateKey key = ExtractKeys.loadPrivateKey(key);
//System.out.println(key.getAlgorithm());
//            String AESpublickeystring = ExtractKeys.decrypt(new ExtractKeys().AESPublicKey(sellerDetails.getTin(), basicInformation.getDeviceNo()), key);
//            String encryptedcontent = ExtractKeys.AESencrypt(json, Base64.decodeBase64(AESpublickeystring));
//PublicKey publickey=ExtractKeys.getKey(AESpublickeystring);
//            System.out.println(encryptedcontent);
//            String signedcontent = Base64.encodeBase64String(new ExtractKeys().sign(encryptedcontent, key));
//String PostData = GeneralUtilities.PostData(encryptedcontent, signedcontent, "AP04", "", "9230489223014123", "123", basicInformation.getDeviceNo(), "T109", sellerDetails.getTin());

            String PostData = GeneralUtilities.PostData_Offline(Base64.encodeBase64String(json.getBytes("UTF-8")), "", "AP04", "", "9230489223014123", "123", "TCS443cd31997972232", "T109", "1001068009");
            //System.out.println(PostData);
            ClientResponse response = webResource.type("application/json").post(ClientResponse.class, PostData);
            String output = response.getEntity(String.class);
            //System.out.println(output);

            JSONObject parentjsonObject = new JSONObject(output);
            JSONObject dataobject = parentjsonObject.getJSONObject("returnStateInfo");
            returnCode = dataobject.getString("returnCode");
            returnMessage = dataobject.getString("returnMessage");
            //System.out.println("-------------------------------------");
            //System.out.println(returnCode);
            //System.out.println(returnMessage);
            //System.out.println("-------------------------------------");
            JSONObject dataobjectcontent = parentjsonObject.getJSONObject("data");
            String content = dataobjectcontent.getString("content");
//System.out.println(AESpublickeystring);
//String DecryptedContent = ExtractKeys.AESdecrypt(content, Base64.decodeBase64(AESpublickeystring));
            String DecryptedContent = new String(Base64.decodeBase64(content));
//System.out.println(DecryptedContent);
            //System.out.println("-------------------------------------");
            JSONObject parentbasicInformationjsonObject = new JSONObject(DecryptedContent);
            JSONObject databasicInformation = parentbasicInformationjsonObject.getJSONObject("basicInformation");
            AntifakeCode = databasicInformation.getString("antifakeCode");
            InvoiceNo = databasicInformation.getString("invoiceNo");
            System.out.println("AntiFakeCode: " + AntifakeCode);
            System.out.println("Invoice: " + InvoiceNo);
            //System.out.println("-------------------------------------");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TestOfflineInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
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

            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TestOfflineInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
