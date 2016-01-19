package com.lopezjuri.uc_accesss

import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.deferred

/**
 * Created by patriciolopez on 19-01-16.
 */
public fun Request.promise(): Promise<Pair<Response, ByteArray>, Exception> {
    val deferred = deferred<Pair<Response, ByteArray>, Exception>()
    response { request, response, result ->
        when (result) {
            is Result.Failure -> deferred.reject(result.error!!)
            is Result.Success -> deferred.resolve(Pair(response, result.value!!))
        }
    }
    return deferred.promise
}