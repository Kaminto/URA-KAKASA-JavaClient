/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.efris.ExtractKeys;
import com.efris.GeneralUtilities;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

/**
 *
 * @author bajuna
 */
public class ItemUpload {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ItemUpload itemUpload = new ItemUpload();
        itemUpload.submit_good();
    }

    public void submit_good() {
        try {
            //EFRISInvoice eFRISInvoice = new EFRISInvoice(sellerDetails, basicInformation, buyerDetails, summary, extend);
            //eFRISInvoice.setPayWay(payWay);
            //eFRISInvoice.setGoodsDetails(goodsDetails);
            //eFRISInvoice.setTaxDetails(taxDetails);
            //Gson gson = new Gson();
            String json = "[\n"
                    + "	{\n"
                    + "	\"goodsName\": \"Mountain Dew2\",\n"
                    + "	\"goodsCode\": \"30000\",\n"
                    + "	\"measureUnit\": \"101\",\n"
                    + "	\"unitPrice\": \"6999.99\",\n"
                    + "	\"currency\": \"101\",\n"
                    + "	\"commodityCategoryId\": \"50202306\",\n"
                    + "	\"haveExciseTax\": \"102\",\n"
                    + "	\"description\": \"1\",\n"
                    + "	\"stockPrewarning\": \"10\",\n"
                    + "	\"pieceMeasureUnit\": \"\",\n"
                    + "	\"havePieceUnit\": \"\",\n"
                    + "	\"pieceUnitPrice\": \"\",\n"
                    + "	\"packageScaledValue\": \"\",\n"
                    + "	\"pieceScaledValue\": \"\",\n"
                    + "	\"exciseDutyCode\": \"\"\n"
                    + "},\n"
                    + "]";
            System.out.println(json);
            com.sun.jersey.api.client.Client client = com.sun.jersey.api.client.Client.create();
            WebResource webResource = client.resource("http://127.0.0.1:9880/efristcs/ws/tcsapp/getInformation");
            String PostData = GeneralUtilities.PostData_Offline(Base64.encodeBase64String(json.getBytes("UTF-8")), "", "AP04", "", "9230489223014123", "123", "TCS443cd31997972232", "T130", "1001068009");

            System.out.println(PostData);
            ClientResponse response = webResource.type("application/json").post(ClientResponse.class, PostData);
            String output = response.getEntity(String.class);
            //System.out.println(output);

            JSONObject parentjsonObject = new JSONObject(output);
            JSONObject dataobject = parentjsonObject.getJSONObject("returnStateInfo");

            JSONObject dataobjectcontent = parentjsonObject.getJSONObject("data");
            String content = dataobjectcontent.getString("content");

            String DecryptedContent = new String(Base64.decodeBase64(content));
            System.out.println(DecryptedContent);

        } catch (Exception ex) {
            Logger.getLogger(InvoiceBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
