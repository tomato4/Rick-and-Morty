package cz.ackee.testtask.rm.repository.list.data.repository

import cz.ackee.testtask.rm.app.Variables
import cz.ackee.testtask.rm.repository.common.data.model.CharacterDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersListApi {
    @GET(Variables.RM_API_LIST)
    fun getCharacters(@Query("page") page: Int): Call<List<CharacterDto>>
}