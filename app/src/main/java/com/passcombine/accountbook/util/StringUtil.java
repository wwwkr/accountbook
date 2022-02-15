package com.passcombine.accountbook.util;

import android.text.TextUtils;
import android.util.Log;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class StringUtil {


    private static final String TAG = StringUtil.class.getSimpleName();

    public StringUtil() {
    }

    /**
     * 인수로 받은 문자열 배열을 delemeter로 구분한 문자열로 변환해서  리턴한다.<p>
     *
     * @param values    <code>대상 문자열 배열</code>
     * @param delemeter <code>delemeter 문자열</code>
     * @return <code>delemeter로 구분된 문자열</code>
     */
    public static String arrayToString(String values[], String delemeter) {
        return arrayToString(values, delemeter, "");
    }

    /**
     * 인수로 받은 ArrayList<String> 객체를 delemeter로 구분된 문자열로 변환한다.
     *
     * @param strList   <code>ArrayList<String> 객체</code>
     * @param delemeter <code>delemeter 문자열</code>
     * @return <code>delemeter로 구분된 문자열</code>
     */
    public static String arrayListToString(ArrayList<String> strList, String delemeter) {
        return arrayToString((String[]) strList.toArray(), delemeter, "");
    }

    /**
     * 인수로 받은 ArrayList<String> 객체를 delemeter로 구분된 문자열로 변환한다.
     *
     * @param strList   <code>ArrayList<String> 객체</code>
     * @param delemeter <code>delemeter 문자열</code>
     * @return <code>delemeter로 구분된 문자열</code>
     */
    public static String arrayListToString(ArrayList<String> strList, String delemeter, String quotation) {
        if (strList != null && !strList.isEmpty()) {
            String arrList[] = new String[strList.size()];

            for (int i = 0; i < strList.size(); i++)
                arrList[i] = strList.get(i);

            return arrayToString(arrList, delemeter, quotation);
        } else
            return null;
    }

    /**
     * 인수로 받은 문자열 배열을 인수로 받은 델리미터로 구분한 문자열로 변환해서  리턴한다.(따옴표를 붙여서)<p>
     *
     * @param values    <code>대상 문자열 배열</code>
     * @param delemeter <code>델리미터 문자열</code>
     * @param quotation <code>따옴표</code>
     * @return <code>변환된 문자열</code>
     */
    @SuppressWarnings("null")
    public static String arrayToString(String values[], String delemeter, String quotation) {
        StringBuffer returnValue = new StringBuffer();

        for (int i = 0; values != null && i < values.length; i++) {
            if (i == 0) {
                returnValue.append(quotation);
                returnValue.append(values[i]);
                returnValue.append(quotation);
            } else {
                returnValue.append(delemeter);
                returnValue.append(quotation);
                returnValue.append(values[i]);
                returnValue.append(quotation);
            }
        }

        return StringUtil.nvl(returnValue);
    }

    /**
     * 인수로 받은 델리미터로 구분된 문자열을 배열로 변환해서 리턴한다.<p>
     *
     * @param source    <code>대상 문자열</code>
     * @param delemeter <code>델리미터 문자열</code>
     * @return <code>변환된 문자열 배열</code>
     */
    public static String[] stringToArray(String source, String delemeter) {
        return stringToArray(source, delemeter, "");
    }

    /**
     * delemeter 문자로 연결된 문자열을 ArrayList로 변환한다.
     *
     * @param sourceStr <code>delemeter 문자로 연결된 문자열</code>
     * @param delimeter <code>delemeter 문자열</code>
     * @return <code>ArrayList<String> 객체</code>
     */
    public static ArrayList stringToArrayList(String sourceStr, String delimeter) {
        StringTokenizer tokenizer = new StringTokenizer(sourceStr, delimeter);

        ArrayList<String> tempList = new ArrayList<String>();

        while (tokenizer.hasMoreTokens()) {
            tempList.add(tokenizer.nextToken());
        }

        return tempList;
    }

    /**
     * 인수로 받은 델리미터로 구분된 문자열을 인수로 받은 EOF 문자열까지 배열로 변환해서 리턴한다.<p>
     *
     * @param sourceStr <code>대상 문자열</code>
     * @param delimeter <code>델리미터 문자열</code>
     * @param endString <code>소스문자열 내 EOF 문자열</code>
     * @return <code>변환된 문자열 배열</code>
     */
    public static String[] stringToArray(String sourceStr, String delimeter, String endString) {
        String source = sourceStr;
        String returnValue[] = null;

        if (source != null && !"".equals(source)) {
            if (delimeter != null && !"".equals(delimeter)) {
                if (endString != null && !"".equals(endString) && source.indexOf(endString) != -1)
                    source = source.substring(0, source.indexOf(endString));

                ArrayList tempList = stringToArrayList(source, delimeter);

                if (tempList != null && !tempList.isEmpty()) {
                    returnValue = new String[tempList.size()];

                    for (int i = 0; i < tempList.size(); i++)
                        returnValue[i] = (String) tempList.get(i);
                }
            }
        }

        return returnValue;
    }

    /**
     * String 문자열을 size 만큼 잘라서 문자열 배열로 변환한다.
     *
     * @param source <code>소스 문자열</code>
     * @param size   <code>잘라낼 사이즈</code>
     * @return <code>파싱된 문자열 배열</code>
     */
    public static String[] stringToArray(String source, int size) {
        return stringToArray(source, size, "");
    }

    /**
     * String 문자열을 지정된 문자까지 size 만큼 잘라서 문자열 배열로 변환한다.
     *
     * @param sourceStr <code>소스 문자열</code>
     * @param size      <code>잘라낼 사이즈</code>
     * @param endStr    <code>마지막 문자</code>
     * @return <code>파싱된 문자열 배열</code>
     */
    public static String[] stringToArray(String sourceStr, int size, String endStr) {
        String source = sourceStr;
        String returnValue[] = null;

        if (source != null && !"".equals(source)) {
            if (endStr != null && !"".equals(endStr) && source.indexOf(endStr) != -1)
                source = source.substring(0, source.indexOf(endStr));        // 종료 문자 검사 후 파싱 문자열 구함...

            int arrayLength = source.length() / size;

            if (source.length() % size > 0)
                arrayLength++;

            returnValue = new String[arrayLength];

            for (int i = 0; i < arrayLength; i++) {
                if (i == arrayLength - 1)
                    returnValue[i] = source.substring(i * size, (i + 1));
                else
                    returnValue[i] = source.substring(i * size, (i + 1) * size);
            }
        }

        return returnValue;
    }

    /**
     * 인수로 받은 문자열내에 인수로 받은 해당 문자열을, 인수로 받은 문자열 치환해서 리턴한다.<p>
     *
     * @param source    <code>대상 문자열</code>
     * @param targetStr <code>변경전 문자(열)</code>
     * @param changeStr <code>변경후 문자(열)</code>
     * @return <code>치환된 문자열</code>
     */
    public static String replaceString(String source, String targetStr, String changeStr) {
        return replaceString(source, new String[]{targetStr}, new String[]{changeStr});
    }

    /**
     * 인수로 받은 문자열내에 인수로 받은 해당 배열에 저장된 문자열을 인수로 받은 배열에 저장된 문자열로 순차적으로 치환해서 리턴한다.<p>
     *
     * @param source    <code>대상 문자열</code>
     * @param targetStr <code>변경전 문자(열)가 저장된 배열</code>
     * @param changeStr <code>변경후 문자(열)가 저장된 배열</code>
     * @return 치환된 문자열
     */
    public static String replaceString(String source, String targetStr[], String changeStr[]) {
        StringBuffer src = new StringBuffer(source);

        if (source != null && !"".equals(source)) {
            if (targetStr != null && changeStr != null && targetStr.length == changeStr.length) {
                for (int i = 0; i < targetStr.length; i++) {
                    int startPos = 0;

                    while (startPos != -1) {
                        startPos = src.indexOf(targetStr[i], startPos);

                        if (startPos != -1) {
                            src.delete(startPos, startPos + targetStr[i].length());
                            src.insert(startPos, changeStr[i]);

                            startPos = startPos + changeStr[i].length() + 1;
                        }
                    }
                }
            }
        }

        return src.toString();
    }


    /**
     * 배열에서 입력된 key 값과 일치하는 값의 index 를 구한다.
     *
     * @param inArray <code>문자열 배열</code>
     * @param key     <code>key 값</code>
     * @return <code>배열내 위치하는 index 값</code>
     */
    public static int indexOfArray(String[] inArray, String key) {
        int indexNum = -1;

        if (inArray != null && key != null) {
            for (int i = 0; i < inArray.length; i++) {
                if (inArray[i].equals(key)) {
                    indexNum = i;

                    break;
                }
            }
        }
        return indexNum;
    }

    /**
     * ArrayList<String>에서 입력된 key 값과 일치하는 값의 index 를 구한다.
     *
     * @param inList <code>ArrayList<String></code>
     * @param key    <code>key 값</code>
     * @return <code>ArrayList 내에 key 값과 일치하는 값   위치하는 index 값</code>
     */
    public static int indexOfArrayList(ArrayList<String> inList, String key) {
        int indexNum = -1;

        if (key != null && inList != null) {
            String tmpStr = "";

            for (int i = 0; i < inList.size(); i++) {
                tmpStr = (String) inList.get(i);

                if (tmpStr.equals(key)) {
                    indexNum = i;

                    break;
                }
            }
        }

        return indexNum;
    }

    /**
     * 원본 문자열에서 특정문자열이 포함된 갯수를 리턴한다.
     *
     * @param source <code>원본 문자열</code>
     * @param key    <code>검색할 문자열</code>
     * @return <code>포함된 갯수</code>
     */
    public static int getMatchCount(String source, String key) {
        int result = 0;

        int pos = 0;

        if ((pos = source.indexOf(key, pos + 1)) > -1)
            result++;

        return result;
    }

    /**
     * 원본이 Not Null 또는 공백이 아닐 경우 입력받은 문자로 치환
     *
     * @param source <code>원본 문자열</code>
     * @param value  <code>원본을 대체할 문자열</code>
     * @return <code>결과값</code>
     */
    public static String replaceNotNullString(String source, String value) {
        String result = "";

        if (source != null && !"".equals(source))
            result = value;

        return result;
    }

    /**
     * 원본이 Null 또는 공백일 경우 입력받은 문자로 치환<p>
     *
     * @param source <code>원본 문자열</code>
     * @param value  <code>원본을 대체할 문자열</code>
     * @return <code>결과값</code>
     */
    public static String replaceNullString(String source, String value) {
        String result = "";

        if (source == null || "".equals(source))
            result = value;

        return result;
    }

    /**
     * delemeter 가 포함된 전화번호를 국번,앞번호,뒷번호 형태로 분리해 배열로 변환 후 전달한다.<p>
     *
     * @param source    <code>delemeter 가 포함된 전화번호 문자열</code>
     * @param delemeter <code>delemeter 문자열</code>
     * @return <code>국번, 앞번호, 뒷번호 형태로 파싱된 문자열 배열</code>
     */
    public static String[] getPhoneType(String source, String delemeter) {
        String result[] = stringToArray(source, delemeter);

        if (result == null || result.equals(delemeter + delemeter))
            result = new String[]{"", "", ""};

        return result;
    }

    /**
     * 국번,앞번호,뒷번호 형태의 전화번호를 delemeter로 구분한 문자열로 변환한다.<p>
     *
     * @param source    <code>국번,앞번호,뒷번호 형태로 분리된 문자열 배열</code>
     * @param delemeter <code>delemeter 문자열</code>
     * @return <code>국번,앞번호,뒷번호 형태의 전화번호를 delemeter로 구분한 문자열</code>
     */
    public static String getPhoneDash(String source[], String delemeter) {
        String result = "";

        if (source != null && source.length == 3) {
            if (source[0] != null && !"".equals(source[0]) && source[1] != null && !"".equals(source[1]) && source[2] != null && !"".equals(source[2]))
                result = arrayToString(source, delemeter);
        }

        return result;
    }

    /**
     * '.xxx' 형태의 문자열을 0.xxx 의 형태의 소수형태의 문자열로 리턴한다.
     *
     * @param str
     * @return <code></code>
     */
    public static String getNumberString(String str) {
        String result = str;

        if (str != null && !"".equals(str)) {
            int pos = str.indexOf(".");

            if (pos == 0)
                result = "0" + str;
        }

        return result;
    }


    /**
     * 입력 받은 문자열이 null 인지 체크한다.
     *
     * @param str <code>문자열</code>
     * @return <code>null 여부</code>
     */
    public static boolean isNull(String str) {
        if (str == null || "".equals(str) || "null".equals(str))
            return true;
        else
            return false;
    }

    /**
     * 입력 받은 문자열이 not null 인지 체크한다.
     *
     * @param str <code>문자열</code>
     * @return <code>not null 여부</code>
     */
    public static boolean isNotNull(String str) {
        if (str != null && !"".equals(str))
            return true;
        else
            return false;
    }

    /**
     * 특정 문자열을 앞 부분에서 찾아 마지막 부분까지 자른다.
     *
     * @param inputStr <code>입력 문자열</code>
     * @param findStr  <code>비교 문자열</code>
     * @return <code>결과 값</code>
     */
    public static String substrFromFinal(String inputStr, String findStr) {
        if (isNotNull(inputStr)) {
            if (isNotNull(findStr))
                return inputStr.substring(inputStr.indexOf(findStr) + 1);

            else
                return inputStr;
        } else
            return "";
    }

    /**
     * 특정 문자열을 뒷 부분에서 찾아 마지막 부분까지 자른다.
     *
     * @param inputStr <code>입력 문자열</code>
     * @param findStr  <code>비교 문자열</code>
     * @return <code>결과 값</code>
     */
    public static String substrToFinal(String inputStr, String findStr) {
        if (isNotNull(inputStr)) {
            if (isNotNull(findStr))
                return inputStr.substring(inputStr.lastIndexOf(findStr) + 1);
            else
                return inputStr;
        } else
            return "";
    }

    /**
     * 특정 문자열을 앞 부분에서 찾아 시작부분부터 자른다.
     *
     * @param inputStr <code>입력 문자열</code>
     * @param findStr  <code>비교 문자열</code>
     * @return <code>결과 값</code>
     */
    public static String substrToStart(String inputStr, String findStr) {
        if (isNotNull(inputStr)) {
            if (isNotNull(findStr))
                return inputStr.substring(0, inputStr.indexOf(findStr));
            else
                return inputStr;
        } else
            return "";
    }

    /**
     * 특정 문자열을 뒷 부분에서 찾아 시작부분부터 자른다.
     *
     * @param inputStr <code>입력 문자열</code>
     * @param findStr  <code>비교 문자열</code>
     * @return <code>결과 값</code>
     */
    public static String substrFromStart(String inputStr, String findStr) {
        if (isNotNull(inputStr)) {
            if (isNotNull(findStr))
                return inputStr.substring(0, inputStr.lastIndexOf(findStr));
            else
                return inputStr;
        } else
            return "";
    }

    /**
     * 문자열 substring 처리한다. (문자열보다 클 경우 원본 문자열을 리턴한다.)
     *
     * @param source <code>원본소스</code>
     * @param spos   <code>시작위치</code>
     * @param epos   <code>마지막위치</code>
     * @return result    <code>결과</code>
     */
    public static String substring(String source, int spos, int epos) {
        String result = "";

        if (source != null && source.length() >= epos)
            result = source.substring(spos, epos);
        else
            result = source;

        return result;
    }

    /**
     * 문자열 substring 처리한다. (문자열보다 클 경우 원본 문자열을 리턴한다.)
     *
     * @param source <code>원본소스</code>
     * @param spos   <code>시작위치</code>
     * @return result    <code>결과</code>
     */
    public static String substring(String source, int spos) {
        String result = "";

        if (source != null && source.length() >= spos)
            result = source.substring(spos);
        else
            result = source;

        return result;
    }


    /**
     * 입력 받은 문자열이 null 값인 경우 공백으로 변환해 리턴한다.
     *
     * @param inputStr <code>입력 문자열</code>
     * @return <code>결과 값</code>
     */
    public static String replaceNullToStr(String inputStr) {
        String str = inputStr;
        if (isNull(str))
            str = "";
        else if ("null".equalsIgnoreCase(str))
            str = "";

        return str;
    }

    /**
     * 입력 받은 문자열이 null 값인 경우 대상문자로 변환해 리턴한다.
     *
     * @param inputStr   <code>입력 문자열</code>
     * @param replaceStr <code>null 일경우 대체할 문자열</code>
     * @return <code>결과 값</code>
     */
    public static String replaceNullToStr(String inputStr, String replaceStr) {
        String str = inputStr;
        if (isNull(str))
            str = replaceStr;
        else if ("null".equalsIgnoreCase(str))
            str = replaceStr;

        return str;
    }

    /**
     * 문자열을 int 형태 값으로 변환한다. (입력 받은 문자열이 null 값인 경우 0 (int 타입)값으로 변환)
     *
     * @param str <code>입력 문자열</code>
     * @return <code>결과 값</code>
     */
    public static int parseInt(String str) {
        int result = 0;

        try {
            result = Integer.parseInt(replaceNullToStr(str, "0"));
        } catch (Exception e) {
            result = 0;
        }

        return result;
    }

    /**
     * 문자열을 int 형태 값으로 변환한다. (입력 받은 문자열이 null 값인 경우 대체할 int 값으로 변환)
     *
     * @param str   <code>입력 문자열</code>
     * @param value <code>null 일경우 대체할 int 값</code>
     * @return <code>결과 값</code>
     */
    public static int parseInt(String str, int value) {
        int result = 0;

        try {
            result = Integer.parseInt(replaceNullToStr(str, StringUtil.nvl(value)));
        } catch (Exception e) {
            result = 0;
        }

        return result;
    }

    /**
     * 문자열을 long 형태 값으로 변환한다. (입력 받은 문자열이 null 값인 경우 0(long 타입)으로 변환)
     *
     * @param str <code>입력 문자열</code>
     * @return <code>결과 값</code>
     */
    public static long parseLong(String str) {
        long result = 0;

        try {
            result = Long.parseLong(replaceNullToStr(str, "0"));
        } catch (Exception e) {
            result = 0;
        }

        return result;
    }

    /**
     * 문자열을 long 형태 값으로 변환한다. (입력 받은 문자열이 null 값인 경우 대체할 long 값으로 변환)
     *
     * @param str   <code>입력 문자열</code>
     * @param value <code>null 일경우 대체할 long 값</code>
     * @return <code>결과 값</code>
     */
    public static long parseLong(String str, long value) {
        long result = 0;

        try {
            result = Long.parseLong(replaceNullToStr(str, StringUtil.nvl(value)));
        } catch (Exception e) {
            result = 0;
        }

        return result;
    }

    /**
     * 문자열을 boolean 값으로 변환 (null일 경우 false로 세팅)
     *
     * @param str <code>문자열</code>
     * @return <code>boolean 값</code>
     */
    public static boolean parseBoolean(String str) {
        if (isNull(str))
            return false;
        else if ("true".equalsIgnoreCase(str))
            return true;
        else
            return false;
    }

    /**
     * name=value 타입의 문자열이 delemeter로 연결된 문자열을 hashMap으로 변환한다.
     *
     * @param sourceStr <code>name=value 타입의 문자열이 delemeter로 연결된 문자열</code>
     * @param deleStr   <code>delemeter 문자열</code>
     * @return <code>변환된 HashMap 객체</code>
     */
    public static HashMap<String, String> queryToHashMap(String sourceStr, String deleStr) {
        StringTokenizer tokenizer = new StringTokenizer(sourceStr, deleStr);

        ArrayList<String> tempList = new ArrayList<String>();

        HashMap<String, String> tmpHash = new HashMap<String, String>();

        String tempStr = "";

        while (tokenizer.hasMoreTokens()) {
            tempList.add(tokenizer.nextToken());
        }

        for (int i = 0; i < tempList.size(); i++) {
            tempStr = tempList.get(i);

            int pos = tempStr.indexOf("=");

            if (pos > -1)
                tmpHash.put(tempStr.substring(0, pos), tempStr.substring(pos + 1));
            else
                tmpHash.put(tempStr, "");
        }

        return tmpHash;
    }

    /**
     * 입력 받은 문자열의 앞 부분 O을 제거
     *
     * @param str <code>입력받은 문자열</code>
     * @return <code>앞부분 부터 0이 제거된 문자열</code>
     */
    public static String clearFrontZero(String str) {
        String ret = "";

        for (int i = 0; i < str.length(); i++) {
            if (!"0".equals(str.substring(i, i + 1))) {
                ret = str.substring(i, str.length());

                break;
            }
        }

        return ret;
    }

    /**
     * 원본 문자열 좌측에 padding 문자열을 입력받은 수 만큼 붙인다.
     *
     * @param inputStr <code>원본 문자열</code>
     * @param padStr   <code>padding 문자열</code>
     * @param padCnt   <code>padding 할 회수</code>
     * @return <code>padding 된 문자열</code>
     */
    public static String paddingLeft(String inputStr, String padStr, int padCnt) {
        StringBuffer str = new StringBuffer();
        str.append(inputStr);
        for (int i = 0; i < padCnt; i++)
            str.append(padStr);
        str.append(str);

        return StringUtil.nvl(str);
    }

    /**
     * 원본 문자열 우측에 padding 문자열을 입력받은 수 만큼 붙인다.
     *
     * @param inputStr <code>원본 문자열</code>
     * @param padStr   <code>padding 문자열</code>
     * @param padCnt   <code>padding 할 회수</code>
     * @return <code>padding 된 문자열</code>
     */
    public static String paddingRight(String inputStr, String padStr, int padCnt) {
        StringBuffer str = new StringBuffer();
        str.append(inputStr);
        for (int i = 0; i < padCnt; i++)
            str.append(str);
        str.append(padStr);

        return StringUtil.nvl(str);
    }

    /**
     * 입력된 Long 값을 입력된 Decimal 포멧으로 변환한 문자열을 리턴한다.
     *
     * @param in         <code>입력된 Long 값</code>
     * @param strPattern <code>Decimal 포멧문자열</code>
     * @return <code>Decimal 포멧으로 변환된 문자열</code>
     */
    public static String formatNumber(long in, String strPattern) {
        DecimalFormat df = new DecimalFormat(strPattern);

        return df.format(in);
    }

    /**
     * 입력된 double 값을 입력된 Decimal 포멧으로 변환한 문자열을 리턴한다.
     *
     * @param in         <code>입력된 double 값</code>
     * @param strPattern <code>Decimal 포멧문자열</code>
     * @return <code>Decimal 포멧으로 변환된 문자열</code>
     */
    public static String formatNumber(double in, String strPattern) {
        DecimalFormat df = new DecimalFormat(strPattern);

        return df.format(in);
    }

    /**
     * 전달 받은 문자열이 UTF-8 형식인지 체크한다.
     *
     * @param str <code>원본 문자열</code>
     * @return <code>UTF-8 여부</code>
     * @throws Exception <code>Exception 객체</code>
     */
    public static boolean isUTF8(String str) throws Exception {
        byte[] bytes = str.getBytes("ISO-8859-1");

        return isUTF8(bytes, 0, bytes.length);
    }

    private static boolean isUTF8(byte[] buf, int offset, int length) {
        boolean yesItIs = false;

        for (int i = offset; i < offset + length; i++) {
            // 11xxxxxx 패턴 인지 체크

            if ((buf[i] & 0xC0) == 0xC0) {
                int nBytes;

                for (nBytes = 2; nBytes < 8; nBytes++) {
                    int mask = 1 << (7 - nBytes);

                    if ((buf[i] & mask) == 0)
                        break;
                }

                //CJK영역이나 아스키 영역의 경우 110xxxxx 10xxxxxx 패턴으로 올수 없다.

                if (nBytes == 2)
                    return false;

                // Check that the following bytes begin with 0b10xxxxxx

                for (int j = 1; j < nBytes; j++) {
                    if (i + j >= length || (buf[i + j] & 0xC0) != 0x80)
                        return false;
                }

                if (nBytes == 3) {
                    // 유니코드 형태로 역치환 해서 0x0800 ~ 0xFFFF 사이의 영역인지 체크한다.

                    char c = (char) (((buf[i] & 0x0f) << 12) + ((buf[i + 1] & 0x3F) << 6) + (buf[i + 2] & 0x3F));

                    if (!(c >= 0x0800 && c <= 0xFFFF))
                        return false;
                }

                yesItIs = true;
            }
        }

        return yesItIs;
    }

    /**
     * 입력받은 문자열의 케릭터 셋을 fromCharSet에서 toCharSet으로 변환한다.
     *
     * @param str         <code>원본 문자열</code>
     * @param fromCharSet <code>원본 캐릭터 셋</code>
     * @param toCharSet   <code>변환할 캐릭터 셋</code>
     * @return <code>변환된 문자열</code>
     */
    public static String transCharacterSet(String str, String fromCharSet, String toCharSet) {
        if (isNotNull(str)) {
            try {
                return new String(str.getBytes(fromCharSet), toCharSet);
            } catch (Exception e) {
                return str;
            }
        } else
            return str;
    }

    /**
     * 입력받은 숫자를 Mega 단위의 숫자로 변경한다.
     *
     * @param sizeStr <code>입력 수</code>
     * @param pos     <code>표시할 소수점 자리수</code>
     * @return <code>Mega 단위 값</code>
     */
    public static double getMegaValue(String sizeStr, int pos) {
        double result = 0;

        double megaValue = Double.parseDouble(sizeStr) / (1024 * 1024);

        BigDecimal bd = new BigDecimal(megaValue);

        result = bd.setScale(pos, BigDecimal.ROUND_DOWN).doubleValue();

        return result;
    }


    /**
     * 입력받은 숫자를 Kilo 단위의 숫자로 변경한다.
     *
     * @param sizeStr <code>입력 수</code>
     * @param pos     <code>표시할 소수점 자리수</code>
     * @return <code>Kilo 단위 값</code>
     */
    public static double getKiloValue(String sizeStr, int pos) {
        double result = 0;

        double megaValue = Double.parseDouble(sizeStr) / 1024;

        BigDecimal bd = new BigDecimal(megaValue);

        result = bd.setScale(pos, BigDecimal.ROUND_DOWN).doubleValue();

        return result;
    }

    /**
     * 입력받은 숫자를 Giga 단위의 숫자로 변경한다.
     *
     * @param sizeStr <code>입력 수</code>
     * @param pos     <code>표시할 소수점 자리수</code>
     * @return <code>Giga 단위 값</code>
     */
    public static double getGigaValue(String sizeStr, int pos) {
        double result = 0;

        double megaValue = Double.parseDouble(sizeStr) / 1024 / 1024 / 1024;

        BigDecimal bd = new BigDecimal(megaValue);

        result = bd.setScale(pos, BigDecimal.ROUND_DOWN).doubleValue();

        return result;
    }

    /**
     * 입력받은 숫자를 사이즈에 맞춰서 B, K, M, G를 붙여 반환한다.
     *
     * @param sizeStr <code>입력 수</code>
     * @param pos     <code>표시할 소수점 자리수</code>
     * @return <code>각 단위 값</code>
     */
    public static String getByteValue(String sizeStr, int pos) {
        if (sizeStr.length() <= 3) {
            return sizeStr + "B";
        } else if (sizeStr.length() <= 6) {
            return getKiloValue(sizeStr, pos) + "K";
        } else if (sizeStr.length() <= 9) {
            return getMegaValue(sizeStr, pos) + "M";
        } else if (sizeStr.length() <= 12) {
            return getGigaValue(sizeStr, pos) + "G";
        } else {
            return sizeStr + "B";
        }
    }

    public static String nvl(Object str) {
        return StringUtil.nvl(str, "");
    }

    public static String nvl(String str) {
        return StringUtil.nvl(str, "");
    }

    public static String nvl(String str, String defaultValue) {
        if (TextUtils.isEmpty(str)) {
            return defaultValue;
        }
        return str;
    }

    public static String nvl(Object str, String defaultValue) {
        if (str == null) {
            return defaultValue;
        }

        return str.toString();
    }

    public static int getInt(String str) {
        int result = 0;
        try {
            result = Integer.parseInt(StringUtil.nvl(str, "0"));        // 보고서 양식 코드
        } catch (Exception e) {
            result = 0;
        }

        return result;
    }


    /**
     * 파일 단위 변환
     *
     * @param size
     * @return
     */
    public static String byteTranslater(long size) {
        NumberFormat nf = NumberFormat.getIntegerInstance();
        DecimalFormat df = new DecimalFormat("#,##0.00");
        int intSize = 0;
        int kbyteSize = 1024;
        double doubleSize = 0;
        String returnSize = null;

        // 파일 사이즈가 1000, 2000 형식이므로 기가는 1024 가 아닌 1000을 기준으로.
        if (size >= (1000 * 1024 * 1024)) {
            intSize = new Long(size / (1000 * 1024 * 1024)).intValue();
            return nf.format(intSize) + " GB";

        } else if (size > (kbyteSize * 1024)) {
            intSize = (int) (((double) size) / ((double) (kbyteSize * 1024)) * 100);
            doubleSize = (double) (((double) intSize) / 100);
            returnSize = df.format(doubleSize);
            if (returnSize.lastIndexOf(".") != -1) {
                if ("00".equals(returnSize.substring(returnSize.length() - 2,
                        returnSize.length()))) {
                    returnSize = returnSize.substring(0, returnSize.lastIndexOf("."));
                }
            }
            return returnSize + " MB";

        } else if (size > kbyteSize) {
            intSize = new Long(size / kbyteSize).intValue();
            return nf.format(intSize) + " KB";

        } else {
            // return nf.format(size) + "Byte";
            return "1 KB";
        }
    }

    /**
     * 현재 시간 정보를 가져오기 위한 메소드 (yyyyMMdd 형식)
     *
     * @return
     */
    public static String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String date = format.format(new Date());
        return date;
    }

    public static String getMon_day() {
        SimpleDateFormat format = new SimpleDateFormat("MM월dd일");
        String date = format.format(new Date());

        return date;
    }
    /**
     * 요일
     *
     * @return
     */
    public static int getDay() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 현재 날짜 정보를 가져오기 위한 메소드 (HH:mm:ss 형식)
     *
     * @return
     */
    public static String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String time = format.format(new Date());
        return time;
    }



    /**
     * n일 전 날짜 정보를 가져오기 위한 메소드 (yyyyMMdd 형식)
     *
     * @return
     */
    public static String getDatetoDay(int day) {
        Calendar dayAgo = Calendar.getInstance();
        dayAgo.add(Calendar.DATE, day);
        String date = new SimpleDateFormat("yyyyMMdd").format(dayAgo.getTime());
        return date;
    }

    /**
     * n월 전 날짜 정보를 가져오기 위한 메소드 (yyyyMMdd 형식)
     *
     * @return
     */
    public static String getDatetoMonth(int month) {
        Calendar monthAgo = Calendar.getInstance();
//		monthAgo.add(Calendar.MONTH , -1); 한달전
        monthAgo.add(Calendar.MONTH, month);
        String date = new SimpleDateFormat("yyyyMMdd").format(monthAgo.getTime());
        return date;
    }

    /**
     * 한달후 날짜 정보를 가져오기 위한 메소드 (yyyyMMdd 형식)
     *
     * @return
     */
