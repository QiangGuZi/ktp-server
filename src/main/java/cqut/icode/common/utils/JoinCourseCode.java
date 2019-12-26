package cqut.icode.common.utils;

import java.util.List;
import java.util.Random;

/**
 * @author tq
 */
public class JoinCourseCode {

    private static String generate() {
        Random rd = new Random();
        StringBuilder code = new StringBuilder();
        String preCodeString = "ABCDEFGHIJKMNLOPKRSTUVWXYZ1234567890";

        for (int k = 0; k < 6; k++) {
            // 随机获取数组长度作为索引
            int index = rd.nextInt(preCodeString.length());
            // 循环添加到字符串后面
            code.append(preCodeString.charAt(index));
        }

        return code.toString();
    }

    public  static String generateNotRepeat(List<String> existingCodes) {
        String result = generate();
        while (existingCodes.contains(result)) {
            result = generate();
        }

        return result;
    }
}
