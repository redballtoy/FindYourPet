package com.example.redballtoy.findyourpet.core.data.api.interceptors

import com.example.redballtoy.findyourpet.core.data.api.ApiConstants
import com.example.redballtoy.findyourpet.core.data.api.ApiConstants.AUTH_ENDPOINT
import com.example.redballtoy.findyourpet.core.data.api.ApiParameters.AUTH_HEADER
import com.example.redballtoy.findyourpet.core.data.api.ApiParameters.CLIENT_ID
import com.example.redballtoy.findyourpet.core.data.api.ApiParameters.CLIENT_SECRET
import com.example.redballtoy.findyourpet.core.data.api.ApiParameters.GRANT_TYPE_KEY
import com.example.redballtoy.findyourpet.core.data.api.ApiParameters.GRANT_TYPE_VALUE
import com.example.redballtoy.findyourpet.core.data.api.ApiParameters.TOKEN_TYPE
import com.example.redballtoy.findyourpet.core.data.api.model.ApiToken
import com.example.redballtoy.findyourpet.core.data.preferences.Preferences
import com.squareup.moshi.Moshi
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.time.Instant
import javax.inject.Inject


class AuthenticationInterceptor @Inject constructor(
    private val preferences: Preferences
) : Interceptor {

    companion object {
        const val UNAUTHORIZED = 401
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val token = preferences.getToken()//1
        val tokenExpirationTime = Instant.ofEpochSecond(preferences.getTokenExoirationTime())

        // For requests that don't need authentication
        //if(chain.request().headers[NO_AUTH_HEADER] != null) return chain.proceed(request)
        val request = chain.request()//3

        val interceptedRequest: Request//4

        if (tokenExpirationTime.isAfter(Instant.now())) {
            //token is still valid, so we can proceed with the request
            interceptedRequest = chain.createAuthenticatedRequest(token)//5
        } else {
            //token expired. Gotta refresh it before proceeding with the actual request
            val tokenRefreshResponse = chain.refreshToken()

            interceptedRequest = if (tokenRefreshResponse.isSuccessful) {
                val newToken = mapToken(tokenRefreshResponse)

                if (newToken.isValid()) {
                    storeNewToken(newToken)
                    chain.createAuthenticatedRequest(newToken.accessToken!!)
                } else {
                    request
                }
            } else {
                request
            }
        }

        return chain.proceedDeletingTokenIfUnauthorized(interceptedRequest)

    }

    private fun storeNewToken(apiToken: ApiToken){
        with(preferences){
            putTokenType(apiToken.tokenType!!)
            putTokenExpirationTime(apiToken.expiresAt)
            putToken(apiToken.accessToken!!)
        }

    }

    private fun mapToken(tokenRefreshResponse: Response): ApiToken {
        val moshi = Moshi.Builder().build()
        val tokenAdapter = moshi.adapter(ApiToken::class.java)
        val responseBody = tokenRefreshResponse.body!!

        return tokenAdapter.fromJson(responseBody.string()) ?: ApiToken.INVALID
    }

    private fun Interceptor.Chain.refreshToken(): Response {
        val url = request()
            .url
            .newBuilder(AUTH_ENDPOINT)!!
            .build()

        val body = FormBody.Builder()
            .add(GRANT_TYPE_KEY, GRANT_TYPE_VALUE)
            .add(CLIENT_ID, ApiConstants.KEY)
            .add(CLIENT_SECRET, ApiConstants.SECRET)
            .build()

        val tokenRefresh = request()
            .newBuilder()
            .post(body)
            .url(url)
            .build()

        return proceedDeletingTokenIfUnauthorized(tokenRefresh)
    }

    private fun Interceptor.Chain.createAuthenticatedRequest(token: String): Request {
        return request()
            .newBuilder()
            .addHeader(AUTH_HEADER, TOKEN_TYPE + token)
            .build()
    }

    private fun Interceptor.Chain.proceedDeletingTokenIfUnauthorized(request: Request)
            : Response {
        val response = proceed(request)
        if (response.code == UNAUTHORIZED) {
            preferences.deleteTokenInfo()
        }
        return response
    }
}