//	public static String getDateOneMonthLater(){
//		Calendar monthLater = Calendar.getInstance();
//		monthLater.add(Calendar.MONTH , 1);
//		String date = new SimpleDateFormat("yyyyMMdd").format(monthLater.getTime());
//		return date;
//	}
    public static String getYear() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String year = format.format(new Date());
        return year;
    }

    public static String getMonth() {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        String month = format.format(new Date());
        return month;
    }

    public static String getYear(String yyyyMMdd) {
        return yyyyMMdd.substring(0, 4);
    }

    public static String getMonth(String yyyyMMdd) {
        return yyyyMMdd.substring(4, 6);
    }

    /**
     * 화폐단위 원일때, 천단위 콤마
     *
     * @param str
     * @return
     */
    public static String formatNumberComma(String str) {
        int num = Integer.parseInt(str);
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(num);
    }

    public static String formatNumberComma(int num) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(num);
    }

    public static String formatNumberCommaWon(int num) {
        DecimalFormat df = new DecimalFormat("#,###원");
        return df.format(num);
    }
//    public static String formatNumberComma_point(String str) {
//        if(str == null || "null".equals(str) )
//        {
//            str = "0";
//        }
//        int num = Integer.parseInt(str);
//        DecimalFormat df = new DecimalFormat("#,###");
//        return MyApplication.getAppContext().getString(R.string.st_convert_point, df.format(num));
//    }
//    public static String formatNumberComma_won(String str) {
//        if(str == null || "null".equals(str) )
//        {
//            str = "0";
//        }
//        int num = Integer.parseInt(str);
//        DecimalFormat df = new DecimalFormat("#,###");
//        return MyApplication.getAppContext().getString(R.string.st_convert_won, df.format(num));
//    }

    /**
     * 날짜형식으로 표시
     *
     * @param yyyyMMdd
     * @param delimiter
     * @return
     */
    public static String formatDate(String yyyyMMdd, String delimiter) {
        if (yyyyMMdd.length() != 8) {
            return yyyyMMdd;
        } else {
            return yyyyMMdd.substring(0, 4)
                    + delimiter + yyyyMMdd.substring(4, 6)
                    + delimiter + yyyyMMdd.substring(6);
        }
    }

    public static String formatTime(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        } else {
            return time.substring(0, 2)
                    + ":" + time.substring(2, 4)
                    + ":" + time.substring(4, 6);
        }
    }

    /**
     * 날짜리턴
     *
     * @param yyyyMMdd
     * @return 리턴값 9/2
     */
    public static String getFormatDate(String yyyyMMdd) {
        if (yyyyMMdd.length() != 8) {
            return yyyyMMdd;
        } else {
            return Integer.parseInt(yyyyMMdd.substring(4, 6))
                    + "/" +
                    +Integer.parseInt(yyyyMMdd.substring(6));
        }
    }

    /**
     * 요일리턴
     *
     * @param dayCode
     * @return 리턴값 토요일
     */
    public static String getFormatDay(String dayCode) {
        String str;
        if ("430100".equals(dayCode)) {
            str = "월요일";
        } else if ("430200".equals(dayCode)) {
            str = "화요일";
        } else if ("430300".equals(dayCode)) {
            str = "수요일";
        } else if ("430400".equals(dayCode)) {
            str = "목요일";
        } else if ("430500".equals(dayCode)) {
            str = "금요일";
        } else if ("430600".equals(dayCode)) {
            str = "토요일";
        } else if ("430700".equals(dayCode)) {
            str = "일요일";
        } else {
            str = "";
        }

        return str;
    }

    /**
     * LunchPass 이미지URL 변환
     * "http://130.211.248.32" + url
     *
     * @param url
     * @return
     */
