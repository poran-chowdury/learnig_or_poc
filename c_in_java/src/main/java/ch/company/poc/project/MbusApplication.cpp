#include "ch_company_poc_project_MbusApplication.h"

#include <iostream>
#include <err.h>
#include <stdio.h>
#include <string.h>
using namespace std;

#ifdef __cplusplus
extern "C" {
#include "mbus/mbus.h"
#endif

/*
 * Class:     ch_company_poc_project_MbusApplication
 * Method:    decodeHexValue
 * Signature: ()Ljava/lang/String;
 */

/*
* Custom Methods Declarations
*/

string getDecodedHexValue(string hexDataForDecode);
string jstringToString(JNIEnv *env, jstring jStr);


/*
* Main Method
*/

JNIEXPORT jstring JNICALL Java_ch_company_poc_project_MbusApplication_decodeHexValue (JNIEnv *env, jobject thisObject, jstring hexDataForDecode) {
	string convertedStr = jstringToString(env, hexDataForDecode);
	string result = "";
//	result = getDecodedHexValue(convertedStr);
	result += "\nCPP File => Tested Done : " + convertedStr;
	char someInput[100];
	cin >> someInput;
	printf("Input taken in CPP File => %s\n", someInput);
	printf("I am printing from CPP file\n");
    return env->NewStringUTF(result.c_str());
}

/*
* Custom Method Definitions
*/
string getDecodedHexValue(string hexDataForDecode) {

    size_t buff_len;
    int result;
    unsigned char raw_buff[4096], buff[4096];
    mbus_frame reply;
    mbus_frame_data frame_data;

    memset(raw_buff, 0, sizeof(raw_buff));
    strcpy((char*) raw_buff, hexDataForDecode.c_str());

    buff_len = mbus_hex2bin(buff, sizeof(buff), raw_buff, sizeof(raw_buff));

    memset(&reply, 0, sizeof(reply));
    memset(&frame_data, 0, sizeof(frame_data));

    result = mbus_parse(&reply, buff, buff_len);

    if (result < 0)
    {
        printf("mbus_parse: %s\n", mbus_error_str());
        return "1";
    }
    else if (result > 0)
    {
        printf("mbus_parse: need %d more bytes\n", result);
        return "1";
    }

    result = mbus_frame_data_parse(&reply, &frame_data);

    if (result != 0)
    {
        mbus_frame_print(&reply);
        printf("mbus_frame_data_parse: %s\n", mbus_error_str());
        return "1";
    }

    return hexDataForDecode + " Found This Data";
}

string jstringToString(JNIEnv *env, jstring jStr) {
    if (!jStr)
        return "";

    const jclass stringClass = env->GetObjectClass(jStr);
    const jmethodID getBytes = env->GetMethodID(stringClass, "getBytes", "(Ljava/lang/String;)[B");
    const jbyteArray stringJbytes = (jbyteArray) env->CallObjectMethod(jStr, getBytes, env->NewStringUTF("UTF-8"));

    size_t length = (size_t) env->GetArrayLength(stringJbytes);
    jbyte* pBytes = env->GetByteArrayElements(stringJbytes, NULL);

    string result = string((char *)pBytes, length);
    env->ReleaseByteArrayElements(stringJbytes, pBytes, JNI_ABORT);

    env->DeleteLocalRef(stringJbytes);
    env->DeleteLocalRef(stringClass);
    return result;
}

#ifdef __cplusplus
} //pay attention to this
#endif