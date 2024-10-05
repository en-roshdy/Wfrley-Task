package com.example.wafarlytask.utils
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateTimeHelper {



    fun getDateFromDateTime(dateTimeString : String): String{
        val dateTime = LocalDateTime.parse(dateTimeString)
        return  DateTimeFormatter.ofPattern("yyyy-MM-dd").format(dateTime)
    }

    fun getTimeFromDateTime(dateTimeString : String): String{
        val dateTime = LocalDateTime.parse(dateTimeString)
        return  DateTimeFormatter.ofPattern("h:mm a").format(dateTime)
    }


    private fun getDateInAr(dateTimeString: String) :String{
        val dateTime = LocalDateTime.parse(dateTimeString)
        val format = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ar")).format(dateTime)
        return format
    }


    private fun getDateTimeAr(dateTimeString : String): String{
        val dateTime = LocalDateTime.parse(dateTimeString)


        val timeFormatter = DateTimeFormatter.ofPattern("h:mm a",Locale("ar"))
        val formattedTime = dateTime.format(timeFormatter)

        return formattedTime
    }

    fun getDataTimeText(dateTimeString : String) : String{
        return  "${getDateInAr(dateTimeString )}\n${getDateTimeAr(dateTimeString)}"
    }


    fun getCurrentDateTime(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .withZone(ZoneId.of("Africa/Cairo"))
        return formatter.format(Instant.now())
    }
}