//    public static String transfImgUrl(String url) {
//        String result = url;
//        if (!"http".contains(url)) {
////            result = CommonNetwork.URL_IMG + url;
//            result = CommonNetwork.URL_MAIN + url;
//        }
//
//        return result;
//    }


    /**
     * 이메일 유효성 체크
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (email == null) return false;
        boolean b = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", email.trim());
        return b;
    }

    /**
     * 월 또는 일을 두자리로 만들기 위함
     *
     * @param i
     * @return
     */
    public static String formatNumberDate(int i) {
        if (i < 10) {
            return "0" + i;
        } else {
            return "" + i;
        }
    }

    /**
     * 서버버전과 앱버전 비교
     * 서버버전이 클 경우 true 리턴
     *
     * @param serverVer
     * @param appVer
     * @return
     */
    public static boolean isBigServerVersion(String serverVer, String appVer) {
        int iServer = Integer.parseInt(serverVer.replace(".", ""));
        int iApp = Integer.parseInt(appVer.replace(".", ""));
        boolean result = iServer > iApp ? true : false;
        return result;
    }

    public static boolean isBig(String a, String b) {
        int ia = Integer.parseInt(a);
        int ib = Integer.parseInt(b);
        if (ia > ib) {
            return true;
        } else {
            return false;
        }
    }

    public static String generateNumber() {
        String barcode = "";

        for (int i = 0; i < 13; i++) {
            int random = (int) (Math.random() * 10);
            barcode = barcode + random;

        }
        return barcode;
    }

    public static String generateMenuCount(String count) {
        int i = count.length();
        if (i == 1) {
            count = "00" + count;
        } else if (i == 2) {
            count = "0" + count;
        }

        return count;
    }

    // 카테고리
    public static String getCategoryTxt(int categoryCode) {
        String str;
        if (categoryCode == 80200) {
            // 중식
            str = "중식";
        } else if (categoryCode == 80300) {
            // 일식
            str = "일식";
        } else if (categoryCode == 80400) {
            // 양식
            str = "양식";
        } else if (categoryCode == 80500) {
            // 분식
            str = "분식";
        } else if (categoryCode == 80600) {
            // 피자
            str = "피자";
        } else if (categoryCode == 80700) {
            // 햄버거
            str = "햄버거";
        } else if (categoryCode == 80800) {
            // 치킨
            str = "치킨";
        } else if (categoryCode == 81000) {
            // 도시락
            str = "도시락";
        } else if (categoryCode == 81100) {
            // 빵
            str = "베이커리";
        } else if (categoryCode == 81200) {
            // 기타
            str = "기타";
        } else if (categoryCode == 81300) {
            // 편의점
            str = "편의점";
        } else if (categoryCode == 81400) {
            // 구내식당
            str = "구내식당";
        } else if (categoryCode == 81500) {
            // 카페
            str = "카페";
        } else if (categoryCode == 81900) {
            // 출석체크
            str = "출석체크";
        } else {
            // 한식
            str = "한식";
        }
        return str;
    }



    /**
     * 특정 날짜에 대하여 요일을 구함(일 ~ 토)
     *
     * @param date
     * @param dateType
     * @return
     * @throws Exception
     */
    public static String getDateDay(String date, String dateType) throws Exception {


        String day = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat(dateType);
        Date nDate = dateFormat.parse(date);

        Calendar cal = Calendar.getInstance();
        cal.setTime(nDate);

        int dayNum = cal.get(Calendar.DAY_OF_WEEK);


        switch (dayNum) {
            case 1:
                day = "일";
                break;
            case 2:
                day = "월";
                break;
            case 3:
                day = "화";
                break;
            case 4:
                day = "수";
                break;
            case 5:
                day = "목";
                break;
            case 6:
                day = "금";
                break;
            case 7:
                day = "토";
                break;

        }


        return day;
    }

    public static String changeDateCommma() {
        String result = "";
        return result;
    }


    public static String addDateComma(String date) {
        String result = date;
        try {
            SimpleDateFormat simpleDate_before = new SimpleDateFormat(
                    "yyyyMMdd");
            SimpleDateFormat simpleDate_after = new SimpleDateFormat(
                    "yyyy.MM.dd");

            Date currentDate = simpleDate_before.parse(date);

            result = simpleDate_after.format(currentDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = date;
        }

        return result;
    }

    public static String addDatebar(String date) {
        String result = date;
        try {
            SimpleDateFormat simpleDate_before = new SimpleDateFormat(
                    "yyyyMMdd");
            SimpleDateFormat simpleDate_after = new SimpleDateFormat(
                    "yyyy-MM-dd");

            Date currentDate = simpleDate_before.parse(date);

            result = simpleDate_after.format(currentDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = date;
        }

        return result;
    }

    public static String removeDateComma(String date) {
        String result = date;
        try {
            SimpleDateFormat simpleDate_before = new SimpleDateFormat(
                    "yyyy.MM.dd");
            SimpleDateFormat simpleDate_after = new SimpleDateFormat(
                    "yyyyMMdd");

            Date currentDate = simpleDate_before.parse(date);

            result = simpleDate_after.format(currentDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = date;
        }

        return result;
    }


    /**
     * yyyy-MM-dd HH:mm:ss  -> yyyy.MM.dd
     *
     * @param "yyyy-MM-dd HH:mm:ss"
     * @return "yyyy.MM.dd"
     */
    public static String changeDateSimpleCommma(String date) {
        String result = date;
        try {
            SimpleDateFormat simpleDate_before = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat simpleDate_after = new SimpleDateFormat(
                    "yyyy.MM.dd");

            Date currentDate = simpleDate_before.parse(date);

            result = simpleDate_after.format(currentDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = date;
        }

        return result;
    }

    public static String getOrderFinishDate()
    {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        return formatter.format(c.getTime());
    }

    // 현재 날짜
    public static String getCurday() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(c.getTime());
    }

    // 현재 날짜 월요일
    public static String getCurMonday() {
        Calendar c = Calendar.getInstance();
//        c.add(Calendar.DATE, -3); // 특정일
        // 일요일 처리
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            c.add(c.DATE, -7);
        }
        c.set(c.DAY_OF_WEEK, c.MONDAY);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(c.getTime());
    }

    // 현재 날짜 일요일
    public static String getCurSunday() {
        Calendar c = Calendar.getInstance();
//        c.add(Calendar.DATE, -3); // 특정일
        // 일요일 처리
        if (c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            c.add(c.DATE, 7);
        }
        c.set(c.DAY_OF_WEEK, c.SUNDAY);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(c.getTime());
    }

    // 현재 날짜 월요일
    public static String getCurWeekDate(int day) {
        Calendar c = Calendar.getInstance();
        c.set(c.DAY_OF_WEEK, day);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(c.getTime());
    }

    public static ArrayList<String> getCurWeek()
    {

        ArrayList<String> dayList = new ArrayList<String>();

        for(int i=2; i<8; i++) {
            dayList.add(getCurWeekDate(i));
        }
        dayList.add(getCurSunday());


        return dayList;

    }


    public static String getCustomDate(String yyyymmdd, String TYPE, int value) {

        int year  = Integer.parseInt(yyyymmdd.substring(0, 4));

        int month = Integer.parseInt(yyyymmdd.substring(4, 6));

        int date  = Integer.parseInt(yyyymmdd.substring(6, 8));


        Calendar cal = Calendar.getInstance();

        cal.set(year, month - 1, date);


        if("YEAR".equals(TYPE))
        {
            cal.add(Calendar.YEAR, -value);     // 1년 전
        }else if("MONTH".equals(TYPE))
        {
            cal.add(Calendar.MONTH, -value);    // 한달 전
        }else if("DAY".equals(TYPE))
        {
            cal.add(Calendar.DATE, -value);     // 하루 전
        }

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");

        String r = dateFormatter.format(cal.getTime());



        return r;

    }


    public static ArrayList<String> getCurWeekDate(Calendar calendar) {
        ArrayList<String> dayList = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = calendar;
        Calendar rcal = Calendar.getInstance();

        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        int w = calendar.get(Calendar.WEEK_OF_MONTH);
        for(int day=2; day<8; day++) {
            c.set(Calendar.YEAR, y);
            c.set(Calendar.MONTH, m);
            c.set(Calendar.WEEK_OF_MONTH, w);
            c.set(Calendar.DAY_OF_WEEK, day);
            String date = sdf.format(c.getTime());
            dayList.add(date);
        }

        rcal.set(Calendar.MONTH, m);
        rcal.set(Calendar.WEEK_OF_MONTH, w);
        rcal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        rcal.add(c.DATE, 7);
        String sunday = sdf.format(rcal.getTime());
        dayList.add(sunday);

        return dayList;
    }

    public static String getWeek(Calendar cal){


       String day_of_week_in_month =  String.valueOf(cal.get(Calendar.DAY_OF_WEEK_IN_MONTH));

       Log.e(TAG, "day_of_week_in_month : " + day_of_week_in_month);
        String week = String.valueOf(cal.get(Calendar.WEEK_OF_MONTH));


        return week;

    }


}