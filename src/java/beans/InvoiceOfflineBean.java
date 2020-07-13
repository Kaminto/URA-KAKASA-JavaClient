/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.efris.EFRISInvoice;
import com.efris.ExtractKeys;
import com.efris.GeneralUtilities;
import com.efris.innerclasses.BasicInformation;
import com.efris.innerclasses.BuyerDetails;
import com.efris.innerclasses.Extend;
import com.efris.innerclasses.GoodsDetails;
import com.efris.innerclasses.PayWay;
import com.efris.innerclasses.SellerDetails;
import com.efris.innerclasses.Summary;
import com.efris.innerclasses.TaxDetails;
import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

/**
 *
 * @author bajuna
 */
@ManagedBean
@SessionScoped
public class InvoiceOfflineBean {

    public void init() {
        if (sellerDetails == null) {
            sellerDetails = new SellerDetails();
        }
        if (basicInformation == null) {
            basicInformation = new BasicInformation();
        }
        if (buyerDetails == null) {
            buyerDetails = new BuyerDetails();
        }
        if (goodsDetails == null) {
            goodsDetails = new ArrayList<>();
        }
        if (taxDetails == null) {
            taxDetails = new ArrayList<>();
        }
        if (summary == null) {
            summary = new Summary();
        }
        if (payWay == null) {
            payWay = new ArrayList<>();
        }
        if (extend == null) {
            extend = new Extend();
        }
        if (item == null) {
            item = new GoodsDetails();
        }
        if (paymentmode == null) {
            paymentmode = new PayWay();
        }
        if (taxdetail == null) {
            taxdetail = new TaxDetails();
        }
    }
    private SellerDetails sellerDetails;
    private BasicInformation basicInformation;
    private BuyerDetails buyerDetails;
    private List<GoodsDetails> goodsDetails = new ArrayList<>();
    private List<TaxDetails> taxDetails = new ArrayList();
    private Summary summary;
    private List<PayWay> payWay = new ArrayList();
    private Extend extend;

    private GoodsDetails item;
    private TaxDetails taxdetail;
    private PayWay paymentmode;

    private String returnCode = "";
    private String returnMessage = "";
    private String AntifakeCode = "";
    private String InvoiceNo = "";

    public void clear_all() {
        sellerDetails = new SellerDetails();
        basicInformation = new BasicInformation();
        buyerDetails = new BuyerDetails();
        goodsDetails = new ArrayList<>();
        taxDetails = new ArrayList<>();
        summary = new Summary();
        payWay = new ArrayList<>();
        extend = new Extend();
        extend.setReason("");
        extend.setReasonCode("");
        item = new GoodsDetails();
        paymentmode = new PayWay();
        taxdetail = new TaxDetails();
        returnCode = "";
        returnMessage = "";
        AntifakeCode = "";
        InvoiceNo = "";
    }

