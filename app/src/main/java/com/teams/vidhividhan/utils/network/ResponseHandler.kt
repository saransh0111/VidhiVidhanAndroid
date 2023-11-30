package com.codenicely.bebroker.utils.network


import android.util.Log
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException


open class ResponseHandler {


    fun <T : Any> handleSuccess(data: T?): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        Log.e("TAG", "handleException: " + e.stackTrace.toString())
        e.printStackTrace()
//        Log.d("TAG", "handleException: " + e.printStackTrace())
        Log.e("TAG", "handleException: " + e.cause.toString())
        Log.e("TAG", "handleException: " + e.message.toString())



        return when (e) {

            is HttpException -> {

//                var errorMessage =
//                if(e.code()!=400 && e.code()!=403){
//
//                    getErrorMessage(e.code(),e)
//
//                } else{


                val body = e.response()?.errorBody()
                var errorMessage = getErrorJSON(body)


                //Resource.error(getErrorMessage(e.code(), e), null)}
                Resource.error(errorMessage, null)
            }
            else -> Resource.error(getErrorMessage(Int.MAX_VALUE, e), null)
        }
    }

    private fun getErrorJSON(body: ResponseBody?): String {
        if (body != null) {
            return try {
                val mainObject = JSONObject(body.string())
                if (mainObject.has("detail"))
                    mainObject.getString("detail").toString()
                else mainObject.getString(("message")).toString()

            } catch (e: Exception) {
                Log.d("checkErrorMessage4", e.message + "");
                "Something wrong happened"
            }
        } else {
            return "Something wrong happened !!!"
        }

    }


    private fun getErrorMessage(code: Int, e: Exception): String {
        return when (code) {
            // SocketTimeOut.code -> "Timeout"
            401 -> "Unauthorised"
            404 -> "Not found"
            403 -> "No Access"
            400 -> e.message.toString()
            429 -> "Try after sometime"
            500 -> "Internal Server Error"
            else -> "Something went wrong"
        }
    }
}