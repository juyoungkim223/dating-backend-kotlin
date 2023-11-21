package com.riti.data.util


import com.google.gson.*
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.riti.data.enums.ResultCode
import com.riti.data.exception.ApiRuntimeException
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * singleton
 */
object GsonUtil {
    private const val PATTERN_DATE = "yyyy-MM-dd"
    private const val PATTERN_TIME = "HH:mm:ss"
    private val PATTERN_DATETIME = String.format("%s %s", PATTERN_DATE, PATTERN_TIME)

    private val gson: Gson = GsonBuilder()
        .disableHtmlEscaping()
        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        .setDateFormat(PATTERN_DATETIME)
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
        .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
        .registerTypeAdapter(LocalTime::class.java, LocalTimeAdapter())
        .create()
    @JvmStatic
    fun toJson(o: Any?): String {
        val result: String = gson.toJson(o)
        return if("null" == result)
            throw ApiRuntimeException(ResultCode.JSON_PARSE_ERROR)
        else
            result
    }
    @JvmStatic
    fun <T> fromJson(s: String?, clazz: Class<T>?): T {
        try {
            return gson.fromJson(s, clazz)
        } catch (e: JsonSyntaxException) {
            throw ApiRuntimeException(ResultCode.JSON_PARSE_ERROR)
        }
    }

    internal class LocalDateTimeAdapter : TypeAdapter<LocalDateTime?>() {
        var format = DateTimeFormatter.ofPattern(PATTERN_DATETIME)
        @Throws(IOException::class)
        override fun write(out: JsonWriter, value: LocalDateTime?) {
            if (value != null) out.value(value.format(format))
        }

        @Throws(IOException::class)
        override fun read(`in`: JsonReader): LocalDateTime {
            return LocalDateTime.parse(`in`.nextString(), format)
        }
    }


    internal class LocalDateAdapter : TypeAdapter<LocalDate?>() {
        var format = DateTimeFormatter.ofPattern(PATTERN_DATE)
        @Throws(IOException::class)
        override fun write(out: JsonWriter, value: LocalDate?) {
            out.value(value!!.format(format))
        }


        @Throws(IOException::class)
        override fun read(`in`: JsonReader): LocalDate {
            return LocalDate.parse(`in`.nextString(), format)
        }
    }


    internal class LocalTimeAdapter : TypeAdapter<LocalTime?>() {
        var format = DateTimeFormatter.ofPattern(PATTERN_TIME)
        @Throws(IOException::class)
        override fun write(out: JsonWriter?, value: LocalTime?) {
            out!!.value(value!!.format(format))
        }

        @Throws(IOException::class)
        override fun read(`in`: JsonReader): LocalTime {
            return LocalTime.parse(`in`.nextString(), format)
        }
    }


}