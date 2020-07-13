/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.efris.EFRISInvoice;
import com.efris.ExtractKeysNWSC;
import com.efris.GeneralUtilities;
import com.google.gson.Gson;
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
public class TestNWSC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try {
            String returnCode = "";
            String returnMessage = "";
            String AntifakeCode = "";
            String InvoiceNo = "";
//        EFRISInvoice eFRISInvoice = new EFRISInvoice(sellerDetails, basicInformation, buyerDetails, summary, extend);
//        eFRISInvoice.setPayWay(payWay);
//        eFRISInvoice.setGoodsDetails(goodsDetails);
//        eFRISInvoice.setTaxDetails(taxDetails);
//        Gson gson = new Gson();
            String json = "{\n"
                    + "  \"sellerDetails\": {\n"
                    + "    \"tin\": \"1000023440\",\n"
                    + "    \"ninBrn\": \"\",\n"
                    + "    \"legalName\": \"NATIONAL WATER & SEWERAGE CORPORATION\",\n"
                    + "    \"businessName\": \"NATIONAL WATER & SEWERAGE CORPORATION\",\n"
                    + "    \"address\": \"\",\n"
                    + "    \"mobilephone\": \"\",\n"
                    + "    \"linephone\": \"\",\n"
                    + "    \"emailaddress\": \"info@nwsc.co.ug\",\n"
                    + "    \"placeOfBusiness\": \"3 NAKASERO ROAD  NAKASERO KAMPALA KAMPALA CENTRAL DIVI KAMPALA CENTRAL DIVISION NAKASERO II\",\n"
                    + "    \"referenceNo\": \"\"\n"
                    + "  },\n"
                    + "  \"basicInformation\": {\n"
                    + "    \"invoiceNo\": \"\",\n"
                    + "    \"antifakeCode\": \"\",\n"
                    + "    \"deviceNo\": \"4444444444455\",\n"
                    + "    \"issuedDate\": \"2020-06-27 07:00:52\",\n"
                    + "    \"operator\": \"Admin\",\n"
                    + "    \"currency\": \"UGX\",\n"
                    + "    \"oriInvoiceId\": \"\",\n"
                    + "    \"invoiceType\": \"1\",\n"
                    + "    \"invoiceKind\": \"1\",\n"
                    + "    \"dataSource\": \"103\",\n"
                    + "    \"invoiceIndustryCode\": \"101\",\n"
                    + "    \"isBatch\": \"0\"\n"
                    + "  },\n"
                    + "  \"buyerDetails\": {\n"
                    + "    \"buyerTin\": \"1001088134\",\n"
                    + "    \"buyerNinBrn\": \"\",\n"
                    + "    \"buyerPassportNum\": \"\",\n"
                    + "    \"buyerLegalName\": \"KIIZA ADRIAN X\",\n"
                    + "    \"buyerBusinessName\": \"KIIZA ADRIAN X\",\n"
                    + "    \"buyerAddress\": \"KAMWOKYA\",\n"
                    + "    \"buyerEmail\": \"info@nwsc.co.ug\",\n"
                    + "    \"buyerMobilePhone\": \"256772417866\",\n"
                    + "    \"buyerLinePhone\": \"\",\n"
                    + "    \"buyerPlaceOfBusi\": \"\",\n"
                    + "    \"buyerType\": \"1\",\n"
                    + "    \"buyerCitizenship\": \"1\",\n"
                    + "    \"buyerSector\": \"\",\n"
                    + "    \"buyerReferenceNo\": \"\"\n"
                    + "  },\n"
                    + "  \"goodsDetails\": [\n"
                    + "    {\n"
                    + "      \"item\": \"Service water\",\n"
                    + "      \"itemCode\": \"SVR\",\n"
                    + "      \"qty\": \"1\",\n"
                    + "      \"unitOfMeasure\": \"Litre\",\n"
                    + "      \"unitPrice\": \"1\",\n"
                    + "      \"total\": \"1770\",\n"
                    + "      \"taxRate\": \"0.18\",\n"
                    + "      \"tax\": \"270.0000\",\n"
                    + "      \"discountTotal\": \"\",\n"
                    + "      \"discountTaxRate\": \"\",\n"
                    + "      \"orderNumber\": \"0\",\n"
                    + "      \"discountFlag\": \"2\",\n"
                    + "      \"deemedFlag\": \"2\",\n"
                    + "      \"exciseFlag\": \"2\",\n"
                    + "      \"categoryId\": \"\",\n"
                    + "      \"categoryName\": \"\",\n"
                    + "      \"goodsCategoryId\": \"83101509\",\n"
                    + "      \"goodsCategoryName\": \"\",\n"
                    + "      \"exciseRate\": \"\",\n"
                    + "      \"exciseRule\": \"\",\n"
                    + "      \"exciseTax\": \"\",\n"
                    + "      \"pack\": \"\",\n"
                    + "      \"stick\": \"\",\n"
                    + "      \"exciseUnit\": \"\",\n"
                    + "      \"exciseCurrency\": \"\",\n"
                    + "      \"exerciseRateName\": \"\"\n"
                    + "    },\n"
                    + "    {\n"
                    + "      \"item\": \"Water treatment services\",\n"
                    + "      \"itemCode\": \"SWR\",\n"
                    + "      \"qty\": \"5\",\n"
                    + "      \"unitOfMeasure\": \"Litre\",\n"
                    + "      \"unitPrice\": \"1\",\n"
                    + "      \"total\": \"15558.3\",\n"
                    + "      \"taxRate\": \"0.18\",\n"
                    + "      \"tax\": \"2373.3000\",\n"
                    + "      \"discountTotal\": \"\",\n"
                    + "      \"discountTaxRate\": \"\",\n"
                    + "      \"orderNumber\": \"1\",\n"
                    + "      \"discountFlag\": \"2\",\n"
                    + "      \"deemedFlag\": \"2\",\n"
                    + "      \"exciseFlag\": \"2\",\n"
                    + "      \"categoryId\": \"\",\n"
                    + "      \"categoryName\": \"\",\n"
                    + "      \"goodsCategoryId\": \"83101506\",\n"
                    + "      \"goodsCategoryName\": \"\",\n"
                    + "      \"exciseRate\": \"\",\n"
                    + "      \"exciseRule\": \"\",\n"
                    + "      \"exciseTax\": \"\",\n"
                    + "      \"pack\": \"\",\n"
                    + "      \"stick\": \"\",\n"
                    + "      \"exciseUnit\": \"\",\n"
                    + "      \"exciseCurrency\": \"\",\n"
                    + "      \"exerciseRateName\": \"\"\n"
                    + "    },\n"
                    + "    {\n"
                    + "      \"item\": \"Supply of water\",\n"
                    + "      \"itemCode\": \"WS\",\n"
                    + "      \"qty\": \"5\",\n"
                    + "      \"unitOfMeasure\": \"Litre\",\n"
                    + "      \"unitPrice\": \"1\",\n"
                    + "      \"total\": \"20744.4\",\n"
                    + "      \"taxRate\": \"0.18\",\n"
                    + "      \"tax\": \"3164.4000\",\n"
                    + "      \"discountTotal\": \"\",\n"
                    + "      \"discountTaxRate\": \"\",\n"
                    + "      \"orderNumber\": \"2\",\n"
                    + "      \"discountFlag\": \"2\",\n"
                    + "      \"deemedFlag\": \"2\",\n"
                    + "      \"exciseFlag\": \"2\",\n"
                    + "      \"categoryId\": \"\",\n"
                    + "      \"categoryName\": \"\",\n"
                    + "      \"goodsCategoryId\": \"83101501\",\n"
                    + "      \"goodsCategoryName\": \"\",\n"
                    + "      \"exciseRate\": \"\",\n"
                    + "      \"exciseRule\": \"\",\n"
                    + "      \"exciseTax\": \"\",\n"
                    + "      \"pack\": \"\",\n"
                    + "      \"stick\": \"\",\n"
                    + "      \"exciseUnit\": \"\",\n"
                    + "      \"exciseCurrency\": \"\",\n"
                    + "      \"exerciseRateName\": \"\"\n"
                    + "    }\n"
                    + "  ],\n"
                    + "  \"taxDetails\": [\n"
                    + "    {\n"
                    + "      \"taxCategory\": \"VAT\",\n"
                    + "      \"netAmount\": \"32265\",\n"
                    + "      \"taxRate\": \"0.18\",\n"
                    + "      \"taxAmount\": \"5807.7\",\n"
                    + "      \"grossAmount\": \"38072.7\",\n"
                    + "      \"exciseUnit\": \"\",\n"
                    + "      \"exciseCurrency\": \"\",\n"
                    + "      \"taxRateName\": \"VAT\"\n"
                    + "    }\n"
                    + "  ],\n"
                    + "  \"summary\": {\n"
                    + "    \"netAmount\": \"32265\",\n"
                    + "    \"taxAmount\": \"5808\",\n"
                    + "    \"grossAmount\": \"38073\",\n"
                    + "    \"itemCount\": \"1\",\n"
                    + "    \"modeCode\": \"1\",\n"
                    + "    \"remarks\": \"\",\n"
                    + "    \"qrCode\": \"\"\n"
                    + "  },\n"
                    + "  \"payWay\": [\n"
                    + "    {\n"
                    + "      \"paymentMode\": \"102\",\n"
                    + "      \"paymentAmount\": \"0\",\n"
                    + "      \"orderNumber\": \"0\"\n"
                    + "    }\n"
                    + "  ],\n"
                    + "  \"extend\": {\n"
                    + "    \"returnCode\": \"\",\n"
                    + "    \"returMessage\": \"\"\n"
                    + "  }\n"
                    + "}";
            System.out.println(json);
            AntifakeCode = "";
            InvoiceNo = "";
            returnCode = "";
            returnMessage = "";
            com.sun.jersey.api.client.Client client = com.sun.jersey.api.client.Client.create();
            WebResource webResource = client.resource("https://efristest.ura.go.ug/efrisws/ws/taapp/getInformation");
            String privatekey = new ExtractKeysNWSC().PrivateKey("1000023440", "4444444444455", returnCode, returnMessage);
            String sub = privatekey.substring(0, 5);
            if (sub.equals("ERROR")) {
                String[] split = privatekey.split(",");
                returnCode = split[1];
                returnMessage = split[2];
            }
            PrivateKey key = ExtractKeysNWSC.loadPrivateKey(privatekey);
            //System.out.println(key.getAlgorithm());
            String AESpublickeystring = ExtractKeysNWSC.decrypt(new ExtractKeysNWSC().AESPublicKey("1000023440", "4444444444455"), key);
            String encryptedcontent = ExtractKeysNWSC.AESencrypt(json, Base64.decodeBase64(AESpublickeystring));
            //PublicKey publickey=ExtractKeysNWSC.getKey(AESpublickeystring);
            System.out.println(encryptedcontent);
            String signedcontent = Base64.encodeBase64String(new ExtractKeysNWSC().sign(encryptedcontent, key));
            String PostData = GeneralUtilities.PostData(encryptedcontent, signedcontent, "AP04", "", "9230489223014123", "123", "4444444444455", "T109", "1000023440");
            System.out.println(PostData);
            ClientResponse response = webResource.type("application/json").post(ClientResponse.class, PostData);
            String output = response.getEntity(String.class);
            System.out.println(output);

