package cz.ackee.testtask.rm.repository.common.data.repository

import cz.ackee.testtask.rm.app.Variables
import cz.ackee.testtask.rm.repository.common.data.model.ApiCharacterResponseDto
import cz.ackee.testtask.rm.repository.common.data.model.CharacterDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersListApi {
    @GET(Variables.RM_API_LIST)
    fun getCharacters(
        @Query("page") page: Int? = null,
        @Query("name") name: String? = null
    ): Call<ApiCharacterResponseDto>

    @GET(Variables.RM_API_SINGLE_CHARACTER)
    fun getCharacter(
        @Path("id") id: Int
    ): Call<CharacterDto>

    @GET(Variables.RM_API_MULTI_CHARACTERS)
    fun getSelectedCharacters(
        @Path("ids") ids: List<Int>
    ): Call<List<CharacterDto>>
}