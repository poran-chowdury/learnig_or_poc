package ts4u.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CustomIOUtils {

    public static String getOtp() throws IOException, InterruptedException {
        Path fileName = Path.of("src/test/resources/files/otp.txt");
        String otp = StringUtils.EMPTY;
        while (otp.isEmpty()) {
//        Scanner scanner = new Scanner(System.in);
//        this.otp = scanner.nextLine();
            System.out.println("Please input otp");
            Thread.sleep(10000);
            otp = Files.readString(fileName).split("\n")[0];
        }
        System.out.println("OTP Found : " + otp);
        return otp;
    }
}
