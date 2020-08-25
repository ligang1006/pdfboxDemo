import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import sun.jvm.hotspot.debugger.proc.arm.ProcARMThread;

import java.io.File;

import static utils.Constant.PATH;

/**
 * @description: 加密
 * @author: lee
 * @create: 2020-08-25 09:18
 **/
public class EncriptPDF {
    public static void main(String args[]) throws Exception {
        //Loading an existing document
        File file = new File(PATH + "addPage.pdf");
        PDDocument document = PDDocument.load(file);

        //Creating access permission object
        AccessPermission ap = new AccessPermission();

        //Creating StandardProtectionPolicy object
        StandardProtectionPolicy spp = new StandardProtectionPolicy("123456", "123456", ap);

        //Setting the length of the encryption key
        spp.setEncryptionKeyLength(128);

        //Setting the access permissions
        spp.setPermissions(ap);

        //Protecting the document
        document.protect(spp);

        System.out.println("Document encrypted");

        //Saving the document
        document.save(PATH + "sample-encript.pdf");
        //Closing the document
        document.close();

    }

}
