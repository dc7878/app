package com.cc.dc.kotlindemo.utils

import org.apache.commons.codec.binary.Hex
import java.io.UnsupportedEncodingException
import java.security.NoSuchAlgorithmException
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


/**
 * Created by dc on 18/6/26.
 */
object StringUtil {
    /**
     * 使用HmacSHA256消息摘要算法计算消息摘要
     *
     * @param paramString 做消息摘要的数据
     * @param secretKey   密钥
     * @return 消息摘要（长度为16的字节数组）
     */
    fun encodeHmacSHA256(paramString: String, secretKey: String): String {
        val secret_key = SecretKeySpec(secretKey.toByteArray(), "HmacSHA256")
        try {
            val mac = Mac.getInstance("HmacSHA256")
            mac.init(secret_key)
            val signData = mac.doFinal(paramString.toByteArray(charset("UTF-8")))
            //进行加密 并转换成16进制
            return String(Hex.encodeHex(signData))
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            return ""
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            return ""
        }

    }
}