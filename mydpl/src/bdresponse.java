

import java.io.IOException;
import java.security.MessageDigest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.security.krb5.internal.crypto.DesCbcCrcEType;

/**
 * Servlet implementation class bdresponse
 */
@WebServlet("/bdresponse")
public class bdresponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String merchantID=null;
    String customerID=null;
    String txnRefNo=null;
    String bankRefNo=null;
    String txnAmt=null;
    String bankID=null;
    String bankMerchantID=null;
    String txnType=null;
    String currencyName=null;
    String itemCode=null;
    String securityType=null;
    String securityID=null;
    String securityPassword=null;
    String transactionDate=null;
    String authStatus=null;
    String settlementType=null;
    String addInfo1=null;
    String addInfo2=null;
    String addInfo3=null;
    String addInfo4=null;
    String addInfo5=null;
    String addInfo6=null;
    String addInfo7=null;
    String errorStatus=null;
    String errorDescription=null;
	
	String ChecksumKey ="a3VkNtgWZNwu";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bdresponse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String msg=request.getParameter("msg");
		StringBuffer sbmsg=new StringBuffer(msg);
		String checksumBD=msg.substring(msg.lastIndexOf("|")+1, msg.length());
		String originalMsg=msg.substring(0,msg.lastIndexOf("|"));
		//String checkSumDPL=HmacSHA256(originalMsg, ChecksumKey);
		String checkSumDPL=new ChecksumBillDesk().HmacSHA256(msg, ChecksumKey);
		
		System.out.println("CheckSum Bd-"+checksumBD);
		System.out.println("CheckSum DPL-"+checkSumDPL);
		System.out.println("Message part only-"+originalMsg);
		
		if(checksumBD.matches(checkSumDPL)){
			System.out.println("Checksum Matched, Authorised Transaction. Transaction Reports will be updated");
			
		}
		else{
			System.out.println("Checksum Mis Match, Transaction Declined. ");
		}
		extractString(originalMsg);
		
		
	}
	
	
	/*public String extractString(String msg){
	    
	    int i=0;
	    String var0=null;
	    String var1=null;
	    String var2=null;
	    String var3=null;
	    String var4=null;
	    
	    String[] value_split = msg.split("\\|");
	    for (String string : value_split) {

	        
	        System.out.println(string);

	    }
	    for(i=0;i<value_split.length;i++){
	        
	        if(i==0){
	            var0=value_split[i];
	        }
	        else if(i==1){
	            var1=value_split[i];
	        }
	        else if(i==2){
	            var2=value_split[i];
	        }
	        
	        else if(i==3){
	            var3=value_split[i];
	        }
	        else if(i==4){
	            var4=value_split[i];
	        }
	        
	    }
	    
	    
	    System.out.println("VAR 0="+var0);
	    System.out.println("VAR 1="+var1);
	    System.out.println("VAR 2="+var2);
	    System.out.println("VAR 3="+var3);
	    System.out.println("VAR 4="+var4);

	}*/
	
	/*public String HmacSHA256(String message,String secret)  {
		MessageDigest md = null;
			try {

				Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
				 SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
				 sha256_HMAC.init(secret_key);
				byte raw[] = sha256_HMAC.doFinal(message.getBytes());
				StringBuffer ls_sb=new StringBuffer();
				for(int i=0;i<raw.length;i++)
					ls_sb.append(char2hex(raw[i]));
					return ls_sb.toString(); //step 6
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
	public static String char2hex(byte x)
	{
	    char arr[]={
	                  '0','1','2','3',
	                  '4','5','6','7',
	                  '8','9','A','B',
	                  'C','D','E','F'
	                };

	    char c[] = {arr[(x & 0xF0)>>4],arr[x & 0x0F]};
	    return (new String(c));
	  }
	*/
	public void extractString(String msg){
	    
	    int i=0;
	    
	    
	    
	    
	    String[] value_split = msg.split("\\|");
	    for (String string : value_split) {

	        
	        System.out.println(string);

	    }
	    for(i=0;i<value_split.length;i++){
	        
	        if(i==0){
	            this.merchantID=value_split[i];
	        }
	        else if(i==1){
	        	 this.customerID=value_split[i];
	        }
	        else if(i==2){
	        	 this.txnRefNo=value_split[i];
	        }
	        
	        else if(i==3){
	        	 this.bankRefNo=value_split[i];
	        }
	        else if(i==4){
	        	 this.txnAmt=value_split[i];
	        }
	        else if(i==5){
	        	 this.bankID=value_split[i];
	        }
	        else if(i==6){
	            this.bankMerchantID=value_split[i];
	        }
	        else if(i==6){
	            this.txnType=value_split[i];
	        }
	        else if(i==7){
	            this.currencyName=value_split[i];
	        }
	        else if(i==8){
	            this.itemCode=value_split[i];
	        }
	        else if(i==9){
	            this.securityType=value_split[i];
	        }
	        else if(i==10){
	            this.securityID=value_split[i];
	        }
	        else if(i==11){
	            this.securityPassword=value_split[i];
	        }
	        else if(i==12){
	            this.transactionDate=value_split[i];
	        }
	        else if(i==13){
	            this.authStatus=value_split[i];
	        }
	        else if(i==14){
	            this.settlementType=value_split[i];
	        }
	        else if(i==15){
	            this.addInfo1=value_split[i];
	        }
	        else if(i==16){
	            this.addInfo2=value_split[i];
	        }
	        else if(i==17){
	            this.addInfo3=value_split[i];
	        }
	        else if(i==18){
	            this.addInfo4=value_split[i];
	        }
	        else if(i==19){
	            this.addInfo5=value_split[i];
	        }
	        else if(i==20){
	            this.addInfo6=value_split[i];
	        }
	        else if(i==21){
	            this.addInfo7=value_split[i];
	        }
	        else if(i==22){
	            this.errorStatus=value_split[i];
	        }
	        else if(i==23){
	            this.errorDescription=value_split[i];
	        }
	        
	    }
	    
	    
	    System.out.println("Merchant id "+merchantID);
	    System.out.println("Consumer id="+customerID);
	    System.out.println("TXN Ref No="+txnRefNo);
	    System.out.println("Bank Ref No="+bankRefNo);
	    System.out.println("TXN Amt="+txnAmt);
	    System.out.println("Bank ID="+bankID);
	    System.out.println("Ban Merchant ID="+bankMerchantID);
	    System.out.println("TXN Type="+txnType);
	    System.out.println("Currency Name="+currencyName);
	    System.out.println("Item code="+itemCode);
	    System.out.println("Security Type="+securityType);
	    System.out.println("Security ID="+securityID);
	    System.out.println("Security Password="+securityPassword);
	    System.out.println("Transaction date="+transactionDate);
	    System.out.println("Auth Status="+authStatus);
	    System.out.println("Settlement Type="+settlementType);
	    System.out.println("Add info 1 ="+addInfo1);
	    System.out.println("Add info 2 ="+addInfo2);
	    System.out.println("Add info 3 ="+addInfo3);
	    System.out.println("Add info 4 ="+addInfo4);
	    System.out.println("Add info 5 ="+addInfo5);
	    System.out.println("Add info 6 ="+addInfo6);
	    System.out.println("Add info 7 ="+addInfo7);
	    System.out.println("Error Status ="+errorStatus);
	    System.out.println("Description ="+errorDescription);
	    
	    
	    
	    

	}


}