            JSONObject parentjsonObject = new JSONObject(output);
            JSONObject dataobject = parentjsonObject.getJSONObject("returnStateInfo");
            returnCode = dataobject.getString("returnCode");
            returnMessage = dataobject.getString("returnMessage");
            System.out.println("-------------------------------------");
            System.out.println(returnCode);
            System.out.println(returnMessage);
            System.out.println("-------------------------------------");
            JSONObject dataobjectcontent = parentjsonObject.getJSONObject("data");
            String content = dataobjectcontent.getString("content");
            System.out.println(AESpublickeystring);
            String DecryptedContent = ExtractKeysNWSC.AESdecrypt(content, Base64.decodeBase64(AESpublickeystring));
            System.out.println(DecryptedContent);
            System.out.println("-------------------------------------");
            JSONObject parentbasicInformationjsonObject = new JSONObject(DecryptedContent);
            JSONObject databasicInformation = parentbasicInformationjsonObject.getJSONObject("basicInformation");
            AntifakeCode = databasicInformation.getString("antifakeCode");
            InvoiceNo = databasicInformation.getString("invoiceNo");
            System.out.println("AntiFakeCode: " + AntifakeCode);
            System.out.println("Invoice: " + InvoiceNo);
            System.out.println("-------------------------------------");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(InvoiceBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(InvoiceBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InvoiceBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(InvoiceBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InvoiceBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