    public void submit_invoice() {
        try {
            EFRISInvoice eFRISInvoice = new EFRISInvoice(sellerDetails, basicInformation, buyerDetails, summary, extend);
            eFRISInvoice.setPayWay(payWay);
            eFRISInvoice.setGoodsDetails(goodsDetails);
            eFRISInvoice.setTaxDetails(taxDetails);
            Gson gson = new Gson();
            String json = gson.toJson(eFRISInvoice);
            System.out.println(json);
            AntifakeCode = "";
            InvoiceNo = "";
            returnCode = "";
            returnMessage = "";
            com.sun.jersey.api.client.Client client = com.sun.jersey.api.client.Client.create();
            //WebResource webResource = client.resource("https://efristest.ura.go.ug/efrisws/ws/taapp/getInformation");
            WebResource webResource = client.resource("http://127.0.0.1:9880/efristcs/ws/tcsapp/getInformation");
            //String privatekey = new ExtractKeys().PrivateKey(sellerDetails.getTin(), basicInformation.getDeviceNo(), returnCode, returnMessage);
//            String sub = privatekey.substring(0, 5);
//            if (sub.equals("ERROR")) {
//                String[] split = privatekey.split(",");
//                returnCode = split[1];
//                returnMessage = split[2];
//            }
//            PrivateKey key = ExtractKeys.loadPrivateKey(privatekey);
            //System.out.println(key.getAlgorithm());
//            String AESpublickeystring = ExtractKeys.decrypt(new ExtractKeys().AESPublicKey(sellerDetails.getTin(), basicInformation.getDeviceNo()), key);
//            String encryptedcontent = ExtractKeys.AESencrypt(json, Base64.decodeBase64(AESpublickeystring));
            //PublicKey publickey=ExtractKeys.getKey(AESpublickeystring);
//            System.out.println(encryptedcontent);
//            String signedcontent = Base64.encodeBase64String(new ExtractKeys().sign(encryptedcontent, key));
            //String PostData = GeneralUtilities.PostData(encryptedcontent, signedcontent, "AP04", "", "9230489223014123", "123", basicInformation.getDeviceNo(), "T109", sellerDetails.getTin());

            String PostData = GeneralUtilities.PostData_Offline(Base64.encodeBase64String(json.getBytes("UTF-8")), "", "AP04", "", "9230489223014123", "123", basicInformation.getDeviceNo(), "T109", sellerDetails.getTin());
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
            //System.out.println(AESpublickeystring);
            //String DecryptedContent = ExtractKeys.AESdecrypt(content, Base64.decodeBase64(AESpublickeystring));
            String DecryptedContent = new String(Base64.decodeBase64(content));
            //System.out.println(DecryptedContent);
            System.out.println("-------------------------------------");
            JSONObject parentbasicInformationjsonObject = new JSONObject(DecryptedContent);
            JSONObject databasicInformation = parentbasicInformationjsonObject.getJSONObject("basicInformation");
            AntifakeCode = databasicInformation.getString("antifakeCode");
            InvoiceNo = databasicInformation.getString("invoiceNo");
            System.out.println("AntiFakeCode: " + AntifakeCode);
            System.out.println("Invoice: " + InvoiceNo);
            System.out.println("-------------------------------------");
        } catch (Exception ex) {
            Logger.getLogger(InvoiceOfflineBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public PayWay getPaymentmode() {
        return paymentmode;
    }

    public void setPaymentmode(PayWay paymentmode) {
        this.paymentmode = paymentmode;
    }

    public TaxDetails getTaxdetail() {
        return taxdetail;
    }

    public void setTaxdetail(TaxDetails taxdetail) {
        this.taxdetail = taxdetail;
    }

    public GoodsDetails getItem() {
        return item;
    }

    public void setItem(GoodsDetails item) {
        this.item = item;
    }

    public void add_item() {
        goodsDetails.remove(item);
        goodsDetails.add(item);
        item = new GoodsDetails();
    }

    public void remove_item(GoodsDetails item) {
        goodsDetails.remove(item);
    }

    public void edit_item(GoodsDetails item) {
        this.item = item;
    }

    public void add_payway() {
        payWay.remove(paymentmode);
        payWay.add(paymentmode);
        paymentmode = new PayWay();
    }

    public void remove_payway(PayWay item) {
        payWay.remove(item);
    }

    public void edit_payway(PayWay item) {
        this.paymentmode = item;
    }

    public void add_taxdetail() {
        taxDetails.remove(taxdetail);
        taxDetails.add(taxdetail);
        taxdetail = new TaxDetails();
    }

    public void remove_taxdetail(TaxDetails taxdetail) {
        taxDetails.remove(taxdetail);
    }

    public void edit_taxdetail(TaxDetails item) {
        this.taxdetail = item;
    }

    public SellerDetails getSellerDetails() {
        return sellerDetails;
    }

    public void setSellerDetails(SellerDetails sellerDetails) {
        this.sellerDetails = sellerDetails;
    }

    public BasicInformation getBasicInformation() {
        return basicInformation;
    }

    public void setBasicInformation(BasicInformation basicInformation) {
        this.basicInformation = basicInformation;
    }

    public BuyerDetails getBuyerDetails() {
        return buyerDetails;
    }

    public void setBuyerDetails(BuyerDetails buyerDetails) {
        this.buyerDetails = buyerDetails;
    }

    public List<GoodsDetails> getGoodsDetails() {
        return goodsDetails;
    }

    public void setGoodsDetails(List<GoodsDetails> goodsDetails) {
        this.goodsDetails = goodsDetails;
    }

    public List<TaxDetails> getTaxDetails() {
        return taxDetails;
    }

    public void setTaxDetails(List<TaxDetails> taxDetails) {
        this.taxDetails = taxDetails;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public List<PayWay> getPayWay() {
        return payWay;
    }

    public void setPayWay(List<PayWay> payWay) {
        this.payWay = payWay;
    }

    public Extend getExtend() {
        return extend;
    }

    public void setExtend(Extend extend) {
        this.extend = extend;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getAntifakeCode() {
        return AntifakeCode;
    }

    public void setAntifakeCode(String AntifakeCode) {
        this.AntifakeCode = AntifakeCode;
    }

    public String getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(String InvoiceNo) {
        this.InvoiceNo = InvoiceNo;
    }

}
