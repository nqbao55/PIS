package bao.nguyen.PIS.common

import bao.nguyen.PIS.entity.PisBakery
import bao.nguyen.PIS.entity.PisCake
import bao.nguyen.PIS.repository.PisBakeryRepository
import bao.nguyen.PIS.repository.PisCakeRepository
import org.springframework.beans.factory.annotation.Autowired
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

abstract class BaseService {

    @Autowired
    lateinit var cakeRepository: PisCakeRepository

    @Autowired
    lateinit var bakeryRepository: PisBakeryRepository


    fun getNow(): String {
        val date = Date()
        return date.toString()
    }

    fun getNow(format: String): String {
        val df = getSimpleDateFormat(format)
        return df.format(Date())
    }

    fun strToTime(hour: String, min: String): Date {
        val df = getSimpleDateFormat("HH:mm")
        var d = df.parse(hour + ":" + min)
        return d
    }

    fun getSimpleDateFormat(format: String): SimpleDateFormat {
        val df = SimpleDateFormat(format, Locale.JAPAN)
        df.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
        return df
    }
    fun localDateTimeToDate(localDateTime: LocalDateTime): Date {
        val zone = ZoneId.systemDefault()
        val zonedDateTime = ZonedDateTime.of(localDateTime, zone).withSecond(0)
        val instant = zonedDateTime.toInstant()
        return Date.from(instant)
    }

    fun localDateTimeToString(localDateTime: LocalDateTime, format: String): String {
        val dateTimeFormatter = DateTimeFormatter.ofPattern(format)
        return localDateTime.format(dateTimeFormatter)
    }

    fun localDateToDate(localDate: LocalDate): Date {
        val zone = ZoneId.systemDefault()
        return Date.from(localDate.atStartOfDay(zone).toInstant())
    }

    fun localDateTimeToDateWithSecondZero(localDateTime: LocalDateTime): Date {
        val zone = ZoneId.systemDefault()
        val zonedDateTime = ZonedDateTime.of(localDateTime, zone).withSecond(0)
        val instant = zonedDateTime.toInstant()
        return Date.from(instant)
    }

    fun dateToLocalDateTime(date: Date?): LocalDateTime? {
        if (date != null) {
            val zone = ZoneId.systemDefault()
            val instant = date.toInstant()
            return LocalDateTime.ofInstant(instant, zone)
        } else {
            return null
        }
    }

    fun nowYMD(): Date {
        val nowLd = LocalDate.now()
        return localDateToDate(nowLd)
    }

    fun stringToDate(strDate: String, pattern: String = "yyyy-MM-dd HH:mm:ss"): Date? {
        val sdFormat = try {
            SimpleDateFormat(pattern)
        } catch (e: IllegalArgumentException) {
            null
        }
        val date = sdFormat?.let {
            try {
                it.parse(strDate)
            } catch (e: ParseException){
                null
            }
        }
        return date
    }

    fun stringToLocalDateTime(strDate: String, pattern: String = "yyyy-MM-dd HH:mm:ss"): LocalDateTime? {
        val formatter = try {
            DateTimeFormatter.ofPattern(pattern)
        } catch (e: IllegalArgumentException) {
            return null
        }
        return try {
            LocalDateTime.parse(strDate, formatter)
        } catch (e: DateTimeParseException) {
            null
        }
    }

    fun getListCake():List<PisCake>{
        return cakeRepository.findAll()
    }

    fun getListBakery():List<PisBakery>{
        return bakeryRepository.findAll()
    }
}