package com.app.jokeappv2.data.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Muthu Narayanan on 19/11/2021.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class JokesResponseBody(

    @JsonProperty("error")
    val error: Boolean?,

    @JsonProperty("amount")
    val amount: Int?,

    @JsonProperty("jokes")
    val jokes: List<Jokes>? = null

)
